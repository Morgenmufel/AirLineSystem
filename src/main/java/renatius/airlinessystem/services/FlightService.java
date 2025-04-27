package renatius.airlinessystem.services;

import renatius.airlinessystem.Entity.AbstractEntity.Flight;

import java.util.List;

public interface FlightService {
    public List<Flight> getAllFlights();
    public Flight getFlightById(int id);
    public void deleteFlight(Flight flight);
    public void addFlight(Flight flight);
    public void updateFlight(Flight flight);
}
