package lk.ijse.gdse66.hostel.dao.custom.impl;

import lk.ijse.gdse66.hostel.dao.custom.RoomDAO;
import lk.ijse.gdse66.hostel.entity.Room;

import java.util.ArrayList;

/**
 * @author : Kavithma Thushal
 * @project : Hostel-Management-System
 * @since : 12:12 AM - 8/30/2023
 **/
public class RoomDAOImpl implements RoomDAO {

    @Override
    public ArrayList<Room> loadAll() {
        return null;
    }

    @Override
    public boolean save(Room student) {
        return false;
    }

    @Override
    public Room search(String id) {
        return null;
    }

    @Override
    public boolean update(Room student) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public boolean exist(String code) {
        return false;
    }
}
