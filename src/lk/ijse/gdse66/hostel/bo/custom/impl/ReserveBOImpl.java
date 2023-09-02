package lk.ijse.gdse66.hostel.bo.custom.impl;

import lk.ijse.gdse66.hostel.bo.custom.ReserveBO;
import lk.ijse.gdse66.hostel.dao.DAOFactory;
import lk.ijse.gdse66.hostel.dao.custom.ReserveDAO;
import lk.ijse.gdse66.hostel.dao.custom.RoomDAO;
import lk.ijse.gdse66.hostel.dao.custom.StudentDAO;
import lk.ijse.gdse66.hostel.dto.ReservationDTO;
import lk.ijse.gdse66.hostel.dto.RoomDTO;
import lk.ijse.gdse66.hostel.dto.StudentDTO;
import lk.ijse.gdse66.hostel.entity.Reservation;
import lk.ijse.gdse66.hostel.entity.Room;
import lk.ijse.gdse66.hostel.entity.Student;
import lk.ijse.gdse66.hostel.util.SessionFactoryConfiguration;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Kavithma Thushal
 * @project : Hostel-Management-System
 * @since : 1:51 PM - 9/1/2023
 **/
public class ReserveBOImpl implements ReserveBO {
    private final ReserveDAO reserveDAO = (ReserveDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.RESERVE);
    private final StudentDAO studentDAO = (StudentDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.STUDENT);
    private final RoomDAO roomDAO = (RoomDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ROOM);

    @Override
    public String generateNextReservationId() {
        return reserveDAO.generateNextId();
    }

    @Override
    public ArrayList<ReservationDTO> loadAllReservations() {
        ArrayList<Reservation> reservationArrayList = reserveDAO.loadAll();
        ArrayList<ReservationDTO> reservationDTOArrayList = new ArrayList<>();
        for (Reservation reservation : reservationArrayList) {
            reservationDTOArrayList.add(new ReservationDTO(reservation.getReserveId(), reservation.getStudentId().getId(), reservation.getRoomId().getId(), reservation.getDate(), reservation.getStatus()));
        }
        return reservationDTOArrayList;
    }

    @Override
    public boolean existReservation(String id) {
        return reserveDAO.exist(id);
    }

    @Override
    public ArrayList<ReservationDTO> searchReservation(String id) {
        Reservation reservation = reserveDAO.search(id);
        ArrayList<ReservationDTO> reservationDTOArrayList = new ArrayList<>();
        reservationDTOArrayList.add(new ReservationDTO(reservation.getReserveId(), reservation.getStudentId().getId(), reservation.getRoomId().getId(), reservation.getDate(), reservation.getStatus()));
        return reservationDTOArrayList;
    }

    @Override
    public List<String> loadStudentIds() {
        return studentDAO.loadStudentIds();
    }

    @Override
    public List<String> loadRoomIds() {
        return roomDAO.loadRoomIds();
    }

    @Override
    public StudentDTO searchByStudentId(String studentId) {
        Student student = studentDAO.search(studentId);
        return new StudentDTO(student.getId(), student.getName(), student.getGender(), student.getAddress(), student.getContact(), student.getDob());
    }

    @Override
    public RoomDTO searchByRoomId(String roomId) {
        Room room = roomDAO.search(roomId);
        return new RoomDTO(room.getId(), room.getType(), String.valueOf(room.getKeyMoney()), String.valueOf(room.getQty()));
    }

    @Override
    public boolean placeReservation(ReservationDTO reservationDTO) {
        Session session = SessionFactoryConfiguration.getInstance().getSession();
        Student studentId = session.get(Student.class, reservationDTO.getStudentId());
        Room roomId = session.get(Room.class, reservationDTO.getRoomId());

        return reserveDAO.save(new Reservation(reservationDTO.getReserveId(), studentId, roomId, reservationDTO.getDate(), reservationDTO.getStatus()));
    }
}
