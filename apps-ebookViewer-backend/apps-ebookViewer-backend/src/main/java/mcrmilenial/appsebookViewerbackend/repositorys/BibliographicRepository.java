package mcrmilenial.appsebookViewerbackend.repositorys;

import mcrmilenial.appsebookViewerbackend.entities.Bibliographic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
    digunakan untuk mengakses data sql pada database dengan menggunakan metode jpa
 */
@Repository
public interface BibliographicRepository extends JpaRepository<Bibliographic, Integer> {
    @Query("SELECT b FROM Bibliographic b WHERE b.title LIKE %:keyword% OR b.author LIKE %:keyword% OR b.subjek LIKE %:keyword%")
    List<Bibliographic> searchByKeyword(@Param("keyword") String keyword);
}