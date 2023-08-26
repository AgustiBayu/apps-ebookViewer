package mcrmilenial.appsebookViewerbackend.services;

import mcrmilenial.appsebookViewerbackend.entities.Role;
import mcrmilenial.appsebookViewerbackend.exeptions.BadRequestException;
import mcrmilenial.appsebookViewerbackend.models.StatusRoles;
import mcrmilenial.appsebookViewerbackend.models.response.MessageResponse;
import mcrmilenial.appsebookViewerbackend.repositorys.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    public ResponseEntity<?> create(Role roles) {
        StatusRoles roleName = roles.getName();

        if (roleName == StatusRoles.ADMIN || roleName == StatusRoles.DOSEN || roleName == StatusRoles.MAHASISWA) {
            roleRepository.save(roles);
            return ResponseEntity.ok().body(new MessageResponse("200","success"));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("400", "invalid role name"));
        }
    }
    public ResponseEntity<?> findAll() {
        List<Role> roles = roleRepository.findAll(); // digunakan untuk menampilkan data
        if(roles.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("404","roles is not found"));
        } else {
            return ResponseEntity.ok().body(roles);
        }
    }

    public ResponseEntity<?> update(Role roles) { // digunakan untuk update data
        StatusRoles roleName = roles.getName();

        if (roleName == StatusRoles.ADMIN || roleName == StatusRoles.DOSEN || roleName == StatusRoles.MAHASISWA) {
            roleRepository.save(roles);
            return ResponseEntity.ok().body(new MessageResponse("200","success"));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("400", "invalid role name"));
        }
    }

    public ResponseEntity<?> deleteById(int roles_id) { // digunakan untuk menghapus data
        if(roleRepository.existsById(roles_id)) {
            roleRepository.deleteById(roles_id);
            return ResponseEntity.ok().body(new MessageResponse("200","success"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("404","role not found"));
        }
    }
}
