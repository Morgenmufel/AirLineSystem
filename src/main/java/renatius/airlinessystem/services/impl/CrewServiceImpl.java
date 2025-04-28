package renatius.airlinessystem.services.impl;

import renatius.airlinessystem.Entity.Crew.FlightCrew;
import renatius.airlinessystem.dao.impl.FlightCrewDAOImpl;
import renatius.airlinessystem.services.CrewService;

import java.util.List;

public class CrewServiceImpl implements CrewService {
    public static FlightCrewDAOImpl flightCrewDAO;

    public CrewServiceImpl() {
        flightCrewDAO = new FlightCrewDAOImpl();
    }

    public List<FlightCrew> getAllCrew(){
        return flightCrewDAO.findAll();
    }

    public FlightCrew getCrewById(int id){
        return flightCrewDAO.findById(id);
    }

    public void deleteCrew(FlightCrew crew){
        flightCrewDAO.delete(crew);
    }

    public void addCrew(FlightCrew crew){
        flightCrewDAO.save(crew);
    }

    public void updateCrew(FlightCrew crew){
        flightCrewDAO.update(crew);
    }

    @Override
    public List<FlightCrew> getCrewByStatus() {
        return flightCrewDAO.findByStatus();
    }
}
