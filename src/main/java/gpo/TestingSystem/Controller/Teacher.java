package gpo.TestingSystem.Controller;


import gpo.TestingSystem.Models.DidacticUnit;
import gpo.TestingSystem.Payload.Request.RequestDidacticUnit;
import gpo.TestingSystem.Payload.Request.RequestTopic;
import gpo.TestingSystem.Payload.Response.ResponseMessage;
import gpo.TestingSystem.Repositories.DidacticUnitRepository;
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

    @GetMapping()
    public List<DidacticUnit> didacticUser(@AuthenticationPrincipal UserDetailsImpl userDetails)
    {
       return didacticUnitRepository.findByDidactic(63L);
    }



}
