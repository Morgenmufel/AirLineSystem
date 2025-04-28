package renatius.airlinessystem.dao;

import renatius.airlinessystem.Entity.Crew.FlightCrew;

import java.util.List;

public interface FlightCrewDAO {
    public FlightCrew findById(int id);
    public void save(FlightCrew flightCrew);
    public void delete(FlightCrew flightCrew);
    public void update(FlightCrew flightCrew);
    public List<FlightCrew> findAll();
    public List<FlightCrew> findByStatus();
}
