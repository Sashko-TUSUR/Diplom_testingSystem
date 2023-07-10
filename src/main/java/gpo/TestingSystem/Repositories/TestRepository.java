package gpo.TestingSystem.Repositories;

import gpo.TestingSystem.Models.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface TestRepository extends JpaRepository<Test, Long> {


    @Modifying
    @Transactional
    @Query(value = "select * from test where topic_id =:idTopic",nativeQuery = true)
    List<Test> testInTopic(@Param("idTopic") Long idTopic);

    @Modifying
    @Transactional
    @Query(value = "select test.* from test join topic on test.topic_id = topic.topic_id " +
            "join didactic_unit on topic.didactic_unit_id = didactic_unit.didactic_unit_id " +
            "join subject on didactic_unit.subject_id = subject.subject_id " +
            "join group_subject on subject.subject_id = group_subject.subject_id " +
            "join groups on group_subject.group_id = groups.id_group " +
            "join student on groups.id_group = student.id_group where student.user_id =:idUser  ",nativeQuery = true)
    List<Test> testUser(@Param("idUser") Long idUser);






}
