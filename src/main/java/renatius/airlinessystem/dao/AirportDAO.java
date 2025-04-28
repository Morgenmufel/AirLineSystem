package renatius.airlinessystem.dao;

import renatius.airlinessystem.Entity.GroundUnit.Airport;

import java.util.*;
public interface AirportDAO {
    public Airport findById(int id);
    public void save(Airport airport);
    public void delete(Airport airport);
    public void update(Airport airport);
    public List<Airport> findAll();
    public Airport findByName(String name);
}
