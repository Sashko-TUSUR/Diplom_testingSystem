package gpo.TestingSystem.Repositories;

import gpo.TestingSystem.Models.Integral;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IntegralRepository extends JpaRepository<Integral,Long> {
}
