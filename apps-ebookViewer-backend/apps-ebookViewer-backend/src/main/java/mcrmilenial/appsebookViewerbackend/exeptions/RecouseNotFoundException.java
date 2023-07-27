package mcrmilenial.appsebookViewerbackend.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RecouseNotFoundException extends RuntimeException {
    public RecouseNotFoundException(String message) {
        super(message);
    }
}
