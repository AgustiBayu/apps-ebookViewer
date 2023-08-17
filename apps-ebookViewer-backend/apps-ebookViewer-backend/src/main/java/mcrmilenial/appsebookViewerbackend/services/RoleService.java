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
    public Role create(Role roles) { // digunakan untuk menambah data
        return roleRepository.save(roles);
    }
    public List<Role> findAll() { // digunakan untuk menampilkan data
        return roleRepository.findAll();
    }
    public Role update(Role roles) { // digunakan untuk update data
        return roleRepository.save(roles);
    }
    public void deleteById(int roles_id) { // digunakan untuk menghapus data
        roleRepository.deleteById(roles_id);
    }
}
