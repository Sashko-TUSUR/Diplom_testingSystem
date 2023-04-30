package gpo.TestingSystem.Controller;

import com.fasterxml.jackson.annotation.JsonView;

import gpo.TestingSystem.Models.*;
import gpo.TestingSystem.Payload.Request.RequestStudent;
import gpo.TestingSystem.Payload.Request.RequestTeacher;
import gpo.TestingSystem.Payload.Views;
import gpo.TestingSystem.Repositories.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/request")
public class GeneralRequest {

    @Autowired
    SubjectRepository subjectRepository;
    @Autowired
    GroupsRepository groupsRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    ComplexityRepository complexityRepository;
    @Autowired
    RoleRepository roleRepository;

    @GetMapping("/subjects")
    public List<Subject> listSubject()
{
    return subjectRepository.findAll();
}

    @JsonView(Views.Subject.class)
    @GetMapping("/groups")
    public List<Groups> listGroup()
{
    return groupsRepository.findAll();
}

    @JsonView(Views.Public.class)
    @GetMapping("/teachers")
    public List<User> listTeacher()
{
    return userRepository.Teachers();
}

    //поиск студента по группе
    @JsonView(Views.Public.class)
    @PostMapping("/studentInGroup")
    public List<Student> listStudent(@RequestBody RequestStudent requestStudent)
    {
        return studentRepository.studentInGroup(requestStudent.getIdGroup());
    }

    //сложности
    @GetMapping("/complexity")
    public List<Complexity> listComplexity()
    {
        return complexityRepository.findAll();
    }

    @JsonView(Views.Student.class)
    @PostMapping("/student")
    public Optional<Student> studentList(@RequestBody RequestStudent requestStudent)
    {
        return studentRepository.findById(requestStudent.getIdUser());
    }

    //иноформация о преподавателе
    @JsonView(Views.Teacher.class)
    @PostMapping("/teacher")
    public List<User> oneTeacher(@RequestBody RequestTeacher requestTeacher)
    {
        return userRepository.oneTeacher(requestTeacher.getIdUser());
    }


}
