package renatius.airlinessystem.services.impl;

import renatius.airlinessystem.Entity.AbstractEntity.Flight;
import renatius.airlinessystem.Entity.AirPlaneUnit.AirPlane;
import renatius.airlinessystem.Entity.Crew.*;
import renatius.airlinessystem.Entity.Enum.WeatherStatus;
import renatius.airlinessystem.Entity.GroundUnit.Airport;
import renatius.airlinessystem.Hibernate.HibernateUtil;
import renatius.airlinessystem.services.MainService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.time.LocalDateTime;

public class MainServiceImpl implements MainService {
    public static AirPlaneServiceImpl airPlaneService;
    public static LoginServiceImpl loginService;
    public static FlightServiceImpl flightService;
    public static CrewServiceImpl crewService;
    public static AirportServiceImpl airportService;

    public MainServiceImpl() {
        airPlaneService = new AirPlaneServiceImpl();
        loginService = new LoginServiceImpl();
        flightService = new FlightServiceImpl();
        crewService = new CrewServiceImpl();
        airportService = new AirportServiceImpl();
    }

    public boolean LogIn(String username, String password) {
        if (loginService.login(username, password)) {
            return true;
        }
        else return false;
    }

    public Flight createFlight(LocalDateTime departureTime, AirPlane airPlane, String fromAirport, String toAirport) {
        Flight flight = new Flight();
        Random random = new Random();
        List<FlightCrew> flightCrewList = null;
        Airport whereArrive = null;
        Airport whereDepart = null;
        try {
            whereArrive = airportService.findAirportByName(toAirport);
            whereDepart = airportService.findAirportByName(fromAirport);
            flightCrewList = getFlightCrews(crewService.getCrewByStatus());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (flightCrewList == null) {
            return null;
        }
            if (!checkWeatherStatus(whereArrive, whereDepart)){
            System.out.println("Погодные условия ужасны");
            return null;
        }
        LocalDateTime arrivalTime = LocalDateTime.of(LocalDate.of(departureTime.getYear(), departureTime.getMonth().getValue(), departureTime.getDayOfMonth()),
                LocalTime.of(random.nextInt(24), random.nextInt(60)));
        flight.setDepartureTime(departureTime);
        flight.setArrivalTime(arrivalTime);
        flight.setAirPlane(airPlane);
        flight.setFromAirport(fromAirport);
        flight.setToAirport(toAirport);
        airPlane.setFlight(flight);
        flightService.addFlight(flight);
        airPlane.setAirPlaneStatus("ASSIGNED");
        flight.setFlightCrewList(flightCrewList);
        flightCrewList.forEach(flightCrew -> {
            flightCrew.setFlight(flight);
            flightCrew.setStatus("ASSIGNED");
            crewService.updateCrew(flightCrew);
        });
        airPlaneService.updateAirPlane(airPlane);
        return flight;
    }

    public void EditFlight(Flight flight) {
        flightService.updateFlight(flight);
    }


    @Override
    public boolean checkWeatherStatus(Airport whereArrive, Airport whereDepart) {
        if(whereArrive.getWeatherStatus().equals(WeatherStatus.STRONGWIND)) return false;
        else if(whereDepart.getWeatherStatus().equals(WeatherStatus.STRONGWIND)) return false;
        else if(whereArrive.getWeatherStatus().equals(WeatherStatus.STRONGRAIN)) return false;
        else if (whereDepart.getWeatherStatus().equals(WeatherStatus.STRONGRAIN)) return false;
        else return true;
    }


    public List<FlightCrew> getFlightCrews(List<FlightCrew> allCrews){
        List<FlightCrew> flightCrewList = null;
        try {
            FlightCrew captain = HibernateUtil.getSessionFactory().openSession().createQuery("from FlightCrew where post = 'Captain'", Captain.class).getSingleResult();
            FlightCrew firstOfficer = HibernateUtil.getSessionFactory().openSession().createQuery("from FlightCrew where post = 'FirstOfficer'", FirstOfficer.class).getSingleResult();
            FlightCrew secondOfficer = HibernateUtil.getSessionFactory().openSession().createQuery("from FlightCrew where post = 'SecondOfficer'", SecondOfficer.class).getSingleResult();
            FlightCrew thirdOfficer = HibernateUtil.getSessionFactory().openSession().createQuery("from FlightCrew where post = 'ThirdOfficer'", ThirdOfficer.class).getSingleResult();
            FlightCrew purser = HibernateUtil.getSessionFactory().openSession().createQuery("from FlightCrew where post = 'Purser'", Purser.class).getSingleResult();
            FlightCrew flightAttendant = HibernateUtil.getSessionFactory().openSession().createQuery("from FlightCrew where post = 'FlightAttendant'", FlightAttendant.class).getSingleResult();
            FlightCrew flightEngineering = HibernateUtil.getSessionFactory().openSession().createQuery("from FlightCrew where post = 'FlightEngineer'", FlightEngineer.class).setMaxResults(1).getSingleResult();
            FlightCrew flightMedic = HibernateUtil.getSessionFactory().openSession().createQuery("from FlightCrew where post = 'FlightMedic'", FlightMedic.class).getSingleResult();
            FlightCrew reliefCrew = HibernateUtil.getSessionFactory().openSession().createQuery("from FlightCrew where post = 'ReliefCrew'", ReliefCrew.class).getSingleResult();
            FlightCrew airBornSensorOperator = HibernateUtil.getSessionFactory().openSession().createQuery("from FlightCrew where post = 'AirborneSensorOperator'", AirborneSensorOperator.class).getSingleResult();

            flightCrewList = new ArrayList<>(){{
                add(captain);
                add(firstOfficer);
                add(secondOfficer);
                add(thirdOfficer);
                add(thirdOfficer);
                add(purser);
                add(flightAttendant);
                add(flightEngineering);
                add(flightMedic);
                add(reliefCrew);
            }};

        }catch (NullPointerException e){
            e.printStackTrace();
        }
        return flightCrewList;
    }
}
