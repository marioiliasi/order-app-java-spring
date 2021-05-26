package controller;

import controller.response.ResponseBuilder;
import converter.CourierConverter;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import service.CourierService;
import transport.CourierTO;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static org.springframework.http.HttpStatus.OK;

@Validated
@RestController
@RequestMapping(value = {"/courier"})
public class CourierController {

    @Inject
    protected CourierService service;

    @Inject
    protected CourierConverter converter;

    @Inject
    private ResponseBuilder<CourierTO> responseBuilder;

    @GetMapping(value = "/{id}")
    public ResponseEntity<String> getCity(HttpServletRequest request,
                                          @PathVariable("id") String id) {
        CourierTO courierTO = converter.convert(service.getCourierByUuid(id).orElse(null));
        return responseBuilder.buildSingleResponse(OK, courierTO, "courier").buildResponse();
    }

    @PostMapping()
    public ResponseEntity<String> createCity(HttpServletRequest request, @RequestBody @Valid CourierTO courier) {
        CourierTO courierTO = converter.convert(service.createCity(converter.convert(courier)).orElse(null));
        return responseBuilder.buildSingleResponse(OK, courierTO, "courier").buildResponse();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<String> updateCity(HttpServletRequest request,
                                             @PathVariable("id") String id,
                                             @RequestBody @Valid CourierTO courier) {
        CourierTO courierTO = converter.convert(service.saveOrUpdateCity(converter.convert(courier)).orElse(null));
        return responseBuilder.buildSingleResponse(OK, courierTO, "courier").buildResponse();
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteCity(HttpServletRequest request,
                                             @PathVariable("id") String id) {
        CourierTO courierTO = converter.convert(service.deleteCourier(id).orElse(null));
        return responseBuilder.buildSingleResponse(OK, courierTO, "courier").buildResponse();
    }
}
