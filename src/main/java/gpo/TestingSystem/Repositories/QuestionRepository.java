package gpo.TestingSystem.Repositories;

import gpo.TestingSystem.Models.Answers;
import gpo.TestingSystem.Models.DidacticUnit;
import gpo.TestingSystem.Models.Question;
import gpo.TestingSystem.Models.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question,Long> {

    // вопросы в теме
    @Modifying
    @Transactional
    @Query(value = " select * from question where topic_id =:idTopic", nativeQuery = true)
    List<Question> findByQuestion(@Param("idTopic") Long idTopic);

    @Modifying
    @Transactional
    @Query(value = "select question_id from test_question where test_question.test_id =:idTest",nativeQuery = true)
    List<Long> testsQuestion(@Param("idTest") Long idTest);


}
