package lk.ijse.gdse66.hostel.dao.custom.impl;

import lk.ijse.gdse66.hostel.dao.custom.RoomDAO;
import lk.ijse.gdse66.hostel.entity.Room;
import lk.ijse.gdse66.hostel.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Kavithma Thushal
 * @project : Hostel-Management-System
 * @since : 12:12 AM - 8/30/2023
 **/
public class RoomDAOImpl implements RoomDAO {
    private Session session = null;
    private Transaction transaction = null;

    @Override
    public ArrayList<Room> loadAll() {
        try {
            session = FactoryConfiguration.getInstance().getSession();
            Query query = session.createQuery("FROM Room");
            ArrayList<Room> roomArrayList = (ArrayList<Room>) query.list();
            return roomArrayList;
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
    public boolean save(Room room) {
        try {
            session = FactoryConfiguration.getInstance().getSession();
            transaction = session.beginTransaction();
            session.save(room);
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
    public Room search(String id) {
        try {
            session = FactoryConfiguration.getInstance().getSession();
            return session.get(Room.class, id);
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
    public boolean update(Room room) {
        try {
            session = FactoryConfiguration.getInstance().getSession();
            transaction = session.beginTransaction();
            session.update(room);
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
            Room room = session.get(Room.class, id);
            session.delete(room);
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
            Query query = session.createQuery("SELECT id FROM Room WHERE id=:code");
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
            Query query = session.createQuery("FROM Room ORDER BY id DESC");
            query.setMaxResults(1);
            List<Room> roomList = query.list();
            if (!roomList.isEmpty()) {
                String currentId = roomList.get(0).getId();
                String[] strings = currentId.split("R");
                int id = Integer.parseInt(strings[1]);
                id++;
                return "R" + String.format("%03d", id);
            }
            return "R001";
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
    public List<String> loadRoomIds() {
        try {
            session = FactoryConfiguration.getInstance().getSession();
            Query query = session.createQuery("SELECT id FROM Room ORDER BY id ASC");
            List<String> roomIds = (List<String>) query.list();
            return roomIds;
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
