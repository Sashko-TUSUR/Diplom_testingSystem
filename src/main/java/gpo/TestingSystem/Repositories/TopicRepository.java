package gpo.TestingSystem.Repositories;

import gpo.TestingSystem.Models.DidacticUnit;
import gpo.TestingSystem.Models.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, Long> {



    @Modifying
    @Transactional
    @Query(value = "select * from topic join didactic_unit on" +
            " topic.didactic_unit_id = didactic_unit.didactic_unit_id Where didactic_unit.didactic_unit_id =:idDidactic", nativeQuery = true)
    List<Topic> findByTopic(@Param("idDidactic") Long idDidactic);




}
