package lk.ijse.gdse66.hostel.bo.custom.impl;

import lk.ijse.gdse66.hostel.bo.custom.RoomBO;
import lk.ijse.gdse66.hostel.dao.DAOFactory;
import lk.ijse.gdse66.hostel.dao.custom.RoomDAO;
import lk.ijse.gdse66.hostel.dto.RoomDTO;
import lk.ijse.gdse66.hostel.entity.Room;

import java.util.ArrayList;

/**
 * @author : Kavithma Thushal
 * @project : Hostel-Management-System
 * @since : 12:13 AM - 8/30/2023
 **/
public class RoomBOImpl implements RoomBO {
    private final RoomDAO roomDAO = (RoomDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ROOM);

    @Override
    public ArrayList<RoomDTO> loadAllRooms() {
        ArrayList<Room> roomArrayList = roomDAO.loadAll();
        ArrayList<RoomDTO> roomDTOArrayList = new ArrayList<>();
        for (Room room : roomArrayList) {
            roomDTOArrayList.add(new RoomDTO(room.getId(), room.getType(), Double.toString(room.getKeyMoney()), Integer.toString(room.getQty())));
        }
        return roomDTOArrayList;
    }

    @Override
    public boolean saveRoom(RoomDTO roomDTO) {
        return roomDAO.save(new Room(roomDTO.getId(), roomDTO.getType(), Double.parseDouble(roomDTO.getKeyMoney()), Integer.parseInt(roomDTO.getQty())));
    }

    @Override
    public ArrayList<RoomDTO> searchRoom(String id) {
        Room room = roomDAO.search(id);
        ArrayList<RoomDTO> roomDTOArrayList = new ArrayList<>();
        roomDTOArrayList.add(new RoomDTO(room.getId(), room.getType(), Double.toString(room.getKeyMoney()), Integer.toString(room.getQty())));
        return roomDTOArrayList;
    }

    @Override
    public boolean updateRoom(RoomDTO roomDTO) {
        return roomDAO.update(new Room(roomDTO.getId(), roomDTO.getType(), Double.parseDouble(roomDTO.getKeyMoney()), Integer.parseInt(roomDTO.getQty())));
    }

    @Override
    public boolean deleteRoom(String id) {
        return roomDAO.delete(id);
    }

    @Override
    public boolean existRoom(String id) {
        return roomDAO.exist(id);
    }

    @Override
    public String generateNextRoomId() {
        return roomDAO.generateNextId();
    }

    @Override
    public String getRoomCount() {
        return roomDAO.count();
    }
}
