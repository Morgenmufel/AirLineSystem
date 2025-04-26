package renatius.airlinessystem.services.impl;

import renatius.airlinessystem.services.MainService;

public class MainServiceImpl implements MainService {
    public static AirPlaneServiceImpl airPlaneService;
    public static LoginServiceImpl loginService;
    public static FlightServiceImpl flightService;
    public static CrewServiceImpl crewService;
    public static CityServiceImpl cityService;

    public MainServiceImpl() {
        airPlaneService = new AirPlaneServiceImpl();
        loginService = new LoginServiceImpl();
        flightService = new FlightServiceImpl();
        crewService = new CrewServiceImpl();
        cityService = new CityServiceImpl();
    }
}
