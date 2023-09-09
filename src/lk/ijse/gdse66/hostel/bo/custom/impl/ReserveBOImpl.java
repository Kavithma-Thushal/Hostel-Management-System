package lk.ijse.gdse66.hostel.bo.custom.impl;

import lk.ijse.gdse66.hostel.bo.custom.ReserveBO;
import lk.ijse.gdse66.hostel.dao.DAOFactory;
import lk.ijse.gdse66.hostel.dao.custom.QueryDAO;
import lk.ijse.gdse66.hostel.dao.custom.ReserveDAO;
import lk.ijse.gdse66.hostel.dao.custom.RoomDAO;
import lk.ijse.gdse66.hostel.dao.custom.StudentDAO;
import lk.ijse.gdse66.hostel.dto.CustomDTO;
import lk.ijse.gdse66.hostel.dto.ReservationDTO;
import lk.ijse.gdse66.hostel.dto.RoomDTO;
import lk.ijse.gdse66.hostel.dto.StudentDTO;
import lk.ijse.gdse66.hostel.entity.Custom;
import lk.ijse.gdse66.hostel.entity.Reservation;
import lk.ijse.gdse66.hostel.entity.Room;
import lk.ijse.gdse66.hostel.entity.Student;
import lk.ijse.gdse66.hostel.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
    private final QueryDAO queryDAO = (QueryDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.QUERY);

    @Override
    public String generateNextReservationId() {
        return reserveDAO.generateNextId();
    }

    @Override
    public ArrayList<ReservationDTO> loadAllReservations() {
        ArrayList<Reservation> reservationArrayList = reserveDAO.loadAll();
        ArrayList<ReservationDTO> reservationDTOArrayList = new ArrayList<>();
        for (Reservation reservation : reservationArrayList) {
            reservationDTOArrayList.add(new ReservationDTO(reservation.getReserveId(), reservation.getStudentId().getId(), reservation.getRoomId().getId(), reservation.getDate(), reservation.getStatus(), reservation.getRoomQty()));
        }
        return reservationDTOArrayList;
    }

    @Override
    public ArrayList<CustomDTO> getAllReservations() {
        ArrayList<Custom> customArrayList = queryDAO.getAllData();
        ArrayList<CustomDTO> customDTOArrayList = new ArrayList<>();
        for (Custom custom : customArrayList) {
            customDTOArrayList.add(new CustomDTO(custom.getResId(), custom.getDate(), custom.getStatus(), custom.getRoomQty(), custom.getName(), custom.getType(),custom.getKeyMoney(),custom.getQty()));
        }
        return customDTOArrayList;
    }

    @Override
    public boolean existReservation(String id) {
        return reserveDAO.exist(id);
    }

    @Override
    public ArrayList<ReservationDTO> searchReservation(String id) {
        Reservation reservation = reserveDAO.search(id);
        ArrayList<ReservationDTO> reservationDTOArrayList = new ArrayList<>();
        reservationDTOArrayList.add(new ReservationDTO(reservation.getReserveId(), reservation.getStudentId().getId(), reservation.getRoomId().getId(), reservation.getDate(), reservation.getStatus(), reservation.getRoomQty()));
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
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Student studentId = session.get(Student.class, reservationDTO.getStudentId());
        Room room = session.get(Room.class, reservationDTO.getRoomId());

        boolean isSaved = reserveDAO.save(new Reservation(reservationDTO.getReserveId(), studentId, room, reservationDTO.getDate(), reservationDTO.getStatus(), reservationDTO.getRoomQty()));
        if (isSaved) {
            room.setQty(room.getQty() - Integer.parseInt(reservationDTO.getRoomQty()));
            session.update(room);
            transaction.commit();
            return true;
        }
        transaction.rollback();
        return false;
    }
}
