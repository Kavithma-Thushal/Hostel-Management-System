package lk.ijse.gdse66.hostel.dao.custom.impl;

import lk.ijse.gdse66.hostel.dao.custom.UserDAO;
import lk.ijse.gdse66.hostel.entity.User;

import java.util.ArrayList;

/**
 * @author : Kavithma Thushal
 * @project : Hostel-Management-System
 * @since : 2:41 PM - 8/30/2023
 **/
public class UserDAOImpl implements UserDAO {

    @Override
    public ArrayList<User> loadAll() {
        return null;
    }

    @Override
    public boolean save(User user) {
        return false;
    }

    @Override
    public User search(String id) {
        return null;
    }

    @Override
    public boolean update(User user) {
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

    @Override
    public String generateNextId() {
        return null;
    }
}
