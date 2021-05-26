package controller.response;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

public class EntityResponse<T> extends BaseResponse {
    private final T response;
    private final String responseName;
    private final int limit;

    public EntityResponse(HttpStatus status, T response, String responseName) {
        super(status);
        this.response = response;
        this.responseName = responseName;
        this.limit = response != null ? 1 : 0;
    }

    public EntityResponse(HttpStatus status, T response, String responseName, int limit) {
        super(status);
        this.response = response;
        this.responseName = responseName;
        this.limit = limit;
    }

    @Override
    public String toString() {
        if (HttpStatus.NOT_FOUND != this.status) {
            if (HttpStatus.OK != this.status) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Cannot generate response properly.");
            } else if (response != null) {
                return toString(Map.of(responseName, response));
            }
        }

        return "{}";
    }

    public ResponseEntity<String> buildResponse() {
        HttpHeaders headers = new HttpHeaders();

        return new ResponseEntity<>(toString(), headers, status);
    }
}
