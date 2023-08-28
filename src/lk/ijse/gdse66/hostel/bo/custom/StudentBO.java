package lk.ijse.gdse66.hostel.bo.custom;

import lk.ijse.gdse66.hostel.bo.SuperBO;
import lk.ijse.gdse66.hostel.dto.StudentDTO;

import java.util.ArrayList;

/**
 * @author : Kavithma Thushal
 * @project : Hostel-Management-System
 * @since : 10:38 PM - 8/26/2023
 **/
public interface StudentBO extends SuperBO {

    ArrayList<StudentDTO> getAllStudents();

    boolean saveStudent(StudentDTO studentDTO);

    boolean updateStudent(StudentDTO studentDTO);

    ArrayList<StudentDTO> searchStudent(String id);

    boolean deleteStudent(String id);
}
