package mcrmilenial.appsebookViewerbackend.repositorys;

import mcrmilenial.appsebookViewerbackend.entities.Roles;
import mcrmilenial.appsebookViewerbackend.models.StatusRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
/*
    digunakan untuk mengakses data sql pada database dengan menggunakan metode jpa
 */
@Repository
public interface RoleRepository extends JpaRepository<Roles, Integer> { // role identifikasi kelas dan long merupkan tipe data primerykey
    Optional<Roles> findByName(StatusRoles name); //dugunakan untuk mencari data field enum pada model yang sudah ditetapkan
}
