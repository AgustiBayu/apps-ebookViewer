package mcrmilenial.appsebookViewerbackend.services;

import mcrmilenial.appsebookViewerbackend.entities.User;
import mcrmilenial.appsebookViewerbackend.exeptions.BadRequestException;
import mcrmilenial.appsebookViewerbackend.exeptions.RecouseNotFoundException;
import mcrmilenial.appsebookViewerbackend.repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    PasswordEncoder encoder;

    public User create(User user) {
        if (!StringUtils.hasText(user.getUsername())) {
            throw new BadRequestException("username is not null");
        } else if (!StringUtils.hasText(user.getPassword())) {
            throw new BadRequestException("password is not null");
        } else {
            user.setPassword(encoder.encode(user.getPassword()));
            return userRepository.save(user);
        }
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User update(User user) {
        if (!StringUtils.hasText(user.getUsername())) {
            throw new BadRequestException("username is not null");
        } else if (!StringUtils.hasText(user.getPassword())) {
            throw new BadRequestException("password is not null");
        } else {
            return userRepository.save(user);
        }
    }

    public void deleteById(long user_id) {
        userRepository.deleteById(user_id);
    }

    public User findByid(long user_id) {
        return userRepository.findById(user_id)
                .orElseThrow(() -> new RecouseNotFoundException("Pnegguna id " + user_id + " Tidak ada"));
    }
}
