package gpo.TestingSystem.Controller;

import com.fasterxml.jackson.annotation.JsonView;
import gpo.TestingSystem.Models.Groups;
import gpo.TestingSystem.Models.Student;
import gpo.TestingSystem.Models.Subject;
import gpo.TestingSystem.Models.Teacher;
import gpo.TestingSystem.Payload.Request.RequestStudent;
import gpo.TestingSystem.Payload.Views;
import gpo.TestingSystem.Repositories.GroupsRepository;
import gpo.TestingSystem.Repositories.StudentRepository;
import gpo.TestingSystem.Repositories.SubjectRepository;
import gpo.TestingSystem.Repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/request")
public class GeneralRequest {

    @Autowired
    SubjectRepository subjectRepository;
    @Autowired
    GroupsRepository groupsRepository;
    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    StudentRepository studentRepository;

    @GetMapping("/subjects")
    public List<Subject> listSubject()
{
    return subjectRepository.findAll();
}

    @GetMapping("/groups")
    public List<Groups> listGroup()
{
    return groupsRepository.findAll();
}

    @JsonView(Views.Public.class)
    @GetMapping("/teachers")
    public List<Teacher> listTeacher()
{
    return teacherRepository.findAll();
}

    //поиск студента по группе
    @JsonView(Views.Internal.class)
    @PostMapping("/studentInGroup")
    public List<Student> listStudent(@RequestBody RequestStudent requestStudent)
    {
        return studentRepository.studentInGroup(requestStudent.getIdGroup());
    }


}
