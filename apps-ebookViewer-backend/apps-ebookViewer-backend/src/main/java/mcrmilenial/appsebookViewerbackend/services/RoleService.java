package mcrmilenial.appsebookViewerbackend.services;

import mcrmilenial.appsebookViewerbackend.entities.Role;
import mcrmilenial.appsebookViewerbackend.repositorys.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;
    public Role create(Role roles) {
        return roleRepository.save(roles);
    }
    public List<Role> findAll() {
        return roleRepository.findAll();
    }
    public Role update(Role roles) {
        return roleRepository.save(roles);
    }
    public void deleteById(long roles_id) {
        roleRepository.deleteById(roles_id);
    }
}
