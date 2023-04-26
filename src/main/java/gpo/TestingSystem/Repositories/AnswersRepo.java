package gpo.TestingSystem.Repositories;

import gpo.TestingSystem.Models.Answers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswersRepo extends JpaRepository<Answers, Long> {
}
