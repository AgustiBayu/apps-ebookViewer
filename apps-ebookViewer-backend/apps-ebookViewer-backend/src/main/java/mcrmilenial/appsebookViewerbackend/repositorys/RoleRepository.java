package mcrmilenial.appsebookViewerbackend.repositorys;

import mcrmilenial.appsebookViewerbackend.entities.Role;
import mcrmilenial.appsebookViewerbackend.models.StatusRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(StatusRoles name);
}
