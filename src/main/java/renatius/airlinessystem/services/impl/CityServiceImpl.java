package renatius.airlinessystem.services.impl;

import renatius.airlinessystem.dao.impl.CityDAOImpl;
import renatius.airlinessystem.services.CityService;

public class CityServiceImpl implements CityService {
    public static CityDAOImpl cityDAO;
    public CityServiceImpl() {
        cityDAO = new CityDAOImpl();
    }

}
