package lk.ijse.gdse66.hostel.bo.custom.impl;

import lk.ijse.gdse66.hostel.bo.custom.StudentBO;
import lk.ijse.gdse66.hostel.dao.DAOFactory;
import lk.ijse.gdse66.hostel.dao.custom.StudentDAO;
import lk.ijse.gdse66.hostel.dto.StudentDTO;
import lk.ijse.gdse66.hostel.entity.Student;

import java.util.ArrayList;

/**
 * @author : Kavithma Thushal
 * @project : Hostel-Management-System
 * @since : 10:39 PM - 8/26/2023
 **/
public class StudentBOImpl implements StudentBO {
    private final StudentDAO studentDAO = (StudentDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.STUDENT);

    @Override
    public ArrayList<StudentDTO> getAllStudents() {
        ArrayList<Student> studentArrayList = studentDAO.getAll();
        ArrayList<StudentDTO> studentDTOArrayList = new ArrayList<>();
        for (Student student : studentArrayList) {
            studentDTOArrayList.add(new StudentDTO(student.getId(), student.getName(), student.getGender(), student.getAddress(), student.getContact(), student.getDob()));
        }
        return studentDTOArrayList;
    }

    @Override
    public boolean saveStudent(StudentDTO studentDTO) {
        return studentDAO.save(new Student(studentDTO.getId(), studentDTO.getName(), studentDTO.getGender(), studentDTO.getAddress(), studentDTO.getContact(), studentDTO.getDob()));
    }

    @Override
    public ArrayList<StudentDTO> searchStudent(String id) {
        Student student = studentDAO.search(id);
        ArrayList<StudentDTO> studentDTOArrayList = new ArrayList<>();
        studentDTOArrayList.add(new StudentDTO(student.getId(), student.getName(), student.getGender(), student.getAddress(), student.getContact(), student.getDob()));
        return studentDTOArrayList;
    }
}
