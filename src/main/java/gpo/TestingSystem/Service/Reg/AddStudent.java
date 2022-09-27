package gpo.TestingSystem.Service.Reg;

import gpo.TestingSystem.Models.Groups;
import gpo.TestingSystem.Models.Student;
import gpo.TestingSystem.Models.User;
import gpo.TestingSystem.Repositories.GroupsRepository;
import gpo.TestingSystem.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AddStudent {

    @Autowired
    GroupsRepository groupsRepository;
    @Autowired
    StudentRepository studentRepository;


    public void addStudent(List<User> userList) {

        List<Student> students = new ArrayList<Student>();
        String numGroup = ExcelHelper.numGroup;
        Groups groups = groupsRepository.findById(numGroup).get();

        for(int i=0;i< userList.size();i++)
        {
            Student student = new Student();
            student.setUser(userList.get(i));
            student.setNumGroup(groups);
            students.add(student);

        }

        studentRepository.saveAll(students);
    }




}
