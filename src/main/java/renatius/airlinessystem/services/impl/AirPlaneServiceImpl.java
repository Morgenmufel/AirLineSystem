package renatius.airlinessystem.services.impl;

import renatius.airlinessystem.Entity.AirPlaneUnit.AirPlane;
import renatius.airlinessystem.dao.impl.AirPlaneDAOImpl;
import renatius.airlinessystem.services.AirPlaneService;
import java.util.*;

public class AirPlaneServiceImpl implements AirPlaneService {

    public static AirPlaneDAOImpl airPlaneDAO;

    public AirPlaneServiceImpl() {
        airPlaneDAO = new AirPlaneDAOImpl();
    }

    public List<AirPlane> getAllAirPlanes(){
        return airPlaneDAO.findAll();
    }

    public AirPlane getAirPlaneById(int id){
        return airPlaneDAO.findById(id);
    }

    public void deleteAirPlane(AirPlane airPlane){
        airPlaneDAO.delete(airPlane);
    }

    public void addAirPlane(AirPlane airPlane){
        airPlaneDAO.save(airPlane);
    }

    @Override
    public List<AirPlane> findByStatus() {
        return airPlaneDAO.findByStatus();
    }

    @Override
    public AirPlane findByName(String name) {
        return airPlaneDAO.findByName(name);
    }

    public void updateAirPlane(AirPlane airPlane){
        airPlaneDAO.update(airPlane);
    }
}
