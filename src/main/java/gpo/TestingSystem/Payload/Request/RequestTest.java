package gpo.TestingSystem.Payload.Request;


import lombok.Data;

@Data
public class RequestTest {

    Long id;
    Long idTopic;
    String name;
    Long idType;
    Long[] questionList;
    Long startTest;
    Long endTest;
    Long duration;
    Integer countTry;

}
