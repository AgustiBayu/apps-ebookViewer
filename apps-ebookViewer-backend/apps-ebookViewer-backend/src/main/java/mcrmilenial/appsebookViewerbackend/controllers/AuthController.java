package mcrmilenial.appsebookViewerbackend.controllers;

import mcrmilenial.appsebookViewerbackend.entities.Roles;
import mcrmilenial.appsebookViewerbackend.entities.User;
import mcrmilenial.appsebookViewerbackend.exeptions.BadRequestException;
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

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/auth")
public class AuthController {
    @Autowired
    UserService userService;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepository userRepository;

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
                //String userRole = roles.isEmpty() ? "Failed Login" : roles.get(0);
                MessageResponse messageResponse = new MessageResponse("200", "success");
                return ResponseEntity.ok().body(new JwtResponse(token, principal.getUsername(), principal.getEmail(), roles.get(0), messageResponse));
            } catch (AuthenticationException exception) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new MessageResponse("401", "Authorization"));
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpRequest signUpRequest) {
        try {
            if (userRepository.existsByUsername(signUpRequest.getUsername())) {
                return ResponseEntity.badRequest().body(new MessageResponse("400", "Error: Username Is Alredy"));
            } else if (userRepository.existsByEmail(signUpRequest.getEmail())) {
                return ResponseEntity.badRequest().body(new MessageResponse("400", "Error: Email Is Alredy"));
            }
            User user = new User();
            user.setUsername(signUpRequest.getUsername());
            user.setPassword(encoder.encode(signUpRequest.getPassword()));
            user.setEmail(signUpRequest.getEmail());
            Roles userRole = roleRepository.findById(signUpRequest.getRole_id()).orElse(null);
            user.setRole(userRole);

            // Simpan user ke database
            userRepository.save(user);
            //return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
            return ResponseEntity.ok().body(new MessageResponse("200", "User registered successfullt!"));

         } catch (AuthenticationException exception) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new MessageResponse("401", "Authorization"));
        }
    }
}