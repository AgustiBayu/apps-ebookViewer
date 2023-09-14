package mcrmilenial.appsebookViewerbackend.models.response;

import lombok.Data;
import mcrmilenial.appsebookViewerbackend.entities.Roles;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Data
public class JwtResponse implements Serializable {
    private String token;
    private String type = "Bearer ";
    private String username;
    private String email;
    private String roles;
    private MessageResponse message;
    public JwtResponse(String accesToken, String username, String email, String roles, MessageResponse message) {
        this.token = accesToken;
        this.username = username;
        this.email = email;
        this.roles = roles;
        this.message = message;
    }
}
