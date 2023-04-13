package gpo.TestingSystem.Service;

import gpo.TestingSystem.Models.DidacticUnit;
import gpo.TestingSystem.Models.Subject;
import gpo.TestingSystem.Models.Topic;
import gpo.TestingSystem.Payload.Request.RequestDidacticUnit;
import gpo.TestingSystem.Payload.Request.RequestTopic;
import gpo.TestingSystem.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserServiceTeacher {

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
    DidacticUnitRepository didacticUnitRepository;
    @Autowired
    TopicRepository topicRepository;


    //создание дидактической еденицы
    public void addDidacticUnit(RequestDidacticUnit requestAddDidacticUnit)
    {
        Subject subject = subjectRepository.findById(requestAddDidacticUnit.getIdSubject()).get();

        DidacticUnit didacticUnit = new DidacticUnit();
        didacticUnit.setName(requestAddDidacticUnit.getName());
        didacticUnit.setSubjectId(subject);
        didacticUnitRepository.save(didacticUnit);
    }

    //редактирование еденицы
    public void editDidactic(RequestDidacticUnit requestDidacticUnit)
    {
        DidacticUnit didacticUnit = didacticUnitRepository.findById(requestDidacticUnit.getIdDidactic()).get();
        didacticUnit.setName(requestDidacticUnit.getName());
        didacticUnitRepository.save(didacticUnit);

    }

    //создание темы
    public void addTopic(RequestTopic requestTopic)
    {
        Topic topic = new Topic();
        topic.setName(requestTopic.getName());
        DidacticUnit didacticUnit = didacticUnitRepository.findById(requestTopic.getIdDidactic()).get();
        topic.setDidacticUnit(didacticUnit);
        topicRepository.save(topic);
    }

    //edit topic
    public void editTopic(RequestTopic requestTopic)
    {
        Topic topic = topicRepository.findById(requestTopic.getIdTopic()).get();
        topic.setName(requestTopic.getName());
        topicRepository.save(topic);
    }



}
