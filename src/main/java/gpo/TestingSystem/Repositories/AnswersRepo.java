package gpo.TestingSystem.Repositories;

import gpo.TestingSystem.Models.Answers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AnswersRepo extends JpaRepository<Answers, Long> {


    @Modifying
    @Transactional
    @Query(value = "Select DISTINCT * from answers where question_id =:idQuestion", nativeQuery = true)
    Answers findByQuestion(@Param("idQuestion") Long idQuestion);
}
