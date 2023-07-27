package mcrmilenial.appsebookViewerbackend.controllers;

import mcrmilenial.appsebookViewerbackend.entitys.Role;
import mcrmilenial.appsebookViewerbackend.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/apikey")
public class RoleController {
    @Autowired
    private RoleService rolesService;

    @PostMapping(path = "/addRoles")
    public Role createRoles(@RequestBody Role roles) {
        return rolesService.create(roles);
    }
    @GetMapping(path = "/getRoles")
    public List<Role> findAll() {
        return rolesService.findAll();
    }
    @PutMapping(path = "/updateRoles")
    private Role updateUser(@RequestBody Role roles) {
        return rolesService.update(roles);
    }
    @DeleteMapping(path = "/deleteRoles/{id}")
    private void deleteByid(@PathVariable("id") int roles) {
        rolesService.deleteById(roles);
    }
}
