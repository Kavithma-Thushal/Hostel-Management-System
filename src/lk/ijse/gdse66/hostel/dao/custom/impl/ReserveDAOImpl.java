package lk.ijse.gdse66.hostel.dao.custom.impl;

import lk.ijse.gdse66.hostel.dao.custom.ReserveDAO;
import lk.ijse.gdse66.hostel.entity.Reservation;

import java.util.ArrayList;

/**
 * @author : Kavithma Thushal
 * @project : Hostel-Management-System
 * @since : 2:05 PM - 9/1/2023
 **/
public class ReserveDAOImpl implements ReserveDAO {

    @Override
    public ArrayList<Reservation> loadAll() {
        return null;
    }

    @Override
    public boolean save(Reservation reservation) {
        return false;
    }

    @Override
    public Reservation search(String id) {
        return null;
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
        return false;
    }

    @Override
    public String generateNextId() {
        return null;
    }
}
