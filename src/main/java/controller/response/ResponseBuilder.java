package controller.response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Component()
public class ResponseBuilder<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResponseBuilder.class);

    public EntityResponse<List<T>> buildMultipleResponse(HttpStatus status, List<T> resources, String entityName) {
        return new EntityResponse<>(status, resources, entityName);
    }

    public EntityResponse<T> buildSingleResponse(HttpStatus status, T resource, String entityName) {
        return new EntityResponse<>(status, resource, entityName);
    }
}
