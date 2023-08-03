package mcrmilenial.appsebookViewerbackend.controllers;

import mcrmilenial.appsebookViewerbackend.entities.Bibliographic;
import mcrmilenial.appsebookViewerbackend.services.BibliographicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class BibliographicController {
    @Autowired
    private BibliographicService bibliographicService;

    @PostMapping(path = "/addBibliographic")
    public Bibliographic createBibliographic(@RequestBody Bibliographic bibliographic) {
        return bibliographicService.create(bibliographic);
    }
    @GetMapping(path = "/getBibliographic")
    public List<Bibliographic> findAll() {
        return bibliographicService.findAll();
    }
    @PutMapping(path = "/updateBibliographic")
    public Bibliographic updateBibliographic(@RequestBody Bibliographic bibliographic) {
        return bibliographicService.update(bibliographic);
    }
    @DeleteMapping(path = "/delete/{id}")
    public void deleteById(@PathVariable("id") int biblion_id) {
        bibliographicService.deleteById(biblion_id);
    }
}
