package mcrmilenial.appsebookViewerbackend.services;

import mcrmilenial.appsebookViewerbackend.entities.Bibliographic;
import mcrmilenial.appsebookViewerbackend.exeptions.BadRequestException;
import mcrmilenial.appsebookViewerbackend.repositorys.BibliographicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class BibliographicService {
    @Autowired
    private BibliographicRepository bibliographicRepository;

    public Bibliographic create(Bibliographic bibliographic) {
        if (!StringUtils.hasText(bibliographic.getTitle())) {
            throw new BadRequestException("Title tidak boleh kosong");
        } else if (!StringUtils.hasText((bibliographic.getAuthor()))) {
            throw new BadRequestException("Nama pengarang tidak boleh kosong");
        } else if (!StringUtils.hasText((bibliographic.getFile()))) {
            throw new BadRequestException("File ebook masih kososng");
        }
        return bibliographicRepository.save(bibliographic);
    }
    public List<Bibliographic> findAll() {
        return bibliographicRepository.findAll();
    }
    public Bibliographic update(Bibliographic bibliographic) {
        if (!StringUtils.hasText(bibliographic.getTitle())) {
            throw new BadRequestException("Title tidak boleh kosong");
        } else if (!StringUtils.hasText((bibliographic.getAuthor()))) {
            throw new BadRequestException("Nama pengarang tidak boleh kosong");
        }
        return bibliographicRepository.save(bibliographic);
    }
    public void deleteById(int biblion_id) {
        bibliographicRepository.deleteById(biblion_id);
    }
    public List<Bibliographic> searchBook(String keyword) {
        List<Bibliographic> list = new ArrayList<>();
        for (Bibliographic bibliographic : bibliographicRepository.findAll()) {
            if(bibliographic.getTitle().contains(keyword) || bibliographic.getAuthor().contains(keyword)) {
                list.add(bibliographic);
            }
        }
        return list;
    }
}
