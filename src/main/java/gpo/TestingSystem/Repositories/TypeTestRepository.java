package gpo.TestingSystem.Repositories;

import gpo.TestingSystem.Models.TypeTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeTestRepository extends JpaRepository<TypeTest,Long> {
}
