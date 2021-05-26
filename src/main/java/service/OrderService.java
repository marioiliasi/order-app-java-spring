package service;

import entity.Courier;
import entity.Order;
import enums.Vehicle;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.OrderRepository;

import javax.inject.Inject;
import java.util.*;

@Service
public class OrderService {
    @Inject
    private OrderRepository repository;

    @Transactional(readOnly = true)
    public Optional<Order> getOrderByUuid(String uuid) {
        return repository.findByUuid(uuid);
    }

    @Transactional(readOnly = true)
    public Optional<Order> createOrder(Order order) {
        order.setUuid(UUID.randomUUID().toString());
        return saveOrUpdateOrder(order);
    }

    @Transactional(readOnly = true)
    public Optional<Order> saveOrUpdateOrder(Order order) {
        repository.saveAndFlush(order);
        return repository.findByUuid(order.getUuid());
    }

    @Transactional(readOnly = true)
    public Optional<Order> assignOrder(Order order, Courier courier) {
        order.setUuid(UUID.randomUUID().toString());
        order.setCourier(courier);
        return saveOrUpdateOrder(order);
    }

    @Transactional(readOnly = true)
    public Optional<List<Order>> getLateOrders() {
        Date date = new Date();

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(date.getTime() + 10 * 60); // pickup

        Date d = cal.getTime();

        //orders that are already late
        List<Order> lateOrders = repository.findPromisedTimeOfDropOffLessThanEqual(d).orElse(new ArrayList<>());


        List<Order> orders = repository.findPromisedTimeOfDropOffGreaterThanEqual(d).orElse(new ArrayList<>());
        orders.forEach(order -> {
            Double distance = getDistance(order);
            Double duration = getDuration(distance, order.getCourier().getVehicle());
            if (cal.getTimeInMillis() + duration < order.getPromisedTimeOfDropOff().getTime()) {
                lateOrders.add(order);
            }
        });

        return Optional.of(lateOrders);
    }

    private Double getDuration(Double distance, Vehicle vehicle) {
        Double speed = 13.0;
        if (vehicle != Vehicle.BIKE) {
            speed = 25.0;
        }
        return distance / speed;//todo: probably need to multiply by some unit measure
    }

    private Double getDistance(Order order) {
        return Math.sqrt(Math.pow(order.getDropoffLat() - order.getPickUpLat(), 2) + Math.pow(order.getDropoffLon() - order.getPickUpLon(), 2));
    }

}
