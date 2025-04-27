package renatius.airlinessystem.services;

import renatius.airlinessystem.Entity.GroundUnit.City;

import java.util.List;

public interface CityService {
    public City findCityByName(String name);
    public List<City> getAllCities();
    public City getCityById(int id);
    public void deleteCity(City city);
    public void addCity(City city);
    public void updateCity(City city);
}
