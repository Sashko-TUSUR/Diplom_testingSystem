package gpo.TestingSystem.Repositories;

import gpo.TestingSystem.Models.DidacticUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DidacticUnitRepository extends JpaRepository<DidacticUnit,Long> {

    @Modifying
    @Transactional
    @Query(value = "Select DISTINCT * from didactic_unit join subject on didactic_unit.subject_id = subject.subject_id join " +
            "teacher_subject on subject.subject_id = teacher_subject.subject_id Where teacher_subject.teacher_id =:idUser", nativeQuery = true)
    List<DidacticUnit> findByDidactic(@Param("idUser") Long idUser);
            }
//didactic_unit.didactic_unit_id, didactic_unit.name