package gpo.TestingSystem.Service.Reg;

import gpo.TestingSystem.Models.User;
import gpo.TestingSystem.Repositories.StudentRepository;
import gpo.TestingSystem.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ExcelService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    AddStudent addStudent;



    public void save(MultipartFile file , Long idGroup) {
        try {

            System.out.println("excelService");

            List<User> users = ExcelHelper.excelToUsers(file.getInputStream());

            userRepository.saveAll(users);
            addStudent.addStudent(users,idGroup);

        } catch (IOException e) {
            System.out.println("excelServiceError");
            throw new RuntimeException("fail to store excel data: " + e.getMessage());

        }
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}