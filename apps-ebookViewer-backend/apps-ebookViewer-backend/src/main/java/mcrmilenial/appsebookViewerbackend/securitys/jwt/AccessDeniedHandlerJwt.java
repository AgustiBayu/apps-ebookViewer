package mcrmilenial.appsebookViewerbackend.securitys.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import mcrmilenial.appsebookViewerbackend.models.response.MessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class AccessDeniedHandlerJwt implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType("aplication/json");
        MessageResponse messageResponse = new MessageResponse("403", "forbidden - not admin");
        ObjectMapper objectMapper = new ObjectMapper();
        response.getWriter().write(objectMapper.writeValueAsString(messageResponse));
    }
}
