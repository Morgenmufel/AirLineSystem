package renatius.airlinessystem.dao.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import renatius.airlinessystem.Entity.Crew.FlightCrew;
import renatius.airlinessystem.Hibernate.HibernateUtil;
import renatius.airlinessystem.dao.FlightCrewDAO;

import java.util.List;

public class FlightCrewDAOImpl implements FlightCrewDAO {
    @Override
    public FlightCrew findById(int id) {
        return HibernateUtil.getSessionFactory().openSession().get(FlightCrew.class, id);
    }

    @Override
    public void save(FlightCrew flightCrew) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(flightCrew);
        tx.commit();
        session.close();
    }

    @Override
    public void delete(FlightCrew flightCrew) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.delete(flightCrew);
        tx.commit();
        session.close();
    }

    @Override
    public void update(FlightCrew flightCrew) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.update(flightCrew);
        tx.commit();
        session.close();
    }

    @Override
    public List<FlightCrew> findAll() {
        List<FlightCrew> flightCrewList = HibernateUtil.getSessionFactory().openSession().createQuery("from FlightCrew").list();
        return flightCrewList;
    }

    @Override
    public List<FlightCrew> findByStatus() {
        List<FlightCrew> flightCrewList = HibernateUtil.getSessionFactory().openSession().createQuery("from FlightCrew where status = 'FREE'").list();
        return flightCrewList;
    }
}
