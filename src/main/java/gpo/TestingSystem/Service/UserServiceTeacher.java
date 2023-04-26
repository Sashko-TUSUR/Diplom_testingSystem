package gpo.TestingSystem.Service;

import gpo.TestingSystem.Models.*;
import gpo.TestingSystem.Payload.Request.QuestionRequest.QuestionMain;
import gpo.TestingSystem.Payload.Request.RequestDidacticUnit;
import gpo.TestingSystem.Payload.Request.RequestTopic;
import gpo.TestingSystem.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserServiceTeacher {

    @Autowired
    GroupsRepository groupsRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    SubjectRepository subjectRepository;
    @Autowired
    DidacticUnitRepository didacticUnitRepository;
    @Autowired
    TopicRepository topicRepository;
    @Autowired
    TypeQuestionRepo typeQuestionRepo;
    @Autowired
    ComplexityRepository complexityRepository;
    @Autowired
    AnswersRepo answersRepo;


    //создание дидактической еденицы
    public void addDidacticUnit(RequestDidacticUnit requestAddDidacticUnit) {
        Subject subject = subjectRepository.findById(requestAddDidacticUnit.getIdSubject()).get();

        DidacticUnit didacticUnit = new DidacticUnit();
        didacticUnit.setName(requestAddDidacticUnit.getName());
        didacticUnit.setSubjectId(subject);
        didacticUnitRepository.save(didacticUnit);
    }

    //редактирование еденицы
    public void editDidactic(RequestDidacticUnit requestDidacticUnit) {
        DidacticUnit didacticUnit = didacticUnitRepository.findById(requestDidacticUnit.getIdDidactic()).get();
        didacticUnit.setName(requestDidacticUnit.getName());
        didacticUnitRepository.save(didacticUnit);

    }

    //создание темы
    public void addTopic(RequestTopic requestTopic) {
        Topic topic = new Topic();
        topic.setName(requestTopic.getName());
        DidacticUnit didacticUnit = didacticUnitRepository.findById(requestTopic.getIdDidactic()).get();
        topic.setDidacticUnit(didacticUnit);
        topicRepository.save(topic);
    }

    //edit topic
    public void editTopic(RequestTopic requestTopic) {
        Topic topic = topicRepository.findById(requestTopic.getIdTopic()).get();
        topic.setName(requestTopic.getName());
        topicRepository.save(topic);
    }

    // создание вопроса
    public void createQuestion(QuestionMain questionMainRequest) {

//        System.out.println(questionMainRequest.getQuestionMultiple().getCountAnswers());
//        System.out.println(questionMainRequest.getTitle());
//        System.out.println(questionMainRequest.getQuestionMultiple().getTruAnswer());
//        for(int i=0;i<2;i++)
//        System.out.println(questionMainRequest.getQuestionMultiple().getAnswers()[i]);

        Question question = new Question();
        TypeQuestion typeQuestion = typeQuestionRepo.findById(questionMainRequest.getIdTypeQuestion()).get();
        Complexity complexity = complexityRepository.findById(questionMainRequest.getIdComplexity()).get();

        question.setTypeQuestion(typeQuestion);
        question.setTitle(questionMainRequest.getTitle());
        question.setContent(questionMainRequest.getContent());

        question.setComplexities(complexity);

        if (typeQuestion.getId() == 1) {

            Answers answer = new Answers();
            answer.setTrueAnswer(questionMainRequest.getQuestionTruFalse().getValue());
            answer.setQuestion(question);
            answersRepo.save(answer);
        }


        if (typeQuestion.getId() == 2) {

            for (int i = 0; i < questionMainRequest.getQuestionMultiple().getCountAnswers(); i++) {

                Answers answers = new Answers();
                answers.setTitle(questionMainRequest.getQuestionMultiple().getAnswers()[i]);
                if(i==questionMainRequest.getQuestionMultiple().getTruAnswer()-1)
                {
                    answers.setTrueAnswer(true);
                }
                else
                {
                    answers.setTrueAnswer(false);
                }
                    answers.setQuestion(question);
                    answersRepo.save(answers);
            }

        }

        questionRepository.save(question);

    }



}
