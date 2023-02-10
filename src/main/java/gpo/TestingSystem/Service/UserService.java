package gpo.TestingSystem.Service;

import gpo.TestingSystem.Enumeration.EnumRole;
import gpo.TestingSystem.Exception.ResourceNotFoundException;
import gpo.TestingSystem.Models.*;
import gpo.TestingSystem.Payload.Request.*;
import gpo.TestingSystem.Payload.Response.ResponseMessage;
import gpo.TestingSystem.Repositories.*;
import gpo.TestingSystem.Service.Reg.LoginGeneration;
import gpo.TestingSystem.Service.Reg.PasswordGeneration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Null;
import java.util.Collections;
import java.util.Optional;

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
    @Autowired
    PasswordEncoder encoder;


    public void createUser(SignUpRequest signUpRequest) {

     /*  User byEmail = userRepository.findByLogin(signUpRequest.getEmail());
        if (!equals(byEmail)) {
            throw new RuntimeException("Пользователь с таким email " + byEmail + " уже зарегистирован");
        }*/
        User user = new User();
        user.setLogin(signUpRequest.getLogin());
        user.setPassword(signUpRequest.getPassword());
        user.setPassword(encoder.encode(signUpRequest.getPassword()));

        Role roles = roleRepository.findByName(EnumRole.ROLE_ADMIN).get();
        user.setRoles(Collections.singleton(roles));
        userRepository.save(user);
    }


    //добавление преподов
    public void addTeacher(RequestTeacher requestTeacher)
    {
        System.out.println(requestTeacher.getSurname());
        System.out.println(requestTeacher.getName());
        System.out.println(requestTeacher.getPatronymic());
        System.out.println(requestTeacher.getNumGroup());
        Role role = roleRepository.findByName(EnumRole.ROLE_TEACHER).get();

        User user = new User();
        user.setLogin(LoginGeneration.loginGeneration(requestTeacher.getName(),requestTeacher.getSurname()));
        user.setPassword(PasswordGeneration.passwordGeneration());
        user.setNameUser(requestTeacher.getName());
        user.setSurname(requestTeacher.getSurname());
        user.setPatronymic(requestTeacher.getPatronymic());
        user.setRoles(Collections.singleton(role));

        if(requestTeacher.getIdGroup() != null)
        {
            Teacher teacher = new Teacher();
            Groups groups = groupsRepository.findById(requestTeacher.getIdGroup()).get();
            System.out.println(groups.getNumGroup());
            System.out.println("end1");
            teacher.setUser(user);
            System.out.println("end2");
            teacher.setGroups(Collections.singleton(groups));
            System.out.println("end3");
            teacherRepository.save(teacher);
        }

        if(requestTeacher.getSubject() != null)
        {
            Teacher teacher = new Teacher();
            Subject subject = subjectRepository.findByName(requestTeacher.getSubject());
            teacher.setSubjects(Collections.singletonList(subject));
            teacherRepository.save(teacher);
        }
        userRepository.save(user);
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
        Subject subject = subjectRepository.findByName(subjectForGroup.getSubject());
        Groups groups = groupsRepository.findById(subjectForGroup.getIdGroup())
                .orElseThrow(()-> new ResourceNotFoundException("Такой группы не существует!"));
        groups.setSubjects(Collections.singletonList(subject));

        return ResponseEntity.ok(new ResponseMessage(true,"Группа добавлена"));

    }
    //добавление группы преподу
    public ResponseEntity<?> addGroupForTeacher(Long id, Long idGroup)
    {
        Teacher teacher = teacherRepository.findById(id).get();
        Groups groups = groupsRepository.findById(idGroup).get();
        teacher.setGroups(Collections.singleton(groups));
        return ResponseEntity.ok(new ResponseMessage(true, "Группа добавлена"));

    }

    /*
    //редактирование препода
 public ResponseEntity<?> editTeacher(RequestTeacher requestTeacher)
    {

        Teacher teacher = teacherRepository.findById(requestTeacher.getId()).get();
        User user= userRepository.findById(requestTeacher.getId()).get();
        try {
            if (requestTeacher.getNumGroup().trim().length() != 0) {
                Groups groups = groupsRepository.findById(requestTeacher.getNumGroup()).get();
                teacher.setGroups(Collections.singleton(groups));
            }
            if (requestTeacher.getName().trim().length() != 0) {
                user.setNameUser(requestTeacher.getName());
            }
            if (requestTeacher.getPatronymic().trim().length() != 0) {
                user.setPatronymic(requestTeacher.getPatronymic());
            }
            if (requestTeacher.getSurname().trim().length() != 0) {
                user.setSurname(requestTeacher.getSurname());
            }
            if (requestTeacher.getSubject().trim().length() != 0) {
                Subject subject = subjectRepository.findByName(requestTeacher.getSubject());
                teacher.setSubjects(Collections.singletonList(subject));
            }
            userRepository.save(user);
            teacherRepository.save(teacher);
            return ResponseEntity.ok(new ResponseMessage(true, "Редактирование выполнено!"));
        }
        catch (Exception exception)
        {
            userRepository.save(user);
            teacherRepository.save(teacher);
            return ResponseEntity.ok(new ResponseMessage(false, "Редактирование не удалось!"));
        }
    }
*/






}
