package mcrmilenial.appsebookViewerbackend.repositorys;

import mcrmilenial.appsebookViewerbackend.entities.Bibliographic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/*
    digunakan untuk mengakses data sql pada database dengan menggunakan metode jpa
 */
@Repository
public interface BibliographicRepository extends JpaRepository<Bibliographic, Integer> {
}
