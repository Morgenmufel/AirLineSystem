package renatius.airlinessystem.services.impl;

import renatius.airlinessystem.dao.impl.FlightCrewDAOImpl;
import renatius.airlinessystem.services.CrewService;

public class CrewServiceImpl implements CrewService {
    public static FlightCrewDAOImpl flightCrewDAO;

    public CrewServiceImpl() {
        flightCrewDAO = new FlightCrewDAOImpl();
    }
}
