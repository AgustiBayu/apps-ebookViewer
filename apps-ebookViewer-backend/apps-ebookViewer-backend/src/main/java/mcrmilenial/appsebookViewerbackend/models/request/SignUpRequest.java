package mcrmilenial.appsebookViewerbackend.models.request;

import lombok.Data;

import java.io.Serializable;
@Data
public class SignUpRequest implements Serializable {
    private String username;
    private String password;
    private String email;
    private int role_id;
}
