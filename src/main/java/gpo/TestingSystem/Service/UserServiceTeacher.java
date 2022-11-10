package gpo.TestingSystem.Service;

import gpo.TestingSystem.Models.DidacticUnit;
import gpo.TestingSystem.Models.Topic;
import gpo.TestingSystem.Payload.Request.RequestAddDidacticUnit;
import gpo.TestingSystem.Payload.Request.RequestAddTopic;
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
    public void addDidacticUnit(RequestAddDidacticUnit requestAddDidacticUnit)
    {
        DidacticUnit didacticUnit = new DidacticUnit();
        didacticUnit.setName(requestAddDidacticUnit.getName());
        didacticUnit.setName(requestAddDidacticUnit.getName());
        didacticUnitRepository.save(didacticUnit);
    }
    //создание темы
    public void addTopic(RequestAddTopic requestAddTopic)
    {
        Topic topic = new Topic();
        topic.setName(requestAddTopic.getName());
        DidacticUnit didacticUnit = didacticUnitRepository.findById(requestAddTopic.getId_didactic()).get();
        topic.setDidacticUnit(didacticUnit);
        topicRepository.save(topic);
    }



}
