package gpo.TestingSystem.Payload.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestAddGroup {

    Long id;
    Long idGroup;
}