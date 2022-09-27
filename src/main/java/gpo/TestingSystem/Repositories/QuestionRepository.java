package gpo.TestingSystem.Repositories;

import gpo.TestingSystem.Models.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question,Long> {
}
