package gpo.TestingSystem.Controller;


import com.fasterxml.jackson.annotation.JsonView;
import gpo.TestingSystem.Models.Student;
import gpo.TestingSystem.Models.Test;
import gpo.TestingSystem.Models.User;
import gpo.TestingSystem.Payload.Request.RequestStudent;
import gpo.TestingSystem.Payload.Response.ResponseMessage;
import gpo.TestingSystem.Payload.Views;
import gpo.TestingSystem.Repositories.StudentRepository;
import gpo.TestingSystem.Repositories.TestRepository;
import gpo.TestingSystem.Repositories.UserRepository;
import gpo.TestingSystem.Security.Auth.UserDetailsImpl;
import gpo.TestingSystem.Service.UserServiceStudent;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/student")
public class StudentController {

    @Autowired
    UserServiceStudent userServiceStudent;

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    TestRepository testRepository;

    //info about student`s subjects
    
    @JsonView(Views.SubjectGroup.class)
    @PostMapping("/subjects")
    public List<Student> studentSubject(@RequestBody RequestStudent requestStudent)
    {
        return  studentRepository.studentSubject(requestStudent.getIdUser());
    }

    //info about one student
    @JsonView(Views.Student.class)
    @PostMapping("/info")
    public Optional<Student> studentList(@RequestBody RequestStudent requestStudent)
    {
        return studentRepository.findById(requestStudent.getIdUser());
    }


    @GetMapping("/tests")
    public List<Test> testUser(@AuthenticationPrincipal UserDetailsImpl userDetails)
    {
            return  testRepository.testUser(4L);
    }

    //получение теста с вопросами

    @GetMapping("QTest")
    public ResponseEntity<?> testUser()
    {
        userServiceStudent.getTestForStudent(94L);
        return ResponseEntity.ok(new ResponseMessage(true, "Группа удалена"));
    }


    }
