package gpo.TestingSystem.Repositories;

import gpo.TestingSystem.Models.TypeQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeQuestionRepo extends JpaRepository<TypeQuestion, Long> {
}
