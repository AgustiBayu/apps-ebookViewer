package mcrmilenial.appsebookViewerbackend.services;

import mcrmilenial.appsebookViewerbackend.entities.User;
import mcrmilenial.appsebookViewerbackend.exeptions.BadRequestException;
import mcrmilenial.appsebookViewerbackend.exeptions.RecouseNotFoundException;
import mcrmilenial.appsebookViewerbackend.models.response.MessageResponse;
import mcrmilenial.appsebookViewerbackend.repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

//    @Autowired
//    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    PasswordEncoder encoder; // digunakan untuk encrype data

    public ResponseEntity<?> create(User user) { // digunakan untuk menambah data
        if (!StringUtils.hasText(user.getUsername())) {
            throw new BadRequestException("username is not null");
        } else if (!StringUtils.hasText(user.getPassword())) {
            throw new BadRequestException("password is not null");
        } else {
            user.setPassword(encoder.encode(user.getPassword()));// impl password encode agar pasword ke encrype
            userRepository.save(user);
            return ResponseEntity.ok().body(new MessageResponse("200", "success"));
        }
    }

    public ResponseEntity<?> findAll() { // digunakan untuk menampikan data
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("404", "user not found"));
        } else {
            return ResponseEntity.ok().body(users);
        }
    }

    public ResponseEntity<?> update(User user) { // digunakan untuk updata/merubah data
        if (!StringUtils.hasText(user.getUsername())) { // membandikan data tidak null dengan identifikasi !StringUtils.hasText bertipe data stiring a
            throw new BadRequestException("username is not null");
        } else if (!StringUtils.hasText(user.getPassword())) {
            throw new BadRequestException("password is not null");
        } else {
            userRepository.save(user);
            return ResponseEntity.ok().body(new MessageResponse("200", "success"));
        }
    }

    public ResponseEntity<?> deleteById(long user_id) { // digunakan untuk menghapus berdasarkan dengan id
        if (userRepository.existsById(user_id)) {
            userRepository.deleteById(user_id);
            return ResponseEntity.ok().body(new MessageResponse("200", "success"));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new MessageResponse("404", "user not found"));
        }
    }
}
