package renatius.airlinessystem.services.impl;

import renatius.airlinessystem.dao.impl.AirPlaneDAOImpl;
import renatius.airlinessystem.services.AirPlaneService;

public class AirPlaneServiceImpl implements AirPlaneService {
    public static AirPlaneDAOImpl airPlaneDAO;
    public AirPlaneServiceImpl() {
        airPlaneDAO = new AirPlaneDAOImpl();
    }
}
