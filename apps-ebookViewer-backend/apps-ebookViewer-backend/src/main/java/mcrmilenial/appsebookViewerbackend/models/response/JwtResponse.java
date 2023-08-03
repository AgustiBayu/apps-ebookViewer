package mcrmilenial.appsebookViewerbackend.models.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class JwtResponse implements Serializable {
    private String token;
    private String type = "Bearer";
    private String username;
    private String email;
    private List<String> roles;

    public JwtResponse(String accesToken, String username, String email,List<String> roles) {
        this.token = accesToken;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }
}
