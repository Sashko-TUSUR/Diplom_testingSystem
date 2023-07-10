package gpo.TestingSystem.Repositories;

import gpo.TestingSystem.Models.DidacticUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DidacticUnitRepository extends JpaRepository<DidacticUnit,Long> {

    //дидактические единицы в предмете
    @Modifying
    @Transactional
    @Query(value = "Select DISTINCT * from didactic_unit join subject on didactic_unit.subject_id = subject.subject_id Where subject.subject_id =:idSubject", nativeQuery = true)
    List<DidacticUnit> findByDidactic(@Param("idSubject") Long idSubject);



    }

