package mcrmilenial.appsebookViewerbackend.controllers;

import mcrmilenial.appsebookViewerbackend.entities.Bibliographic;
import mcrmilenial.appsebookViewerbackend.entities.User;
import mcrmilenial.appsebookViewerbackend.exeptions.BadRequestException;
import mcrmilenial.appsebookViewerbackend.exeptions.RecouseNotFoundException;
import mcrmilenial.appsebookViewerbackend.models.response.MessageResponse;
import mcrmilenial.appsebookViewerbackend.repositorys.UserRepository;
import mcrmilenial.appsebookViewerbackend.services.BibliographicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api")
@PreAuthorize("isAuthenticated()")
public class BibliographicController {
    @Autowired
    private BibliographicService bibliographicService;
    @Value("${project.image}")
    private String pathImage;
    @Value("${project.file}")
    private String pathFile;

    @PostMapping(path = "/bibliographic", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> createBibliographic(@RequestParam("title") String title,
                                                 @RequestParam("author") String author,
                                                 @RequestParam("abstrack") String abstrack,
                                                 @RequestParam("edition") String edition,
                                                 @RequestParam("no_register") String no_register,
                                                 @RequestParam("publisher") String publisher,
                                                 @RequestParam("publisher_year") String publisher_year,
                                                 @RequestParam("publisher_place") String publisher_place,
                                                 @RequestParam("subjek") String subjek,
                                                 @RequestParam("image") MultipartFile image,
                                                 @RequestParam("fileEbook") MultipartFile file,
                                                 @RequestParam("user_id") int user_id) throws IOException {
        Bibliographic bibliographic = new Bibliographic();
        bibliographic.setTitle(title);
        bibliographic.setAuthor(author);
        bibliographic.setAbstrack(abstrack);
        bibliographic.setEdition(edition);
        bibliographic.setNo_register(no_register);
        bibliographic.setPublisher(publisher);
        bibliographic.setPublisher_year(publisher_year);
        bibliographic.setPublisher_place(publisher_place);
        bibliographic.setSubjek(subjek);
        bibliographic.setImage(String.valueOf(image.getOriginalFilename()));
        bibliographic.setFile(String.valueOf(file.getOriginalFilename()));
        ResponseEntity<?> response = bibliographicService.create(bibliographic, image, file, pathImage, pathFile, user_id);
        return response;
    }

    @GetMapping(path = "/bibliographic")
    @PreAuthorize("hasAnyAuthority('ADMIN','DOSEN','MAHASISWA')")
    public ResponseEntity<?> findAll() {
        return bibliographicService.findAll();
    }

    @PutMapping(path = "/bibliographic/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> updateBibliographic(@PathVariable("id") int biblion_id,
                                                 @RequestParam("title") String title,
                                                 @RequestParam("author") String author,
                                                 @RequestParam("abstrack") String abstrack,
                                                 @RequestParam("edition") String edition,
                                                 @RequestParam("no_register") String no_register,
                                                 @RequestParam("publisher") String publisher,
                                                 @RequestParam("publisher_year") String publisher_year,
                                                 @RequestParam("publisher_place") String publisher_place,
                                                 @RequestParam("subjek") String subjek,
                                                 @RequestParam("image") MultipartFile image,
                                                 @RequestParam("fileEbook") MultipartFile file,
                                                 @RequestParam("user_id") int user_id) throws IOException {
        // Validasi input dan pemanggilan service untuk mengupdate data bibliografi
        ResponseEntity<?> response = bibliographicService.update(
                biblion_id, title, author, abstrack, edition, no_register,
                publisher, publisher_year, publisher_place, subjek,
                image, file, pathImage, pathFile, user_id
        );
        return response ;
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
