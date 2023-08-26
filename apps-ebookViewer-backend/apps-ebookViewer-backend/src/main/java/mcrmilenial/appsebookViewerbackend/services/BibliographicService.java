package mcrmilenial.appsebookViewerbackend.services;

import mcrmilenial.appsebookViewerbackend.entities.Bibliographic;
import mcrmilenial.appsebookViewerbackend.exeptions.BadRequestException;
import mcrmilenial.appsebookViewerbackend.models.response.MessageResponse;
import mcrmilenial.appsebookViewerbackend.repositorys.BibliographicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

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
        } else {
            bibliographicRepository.save(bibliographic);
            return ResponseEntity.ok().body(new MessageResponse("200", "success"));
        }
    }
    public ResponseEntity<?> findAll() {
        List<Bibliographic> bibliographics = bibliographicRepository.findAll();
        if(bibliographics.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("404","bibliographic not found"));
        } else {
            return ResponseEntity.ok().body(bibliographics);
        }
    }

    public ResponseEntity<?> update(Bibliographic bibliographic) {
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

    public ResponseEntity<?> deleteById(int biblion_id) {
        if(bibliographicRepository.existsById(biblion_id)) {
            bibliographicRepository.deleteById(biblion_id);
            return ResponseEntity.ok().body(new MessageResponse("200","success"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("404","bibliographic not found"));
        }
    }
    public List<Bibliographic> searchByKeyword(String keyword) {
        return bibliographicRepository.searchByKeyword(keyword);
    }
}
