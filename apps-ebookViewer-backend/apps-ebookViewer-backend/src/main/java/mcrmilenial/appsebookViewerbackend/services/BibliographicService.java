package mcrmilenial.appsebookViewerbackend.services;

import mcrmilenial.appsebookViewerbackend.entities.Bibliographic;
import mcrmilenial.appsebookViewerbackend.exeptions.BadRequestException;
import mcrmilenial.appsebookViewerbackend.exeptions.RecouseNotFoundException;
import mcrmilenial.appsebookViewerbackend.models.response.MessageResponse;
import mcrmilenial.appsebookViewerbackend.repositorys.BibliographicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.List;
import java.util.Optional;

@Service
public class BibliographicService {
    @Autowired
    private BibliographicRepository bibliographicRepository;
    public ResponseEntity<?> create(Bibliographic bibliographic) {
        if (!StringUtils.hasText(bibliographic.getTitle())) {
            throw new BadRequestException("Title tidak boleh kosong");
        } else if (!StringUtils.hasText((bibliographic.getAuthor()))) {
            throw new BadRequestException("Nama pengarang tidak boleh kosong");
        } else if (!StringUtils.hasText((bibliographic.getFile()))) {
            throw new BadRequestException("File ebook masih kososng");
        }
        bibliographicRepository.save(bibliographic);
        return ResponseEntity.ok().body(new MessageResponse("200", "success"));
    }
    public ResponseEntity<?> findAll() {
        List<Bibliographic> bibliographics = bibliographicRepository.findAll();
        if (bibliographics.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("404", "bibliographic not found"));
        } else {
            return ResponseEntity.ok().body(bibliographics);
        }
    }
    public ResponseEntity<?> update(int biblion_id, Bibliographic bibliographic) {

        if (!StringUtils.hasText(bibliographic.getTitle())) {
            throw new BadRequestException("Title tidak boleh kosong");
        } else if (!StringUtils.hasText((bibliographic.getAuthor()))) {
            throw new BadRequestException("Nama pengarang tidak boleh kosong");
        } else if (!StringUtils.hasText((bibliographic.getFile()))) {
            throw new BadRequestException("File ebook masih kososng");
        }

        Optional<Bibliographic> optionalBibliographic = bibliographicRepository.findById(biblion_id);
        if (optionalBibliographic.isPresent()) {
            Bibliographic existingData = optionalBibliographic.get();
            existingData.setTitle(bibliographic.getTitle());
            existingData.setAuthor(bibliographic.getAuthor());
            existingData.setAbstrack(bibliographic.getAbstrack());
            existingData.setEdition(bibliographic.getEdition());
            existingData.setNo_register(bibliographic.getNo_register());
            existingData.setPublisher(bibliographic.getPublisher());
            existingData.setPublisher_year(bibliographic.getPublisher_year());
            existingData.setPublisher_place(bibliographic.getPublisher_place());
            existingData.setSubjek(bibliographic.getSubjek());
            existingData.setImage(bibliographic.getImage());
            existingData.setFile(bibliographic.getFile());
            existingData.setUser(bibliographic.getUser());

            bibliographicRepository.save(existingData);
            return ResponseEntity.ok().body(new MessageResponse("200", "success"));
        } else {
            throw new RecouseNotFoundException("id :" + biblion_id + " is not exis");
        }
    }
    public ResponseEntity<?> deleteById(int biblion_id) {
        if (bibliographicRepository.existsById(biblion_id)) {
            bibliographicRepository.deleteById(biblion_id);
            return ResponseEntity.ok().body(new MessageResponse("200", "success"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("404", "bibliographic not found"));
        }
    }
    public List<Bibliographic> searchByKeyword(String keyword) {
        return bibliographicRepository.searchByKeyword(keyword);
    }
}
