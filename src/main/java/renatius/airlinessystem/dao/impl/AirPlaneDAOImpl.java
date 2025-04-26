package renatius.airlinessystem.dao.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import renatius.airlinessystem.Entity.AirPlaneUnit.AirPlane;
import renatius.airlinessystem.Hibernate.HibernateUtil;
import renatius.airlinessystem.dao.AirPlaneDAO;

import java.util.ArrayList;
import java.util.List;

public class AirPlaneDAOImpl implements AirPlaneDAO {
    @Override
    public AirPlane findById(int id) {
        return HibernateUtil.getSessionFactory().openSession().get(AirPlane.class, id);
    }

    @Override
    public void save(AirPlane airPlane) {
       Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(airPlane);
        tx.commit();
        session.close();
    }

    @Override
    public void delete(AirPlane airPlane) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.delete(airPlane);
        tx.commit();
        session.close();
    }

    @Override
    public void update(AirPlane airPlane) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.update(airPlane);
        tx.commit();
        session.close();
    }

    @Override
    public List<AirPlane> findAll() {
        List<AirPlane> airPlanes = HibernateUtil.getSessionFactory().openSession().createQuery("from AirPlane").list();
        return airPlanes;
    }
}
