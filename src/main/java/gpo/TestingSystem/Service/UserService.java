package gpo.TestingSystem.Service;

import gpo.TestingSystem.Enumeration.EnumRole;
import gpo.TestingSystem.Exception.ResourceNotFoundException;
import gpo.TestingSystem.Models.*;
import gpo.TestingSystem.Payload.Request.RequestAddSubjectForGroup;
import gpo.TestingSystem.Payload.Request.RequestGroup;
import gpo.TestingSystem.Payload.Request.RequestSubject;
import gpo.TestingSystem.Payload.Request.RequestTeacher;
import gpo.TestingSystem.Payload.Response.ResponseMessage;
import gpo.TestingSystem.Repositories.*;
import gpo.TestingSystem.Service.Reg.LoginGeneration;
import gpo.TestingSystem.Service.Reg.PasswordGeneration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService {

    @Autowired
    GroupsRepository groupsRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    SubjectRepository subjectRepository;



    //добавление преподов
    public void addTeacher(RequestTeacher requestTeacher)
    {
        Role role = roleRepository.findByName(EnumRole.ROLE_TEACHER).get();
        User user = new User();
        user.setLogin(LoginGeneration.loginGeneration(requestTeacher.getName(),requestTeacher.getSurname()));
        user.setPassword(PasswordGeneration.passwordGeneration());
        user.setNameUser(requestTeacher.getName());
        user.setSurname(requestTeacher.getSurname());
        user.setPatronymic(requestTeacher.getPatronymic());
        user.setRoles(Collections.singleton(role));

        userRepository.save(user);
        if(requestTeacher.getGroupsList().trim().length()!=0)
        {
            Teacher teacher = new Teacher();
            Groups groups = groupsRepository.findById(requestTeacher.getSubject())
                    .orElseThrow(()-> new ResourceNotFoundException("Группы: "+requestTeacher.getSubject() +" не существует"));
            teacher.setUser(user);
            teacher.setGroups(Collections.singleton(groups));
            teacherRepository.save(teacher);
        }
        if(requestTeacher.getSubject().trim().length()!=0)
        {
            Teacher teacher = new Teacher();
            Subject subject = subjectRepository.findByNumGroup(requestTeacher.getSubject());
            teacher.setSubjects(Collections.singletonList(subject));
            teacherRepository.save(teacher);
        }

    }

    //добавление группы
    public ResponseEntity<?> addGroup(RequestGroup requestGroup)
    {
        try {
            Groups groups = new Groups();
            groups.setNumGroup(requestGroup.getNumGroup());
            return ResponseEntity.ok(new ResponseMessage(true,"Группа добавлена"));
        }
        catch (Exception exception)
        {
            return ResponseEntity.ok(new ResponseMessage(false,"Группа с таким номером уже существет!"));
        }

    }
    //добавление дисциплины
    public  ResponseEntity<?> addSubject(RequestSubject requestSubject)
    {
        try {
            Subject subject = new Subject();
            subject.setName(requestSubject.getName());
            return ResponseEntity.ok(new ResponseMessage(true,"Предмет добавлена"));
        }
        catch (Exception exception)
        {
            return ResponseEntity.ok(new ResponseMessage(false,"Такой предмет уже существует!"));
        }

    }

    //добавление группе предмет
    public ResponseEntity<?> addSubjectForGroup(RequestAddSubjectForGroup subjectForGroup)
    {
        Subject subject = subjectRepository.findByNumGroup(subjectForGroup.getNumGroup());
        Groups groups = groupsRepository.findById(subjectForGroup.getSubject())
                .orElseThrow(()-> new ResourceNotFoundException("Такой группы не существует!"));
        subject.setGroups(Collections.singleton(groups));
        return ResponseEntity.ok(new ResponseMessage(true,"Группа добавлена"));

    }







}
