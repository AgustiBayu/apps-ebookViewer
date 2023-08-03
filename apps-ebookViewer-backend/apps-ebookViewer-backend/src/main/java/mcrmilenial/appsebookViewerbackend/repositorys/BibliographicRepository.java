package mcrmilenial.appsebookViewerbackend.repositorys;

import mcrmilenial.appsebookViewerbackend.entities.Bibliographic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BibliographicRepository extends JpaRepository<Bibliographic, Integer> {
}
