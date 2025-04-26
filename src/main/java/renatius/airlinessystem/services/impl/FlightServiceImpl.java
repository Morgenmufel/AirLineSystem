package renatius.airlinessystem.services.impl;


import renatius.airlinessystem.dao.impl.FlightDAOImpl;
import renatius.airlinessystem.services.FlightService;

public class FlightServiceImpl implements FlightService {
    public static FlightDAOImpl flightDAO;

    public FlightServiceImpl() {
        flightDAO = new FlightDAOImpl();
    }


}
