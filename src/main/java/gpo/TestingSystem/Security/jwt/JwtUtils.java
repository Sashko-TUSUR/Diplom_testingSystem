package gpo.TestingSystem.Security.jwt;


import gpo.TestingSystem.Exception.TokenRefreshException;
import gpo.TestingSystem.Models.RefreshToken;
import gpo.TestingSystem.Repositories.RefreshRepository;
import gpo.TestingSystem.Repositories.UserRepository;
import gpo.TestingSystem.Security.Auth.UserDetailsImpl;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;

@Component
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${testingSystem.app.jwtSecret}")
    private String jwtSecret;

    @Value("${testingSystem.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    @Value("${todolist.app.jwtRefreshSecret}")
    private String jwtSecretRefresh;

    @Value("${todolist.app.jwtRefreshExpirationMs}")
    private Long jwtRefreshExpirationMs;

    @Value("${testingSystem.app.jwtCookieName}")
    private String jwtCookie;

    @Autowired
    private RefreshRepository refreshTokenRepository;
    @Autowired
    private UserRepository userRepository;

    //////РЕФРЕШ ТОКЕН ///////////////////// ///////////////////// ///////////////////// ///////////////////// /////////////////////



    public String createRefreshToken(String email) {

        return Jwts.builder()
                .setSubject((email))
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS512, jwtSecretRefresh)
                .setExpiration(new Date((new Date()).getTime() + jwtRefreshExpirationMs))
                .compact();

    }
    public RefreshToken saveRefreshToken(String login, Long id)
    {
        if(refreshTokenRepository.findUser(id)==null)
        {
            RefreshToken refreshToken = new RefreshToken();
            refreshToken.setToken(createRefreshToken(login));
            refreshToken.setExpiryDate(Instant.now().plusMillis(jwtRefreshExpirationMs));
            refreshToken.setUser(userRepository.findByLogin(login));
            refreshTokenRepository.save(refreshToken);
            return refreshToken;
        }
        else {
            RefreshToken refreshToken1 = refreshTokenRepository.findToken(id);
            refreshToken1.setToken(createRefreshToken(login));
            refreshToken1.setExpiryDate(Instant.now().plusMillis(jwtRefreshExpirationMs));
            refreshToken1.setUser(userRepository.findByLogin(login));
            refreshTokenRepository.save(refreshToken1);
            return refreshToken1;
        }
    }


    public Optional<RefreshToken> findByToken(String token) {

        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new TokenRefreshException(token.getToken(), "Refresh token was expired. Please make a new signin request");
        }
        return token;
    }

    @Transactional
    public int deleteByUserId(Long userId) {
        return refreshTokenRepository.deleteByUser(userRepository.findById(userId).get());
    }

    public String getEmailFromRefreshToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecretRefresh).parseClaimsJws(token).getBody().getSubject();
    }


    /////////////////////АКСЕСС ТОКЕН ///////////////////// ///////////////////// ///////////////////// ///////////////////// /////////////////////
    public String generateJwtToken(UserDetailsImpl userPrincipal) {
        return generateJwtTokenFromLogin(userPrincipal.getId());
    }

    public String generateJwtTokenFromLogin(Long id) {

        return Jwts.builder()
                .setSubject((id.toString()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }
    public String getEmailFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }

    ///////////////////// КУКИ ///////////////////// ///////////////////// ///////////////////// ///////////////////// /////////////////////

    public ResponseCookie generateJwtCookie(RefreshToken refreshToken) {
        ResponseCookie cookie = ResponseCookie.from(jwtCookie, refreshToken.getToken()).path("/").maxAge(24*60*60*15).sameSite("None").secure(true).httpOnly(true).build();
        return cookie;
    }


    public String getJwtFromCookies(HttpServletRequest request) {

        Cookie cookie = WebUtils.getCookie(request, jwtCookie);
        if (cookie != null) {
            return cookie.getValue();
        } else {
            return null;
        }

    }
    public ResponseCookie getCleanJwtCookie() {
        ResponseCookie cookie = ResponseCookie.from(jwtCookie, null).path("/").build();
        return cookie;
    }


}
