package lk.ijse.gdse66.hostel.bo.custom.impl;

import lk.ijse.gdse66.hostel.bo.custom.UserBO;
import lk.ijse.gdse66.hostel.dto.UserDTO;

import java.util.ArrayList;

/**
 * @author : Kavithma Thushal
 * @project : Hostel-Management-System
 * @since : 2:39 PM - 8/30/2023
 **/
public class UserBOImpl implements UserBO {

    @Override
    public ArrayList<UserDTO> loadAllUsers() {
        return null;
    }

    @Override
    public boolean saveUser(UserDTO userDTO) {
        return false;
    }

    @Override
    public ArrayList<UserDTO> searchUser(String id) {
        return null;
    }

    @Override
    public boolean updateUser(UserDTO userDTO) {
        return false;
    }

    @Override
    public boolean deleteUser(String id) {
        return false;
    }

    @Override
    public boolean existUser(String id) {
        return false;
    }

    @Override
    public String generateNextUserId() {
        return null;
    }
}
