package service;

import entity.Courier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.CourierRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class CourierService {
    @Autowired
    private CourierRepository courierRepository;

    @Transactional(readOnly = true)
    public Optional<Courier> getCourierByUuid(String uuid) {
        return courierRepository.findByUuid(uuid);
    }

    @Transactional(readOnly = true)
    public Optional<Courier> createCity(Courier city) {
        city.setUuid(UUID.randomUUID().toString());
        return saveOrUpdateCity(city);
    }

    @Transactional(readOnly = true)
    public Optional<Courier> saveOrUpdateCity(Courier city) {
        Courier savedCourier = courierRepository.saveAndFlush(city);
        return courierRepository.findByUuid(city.getUuid());
    }

    @Transactional(readOnly = true)
    public Optional<Courier> deleteCourier(String uuid) {
        Courier courier = courierRepository.findByUuid(uuid).orElse(null);
//        courier.setStatus();
        courierRepository.saveAndFlush(courier);
        return courierRepository.findByUuid(uuid);
    }
}
