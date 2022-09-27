package gpo.TestingSystem.Repositories;

import gpo.TestingSystem.Models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher,Long> {
}
