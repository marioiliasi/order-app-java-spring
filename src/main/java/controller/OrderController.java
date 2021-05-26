package controller;

import controller.response.ResponseBuilder;
import converter.OrderConverter;
import entity.Courier;
import entity.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import service.CourierService;
import service.OrderService;
import transport.OrderTO;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.OK;

@Validated
@RestController
@RequestMapping(value = {"/order"})
public class OrderController {

    @Inject
    protected OrderService service;

    @Inject
    protected OrderConverter converter;
    @Inject
    protected CourierService courierService;

    @Inject
    private ResponseBuilder<OrderTO> responseBuilder;

    @PostMapping()
    public ResponseEntity<String> createOrder(HttpServletRequest request, @RequestBody @Valid OrderTO order) {
        OrderTO orderTO = converter.convert(service.createOrder(converter.convert(order)).orElse(null));
        return responseBuilder.buildSingleResponse(OK, orderTO, "order").buildResponse();
    }

    @PostMapping(value = "/assign")
    public ResponseEntity<String> getCity(HttpServletRequest request,
                                          @PathVariable("id") String id,
                                          @PathVariable("courierId") String courierId) {
        Courier courier = courierService.getCourierByUuid(courierId).orElse(null);
        Order order = service.getOrderByUuid(id).orElse(null);
        //TODO: add null checks
        OrderTO orderTO = converter.convert(service.assignOrder(order, courier).orElse(null));
        return responseBuilder.buildSingleResponse(OK, orderTO, "order").buildResponse();
    }

    @GetMapping(value = "/late")
    public ResponseEntity<String> getRunningLateOrders(HttpServletRequest request) {
        List<Order> orders = service.getLateOrders().orElse(new ArrayList<>());

        List<OrderTO> ordersTO = orders.stream().map(order -> converter.convert(order)).collect(Collectors.toList());
        return responseBuilder.buildMultipleResponse(OK, ordersTO, "classes").buildResponse();
    }
}
