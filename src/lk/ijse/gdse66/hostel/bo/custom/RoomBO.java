package lk.ijse.gdse66.hostel.bo.custom;

import lk.ijse.gdse66.hostel.bo.SuperBO;
import lk.ijse.gdse66.hostel.dto.RoomDTO;

import java.util.ArrayList;

/**
 * @author : Kavithma Thushal
 * @project : Hostel-Management-System
 * @since : 12:13 AM - 8/30/2023
 **/
public interface RoomBO extends SuperBO {

    ArrayList<RoomDTO> loadAllRooms();

    boolean saveRoom(RoomDTO roomDTO);

    ArrayList<RoomDTO> searchRoom(String id);

    boolean updateRoom(RoomDTO roomDTO);

    boolean deleteRoom(String id);

    boolean existRoom(String id);

    String generateNextRoomId();

    String getRoomCount();
}
