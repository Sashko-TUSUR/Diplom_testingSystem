package gpo.TestingSystem.Repositories;

import gpo.TestingSystem.Models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Long>  {


    @Modifying
    @Transactional
    @Query(value = "Select * FROM student WHERE id_group =:idGroup" ,nativeQuery = true)
    List<Student> studentInGroup(@Param("idGroup") Long idGroup);


}
