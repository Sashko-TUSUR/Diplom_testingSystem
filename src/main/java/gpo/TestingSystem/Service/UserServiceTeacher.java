package gpo.TestingSystem.Service;

import gpo.TestingSystem.Models.DidacticUnit;
import gpo.TestingSystem.Models.Topic;
import gpo.TestingSystem.Payload.Request.RequestDidacticUnit;
import gpo.TestingSystem.Payload.Request.RequestTopic;
import gpo.TestingSystem.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        DidacticUnit didacticUnit = new DidacticUnit();
        didacticUnit.setName(requestAddDidacticUnit.getName());
        didacticUnit.setName(requestAddDidacticUnit.getName());
        didacticUnitRepository.save(didacticUnit);
    }
    //создание темы
    public void addTopic(RequestTopic requestTopic)
    {
        Topic topic = new Topic();
        topic.setName(requestTopic.getName());
        DidacticUnit didacticUnit = didacticUnitRepository.findById(requestTopic.getId_didactic()).get();
        topic.setDidacticUnit(didacticUnit);
        topicRepository.save(topic);
    }



}
