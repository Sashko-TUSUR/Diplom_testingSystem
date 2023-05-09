package gpo.TestingSystem.Controller;


import com.fasterxml.jackson.annotation.JsonView;
import gpo.TestingSystem.Models.Student;
import gpo.TestingSystem.Models.User;
import gpo.TestingSystem.Payload.Request.RequestStudent;
import gpo.TestingSystem.Payload.Views;
import gpo.TestingSystem.Repositories.StudentRepository;
import gpo.TestingSystem.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/student")
public class StudentController {

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    UserRepository userRepository;
    Pageable pageable;

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



}
