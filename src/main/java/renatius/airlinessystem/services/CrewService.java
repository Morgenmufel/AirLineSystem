package renatius.airlinessystem.services;

import renatius.airlinessystem.Entity.Crew.FlightCrew;
import java.util.List;


public interface CrewService {
    public List<FlightCrew> getCrewByStatus();
    public List<FlightCrew> getAllCrew();
    public FlightCrew getCrewById(int id);
    public void deleteCrew(FlightCrew crew);
    public void addCrew(FlightCrew crew);
}
