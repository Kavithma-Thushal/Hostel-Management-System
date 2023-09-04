package lk.ijse.gdse66.hostel.dao.custom.impl;

import lk.ijse.gdse66.hostel.dao.custom.UserDAO;
import lk.ijse.gdse66.hostel.entity.User;
import lk.ijse.gdse66.hostel.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Kavithma Thushal
 * @project : Hostel-Management-System
 * @since : 2:41 PM - 8/30/2023
 **/
public class UserDAOImpl implements UserDAO {
    private Session session = null;
    private Transaction transaction = null;

    @Override
    public ArrayList<User> loadAll() {
        try {
            session = FactoryConfiguration.getInstance().getSession();
            Query query = session.createQuery("FROM User");
            ArrayList<User> userArrayList = (ArrayList<User>) query.list();
            return userArrayList;
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
    public boolean save(User user) {
        try {
            session = FactoryConfiguration.getInstance().getSession();
            transaction = session.beginTransaction();
            session.save(user);
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
    public User search(String id) {
        try {
            session = FactoryConfiguration.getInstance().getSession();
            return session.get(User.class, id);
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
    public boolean update(User user) {
        try {
            session = FactoryConfiguration.getInstance().getSession();
            transaction = session.beginTransaction();
            session.update(user);
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
            session = FactoryConfiguration.getInstance().getSession();
            transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
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
            session = FactoryConfiguration.getInstance().getSession();
            Query query = session.createQuery("SELECT id FROM User WHERE id=:code");
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
            session = FactoryConfiguration.getInstance().getSession();
            Query query = session.createQuery("FROM User ORDER BY id DESC");
            query.setMaxResults(1);
            List<User> userList = query.list();
            if (!userList.isEmpty()) {
                String currentId = userList.get(0).getId();
                String[] strings = currentId.split("U");
                int id = Integer.parseInt(strings[1]);
                id++;
                return "U" + String.format("%03d", id);
            }
            return "U001";
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
    public String count() {
        return null;
    }
}
