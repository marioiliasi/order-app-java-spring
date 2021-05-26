package converter;

import transport.CourierTO;
import entity.Courier;

public class CourierConverter {
    public CourierTO convert(Courier courier) {
        if (courier == null) {
            return null;
        }

        CourierTO courierTO = new CourierTO();
        courierTO.setName(courier.getName());
        courierTO.setUuid(courier.getUuid());

        return courierTO;
    }

    public Courier convert(CourierTO courierTO) {
        Courier courier = new Courier();

        courier.setName(courierTO.getName());
        courier.setUuid(courierTO.getUuid());

        return courier;
    }
}
