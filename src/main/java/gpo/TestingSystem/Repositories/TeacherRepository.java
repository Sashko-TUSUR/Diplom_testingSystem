//package gpo.TestingSystem.Repositories;
//
//import gpo.TestingSystem.Models.Teacher;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
//import javax.transaction.Transactional;
//
//public interface TeacherRepository extends JpaRepository<Teacher,Long> {
//
//    //удаление дисциплины у преподавателя
//    @Modifying
//    @Transactional
//    @Query(value = "Delete FROM teacher_subject WHERE teacher_id =:idTeach AND subject_id =:idSubject" ,nativeQuery = true)
//    void delSubForTeach(@Param("idTeach") Long idTeach , @Param("idSubject") Long idSubject );
//
//    //Удаление группы у препода
//
//    @Modifying
//    @Transactional
//    @Query(value = "Delete from teacher_group where teacher_id =:idTeach AND groups_id =:idGroup",nativeQuery = true)
//    void  delGroupForTeach(@Param("idTeach") Long idTeach , @Param("idGroup") Long idGroup );
//
//
//
//
//}
