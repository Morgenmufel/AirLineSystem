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
        List<AirPlane> airPlanes = HibernateUtil.getSessionFactory().openSession().createQuery("from AirPlane", AirPlane.class).list();
        return airPlanes;
    }
    @Override
    public List<AirPlane> findByStatus() {
        List<AirPlane> airPlanes = HibernateUtil.getSessionFactory().openSession().createQuery("from AirPlane where airPlaneStatus = :status", AirPlane.class).setParameter("status", "FREE").list();
                return airPlanes;
    }

    @Override
    public AirPlane findByName(String name) {
        AirPlane airPlane = HibernateUtil.getSessionFactory().openSession().createQuery("from AirPlane where planeName = :name", AirPlane.class).setParameter("name", name).setMaxResults(1).getSingleResult();
        return airPlane;
    }
}
