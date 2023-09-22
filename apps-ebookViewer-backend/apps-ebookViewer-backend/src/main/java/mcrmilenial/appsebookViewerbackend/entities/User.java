package mcrmilenial.appsebookViewerbackend.entities;

import lombok.*;
import mcrmilenial.appsebookViewerbackend.models.StatusRoles;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@Getter
@Setter

@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;
    private String username;
    private String password;
    private String email;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Roles role;

    //constructor digunakan untuk generet data registrasi yang diberikan pada akses signup register
    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User() {
    }

    //    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
//    inverseJoinColumns = @JoinColumn(name = "roles_id", referencedColumnName = "roles_id"))
//    private List<Role> roles = new ArrayList<>();

//    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinColumn(name = "roles_id")
//    private Role roles;

}
