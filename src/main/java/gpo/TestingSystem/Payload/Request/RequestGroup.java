package gpo.TestingSystem.Payload.Request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestGroup {

    Long idGroup;
    Long idSubject;
    String numGroup;
}
