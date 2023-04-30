package gpo.TestingSystem.Controller;


import gpo.TestingSystem.Models.Student;
import gpo.TestingSystem.Models.User;
import gpo.TestingSystem.Payload.Request.RequestStudent;
import gpo.TestingSystem.Repositories.StudentRepository;
import gpo.TestingSystem.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("api/denis")
public class StudentController {

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    UserRepository userRepository;





}
