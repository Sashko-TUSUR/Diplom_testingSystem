package gpo.TestingSystem.Repositories;

import gpo.TestingSystem.Models.Result;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultRepository extends JpaRepository<Result,Long> {
}
