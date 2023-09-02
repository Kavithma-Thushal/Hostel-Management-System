package lk.ijse.gdse66.hostel.bo.custom;

import lk.ijse.gdse66.hostel.bo.SuperBO;
import lk.ijse.gdse66.hostel.dto.ReservationDTO;
import lk.ijse.gdse66.hostel.dto.RoomDTO;
import lk.ijse.gdse66.hostel.dto.StudentDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Kavithma Thushal
 * @project : Hostel-Management-System
 * @since : 1:52 PM - 9/1/2023
 **/
public interface ReserveBO extends SuperBO {

    String generateNextReservationId();
    ArrayList<ReservationDTO> loadAllReservations();

    List<String> loadStudentIds();

    List<String> loadRoomIds();

    StudentDTO searchByStudentId(String studentId);

    RoomDTO searchByRoomId(String roomId);

    boolean placeReservation(ReservationDTO reservationDTO);

}
