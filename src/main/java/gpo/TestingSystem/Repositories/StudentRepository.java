package gpo.TestingSystem.Repositories;

import gpo.TestingSystem.Models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student , Long> {
}
