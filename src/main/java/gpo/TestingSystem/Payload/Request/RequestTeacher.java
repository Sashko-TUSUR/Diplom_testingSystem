package gpo.TestingSystem.Payload.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestTeacher {

    Long idUser;
    Long idGroup;
    Long idSubject;
    String name;
    String surname;
    String patronymic;

}
