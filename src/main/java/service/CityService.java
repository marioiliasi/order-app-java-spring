package service;

import entity.City;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.CityRepository;

import javax.inject.Inject;
import java.util.Optional;
import java.util.UUID;

@Service
public class CityService {
    @Inject
    private CityRepository cityRepository;

    @Transactional(readOnly = true)
    public Optional<City> getCityByUuid(String uuid) {
        return cityRepository.findByUuid(uuid);
    }

    @Transactional(readOnly = true)
    public Optional<City> createCity(City city) {
        city.setUuid(UUID.randomUUID().toString());
        return saveOrUpdateCity(city);
    }

    @Transactional(readOnly = true)
    public Optional<City> saveOrUpdateCity(City city) {
        cityRepository.saveAndFlush(city);
        return cityRepository.findByUuid(city.getUuid());
    }

    @Transactional(readOnly = true)
    public Optional<City> deleteCity(String uuid) {
        City city = cityRepository.findByUuid(uuid).orElse(null);
//        city.setDeletedDate();
        cityRepository.saveAndFlush(city);
        return cityRepository.findByUuid(uuid);
    }
}
