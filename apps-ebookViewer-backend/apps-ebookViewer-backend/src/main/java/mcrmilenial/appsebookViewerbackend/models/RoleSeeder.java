package mcrmilenial.appsebookViewerbackend.models;

import mcrmilenial.appsebookViewerbackend.entities.Roles;
import mcrmilenial.appsebookViewerbackend.repositorys.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RoleSeeder implements CommandLineRunner {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        for (StatusRoles status : StatusRoles.values()) {
            Roles role = new Roles();
            role.setName(String.valueOf(status));
            roleRepository.save(role);
        }
    }
}
