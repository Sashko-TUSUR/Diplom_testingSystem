package gpo.TestingSystem.Payload.Response;



import gpo.TestingSystem.Models.User;
import lombok.Data;

import java.util.Optional;

@Data
public class TokenRefreshResponse {

    private String accessToken;
    private Optional<User> user;

    public TokenRefreshResponse(String accessToken, Optional<User> user) {
        this.accessToken = accessToken;
        this.user = user;
    }


}