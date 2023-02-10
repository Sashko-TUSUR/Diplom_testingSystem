package gpo.TestingSystem.Payload.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestTeacher {

    Long idGroup;
    String name;
    String surname;
    String patronymic;
    String subject;
    String numGroup;
}
