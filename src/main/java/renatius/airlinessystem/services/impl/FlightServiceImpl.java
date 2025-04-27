package renatius.airlinessystem.services.impl;


import renatius.airlinessystem.Entity.AbstractEntity.Flight;
import renatius.airlinessystem.Entity.Crew.FlightCrew;
import renatius.airlinessystem.dao.impl.FlightDAOImpl;
import renatius.airlinessystem.services.FlightService;

import java.util.List;

public class FlightServiceImpl implements FlightService {
    public static FlightDAOImpl flightDAO;

    public FlightServiceImpl() {
        flightDAO = new FlightDAOImpl();
    }

    public List<Flight> getAllFlights(){
        return flightDAO.findAll();
    }

    public Flight getFlightById(int id){
        return flightDAO.findById(id);
    }

    public void deleteFlight(Flight flight){
        flightDAO.delete(flight);
    }

    public void addFlight(Flight flight){
        flightDAO.save(flight);
    }

    public void updateFlight(Flight flight){
        flightDAO.update(flight);
    }

}
