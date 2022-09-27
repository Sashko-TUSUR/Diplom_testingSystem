package gpo.TestingSystem.Repositories;

import gpo.TestingSystem.Models.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long> {

}
