package renatius.airlinessystem.services.impl;

import renatius.airlinessystem.Entity.GroundUnit.Airport;
import renatius.airlinessystem.dao.impl.AirportDAOImpl;
import renatius.airlinessystem.services.AirportService;

import java.util.List;

public class AirportServiceImpl implements AirportService {
    public static AirportDAOImpl airportDAO;
    public AirportServiceImpl() {
        airportDAO = new AirportDAOImpl();
    }
    public List<Airport> getAllAirport(){
        return airportDAO.findAll();
    }

    @Override
    public Airport getAirportById(int id) {
        return airportDAO.findById(id);
    }

    public Airport getCityById(int id){
        return airportDAO.findById(id);
    }

    public void deleteAirport(Airport airport){
        airportDAO.delete(airport);
    }

    public void addAirport(Airport airport){
        airportDAO.save(airport);
    }

    public void updateAirport(Airport airport){
        airportDAO.update(airport);
    }

    @Override
    public Airport findAirportByName(String name) {
        return airportDAO.findByName(name);
    }
}
