package lk.ijse.gdse66.hostel.dao.custom;

import lk.ijse.gdse66.hostel.dao.SuperDAO;
import lk.ijse.gdse66.hostel.entity.Custom;

import java.util.ArrayList;

/**
 * @author : Kavithma Thushal
 * @project : Hostel-Management-System
 * @since : 8:20 PM - 9/8/2023
 **/
public interface QueryDAO extends SuperDAO {
    ArrayList<Custom> getAllData();
}
