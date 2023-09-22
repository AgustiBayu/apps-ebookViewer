package mcrmilenial.appsebookViewerbackend.services;

import mcrmilenial.appsebookViewerbackend.entities.Bibliographic;
import mcrmilenial.appsebookViewerbackend.entities.User;
import mcrmilenial.appsebookViewerbackend.exeptions.BadRequestException;
import mcrmilenial.appsebookViewerbackend.exeptions.RecouseNotFoundException;
import mcrmilenial.appsebookViewerbackend.models.response.MessageResponse;
import mcrmilenial.appsebookViewerbackend.repositorys.BibliographicRepository;
import mcrmilenial.appsebookViewerbackend.repositorys.UserRepository;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BibliographicService {
    @Autowired
    private BibliographicRepository bibliographicRepository;
    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<?> create(Bibliographic bibliographic,
                                    MultipartFile image, MultipartFile file,
                                    String pathImage, String pathFile,
                                    int user_id) throws IOException {
        if (!StringUtils.hasText(bibliographic.getTitle())) {
            throw new BadRequestException("Title tidak boleh kosong");
        } else if (!StringUtils.hasText((bibliographic.getAuthor()))) {
            throw new BadRequestException("Nama pengarang tidak boleh kosong");
        } else if (image.isEmpty() || file.isEmpty()) {
            throw new BadRequestException("File ebook masih kososng");
        }
        String imageName = image.getOriginalFilename();
        String currDate = String.valueOf(new Date().getDate() + new Date().getTime());
        String imagePath = pathImage + File.separator + currDate + imageName;
        File images = new File(pathImage);
        if (!images.exists()) {
            images.mkdir();
        }
        Files.copy(image.getInputStream(), Paths.get(imagePath));

        String fileName = file.getOriginalFilename();
        String filePath = pathFile + File.separator + currDate + fileName;

        File files = new File(pathFile);
        if (!files.exists()) {
            files.mkdir();
        }
        Files.copy(file.getInputStream(), Paths.get(filePath));
        Optional<User> userOptional = userRepository.findById(user_id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            bibliographic.setUser(user);
        } else {
            throw new BadRequestException("user_id is not found");
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
    public ResponseEntity<?> update( int biblion_id,
                                     String title,
                                     String author,
                                     String abstrack,
                                     String edition,
                                     String no_register,
                                     String publisher,
                                     String publisher_year,
                                     String publisher_place,
                                     String subjek,
                                     MultipartFile image,
                                     MultipartFile file,
                                     String pathImage,
                                     String pathFile,
                                     int user_id) throws IOException {
        String currDate = String.valueOf(new Date().getDate() + new Date().getTime());
        Optional<Bibliographic> optionalBibliographic = bibliographicRepository.findById(biblion_id);
        if (optionalBibliographic.isPresent()) {
            Bibliographic existingData = optionalBibliographic.get();
            // Update data bibliografi dengan data yang baru
            existingData.setTitle(title);
            existingData.setAuthor(author);
            existingData.setAbstrack(abstrack);
            existingData.setEdition(edition);
            existingData.setNo_register(no_register);
            existingData.setPublisher(publisher);
            existingData.setPublisher_year(publisher_year);
            existingData.setPublisher_place(publisher_place);
            existingData.setSubjek(subjek);

            // Tambahkan logika untuk mengganti file gambar dan file ebook jika diperlukan
            if (!image.isEmpty()) {
                String imageName = image.getOriginalFilename();
                String imagePath = pathImage + File.separator + currDate + imageName;
                File images = new File(pathImage);
                if (!images.exists()) {
                    images.mkdir();
                }
                Files.copy(image.getInputStream(), Paths.get(imagePath));
                existingData.setImage(currDate + imageName);
            }

            if (!file.isEmpty()) {
                String fileName = file.getOriginalFilename();
                String filePath = pathFile + File.separator + currDate + fileName;
                File files = new File(pathFile);
                if (!files.exists()) {
                    files.mkdir();
                }
                Files.copy(file.getInputStream(), Paths.get(filePath));
                existingData.setFile(currDate + fileName);
            }

            // Simpan perubahan ke dalam basis data
            Optional<User> userOptional = userRepository.findById(user_id);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                existingData.setUser(user);
            } else {
                throw new BadRequestException("user_id is not found");
            }
            bibliographicRepository.save(existingData);
            return ResponseEntity.ok().body(new MessageResponse("200", "success"));
        } else {
            throw new RecouseNotFoundException("id :" + biblion_id + " is not exist");
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
