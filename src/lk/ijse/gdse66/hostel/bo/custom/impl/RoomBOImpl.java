package lk.ijse.gdse66.hostel.bo.custom.impl;

import lk.ijse.gdse66.hostel.bo.custom.RoomBO;
import lk.ijse.gdse66.hostel.dto.RoomDTO;

import java.util.ArrayList;

/**
 * @author : Kavithma Thushal
 * @project : Hostel-Management-System
 * @since : 12:13 AM - 8/30/2023
 **/
public class RoomBOImpl implements RoomBO {

    @Override
    public ArrayList<RoomDTO> loadAllRooms() {
        return null;
    }

    @Override
    public boolean saveRoom(RoomDTO roomDTO) {
        return false;
    }

    @Override
    public ArrayList<RoomDTO> searchRoom(String id) {
        return null;
    }

    @Override
    public boolean updateRoom(RoomDTO roomDTO) {
        return false;
    }

    @Override
    public boolean deleteRoom(String id) {
        return false;
    }

    @Override
    public boolean existRoom(String id) {
        return false;
    }
}
