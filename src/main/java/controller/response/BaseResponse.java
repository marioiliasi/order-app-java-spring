package controller.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;

public class BaseResponse {
    protected HttpStatus status;

    public BaseResponse(HttpStatus status) {
        this.status = status;
    }

    public String toString(Object response) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(response);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
