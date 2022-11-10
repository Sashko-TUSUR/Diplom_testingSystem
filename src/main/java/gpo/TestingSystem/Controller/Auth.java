package gpo.TestingSystem.Controller;


import gpo.TestingSystem.Payload.Request.LoginRequest;
import gpo.TestingSystem.Payload.Response.JwtResponse;
import gpo.TestingSystem.Repositories.RoleRepository;
import gpo.TestingSystem.Repositories.UserRepository;
import gpo.TestingSystem.Security.UserDetailsImpl;
import gpo.TestingSystem.Security.jwt.jwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
    jwtUtils jwtUtils;


    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getLogin(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userRepository.findById(userDetails.getId())));
    }

    /*
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
                    String token = jwtUtils.generateJwtTokenFromEmail(Token);
                    User user1 = userRepository.findByEmail(Token).get();
                    return ResponseEntity.ok(new TokenRefreshResponse(token, userRepository.findById(user1.getId())));
                })
                .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
                        "Refresh token is not in database!"));
    }
*/
/*
    @PostMapping("signup")
    public ResponseEntity createUser(@RequestBody SignUpRequest signUpRequest) {


        userService.createUser(signUpRequest);

        return ResponseEntity.ok().body(new ApiResponse(true,"Вы успешно зарегистрированы, далее, произведите авторизацию"));
    }

    @PostMapping("logout")
    public ResponseEntity<?> logoutUser() {
        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).build();
    }

*/
}
