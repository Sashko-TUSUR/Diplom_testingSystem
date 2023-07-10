package gpo.TestingSystem.Repositories;

import gpo.TestingSystem.Models.TypeIntegral;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeIntegralRepository extends JpaRepository<TypeIntegral,Long> {

}
