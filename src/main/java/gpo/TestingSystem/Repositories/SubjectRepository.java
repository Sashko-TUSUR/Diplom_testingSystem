package gpo.TestingSystem.Repositories;

import gpo.TestingSystem.Models.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject,Long> {
   Subject findByName(String name);


   //дисциплины препода

   @Modifying
   @Transactional
   @Query(value = "select * from subject join teacher_subject on subject.subject_id = teacher_subject.subject_id where teacher_id =:idTeacher"
           ,nativeQuery = true)
   List<Subject> findBySubjectTeacher(@Param("idTeacher") Long idTeacher);

}
