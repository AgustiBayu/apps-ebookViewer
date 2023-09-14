package mcrmilenial.appsebookViewerbackend.controllers;

import mcrmilenial.appsebookViewerbackend.entities.Bibliographic;
import mcrmilenial.appsebookViewerbackend.exeptions.RecouseNotFoundException;
import mcrmilenial.appsebookViewerbackend.models.response.MessageResponse;
import mcrmilenial.appsebookViewerbackend.services.BibliographicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
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
    @PreAuthorize("hasAnyAuthority('ADMIN','DOSEN','MAHASISWA')")
    public ResponseEntity<?> findAll() {
        return bibliographicService.findAll();
    }

    @PutMapping(path = "/bibliographic/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> updateBibliographic(@PathVariable("id") int biblion_id, @RequestBody Bibliographic bibliographic) {
            ResponseEntity<?> response = bibliographicService.update(biblion_id, bibliographic);
            return response;
    }

    @DeleteMapping(path = "/bibliographic/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> deleteById(@PathVariable("id") int biblion_id) {
        return bibliographicService.deleteById(biblion_id);
    }

    @GetMapping(path = "/bibliographic/tile/pengarang")
    @PreAuthorize("hasAnyAuthority('ADMIN','DOSEN','MAHASISWA')")
    public List<Bibliographic> searchByKeyword(@RequestParam String keyword) {
        return bibliographicService.searchByKeyword(keyword);
    }
}
