package gpo.TestingSystem.Controller;



import gpo.TestingSystem.Models.User;
import gpo.TestingSystem.Payload.Request.RequestAddSubjectForGroup;
import gpo.TestingSystem.Payload.Request.RequestGroup;
import gpo.TestingSystem.Payload.Request.RequestSubject;
import gpo.TestingSystem.Payload.Request.RequestTeacher;
import gpo.TestingSystem.Payload.Response.ResponseMessage;
import gpo.TestingSystem.Service.Reg.ExcelHelper;
import gpo.TestingSystem.Service.Reg.ExcelService;
import gpo.TestingSystem.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/admin")
public class Admin {

    @Autowired
    ExcelService fileService;
    @Autowired
    UserService userService;


    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";

        if (ExcelHelper.hasExcelFormat(file)) {
            try {
                System.out.println("I cat");
                fileService.save(file);
                System.out.println("I dog");
                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(true, message));

            } catch (Exception e) {
                System.out.println("I dog2");
               message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(false, message));
            }
        }

        message = "Please upload an excel file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(false, message));
    }

    @GetMapping("/tutorials")
    public ResponseEntity<List<User>> getAllTutorials() {
        try {
            List<User> users = fileService.getAllUsers();

            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //добавление препода
    @PostMapping("/Teacher")
    public ResponseEntity<?> addTeacher(RequestTeacher requestTeacher)
    {
        userService.addTeacher(requestTeacher);
        return ResponseEntity.ok(new ResponseMessage(true,"Преподаватель добавлен"));
    }
    //добавление группы
    @PostMapping("/addGroup")
     public void addGroup(RequestGroup requestGroup)
    {
        userService.addGroup(requestGroup);
    }
    //добавление дисциплины
    @PostMapping("/addSubject")
    public void addSubject(RequestSubject requestSubject)
    {
        userService.addSubject(requestSubject);
    }
    //добавление дисциплину группе
    @PostMapping("/addSubGroup")
    public void addSubjectForGroup(RequestAddSubjectForGroup subjectForGroup)
    {
        userService.addSubjectForGroup(subjectForGroup);
    }
    //добавление группы преподу
    @PostMapping("/addSubTeacher")
    public  void  addSubForTeacher(@RequestParam Long id, @RequestParam String numGroup)
    {
        userService.addGroupForTeacher(id,numGroup);
    }

    //редактирование препода
    @PutMapping("/editTeacher")
    public void editTeacher(RequestTeacher requestTeacher)
    {
        userService.editTeacher(requestTeacher);
    }


}
