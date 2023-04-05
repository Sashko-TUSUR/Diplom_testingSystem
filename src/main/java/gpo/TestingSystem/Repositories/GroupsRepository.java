package gpo.TestingSystem.Repositories;

import gpo.TestingSystem.Models.Groups;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;


@Transactional(readOnly = true)

public interface GroupsRepository extends JpaRepository<Groups,Long > {
 Groups findByNumGroup(String NumGroup);
}
