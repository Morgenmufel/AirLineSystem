package renatius.airlinessystem.dao.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import renatius.airlinessystem.Entity.GroundUnit.Airport;
import renatius.airlinessystem.Hibernate.HibernateUtil;
import renatius.airlinessystem.dao.AirportDAO;

import java.util.List;

public class AirportDAOImpl implements AirportDAO {
    @Override
    public Airport findById(int id){
        return HibernateUtil.getSessionFactory().openSession().get(Airport.class, id);
    }

    public Airport findByName(String name){
        return HibernateUtil.getSessionFactory().openSession().get(Airport.class, name);
    }

    @Override
    public void save(Airport airport) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(airport);
        tx.commit();
        session.close();
    }

    @Override
    public void delete(Airport airport) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.delete(airport);
        tx.commit();
        session.close();
    }

    @Override
    public void update(Airport airport) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.update(airport);
        tx.commit();
        session.close();
    }

    @Override
    public List<Airport> findAll() {
        List<Airport> cities = HibernateUtil.getSessionFactory().openSession().createQuery("from Airport").list();
        return cities;
    }


}
