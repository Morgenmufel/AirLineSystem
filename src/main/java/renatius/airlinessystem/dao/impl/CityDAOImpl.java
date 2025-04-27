package renatius.airlinessystem.dao.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import renatius.airlinessystem.Entity.GroundUnit.City;
import renatius.airlinessystem.Hibernate.HibernateUtil;
import renatius.airlinessystem.dao.CityDAO;

import java.util.List;

public class CityDAOImpl implements CityDAO {
    @Override
    public City findById(int id){
        return HibernateUtil.getSessionFactory().openSession().get(City.class, id);
    }

    public City findByName(String name){
        return HibernateUtil.getSessionFactory().openSession().get(City.class, name);
    }

    @Override
    public void save(City city) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(city);
        tx.commit();
        session.close();
    }

    @Override
    public void delete(City city) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.delete(city);
        tx.commit();
        session.close();
    }

    @Override
    public void update(City city) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.update(city);
        tx.commit();
        session.close();
    }

    @Override
    public List<City> findAll() {
        List<City> cities = HibernateUtil.getSessionFactory().openSession().createQuery("from City").list();
        return cities;
    }


}
