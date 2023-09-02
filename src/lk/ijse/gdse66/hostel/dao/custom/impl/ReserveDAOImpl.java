package lk.ijse.gdse66.hostel.dao.custom.impl;

import lk.ijse.gdse66.hostel.dao.custom.ReserveDAO;
import lk.ijse.gdse66.hostel.entity.Reservation;
import lk.ijse.gdse66.hostel.util.SessionFactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;

/**
 * @author : Kavithma Thushal
 * @project : Hostel-Management-System
 * @since : 2:05 PM - 9/1/2023
 **/
public class ReserveDAOImpl implements ReserveDAO {
    private Session session = null;
    private Transaction transaction = null;

    @Override
    public ArrayList<Reservation> loadAll() {
        try {
            session = SessionFactoryConfiguration.getInstance().getSession();
            Query query = session.createQuery("FROM Reservation ");
            ArrayList<Reservation> reservationArrayList = (ArrayList<Reservation>) query.list();
            return reservationArrayList;
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
    public boolean save(Reservation reservation) {
        try {
            session = SessionFactoryConfiguration.getInstance().getSession();
            transaction = session.beginTransaction();
            session.save(reservation);
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
    public Reservation search(String id) {
        try {
            session = SessionFactoryConfiguration.getInstance().getSession();
            return session.get(Reservation.class, id);
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
    public boolean update(Reservation reservation) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public boolean exist(String code) {
        try {
            session = SessionFactoryConfiguration.getInstance().getSession();
            Query query = session.createQuery("SELECT id FROM Reservation WHERE id=:code");
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
            Query query = session.createQuery("SELECT id FROM Reservation ORDER BY id DESC");
            query.setMaxResults(1);
            String currentId = (String) query.uniqueResult();
            if (currentId != null) {
                int id = Integer.parseInt(currentId.substring(3)) + 1;
                return "RES" + String.format("%03d", id);
            }
            return "RES001";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    /*@Override
    public String generateNextId() {
        try {
            session = SessionFactoryConfiguration.getInstance().getSession();
            Query query = session.createQuery("FROM Reservation ORDER BY id DESC");
            query.setMaxResults(1);
            List<Reservation> reservationList = query.list();
            if (!reservationList.isEmpty()) {
                String currentId = reservationList.get(0).getId();
                String[] strings = currentId.split("K");
                int id = Integer.parseInt(strings[1]);
                id++;
                return "K" + String.format("%03d", id);
            }
            return "K001";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }*/
}
