package mcrmilenial.appsebookViewerbackend.controllers;

import mcrmilenial.appsebookViewerbackend.entities.Bibliographic;
import mcrmilenial.appsebookViewerbackend.services.BibliographicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
@PreAuthorize("isAuthenticated()")
public class BibliographicController {
    @Autowired
    private BibliographicService bibliographicService;

    @PostMapping(path = "/bibliographic")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Bibliographic createBibliographic(@RequestBody Bibliographic bibliographic) {
        return bibliographicService.create(bibliographic);
    }
    @GetMapping(path = "/bibliographic")
    @PreAuthorize("hasAnyRole('ADMIN','DOSEN','MAHASISWA')")
    public List<Bibliographic> findAll() {
        return bibliographicService.findAll();
    }
    @PutMapping(path = "/bibliographic/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public Bibliographic updateBibliographic(@PathVariable("id") @RequestBody Bibliographic bibliographic) {
        return bibliographicService.update(bibliographic);
    }
    @DeleteMapping(path = "/bibliographic/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteById(@PathVariable("id") int biblion_id) {
        bibliographicService.deleteById(biblion_id);
    }
    @GetMapping(path = "/bibliographic/tile/pengarang")
    @PreAuthorize("hasAnyRole('ADMIN','DOSEN','MAHASISWA')")
    public List<Bibliographic> searchBook(@RequestParam String keyword) {
        return bibliographicService.searchBook(keyword);
    }
}
