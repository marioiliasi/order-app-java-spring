package converter;

import transport.CityTO;
import entity.City;

public class CityConverter {
    public CityTO convert(City city) {
        if (city == null) {
            return null;
        }

        CityTO cityTO = new CityTO();
        cityTO.setName(city.getName());
        cityTO.setUuid(city.getUuid());

        return cityTO;
    }

    public City convert(CityTO cityTO) {
        City city = new City();

        city.setName(cityTO.getName());
        city.setUuid(cityTO.getUuid());

        return city;
    }
}
