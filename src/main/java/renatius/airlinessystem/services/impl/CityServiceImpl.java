package renatius.airlinessystem.services.impl;

import renatius.airlinessystem.Entity.AirPlaneUnit.AirPlane;
import renatius.airlinessystem.Entity.GroundUnit.City;
import renatius.airlinessystem.dao.impl.CityDAOImpl;
import renatius.airlinessystem.services.CityService;

import java.util.List;

public class CityServiceImpl implements CityService {
    public static CityDAOImpl cityDAO;
    public CityServiceImpl() {
        cityDAO = new CityDAOImpl();
    }
    public List<City> getAllCities(){
        return cityDAO.findAll();
    }

    public City getCityById(int id){
        return cityDAO.findById(id);
    }

    public void deleteCity(City city){
        cityDAO.delete(city);
    }

    public void addCity(City city){
        cityDAO.save(city);
    }

    public void updateCity(City city){
        cityDAO.update(city);
    }

    @Override
    public City findCityByName(String name) {
        return cityDAO.findByName(name);
    }
}
