package mcrmilenial.appsebookViewerbackend.entities;

import lombok.Getter;
import lombok.Setter;
import mcrmilenial.appsebookViewerbackend.models.StatusRoles;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roles_id;
    @Enumerated(value = EnumType.STRING)
    private StatusRoles name;
}
