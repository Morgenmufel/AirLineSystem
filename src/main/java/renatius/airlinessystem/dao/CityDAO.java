package renatius.airlinessystem.dao;

import renatius.airlinessystem.Entity.GroundUnit.City;

import java.util.*;
public interface CityDAO {
    public City findById(int id);
    public void save(City city);
    public void delete(City city);
    public void update(City city);
    public List<City> findAll();
}
