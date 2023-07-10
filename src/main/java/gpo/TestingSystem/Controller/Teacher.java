package gpo.TestingSystem.Controller;


import gpo.TestingSystem.Integrals.Indefinite.indefiniteIntegral;
import gpo.TestingSystem.Models.*;
import gpo.TestingSystem.Payload.Request.QuestionRequest.QuestionMain;
import gpo.TestingSystem.Payload.Request.RequestDidacticUnit;
import gpo.TestingSystem.Payload.Request.RequestSubject;
import gpo.TestingSystem.Payload.Request.RequestTest;
import gpo.TestingSystem.Payload.Request.RequestTopic;
import gpo.TestingSystem.Payload.Response.ResponseMessage;
import gpo.TestingSystem.Repositories.*;
import gpo.TestingSystem.Security.Auth.UserDetailsImpl;
import gpo.TestingSystem.Service.UserServiceTeacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/teacher")
public class Teacher {


    @Autowired
    DidacticUnitRepository didacticUnitRepository;

    @Autowired
    TopicRepository topicRepository;

    @Autowired
    UserServiceTeacher userServiceTeacher;
    @Autowired
    SubjectRepository subjectRepository;
    @Autowired
    indefiniteIntegral indefiniteIntegral;
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    TestRepository testRepository;
    @Autowired
    AnswersRepo answersRepo;

    //Создание дидактической еденицы
    @PostMapping("/createDidactic")
    public ResponseEntity<?> createDidactic(@RequestBody RequestDidacticUnit requestAddDidacticUnit) {
        try {
            userServiceTeacher.addDidacticUnit(requestAddDidacticUnit);
            return ResponseEntity.ok(new ResponseMessage(true, "Дидактическая еденица добавлена"));
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseMessage(false, "Что-то пошло не так!"));
        }
    }

    //удаление дидактической еденицы
    @DeleteMapping("/delDidactic")
    public ResponseEntity<?> delDidactic(@RequestBody RequestDidacticUnit requestDidacticUnit) {
        didacticUnitRepository.deleteById(requestDidacticUnit.getIdDidactic());
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    //edit didactic
    @PutMapping("/editDidactic")
    public ResponseEntity<?> editDidactic(@RequestBody RequestDidacticUnit requestDidacticUnit)
    {
        userServiceTeacher.editDidactic(requestDidacticUnit);
        return ResponseEntity.ok(new ResponseMessage(true, "Дидактическая еденица исправлена"));
    }

    //создание темы
    @PostMapping("/createTopic")
    public ResponseEntity<?> addTopic(@RequestBody RequestTopic requestTopic) {
        try {
            userServiceTeacher.addTopic(requestTopic);
            return ResponseEntity.ok(new ResponseMessage(true, "Тема добавлена"));
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseMessage(false, "Что-то пошло не так!"));
        }
    }

    //удаление темы
    @DeleteMapping("/delTopic")
    public ResponseEntity<?> delTopic(@RequestBody RequestTopic requestTopic)
    {
        topicRepository.deleteById(requestTopic.getIdTopic());
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    //редактирование темы
    @PutMapping("/editTopic")
    public ResponseEntity<?> editTopic(@RequestBody RequestTopic requestTopic)
    {
        userServiceTeacher.editTopic(requestTopic);
        return ResponseEntity.ok(new ResponseMessage(true, "Тема обновлена"));
    }

    //дидактические еденицы препода
    @PostMapping("/didactic")
    public List<DidacticUnit> didacticUser(@RequestBody RequestSubject requestSubject)
    {
       return didacticUnitRepository.findByDidactic(requestSubject.getId());
    }

//    // темы препода
//    @GetMapping("/topic")
//    public List<Topic> topicsUser(@RequestBody RequestTopic requestTopic)
//    {
//        return topicRepository.findByTopic(requestTopic.getIdDidactic());
//    }

    //дисциплины препода
    @GetMapping("/subject")
    public List<Subject> subjectUser(@AuthenticationPrincipal UserDetailsImpl userDetails)
    {
        return subjectRepository.findBySubjectTeacher(userDetails.getId());
    }

    @GetMapping("/test")
    public Object integral()
    {
        System.out.println("я пришёл");
        return gpo.TestingSystem.Integrals.Indefinite.indefiniteIntegral.iTask();

    }

    //создание вопроса
   @PostMapping("/createQuestion")
   public ResponseEntity<?> createQuestion(@RequestBody QuestionMain questionMain)
   {
       userServiceTeacher.createQuestion(questionMain);
       return ResponseEntity.ok(new ResponseMessage(true, "Вопрос добавлен"));
   }

   // удаление вопроса
    @DeleteMapping("/delQuestion")
    public ResponseEntity<?> delQuestion(@RequestBody QuestionMain questionMain)
    {

        questionRepository.deleteById(questionMain.getId());
        return ResponseEntity.ok(new ResponseMessage(true, "Вопрос удалён"));
    }

    // создание теста
    @PostMapping("/createTest")
    public ResponseEntity<?> createTest(@RequestBody RequestTest requestTest)
    {
        userServiceTeacher.createTest(requestTest);
        return ResponseEntity.ok(new ResponseMessage(true, "тест создан"));
    }
    // редактирование теста
    @PutMapping("/editTest")
    public ResponseEntity<?> editTest(@RequestBody RequestTest requestTest)
    {
        userServiceTeacher.editTest(requestTest);
        return ResponseEntity.ok(new ResponseMessage(true, "тест изменён"));
    }
    // удаление теста

    @DeleteMapping("/delTest")
    public ResponseEntity<?> delTest(@RequestBody RequestTest requestTest)
    {
        testRepository.deleteById(requestTest.getId());
        return ResponseEntity.ok(new ResponseMessage(true, "тест удалён" ));
    }



    @GetMapping("/getQuestion")
    public List<Question> allQuestion()
    {
        return questionRepository.findAll();
    }

    @GetMapping("/getTest")
    public List<Test> allTest()
    {

        return testRepository.findAll();
    }




}
