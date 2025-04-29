package renatius.airlinessystem.dao;

import renatius.airlinessystem.Entity.AirPlaneUnit.AirPlane;

import java.util.List;

public interface AirPlaneDAO {
    public AirPlane findById(int id);
    public void save(AirPlane airPlane);
    public void delete(AirPlane airPlane);
    public void update(AirPlane airPlane);
    public List<AirPlane> findAll();
    public List<AirPlane> findByStatus();
    public AirPlane findByName(String name);
}
