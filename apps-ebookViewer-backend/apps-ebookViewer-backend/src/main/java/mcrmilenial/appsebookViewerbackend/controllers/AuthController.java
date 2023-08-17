package mcrmilenial.appsebookViewerbackend.controllers;

import mcrmilenial.appsebookViewerbackend.entities.Role;
import mcrmilenial.appsebookViewerbackend.entities.User;
import mcrmilenial.appsebookViewerbackend.models.StatusRoles;
import mcrmilenial.appsebookViewerbackend.models.response.JwtResponse;
import mcrmilenial.appsebookViewerbackend.models.request.LoginRequest;
import mcrmilenial.appsebookViewerbackend.models.request.SignUpRequest;
import mcrmilenial.appsebookViewerbackend.models.response.MessageResponse;
import mcrmilenial.appsebookViewerbackend.repositorys.RoleRepository;
import mcrmilenial.appsebookViewerbackend.repositorys.UserRepository;
import mcrmilenial.appsebookViewerbackend.securitys.jwt.JwtUtils;
import mcrmilenial.appsebookViewerbackend.securitys.services.UserDetailsImpl;
import mcrmilenial.appsebookViewerbackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/auth")
public class AuthController {
    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    PasswordEncoder encoder;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtUtils.generateJwtToken(authentication);

            UserDetailsImpl principal = (UserDetailsImpl) authentication.getPrincipal();
            List<String> roles = principal.getAuthorities().stream()
                    .map(item -> item.getAuthority())
                    .collect(Collectors.toList());
            return ResponseEntity.ok().body(new JwtResponse(token, principal.getUsername(), principal.getEmail(), roles,"success","200"));
        } catch (AuthenticationException exception) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("401");
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpRequest signUpRequest) {
        try {
            if (userRepository.existsByUsername(signUpRequest.getUsername())) {
                return ResponseEntity.badRequest().body(new MessageResponse("Error: Username Is Alredy"));
            } else if (userRepository.existsByEmail(signUpRequest.getEmail())) {
                return ResponseEntity.badRequest().body(new MessageResponse("Error: Email Is Alredy"));
            }
            User user = new User(
                    signUpRequest.getUsername(),
                    encoder.encode(signUpRequest.getPassword()),
                    signUpRequest.getEmail()
            );
            Set<String> userRoles = signUpRequest.getRoles();
            Set<Role> roles = new HashSet<>();
            if (userRoles == null) {
                Role mahasiswaRole = roleRepository.findByName(StatusRoles.MAHASISWA)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                roles.add(mahasiswaRole);
            } else {
                userRoles.forEach(role -> {
                    switch (role) {
                        case "ADMIN":
                            Role adminRole = roleRepository.findByName(StatusRoles.ADMIN)
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                            roles.add(adminRole);

                            break;
                        case "DOSEN":
                            Role dosenRole = roleRepository.findByName(StatusRoles.DOSEN)
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                            roles.add(dosenRole);

                            break;
                        default:
                            Role mahasiswaRole = roleRepository.findByName(StatusRoles.MAHASISWA)
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                            roles.add(mahasiswaRole);
                    }
                });
            }
            user.setRoles(roles);
            userRepository.save(user);

            //return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
            return ResponseEntity.ok().body(new JwtResponse("User registered successfullt!", "200"));
        } catch (AuthenticationException exception) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("401");
        }
    }
}