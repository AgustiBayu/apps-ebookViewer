package mcrmilenial.appsebookViewerbackend.securitys.jwt;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandlerJwt {
    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException ex) {
        String errorMessage = "Permintaan tidak valid. Harap periksa kembali data yang Anda kirim.";
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
}
