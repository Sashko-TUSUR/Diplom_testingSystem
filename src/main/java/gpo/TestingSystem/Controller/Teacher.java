package gpo.TestingSystem.Controller;


import gpo.TestingSystem.Payload.Request.RequestDidacticUnit;
import gpo.TestingSystem.Payload.Request.RequestTopic;
import gpo.TestingSystem.Payload.Response.ResponseMessage;
import gpo.TestingSystem.Service.UserServiceTeacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/teacher")
public class Teacher {

    @Autowired
    UserServiceTeacher userServiceTeacher;

    @PostMapping("addDidactic")
    public ResponseEntity<?> addDidactic(@RequestBody RequestDidacticUnit requestAddDidacticUnit) {
        try {
            userServiceTeacher.addDidacticUnit(requestAddDidacticUnit);
            return ResponseEntity.ok(new ResponseMessage(true, "Дидактическая еденица добавлена"));
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseMessage(false, "Что-то пошло не так!"));
        }
    }
        @PostMapping("addTopic")
        public ResponseEntity<?> addTopic(@RequestBody RequestTopic requestTopic)
        {
            try {
                userServiceTeacher.addTopic(requestTopic);
                return ResponseEntity.ok(new ResponseMessage(true, "Тема добавлена"));
            }
            catch (Exception e)
            {
                return ResponseEntity.ok(new ResponseMessage(false, "Что-то пошло не так!"));
            }
        }




}
