package mcrmilenial.appsebookViewerbackend.controllers;

import mcrmilenial.appsebookViewerbackend.entities.Role;
import mcrmilenial.appsebookViewerbackend.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
@PreAuthorize("isAuthenticated()")
public class RoleController {
    @Autowired
    private RoleService rolesService;

    @PostMapping(path = "/addRoles")
    public ResponseEntity<?> createRoles(@RequestBody Role roles) {
        return rolesService.create(roles);
    }
    @GetMapping(path = "/getRoles")
    public ResponseEntity<?> findAll() {
        return rolesService.findAll();
    }
    @PutMapping(path = "/updateRoles")
    private ResponseEntity<?> updateUser(@RequestBody Role roles) {
        return rolesService.update(roles);
    }
    @DeleteMapping(path = "/deleteRoles/{id}")
    private ResponseEntity<?> deleteByid(@PathVariable("id") int roles) {
        return rolesService.deleteById(roles);
    }
}
