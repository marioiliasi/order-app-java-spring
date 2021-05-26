package converter;

import transport.OrderTO;
import entity.Order;

public class OrderConverter {
    public OrderTO convert(Order order) {
        if (order == null) {
            return null;
        }

        OrderTO orderTO = new OrderTO();
        orderTO.setUuid(order.getUuid());

        return orderTO;
    }

    public Order convert(OrderTO orderTO) {
        Order order = new Order();

        order.setUuid(orderTO.getUuid());

        return order;
    }
}
