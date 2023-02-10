package gpo.TestingSystem.Payload.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.JoinTable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {

    String login;
    String password;

}
