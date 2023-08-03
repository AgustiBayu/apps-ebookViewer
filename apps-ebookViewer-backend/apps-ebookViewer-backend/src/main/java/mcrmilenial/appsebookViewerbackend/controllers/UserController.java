package mcrmilenial.appsebookViewerbackend.controllers;

import mcrmilenial.appsebookViewerbackend.entities.User;
import mcrmilenial.appsebookViewerbackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping(path = "/addUser")
    public User creteUser(@RequestBody User user) {
        return userService.create(user);
    }
    @GetMapping(path = "/getUser")
    public List<User> findAll() {
        return userService.findAll();
    }
    @PutMapping(path = "/updateUser")
    public User updateUser(@RequestBody User user) {
        return userService.update(user);
    }
    @DeleteMapping(path = "/deleteUser/{id}")
    public void deleteById(@PathVariable("id") int user_id) {
        userService.deleteById(user_id);
    }
    @GetMapping(path = "/getUser/{id}")
    public User findById(@PathVariable("id") int user_id) {
        return userService.findByid(user_id);
    }
}
