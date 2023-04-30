package gpo.TestingSystem.Repositories;

import gpo.TestingSystem.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository <User,Long>{
 User findByLogin(String login);

 //удаление дисциплины у преподавателя
 @Modifying
 @Transactional
 @Query(value = "Delete FROM teacher_subject WHERE teacher_id =:idTeach AND subject_id =:idSubject" ,nativeQuery = true)
 void delSubForTeach(@Param("idTeach") Long idTeach , @Param("idSubject") Long idSubject );

 //Удаление группы у препода

 @Modifying
 @Transactional
 @Query(value = "Delete from teacher_group where teacher_id =:idTeach AND groups_id =:idGroup",nativeQuery = true)
 void  delGroupForTeach(@Param("idTeach") Long idTeach , @Param("idGroup") Long idGroup );

 @Modifying
 @Transactional
 @Query(value = "Select * from users join role_user on users.user_id = role_user.user_id where role_user.role_id = 3",nativeQuery = true)
 List<User> Teachers();

 //конкретный преподаватель
 @Modifying
 @Transactional
 @Query(value = " Select *  from users join role_user on users.user_id = role_user.user_id where role_user.role_id = 3" +
         " and users.user_id =:idUser",nativeQuery = true)
 List<User> oneTeacher(@Param("idUser") Long idUser);

}
