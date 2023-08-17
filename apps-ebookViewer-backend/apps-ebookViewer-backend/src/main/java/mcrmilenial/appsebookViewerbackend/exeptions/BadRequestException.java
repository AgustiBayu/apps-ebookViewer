package mcrmilenial.appsebookViewerbackend.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {
    /*
        digunakan untuk message request pada suatu kondisi pengecekan
     */
    public BadRequestException(String massage) {
        super(massage);
    }
}
