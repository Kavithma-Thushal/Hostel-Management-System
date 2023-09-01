package lk.ijse.gdse66.hostel.bo.custom.impl;

import lk.ijse.gdse66.hostel.bo.custom.ReserveBO;
import lk.ijse.gdse66.hostel.dao.DAOFactory;
import lk.ijse.gdse66.hostel.dao.custom.ReserveDAO;
import lk.ijse.gdse66.hostel.dao.custom.RoomDAO;
import lk.ijse.gdse66.hostel.dao.custom.StudentDAO;
import lk.ijse.gdse66.hostel.dto.StudentDTO;
import lk.ijse.gdse66.hostel.entity.Student;

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
    public List<String> loadStudentIds() {
        return studentDAO.loadStudentIds();
    }

    @Override
    public StudentDTO searchByStudentId(String studentId) {
        Student student = studentDAO.search(studentId);
        return new StudentDTO(student.getId(),student.getName(),student.getGender(),student.getAddress(),student.getContact(),student.getDob());
    }
}
