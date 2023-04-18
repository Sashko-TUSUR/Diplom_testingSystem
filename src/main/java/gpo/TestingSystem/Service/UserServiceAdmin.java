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
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Null;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceAdmin {

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


    //добавление админа
    public void createUser(SignUpRequest signUpRequest) {

        User user = new User();
        user.setLogin(signUpRequest.getLogin());
        user.setPassword(encoder.encode(signUpRequest.getPassword()));

        Role roles = roleRepository.findByName(EnumRole.ROLE_ADMIN).get();
        user.setRoles(Collections.singleton(roles));
        userRepository.save(user);
    }

    //добавление студента * добавить проверку на ноль
    public void createStudent (RequestStudent requestStudent)
    {
        System.out.println(requestStudent.getName());
        System.out.println(requestStudent.getSurname());
        System.out.println(requestStudent.getPatronymic());
        System.out.println(requestStudent.getIdGroup());

        Student student = new Student();
        Role role = roleRepository.findByName(EnumRole.ROLE_STUDENT).get();
        User user = new User();
        user.setLogin(LoginGeneration.loginGeneration(requestStudent.getName(), requestStudent.getSurname()));
        user.setPassword(PasswordGeneration.passwordGeneration());

        if (requestStudent.getName() != null) {
            user.setNameUser(requestStudent.getName());
        }
        if (requestStudent.getSurname() != null) {
            user.setSurname(requestStudent.getSurname());
        }

        if (requestStudent.getPatronymic() != null) {
            user.setPatronymic(requestStudent.getPatronymic());
        }
        user.setRoles(Collections.singleton(role));

        if (requestStudent.getIdGroup() != null) {
            Groups groups = groupsRepository.findById(requestStudent.getIdGroup()).get();
            student.setIdGroup(groups);
        }

        userRepository.save(user);
        student.setUser(user);
        studentRepository.save(student);


    }

    //редактирование студента *
    public void editStudent(RequestStudent requestStudent)
    {
        User user = userRepository.findById(requestStudent.getIdUser()).get();
        Student student = studentRepository.findById(user.getUserId()).get();

        if (requestStudent.getName() != null) {
            user.setNameUser(requestStudent.getName());
        }
        if (requestStudent.getSurname() != null) {
            user.setSurname(requestStudent.getSurname());
        }

        if (requestStudent.getPatronymic() != null) {
            user.setPatronymic(requestStudent.getPatronymic());
        }
        if (requestStudent.getIdGroup() != null) {
            Groups groups = groupsRepository.findById(requestStudent.getIdGroup()).get();
            student.setIdGroup(groups);
        }
        studentRepository.save(student);
        userRepository.save(user);
    }

    //добавление преподов *
    public void createTeacher(RequestTeacher requestTeacher) {

        Teacher teacher = new Teacher();
        Role role = roleRepository.findByName(EnumRole.ROLE_TEACHER).get();
        User user = new User();
        user.setLogin(LoginGeneration.loginGeneration(requestTeacher.getName(), requestTeacher.getSurname()));
        user.setPassword(encoder.encode(PasswordGeneration.passwordGeneration()));
        user.setNameUser(requestTeacher.getName());
        user.setSurname(requestTeacher.getSurname());
        user.setPatronymic(requestTeacher.getPatronymic());
        user.setRoles(Collections.singleton(role));

        if (requestTeacher.getIdSubject() != null) {
            Subject subject = subjectRepository.findById(requestTeacher.getIdSubject()).get();
            teacher.getSubject().add(subject);
        }
        if (requestTeacher.getIdGroup() != null) {
            Groups groups = groupsRepository.findById(requestTeacher.getIdGroup()).get();
            teacher.getTeacher_group().add(groups);
        }

        userRepository.save(user);
        teacher.setUser(user);
        teacherRepository.save(teacher);

    }

    //добавление группы преподу *
    public void addGroupTeacher(RequestTeacher requestTeacher) {

        Teacher teacher = teacherRepository.findById(requestTeacher.getIdUser()).get();
        Groups groups = groupsRepository.findById(requestTeacher.getIdGroup()).get();
        teacher.getTeacher_group().add(groups);

        teacherRepository.save(teacher);

    }

    //редактирование препода *
    public void editTeacher(RequestTeacher requestTeacher) {

        User user = userRepository.findById(requestTeacher.getIdUser()).get();
        Teacher teacher = teacherRepository.findById(user.getUserId()).get();

        if (requestTeacher.getName() != null) {
            user.setNameUser(requestTeacher.getName());
        }
        if (requestTeacher.getSurname() != null) {
            user.setSurname(requestTeacher.getSurname());
        }

        if (requestTeacher.getPatronymic() != null) {
            user.setPatronymic(requestTeacher.getPatronymic());
        }
        if (requestTeacher.getIdSubject() != null) {
            Subject subject = subjectRepository.findById(requestTeacher.getIdSubject()).get();
            teacher.getSubject().add(subject);
        }
        if (requestTeacher.getIdGroup() != null) {
            Groups groups = groupsRepository.findById(requestTeacher.getIdGroup()).get();
            teacher.getTeacher_group().add(groups);
        }
        teacherRepository.save(teacher);
        userRepository.save(user);

    }



    //добавление группы * ОТВЕТ НА ТО, ЕСЛИ ЕСТЬ УЖЕ ТАКАЯ ГРУППА
    public void createGroup(RequestGroup requestGroup) {
        try {

            Groups groups = new Groups();
            groups.setNumGroup(requestGroup.getNumGroup());
            groupsRepository.save(groups);
        } catch (Throwable t) {

        }
    }

    //добавление дисциплины *
    public void createSubject(RequestSubject requestSubject) {
        try {
            Subject subject = new Subject();
            subject.setName(requestSubject.getName());
            subjectRepository.save(subject);
        } catch (Throwable t) {

        }

    }



    //Редактирование дисциплины * отменить, если уже есть с таким названием
    public void editSubject(RequestSubject requestSubject) {
        Subject subject = subjectRepository.findById(requestSubject.getIdSubject()).get();

        subject.setName(requestSubject.getName());

        subjectRepository.save(subject);

    }

    //удаление дисциплины *
    public void delSubject(RequestSubject requestSubject) {
        subjectRepository.deleteById(requestSubject.getIdSubject());
    }

    //добавление группе дисциплину
    public void addSubjectForGroup(RequestAddSubjectForGroup subjectForGroup) {
        Subject subject = subjectRepository.findById(subjectForGroup.getIdSubject()).get();
        Groups groups = groupsRepository.findById(subjectForGroup.getIdGroup()).get();
        groups.getSubjects().add(subject);
        groupsRepository.save(groups);
    }



    // редактирование группы
    public void renameGroup(RequestGroup requestGroup) {
        Groups groups = groupsRepository.findById(requestGroup.getIdGroup()).get();
        groups.setNumGroup(requestGroup.getNumGroup());
        groupsRepository.save(groups);

    }

    //удаление группы
    public void delGroup(RequestGroup requestGroup) {
        groupsRepository.deleteById(requestGroup.getIdGroup());
    }

    //удаление пользователя * ответ, если нет такого пользователя
    public void delUser(RequestStudent requestStudent) {
        System.out.println(requestStudent.getIdUser());
        userRepository.deleteById(requestStudent.getIdUser());
    }






}
