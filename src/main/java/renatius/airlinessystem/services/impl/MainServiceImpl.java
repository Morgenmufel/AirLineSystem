package renatius.airlinessystem.services.impl;

import renatius.airlinessystem.Entity.AbstractEntity.Flight;
import renatius.airlinessystem.Entity.AirPlaneUnit.AirPlane;
import renatius.airlinessystem.Entity.Crew.FlightCrew;
import renatius.airlinessystem.Entity.Enum.AirPlaneStatus;
import renatius.airlinessystem.Entity.Enum.CrewStatus;
import renatius.airlinessystem.Entity.Enum.WeatherStatus;
import renatius.airlinessystem.Entity.GroundUnit.Airport;
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

    public Flight createFlight(LocalDateTime departureTime, AirPlane airPlane, String fromCity, String toCity) {
        Flight flight = new Flight();
        Random random = new Random();
        Airport whereArrive = airportService.findAirportByName(toCity);
        Airport whereDepart = airportService.findAirportByName(fromCity);
        if (!checkWeatherStatus(whereArrive, whereDepart)){
            System.out.println("Погодные условия ужасны");
            return null;
        }
        LocalDateTime arrivalTime = LocalDateTime.of(LocalDate.of(departureTime.getYear(), departureTime.getMonth().getValue(), departureTime.getDayOfMonth() + random.nextInt(64)),
                LocalTime.of(random.nextInt(24), random.nextInt(60)));
        flight.setDepartureTime(departureTime);
        flight.setArrivalTime(arrivalTime);
        flight.setAirPlane(airPlane);
        flight.setFromAirport(fromCity);
        flight.setToAirport(toCity);
        airPlane.setFlight(flight);
        airPlane.setAirPlaneStatus(AirPlaneStatus.ASSIGNED);
        List<FlightCrew> flightCrewList = crewService.getCrewByStatus();
        if (flightCrewList == null) {
            return null;
        }
        flight.setFlightCrewList(flightCrewList);
        flightService.addFlight(flight);
        flightCrewList.forEach(flightCrew -> {
            flightCrew.setFlight(flight);
            flightCrew.setStatus(CrewStatus.ASSIGNED);
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
}
