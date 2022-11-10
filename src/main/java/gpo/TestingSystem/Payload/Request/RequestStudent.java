package gpo.TestingSystem.Payload.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestStudent {

    Long idUser;
    String name;
    String surname;
    String patronymic;
    String numGroup;
}
