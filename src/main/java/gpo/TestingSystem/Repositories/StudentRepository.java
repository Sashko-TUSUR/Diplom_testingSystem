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

    @Modifying
    @Transactional
    @Query(value = "SELECT * FROM subject join group_subject on subject.subject_id = group_subject.subject_id join groups on group_subject.group_id = groups.id_group " +
            "join student on groups.id_group = student.id_group where student.user_id=:idUser",nativeQuery = true)
    List<Student> studentSubject(@Param("idUser") Long idUser);

}
