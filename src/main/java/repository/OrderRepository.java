package repository;

import entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByUuid(String uuid);

    Optional<List<Order>> findPromisedTimeOfDropOffLessThanEqual(Date date);

    Optional<List<Order>> findPromisedTimeOfDropOffGreaterThanEqual(Date date);
}
