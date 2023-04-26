package gpo.TestingSystem.Payload.Request.QuestionRequest;

import lombok.Data;

@Data
public class QuestionMultiple {

    Integer countAnswers;
    String[] answers;
    Integer  truAnswer;


}
