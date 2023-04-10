package gpo.TestingSystem.Repositories;

import gpo.TestingSystem.Models.Groups;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


@Transactional(readOnly = true)

public interface GroupsRepository extends JpaRepository<Groups, Long> {
 Groups findByNumGroup(String NumGroup);


 @Modifying
 @Transactional
 @Query(value = "Delete from group_subject where group_id =:idGroup AND subject_id =:idSubject", nativeQuery = true)
 void delGroupForTeach(@Param("idSubject") Long idSubject, @Param("idGroup") Long idGroup);
}
