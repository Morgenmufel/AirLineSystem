package renatius.airlinessystem.Hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import renatius.airlinessystem.Entity.AbstractEntity.Flight;
import renatius.airlinessystem.Entity.AirPlaneUnit.AirPlane;
import renatius.airlinessystem.Entity.Crew.*;
import renatius.airlinessystem.Entity.GroundUnit.Airport;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    public HibernateUtil(){}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(FlightCrew.class)
                        .addAnnotatedClass(FirstOfficer.class)
                        .addAnnotatedClass(SecondOfficer.class)
                        .addAnnotatedClass(ThirdOfficer.class)
                        .addAnnotatedClass(ReliefCrew.class)
                        .addAnnotatedClass(Purser.class)
                        .addAnnotatedClass(FlightMedic.class)
                        .addAnnotatedClass(FlightEngineer.class)
                        .addAnnotatedClass(FlightAttendant.class)
                        .addAnnotatedClass(Captain.class)
                        .addAnnotatedClass(AirborneSensorOperator.class)
                        .addAnnotatedClass(Airport.class)
                        .addAnnotatedClass(Flight.class)
                        .addAnnotatedClass(AirPlane.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());
            }catch (Exception e) {
                System.out.println("HibernateUtil Exception: " + e);
            }
        }
        return sessionFactory;
    }
}
