package lk.ijse.gdse66.hostel.dao.custom.impl;

import lk.ijse.gdse66.hostel.dao.custom.StudentDAO;
import lk.ijse.gdse66.hostel.entity.Student;
import lk.ijse.gdse66.hostel.util.SessionFactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @author : Kavithma Thushal
 * @project : Hostel-Management-System
 * @since : 10:45 PM - 8/26/2023
 **/
public class StudentDAOImpl implements StudentDAO {

    @Override
    public boolean save(Student student) {
        Session session = SessionFactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(student);
        transaction.commit();
        session.close();
        return true;
    }
}
