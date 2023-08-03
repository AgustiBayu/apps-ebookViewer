package mcrmilenial.appsebookViewerbackend.services;

import mcrmilenial.appsebookViewerbackend.entities.Bibliographic;
import mcrmilenial.appsebookViewerbackend.repositorys.BibliographicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BibliographicService {
    @Autowired
    private BibliographicRepository bibliographicRepository;

    public Bibliographic create(Bibliographic bibliographic) {
        return bibliographicRepository.save(bibliographic);
    }
    public List<Bibliographic> findAll() {
        return bibliographicRepository.findAll();
    }
    public Bibliographic update(Bibliographic bibliographic) {
        return bibliographicRepository.save(bibliographic);
    }
    public void deleteById(int biblion_id) {
        bibliographicRepository.deleteById(biblion_id);
    }
}
