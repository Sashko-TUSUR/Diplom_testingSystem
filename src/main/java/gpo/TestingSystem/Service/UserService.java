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
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
        user.setPassword(encoder.encode(signUpRequest.getPassword()));

        Role roles = roleRepository.findByName(EnumRole.ROLE_ADMIN).get();
        user.setRoles(Collections.singleton(roles));
        userRepository.save(user);
    }


    //добавление преподов ///ПОРАБОТАТЬ НАД ЛОГИКОЙ!!!!
    public void addTeacher(RequestTeacher requestTeacher)
    {
        try {


            Teacher teacher = new Teacher();
            Role role = roleRepository.findByName(EnumRole.ROLE_TEACHER).get();
            User user = new User();
            user.setLogin(LoginGeneration.loginGeneration(requestTeacher.getName(), requestTeacher.getSurname()));
            user.setPassword(encoder.encode(PasswordGeneration.passwordGeneration()));
            user.setNameUser(requestTeacher.getName());
            user.setSurname(requestTeacher.getSurname());
            user.setPatronymic(requestTeacher.getPatronymic());
            user.setRoles(Collections.singleton(role));

            // добавление группы
            if (requestTeacher.getIdGroup() != null) {
                //Teacher teacher = new Teacher();
                Groups groups = groupsRepository.findById(requestTeacher.getIdGroup()).get();
                System.out.println(groups.getNumGroup());
                System.out.println("end1");
                teacher.setUser(user);
                System.out.println("end2");
               // teacher.setGroups(Collections.singleton(groups));
                System.out.println("end3");
                teacherRepository.save(teacher);
            }

            //добавление предмета
            if (requestTeacher.getSubject() != null) {
                //Teacher teacher = new Teacher();
                Subject subject = subjectRepository.findByName(requestTeacher.getSubject());
                //teacher.setSubjects(Collections.singleton(subject));
                teacherRepository.save(teacher);
            }

            userRepository.save(user);
            teacher.setUser(user);
            teacherRepository.save(teacher);
        }
        catch (Throwable t) {}
    }

    //добавление группы * поработать над тем,если не будет трай
    public void addGroup(RequestGroup requestGroup)
    {
        try {

            Groups groups = new Groups();
            groups.setNumGroup(requestGroup.getNumGroup());
            groupsRepository.save(groups);
        }
        catch (Throwable t) {

        }
    }
    //добавление дисциплины *
    public void addSubject(RequestSubject requestSubject)
    {
        try {
            Subject subject = new Subject();
            subject.setName(requestSubject.getName());
            subjectRepository.save(subject);
        }
        catch (Throwable t) {

        }

    }

    //Редактирование дисциплины * отменить, если уже есть с таким названием
    public void editSubject(RequestSubject requestSubject)
    {
        Subject subject = subjectRepository.findById(requestSubject.getIdSubject()).get();

        subject.setName(requestSubject.getName());

        subjectRepository.save(subject);

    }

    //удаление дисциплины *
    public void delSubject(RequestSubject requestSubject)
    {
       subjectRepository.deleteById(requestSubject.getIdSubject());
    }

    //добавление группе дисциплину
    public void addSubjectForGroup(RequestAddSubjectForGroup subjectForGroup)
    {
        Subject subject = subjectRepository.findById(subjectForGroup.getIdSubject()).get();
        Groups groups = groupsRepository.findById(subjectForGroup.getIdGroup()).get();
        groups.getSubjects().add(subject);
        groupsRepository.save(groups);
    }

    //добавление группы преподу *
    public void addGroupTeacher(RequestTeacher requestTeacher) {

        Teacher teacher = teacherRepository.findById(requestTeacher.getIdTeach()).get();
        Groups groups = groupsRepository.findById(requestTeacher.getIdGroup()).get();

        teacher.getTeacher_group().add(groups);

        teacherRepository.save(teacher);


    }

    //редактирование препода
    /*
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
    // редактирование группы
    public void renameGroup(RequestGroup requestGroup)
    {
        Groups groups = groupsRepository.findById(requestGroup.getIdGroup()).get();
        groups.setNumGroup(requestGroup.getNumGroup());
        groupsRepository.save(groups);

    }

    //удаление группы
    public void delGroup(RequestGroup requestGroup)
    {
        groupsRepository.deleteById(requestGroup.getIdGroup());
    }

    //удаление пользователя * ответ, если нет такого пользователя
    public void delUser(RequestStudent requestStudent)
    {
        System.out.println(requestStudent.getIdUser());
        userRepository.deleteById(requestStudent.getIdUser());
    }





}
