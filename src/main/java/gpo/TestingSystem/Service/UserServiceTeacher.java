package gpo.TestingSystem.Service;

import gpo.TestingSystem.Models.*;
import gpo.TestingSystem.Payload.Request.QuestionRequest.QuestionMain;
import gpo.TestingSystem.Payload.Request.RequestDidacticUnit;
import gpo.TestingSystem.Payload.Request.RequestTest;
import gpo.TestingSystem.Payload.Request.RequestTopic;
import gpo.TestingSystem.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;

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
    @Autowired
    IntegralRepository integralRepository;
    @Autowired
    TypeTestRepository typeTestRepository;
    @Autowired
    TestRepository testRepository;
    @Autowired
    TypeIntegralRepository typeIntegralRepository;


    //создание дидактической еденицы
    public void addDidacticUnit(RequestDidacticUnit requestAddDidacticUnit) {
        Subject subject = subjectRepository.findById(requestAddDidacticUnit.getIdSubject()).get();

        DidacticUnit didacticUnit = new DidacticUnit();
        didacticUnit.setName(requestAddDidacticUnit.getName());
        didacticUnit.setSubject(subject);
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

        Question question = new Question();
        TypeQuestion typeQuestion = typeQuestionRepo.findById(questionMainRequest.getIdTypeQuestion()).get();
        Complexity complexity = complexityRepository.findById(questionMainRequest.getIdComplexity()).get();
        Topic topic = topicRepository.findById(questionMainRequest.getIdTopic()).get();

        question.setTopic(topic);
        question.setTypeQuestion(typeQuestion);
        question.setTitle(questionMainRequest.getTitle());
        question.setContent(questionMainRequest.getContent());

        question.setComplexities(complexity);

        //ПРОПИСАТЬ УСЛОВИЯ ЕСЛИ БУДУТ ПУСТЫЕ ПРИХОДИТЬ


        //TRU/FALSE
        if (typeQuestion.getId() == 1) {

            Answers answer = new Answers();
            answer.setTrueAnswer(questionMainRequest.getQuestionTruFalse().getValue());
            answer.setQuestion(question);
            answersRepo.save(answer);
        }

        //MULTIPLE
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

        //INTEGRAL
         if(typeQuestion.getId()==3)
            {
                System.out.println("hi");
            Integral integral = new Integral();
            integral.setQuestion(question);
            TypeIntegral typeIntegral = typeIntegralRepository.findById(questionMainRequest.getQuestionIntegral().getTypeId()).get();
            integral.setTypeIntegral(typeIntegral);
            integralRepository.save(integral);
            }


        questionRepository.save(question);

    }

    //редактирование вопроса
    public void editQuestion(QuestionMain questionMainRequest)
    {
        Question question = questionRepository.findById(questionMainRequest.getId()).get();

        if(questionMainRequest.getTitle()!=null)
        {
            question.setTitle(questionMainRequest.getTitle());
        }
        if(questionMainRequest.getContent()!=null)
        {
            question.setContent(questionMainRequest.getContent());
        }
        if(questionMainRequest.getIdComplexity()!=null)
        {
            Complexity complexity = complexityRepository.findById(questionMainRequest.getIdComplexity()).get();
            question.setComplexities(complexity);
        }
        if(questionMainRequest.getQuestionTruFalse().getValue()!=null)
        {
            Answers answers = answersRepo.findByQuestion(question.getQuestionId());
            answers.setTrueAnswer(questionMainRequest.getQuestionTruFalse().getValue());
            answersRepo.save(answers);
        }
        if(questionMainRequest.getQuestionMultiple()!=null)
        {

        }

    questionRepository.save(question);

    }



    //создание теста
    public void createTest(RequestTest requestTest)
    {

        Test test = new Test();
        Topic topic = topicRepository.findById(requestTest.getIdTopic()).get();
        TypeTest typeTest = typeTestRepository.findById(requestTest.getIdType()).get();
        test.setName(requestTest.getName());
        test.setTopic(topic);
        test.setTypeTest(typeTest);

        if(requestTest.getStartTest()!=null)
        test.setStartTest(requestTest.getStartTest());

        if(requestTest.getEndTest()!=null)
        test.setEndTest(requestTest.getEndTest());

        if(requestTest.getDuration()!=null)
        test.setDuration(requestTest.getDuration());

        if(requestTest.getCountTry()!=null)
        test.setCountTry(requestTest.getCountTry());

        if(requestTest.getQuestionList() != null )
        {

            for(int i=0;i<requestTest.getQuestionList().length;i++)
            {
                Question question = questionRepository.findById(requestTest.getQuestionList()[i]).get();
                question.getTests().add(test);
                questionRepository.save(question);
            }

        }

        testRepository.save(test);

    }

    //редактирование теста
    public void editTest(RequestTest requestTest)
    {
        Test test = testRepository.findById(requestTest.getId()).get();

        if(requestTest.getIdTopic()!=null)
        {
            Topic topic = topicRepository.findById(requestTest.getIdTopic()).get();
            test.setTopic(topic);
        }
        if(requestTest.getName()!=null)
        {
            test.setName(requestTest.getName());
        }
        if(requestTest.getIdType()!=null)
        {
            TypeTest typeTest = typeTestRepository.findById(requestTest.getIdType()).get();
            test.setTypeTest(typeTest);
        }

        if(requestTest.getStartTest()!=null)
            test.setStartTest(requestTest.getStartTest());

        if(requestTest.getEndTest()!=null)
            test.setEndTest(requestTest.getEndTest());

        if(requestTest.getDuration()!=null)
            test.setDuration(requestTest.getDuration());

        if(requestTest.getCountTry()!=null)
            test.setCountTry(requestTest.getCountTry());

        if(requestTest.getQuestionList() != null )
        {
            for(int i=0;i<requestTest.getQuestionList().length;i++)
            {
                Question question = questionRepository.findById(requestTest.getQuestionList()[i]).get();
                test.getQuestions().add(question);
            }

        }

        testRepository.save(test);

    }

}
