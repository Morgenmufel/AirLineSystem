package renatius.airlinessystem.services;

import renatius.airlinessystem.Entity.AirPlaneUnit.AirPlane;

import java.util.List;

public interface AirPlaneService {
    public List<AirPlane> getAllAirPlanes();
    public AirPlane getAirPlaneById(int id);
    public void deleteAirPlane(AirPlane airPlane);
    public void addAirPlane(AirPlane airPlane);
}
