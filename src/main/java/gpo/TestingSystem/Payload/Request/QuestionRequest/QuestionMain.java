package gpo.TestingSystem.Payload.Request.QuestionRequest;

import lombok.Data;


@Data
public class QuestionMain {

    Long id;
    Long idTopic;
    Long idComplexity;
    Long idTypeQuestion;
    String title;
    String content;
    QuestionTruFalse questionTruFalse;
    QuestionMultiple questionMultiple;
    QuestionIntegral questionIntegral;
}
