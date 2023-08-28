package lk.ijse.gdse66.hostel.dao.custom.impl;

import lk.ijse.gdse66.hostel.dao.custom.StudentDAO;
import lk.ijse.gdse66.hostel.entity.Student;
import lk.ijse.gdse66.hostel.util.SessionFactoryConfiguration;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;

/**
 * @author : Kavithma Thushal
 * @project : Hostel-Management-System
 * @since : 10:45 PM - 8/26/2023
 **/
public class StudentDAOImpl implements StudentDAO {
    private Session session = null;
    private Transaction transaction = null;

    @Override
    public boolean save(Student student) {
        try {
            session = SessionFactoryConfiguration.getInstance().getSession();
            transaction = session.beginTransaction();
            session.save(student);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public ArrayList<Student> getAll() {
        try {
            session = SessionFactoryConfiguration.getInstance().getSession();

            Query query = session.createQuery("FROM Student");      //HQL
            //Query query = session.createQuery("SELECT s FROM Student s");     //JPQL
            //SQLQuery query = session.createSQLQuery("SELECT * FROM Student").addEntity(Student.class);      //SQL
            ArrayList<Student> allStudentsEntity = (ArrayList<Student>) query.list();
            return allStudentsEntity;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Student search(String id) {
        session = SessionFactoryConfiguration.getInstance().getSession();
        return null;
    }
}
