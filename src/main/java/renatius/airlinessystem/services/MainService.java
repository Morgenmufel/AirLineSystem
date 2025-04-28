package renatius.airlinessystem.services;

import renatius.airlinessystem.Entity.AbstractEntity.Flight;
import renatius.airlinessystem.Entity.AirPlaneUnit.AirPlane;
import renatius.airlinessystem.Entity.GroundUnit.Airport;

import java.time.LocalDateTime;

public interface MainService {
    public boolean checkWeatherStatus(Airport whereArrive, Airport whereDepart);
    public boolean LogIn(String username, String password);
    public Flight createFlight(LocalDateTime departureTime, AirPlane airPlane, String fromCity, String toCity);
    public void EditFlight(Flight flight);
}
