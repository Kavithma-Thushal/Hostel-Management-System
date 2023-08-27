package lk.ijse.gdse66.hostel.bo.custom.impl;

import lk.ijse.gdse66.hostel.bo.custom.StudentBO;
import lk.ijse.gdse66.hostel.dao.DAOFactory;
import lk.ijse.gdse66.hostel.dao.custom.StudentDAO;
import lk.ijse.gdse66.hostel.dto.StudentDTO;
import lk.ijse.gdse66.hostel.entity.Student;

/**
 * @author : Kavithma Thushal
 * @project : Hostel-Management-System
 * @since : 10:39 PM - 8/26/2023
 **/
public class StudentBOImpl implements StudentBO {
    private final StudentDAO studentDAO = (StudentDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.STUDENT);

    @Override
    public boolean saveStudent(StudentDTO studentDTO) {
        return studentDAO.save(new Student(studentDTO.getId(), studentDTO.getName(), studentDTO.getGender(), studentDTO.getAddress(), studentDTO.getContact(), studentDTO.getDob()));
    }
}
