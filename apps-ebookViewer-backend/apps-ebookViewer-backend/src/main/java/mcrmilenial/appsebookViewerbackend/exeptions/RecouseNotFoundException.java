package mcrmilenial.appsebookViewerbackend.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RecouseNotFoundException extends RuntimeException {
    /*
        digunakan untuk mengecek data yang dicari ada atau tidak
     */
    public RecouseNotFoundException(String message) {
        super(message);
    }
}
