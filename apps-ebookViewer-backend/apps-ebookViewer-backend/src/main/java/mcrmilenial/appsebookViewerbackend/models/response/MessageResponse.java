package mcrmilenial.appsebookViewerbackend.models.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MessageResponse {
    private String status;
    private String message;
}
