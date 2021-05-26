package controller;

import controller.response.ResponseBuilder;
import converter.CityConverter;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import service.CityService;
import transport.CityTO;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static org.springframework.http.HttpStatus.OK;

@Validated
@RestController
@RequestMapping(value = {"/city"})
public class CityController {

    @Inject
    protected CityService service;

    @Inject
    protected CityConverter converter;

    @Inject
    private ResponseBuilder<CityTO> responseBuilder;

    @GetMapping(value = "/{id}")
    public ResponseEntity<String> getCity(HttpServletRequest request,
                                          @PathVariable("id") String id) {
        CityTO city = converter.convert(service.getCityByUuid(id).orElse(null));
        return responseBuilder.buildSingleResponse(OK, city, "city").buildResponse();
    }

    @PostMapping()
    public ResponseEntity<String> createCity(HttpServletRequest request, @RequestBody @Valid CityTO city) {
        CityTO cityTO = converter.convert(service.createCity(converter.convert(city)).orElse(null));
        return responseBuilder.buildSingleResponse(OK, cityTO, "city").buildResponse();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<String> updateCity(HttpServletRequest request,
                                             @PathVariable("id") String id,
                                             @RequestBody @Valid CityTO city) {
        CityTO cityTO = converter.convert(service.saveOrUpdateCity(converter.convert(city)).orElse(null));
        return responseBuilder.buildSingleResponse(OK, cityTO, "city").buildResponse();
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteCity(HttpServletRequest request,
                                             @PathVariable("id") String id) {
        CityTO cityTO = converter.convert(service.deleteCity(id).orElse(null));
        return responseBuilder.buildSingleResponse(OK, cityTO, "city").buildResponse();
    }
}
