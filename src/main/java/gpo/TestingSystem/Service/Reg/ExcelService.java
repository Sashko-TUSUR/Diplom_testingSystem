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



    public void save(MultipartFile file) {
        try {
            List<User> users = ExcelHelper.excelToUsers(file.getInputStream());

            userRepository.saveAll(users);
            addStudent.addStudent(users);

        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());

        }
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}