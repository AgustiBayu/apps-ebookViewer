package mcrmilenial.appsebookViewerbackend.repositorys;

import mcrmilenial.appsebookViewerbackend.entities.Role;
import mcrmilenial.appsebookViewerbackend.models.StatusRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
/*
    digunakan untuk mengakses data sql pada database dengan menggunakan metode jpa
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> { // role identifikasi kelas dan long merupkan tipe data primerykey
    Optional<Role> findByName(StatusRoles name); //dugunakan untuk mencari data field enum pada model yang sudah ditetapkan
}
