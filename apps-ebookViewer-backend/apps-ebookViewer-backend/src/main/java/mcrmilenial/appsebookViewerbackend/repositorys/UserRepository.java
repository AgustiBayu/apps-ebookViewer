package mcrmilenial.appsebookViewerbackend.repositorys;

import mcrmilenial.appsebookViewerbackend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
/*
    digunakan untuk mengakses data sql pada database dengan menggunakan metode jpa
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username); // digunakan untuk mencari berdasarkan dengan username
    Boolean existsByUsername(String username); // digunakan untuk mengecek apakan username ada atau tidak
    Boolean existsByEmail(String email); // digunakan untuk mengecek apakah email ada atau tidak
}
