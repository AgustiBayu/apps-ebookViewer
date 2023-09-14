package mcrmilenial.appsebookViewerbackend.controllers;

import mcrmilenial.appsebookViewerbackend.entities.User;
import mcrmilenial.appsebookViewerbackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
@PreAuthorize("isAuthenticated()")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping(path = "/user")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> creteUser(@RequestBody User user) {
        return userService.create(user);
    }
    @GetMapping(path = "/user")
    @PreAuthorize("hasAnyAuthority('ADMIN','DOSEN','MAHASISWA')")
    public ResponseEntity<?> findAll() {
        return userService.findAll();
    }
    @PutMapping(path = "/user/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> updateUser(@PathVariable("id") int user_id,@RequestBody User user) {
        ResponseEntity<?> response = userService.update(user_id, user);
        return response;
    }
    @DeleteMapping(path = "/user/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> deleteById(@PathVariable("id") int user_id) {
        return userService.deleteById(user_id);
    }
}
