package gpo.TestingSystem.Controller;


import gpo.TestingSystem.Exception.TokenRefreshException;
import gpo.TestingSystem.Models.RefreshToken;
import gpo.TestingSystem.Models.User;
import gpo.TestingSystem.Payload.Request.LoginRequest;
import gpo.TestingSystem.Payload.Request.SignUpRequest;
import gpo.TestingSystem.Payload.Response.JwtResponse;
import gpo.TestingSystem.Payload.Response.ResponseMessage;
import gpo.TestingSystem.Payload.Response.TokenRefreshResponse;
import gpo.TestingSystem.Repositories.RoleRepository;
import gpo.TestingSystem.Repositories.UserRepository;
import gpo.TestingSystem.Security.Auth.UserDetailsImpl;
import gpo.TestingSystem.Security.jwt.JwtUtils;
import gpo.TestingSystem.Service.UserServiceAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/auth")
public class Auth {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    UserServiceAdmin userServiceAdmin;

    @Autowired
    JwtUtils jwtUtils;


    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getLogin(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        String jwt = jwtUtils.generateJwtTokenFromLogin(userDetails.getId());
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        RefreshToken refreshToken = jwtUtils.saveRefreshToken(userDetails.getLogin(),userDetails.getId());
        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(refreshToken);


        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, String.valueOf(jwtCookie))
                .body(new JwtResponse(jwt,
                        userRepository.findById(userDetails.getId())));
    }



    @PostMapping("refresh")
    public ResponseEntity<?> refreshToken(HttpServletRequest request)
    {
        Cookie cookie = WebUtils.getCookie(request, "refresh");
        String requestRefreshToken = cookie.getValue();
        String Token = jwtUtils.getEmailFromRefreshToken(requestRefreshToken);
        return jwtUtils.findByToken(requestRefreshToken)
                .map(jwtUtils::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String token = jwtUtils.generateJwtTokenFromLogin(Long.parseLong(Token));
                    User user1 = userRepository.findByLogin(Token);
                    return ResponseEntity.ok(new TokenRefreshResponse(token, userRepository.findById(user1.getUserId())));
                })
                .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
                        "Refresh token is not in database!"));
    }

    @PostMapping("/signup")
    public ResponseEntity createUser(@RequestBody SignUpRequest signUpRequest) {

        System.out.println(signUpRequest.getLogin());
        userServiceAdmin.createUser(signUpRequest);

        return ResponseEntity.ok().body(new ResponseMessage(true,"Вы успешно зарегистрированы, далее, произведите авторизацию"));
    }
/*
    @PostMapping("logout")
    public ResponseEntity<?> logoutUser() {
        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).build();
    }
*/

}
