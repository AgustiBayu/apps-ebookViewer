package mcrmilenial.appsebookViewerbackend.models.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class JwtResponse implements Serializable {
    private String token;
    private String type = "Bearer ";
    private String username;
    private String email;
    private List<String> roles;
    private String status;
    private String massage;
    public JwtResponse(String accesToken, String username, String email,List<String> roles,String massage, String status) {
        this.token = accesToken;
        this.username = username;
        this.email = email;
        this.roles = roles;
        this.massage = massage;
        this.status = status;
    }
    public JwtResponse(String massage,String status) {
        this(null,null,null,null,massage,status);
    }
}
