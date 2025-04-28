package renatius.airlinessystem.services;

import renatius.airlinessystem.Entity.GroundUnit.Airport;

import java.util.List;

public interface AirportService {
    public Airport findAirportByName(String name);
    public List<Airport> getAllAirport();
    public Airport getAirportById(int id);
    public void deleteAirport(Airport airport);
    public void addAirport(Airport airport);
    public void updateAirport(Airport airport);
}
