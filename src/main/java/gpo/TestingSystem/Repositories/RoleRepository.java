package gpo.TestingSystem.Repositories;

import gpo.TestingSystem.Enumeration.EnumRole;
import gpo.TestingSystem.Models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(EnumRole name);
}
