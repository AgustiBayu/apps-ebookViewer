package mcrmilenial.appsebookViewerbackend.models.request;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
public class SignUpRequest implements Serializable {
    private String username;
    private String password;
    private String email;
    private Set<String> roles;
}
