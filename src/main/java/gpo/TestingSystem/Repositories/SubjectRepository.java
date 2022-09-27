package gpo.TestingSystem.Repositories;

import gpo.TestingSystem.Models.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject,Long> {
   Subject findByNumGroup(String num);
}
