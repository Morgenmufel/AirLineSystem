package renatius.airlinessystem.dao;

import renatius.airlinessystem.Entity.AbstractEntity.Flight;
import renatius.airlinessystem.Entity.GroundUnit.City;

import java.util.List;

public interface FlightDAO {
    public Flight findById(int id);
    public void save(Flight flight);
    public void delete(Flight flight);
    public void update(Flight flight);
    public List<Flight> findAll();
}
