package gpo.TestingSystem.Controller;



import gpo.TestingSystem.Models.User;
import gpo.TestingSystem.Payload.Request.*;
import gpo.TestingSystem.Payload.Response.ResponseMessage;
import gpo.TestingSystem.Service.Reg.ExcelHelper;
import gpo.TestingSystem.Service.Reg.ExcelService;
import gpo.TestingSystem.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Validated
@RestController
@RequestMapping("api/admin")
public class Admin {

    @Autowired
    ExcelService fileService;
    @Autowired
    UserService userService;


    //загрузка студентов
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

    //добавление препода *
    @PostMapping("/Teacher")
    public ResponseEntity<?> addTeacher(@RequestBody RequestTeacher requestTeacher)
    {
        userService.addTeacher(requestTeacher);
        return ResponseEntity.ok(new ResponseMessage(true,"Преподаватель добавлен"));
    }

    //добавление группы *
    @PostMapping("/addGroup")
     public ResponseEntity<?> addGroup(@RequestBody RequestGroup requestGroup)
    {
        try {
            userService.addGroup(requestGroup);
            return ResponseEntity.ok(new ResponseMessage(true,"Группа добавлена"));
        }
        catch (Exception exception)
        {
            return ResponseEntity.ok(new ResponseMessage(false,"Группа с таким номером уже существет!"));
        }

    }
    //редактирование группы * проверять существует ли уже такая группа
    @PutMapping("/editGroup")
    public ResponseEntity<?> editGroup(@RequestBody RequestGroup requestGroup)
    {
        userService.renameGroup(requestGroup);
        return ResponseEntity.ok(new ResponseMessage(true,"Группа переименована"));
    }
    //удаление группы
    @DeleteMapping("/delGroup")
    public  ResponseEntity<?> delGroup(@RequestBody RequestGroup requestGroup)
    {
        userService.delGroup(requestGroup);
        return ResponseEntity.ok(new ResponseMessage(true,"Группа удалена"));
    }

    //добавление дисциплины *
    @PostMapping("/addSubject")
    public ResponseEntity<?> addSubject(@RequestBody RequestSubject requestSubject)
    {
        try {
            userService.addSubject(requestSubject);
            return ResponseEntity.ok(new ResponseMessage(true,"Предмет добавлена"));
        }
        catch (Exception exception)
        {
            return ResponseEntity.ok(new ResponseMessage(false,"Такой предмет уже существует!"));
        }

    }

    //редактирование дисциплины *
    @PutMapping("/editSubject")
    public ResponseEntity<?> editSubject(@RequestBody RequestSubject requestSubject)
    {
        userService.editSubject(requestSubject);
        return ResponseEntity.ok(new ResponseMessage(true,"Дисциплина изменена"));
    }

    //удаление дисциплины*
    @DeleteMapping("/delSubject")
    public ResponseEntity<?> delSubject(@RequestBody RequestSubject requestSubject)
    {
        userService.delSubject(requestSubject);
        return ResponseEntity.ok(new ResponseMessage(true,"Дисциплина удалена"));
    }

    //добавление дисциплины группе *ответы поправить
  @PostMapping("/addSubGroup")
    public ResponseEntity<?> addSubjectForGroup(@RequestBody RequestAddSubjectForGroup subjectForGroup)
    {
        try {
            userService.addSubjectForGroup(subjectForGroup);
            return ResponseEntity.ok(new ResponseMessage(true,"Группа добавлена"));
        }
        catch (Exception exception)
        {
            return ResponseEntity.ok(new ResponseMessage(false,"Такой группы не существует!"));
        }

    }

 //   добавление группы преподу *
    @PostMapping("/addGroupTeacher")
    public ResponseEntity<?> addGroupTeacher(@RequestBody RequestTeacher requestTeacher)
    {
        userService.addGroupTeacher(requestTeacher);
        return ResponseEntity.ok(new ResponseMessage(true, "Группа добавлена"));
    }

    //удаление пользователя
    @DeleteMapping("/delUser")
    public ResponseEntity<?> delUser(@RequestBody RequestStudent requestStudent)
    {
        userService.delUser(requestStudent);
        return ResponseEntity.ok(new ResponseMessage(true, "Пользователь удалён"));
    }

    /*//редактирование препода
    @PutMapping("/editTeacher")
    public void editTeacher(RequestTeacher requestTeacher)
    {
        userService.editTeacher(requestTeacher);
    }*/
}
