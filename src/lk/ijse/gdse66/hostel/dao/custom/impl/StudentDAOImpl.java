package lk.ijse.gdse66.hostel.dao.custom.impl;

import lk.ijse.gdse66.hostel.dao.custom.StudentDAO;
import lk.ijse.gdse66.hostel.entity.Student;
import lk.ijse.gdse66.hostel.util.SessionFactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : Kavithma Thushal
 * @project : Hostel-Management-System
 * @since : 10:45 PM - 8/26/2023
 **/
public class StudentDAOImpl implements StudentDAO {
    private Session session = null;
    private Transaction transaction = null;

    @Override
    public ArrayList<Student> loadAll() {
        try {
            session = SessionFactoryConfiguration.getInstance().getSession();

            Query query = session.createQuery("FROM Student");      //HQL
            //Query query = session.createQuery("SELECT s FROM Student s");     //JPQL
            //SQLQuery query = session.createSQLQuery("SELECT * FROM Student").addEntity(Student.class);      //SQL

            ArrayList<Student> studentArrayList = (ArrayList<Student>) query.list();
            return studentArrayList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

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
    public Student search(String id) {
        try {
            session = SessionFactoryConfiguration.getInstance().getSession();
            return session.get(Student.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public boolean update(Student student) {
        try {
            session = SessionFactoryConfiguration.getInstance().getSession();
            transaction = session.beginTransaction();
            session.update(student);
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
    public boolean delete(String id) {
        try {
            session = SessionFactoryConfiguration.getInstance().getSession();
            transaction = session.beginTransaction();
            Student student = session.get(Student.class, id);
            session.delete(student);
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
    public boolean exist(String code) {
        try {
            session = SessionFactoryConfiguration.getInstance().getSession();
            Query query = session.createQuery("SELECT id FROM Student WHERE id=:code");
            String id = (String) query.setParameter("code", code).uniqueResult();
            if (id != null) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public String generateNextId() {
        try {
            session = SessionFactoryConfiguration.getInstance().getSession();
            Query query = session.createQuery("FROM Student ORDER BY id DESC");
            query.setMaxResults(1);
            List<Student> studentList = query.list();
            if (!studentList.isEmpty()) {
                String currentId = studentList.get(0).getId();
                String[] strings = currentId.split("S");
                int id = Integer.parseInt(strings[1]);
                id++;
                return "S" + String.format("%03d", id);
            }
            return "S001";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<String> loadStudentIds() {
        try {
            session = SessionFactoryConfiguration.getInstance().getSession();
            Query query = session.createQuery("SELECT id FROM Student ORDER BY id ASC");
            List<String> studentIds = (List<String>) query.list();
            return studentIds;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
