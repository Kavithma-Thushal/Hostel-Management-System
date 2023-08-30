package lk.ijse.gdse66.hostel.bo.custom;

import lk.ijse.gdse66.hostel.bo.SuperBO;
import lk.ijse.gdse66.hostel.dto.StudentDTO;
import lk.ijse.gdse66.hostel.dto.UserDTO;

import java.util.ArrayList;

/**
 * @author : Kavithma Thushal
 * @project : Hostel-Management-System
 * @since : 2:39 PM - 8/30/2023
 **/
public interface UserBO extends SuperBO {
    ArrayList<UserDTO> loadAllUsers();

    boolean saveUser(UserDTO userDTO);

    ArrayList<UserDTO> searchUser(String id);

    boolean updateUser(UserDTO userDTO);

    boolean deleteUser(String id);

    boolean existUser(String id);

    String generateNextUserId();
}
