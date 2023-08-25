package lk.ijse.gdse66.hostel.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author : Kavithma Thushal
 * @project : Hostel-Management-System
 * @since : 8:26 PM - 8/25/2023
 **/
public class SessionFactoryConfiguration {
    private static SessionFactoryConfiguration sessionFactoryConfiguration;
    private final SessionFactory sessionFactory;

    private SessionFactoryConfiguration() {
        sessionFactory = new Configuration()
                .addAnnotatedClass(Customer.class)
                .configure("lk/ijse/gdse66/hostel/util/hibernate.properties")
                .buildSessionFactory();
    }

    public static SessionFactoryConfiguration getInstance() {
        return (sessionFactoryConfiguration == null) ? sessionFactoryConfiguration = new SessionFactoryConfiguration() : sessionFactoryConfiguration;
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }
}
