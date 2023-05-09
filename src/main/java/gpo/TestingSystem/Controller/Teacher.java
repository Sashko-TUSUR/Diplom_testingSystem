package gpo.TestingSystem.Controller;


import gpo.TestingSystem.Integrals.Indefinite.indefiniteIntegral;
import gpo.TestingSystem.Models.DidacticUnit;
import gpo.TestingSystem.Models.Subject;
import gpo.TestingSystem.Payload.Request.QuestionRequest.QuestionMain;
import gpo.TestingSystem.Payload.Request.RequestDidacticUnit;
import gpo.TestingSystem.Payload.Request.RequestSubject;
import gpo.TestingSystem.Payload.Request.RequestTopic;
import gpo.TestingSystem.Payload.Response.ResponseMessage;
import gpo.TestingSystem.Repositories.DidacticUnitRepository;
import gpo.TestingSystem.Repositories.SubjectRepository;
import gpo.TestingSystem.Repositories.TopicRepository;
import gpo.TestingSystem.Security.Auth.UserDetailsImpl;
import gpo.TestingSystem.Service.UserServiceTeacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

}
