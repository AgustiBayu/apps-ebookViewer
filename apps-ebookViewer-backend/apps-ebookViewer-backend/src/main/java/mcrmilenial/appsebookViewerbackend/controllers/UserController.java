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

    @PostMapping(path = "/addUser")
    public ResponseEntity<?> creteUser(@RequestBody User user) {
        return userService.create(user);
    }

    @GetMapping(path = "/getUser")
    public ResponseEntity<?> findAll() {
        return userService.findAll();
    }

    @PutMapping(path = "/updateUser")
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        return userService.update(user);
    }

    @DeleteMapping(path = "/deleteUser/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") int user_id) {
        return userService.deleteById(user_id);
    }
}
