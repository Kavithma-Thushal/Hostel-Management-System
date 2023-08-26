package lk.ijse.gdse66.hostel.util;

import lk.ijse.gdse66.hostel.entity.Login;
import lk.ijse.gdse66.hostel.entity.Reservation;
import lk.ijse.gdse66.hostel.entity.Room;
import lk.ijse.gdse66.hostel.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.Properties;

/**
 * @author : Kavithma Thushal
 * @project : Hostel-Management-System
 * @since : 8:26 PM - 8/25/2023
 **/
public class SessionFactoryConfiguration {
    private static SessionFactoryConfiguration sessionFactoryConfiguration;
    private final SessionFactory sessionFactory;

    private SessionFactoryConfiguration() throws IOException {
        /*sessionFactory = new Configuration()
                .addAnnotatedClass(Student.class)
                *//*.addAnnotatedClass(Room.class)
                .addAnnotatedClass(Reservation.class)
                .addAnnotatedClass(Login.class)*//*
                .configure("lk/ijse/gdse66/hostel/util/hibernate.cfg.xml")
                .buildSessionFactory();*/

        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Student.class);

        Properties properties = new Properties();
        properties.load(ClassLoader.getSystemResourceAsStream("lk/ijse/gdse66/hostel/util/hibernate.properties"));
        configuration.mergeProperties(properties);

        sessionFactory = configuration.buildSessionFactory();
    }

    public static SessionFactoryConfiguration getInstance() {
        try {
            return (sessionFactoryConfiguration == null) ? sessionFactoryConfiguration = new SessionFactoryConfiguration() : sessionFactoryConfiguration;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }
}
