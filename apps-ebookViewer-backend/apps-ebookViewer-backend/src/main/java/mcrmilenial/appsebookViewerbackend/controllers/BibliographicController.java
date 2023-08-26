package mcrmilenial.appsebookViewerbackend.controllers;

import mcrmilenial.appsebookViewerbackend.entities.Bibliographic;
import mcrmilenial.appsebookViewerbackend.services.BibliographicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> createBibliographic(@RequestBody Bibliographic bibliographic) {
        return bibliographicService.create(bibliographic);
    }
    @GetMapping(path = "/bibliographic")
    @PreAuthorize("hasAnyRole('ADMIN','DOSEN','MAHASISWA')")
    public ResponseEntity<?> findAll() {
        return bibliographicService.findAll();
    }
    @PutMapping(path = "/bibliographic/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> updateBibliographic(@PathVariable("id") @RequestBody Bibliographic bibliographic) {
        return bibliographicService.update(bibliographic);
    }
    @DeleteMapping(path = "/bibliographic/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> deleteById(@PathVariable("id") int biblion_id) {
        return bibliographicService.deleteById(biblion_id);
    }
    @GetMapping(path = "/bibliographic/tile/pengarang")
    @PreAuthorize("hasAnyRole('ADMIN','DOSEN','MAHASISWA')")
    public List<Bibliographic> searchByKeyword(@RequestParam String keyword) {
        return bibliographicService.searchByKeyword(keyword);
    }
}
