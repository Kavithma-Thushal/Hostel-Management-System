package lk.ijse.gdse66.hostel.dao.custom;

import lk.ijse.gdse66.hostel.dao.CrudDAO;
import lk.ijse.gdse66.hostel.entity.Room;

import java.util.List;

/**
 * @author : Kavithma Thushal
 * @project : Hostel-Management-System
 * @since : 12:12 AM - 8/30/2023
 **/
public interface RoomDAO extends CrudDAO<Room> {

    List<String> loadRoomIds();
}
