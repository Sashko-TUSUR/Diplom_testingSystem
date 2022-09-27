package gpo.TestingSystem.Repositories;

import gpo.TestingSystem.Models.Groups;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface GroupsRepository extends JpaRepository<Groups,String > {
 
}
