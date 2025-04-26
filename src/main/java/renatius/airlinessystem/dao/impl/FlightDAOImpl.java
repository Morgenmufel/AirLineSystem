package renatius.airlinessystem.dao.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import renatius.airlinessystem.Entity.AbstractEntity.Flight;
import renatius.airlinessystem.Hibernate.HibernateUtil;
import renatius.airlinessystem.dao.FlightDAO;

import java.util.List;

public class FlightDAOImpl implements FlightDAO {
    @Override
    public Flight findById(int id) {
        return HibernateUtil.getSessionFactory().openSession().get(Flight.class, id);
    }

    @Override
    public void save(Flight flight) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(flight);
        tx.commit();
        session.close();
    }

    @Override
    public void delete(Flight flight) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.delete(flight);
        tx.commit();
        session.close();
    }

    @Override
    public void update(Flight flight) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.update(flight);
        tx.commit();
        session.close();
    }

    @Override
    public List<Flight> findAll() {
        List<Flight> flights = HibernateUtil.getSessionFactory().openSession().createQuery("from Flight ").list();
        return flights;
    }
}
