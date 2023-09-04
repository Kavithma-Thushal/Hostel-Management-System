package lk.ijse.gdse66.hostel.bo.custom.impl;

import lk.ijse.gdse66.hostel.bo.custom.UserBO;
import lk.ijse.gdse66.hostel.dao.DAOFactory;
import lk.ijse.gdse66.hostel.dao.custom.UserDAO;
import lk.ijse.gdse66.hostel.dto.UserDTO;
import lk.ijse.gdse66.hostel.entity.User;

import java.util.ArrayList;

/**
 * @author : Kavithma Thushal
 * @project : Hostel-Management-System
 * @since : 2:39 PM - 8/30/2023
 **/
public class UserBOImpl implements UserBO {
    private final UserDAO userDAO = (UserDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.USER);

    @Override
    public ArrayList<UserDTO> loadAllUsers() {
        ArrayList<User> userArrayList = userDAO.loadAll();
        ArrayList<UserDTO> userDTOArrayList = new ArrayList<>();
        for (User user : userArrayList) {
            userDTOArrayList.add(new UserDTO(user.getId(), user.getUserName(), user.getGender(), user.getAddress(), user.getContact(), user.getUserPassword()));
        }
        return userDTOArrayList;
    }

    @Override
    public boolean saveUser(UserDTO userDTO) {
        return userDAO.save(new User(userDTO.getId(), userDTO.getUserName(), userDTO.getGender(), userDTO.getAddress(), userDTO.getContact(), userDTO.getUserPassword()));
    }

    @Override
    public ArrayList<UserDTO> searchUser(String id) {
        User user = userDAO.search(id);
        ArrayList<UserDTO> userDTOArrayList = new ArrayList<>();
        userDTOArrayList.add(new UserDTO(user.getId(), user.getUserName(), user.getGender(), user.getAddress(), user.getContact(), user.getUserPassword()));
        return userDTOArrayList;
    }

    @Override
    public boolean updateUser(UserDTO userDTO) {
        return userDAO.update(new User(userDTO.getId(), userDTO.getUserName(), userDTO.getGender(), userDTO.getAddress(), userDTO.getContact(), userDTO.getUserPassword()));
    }

    @Override
    public boolean deleteUser(String id) {
        return userDAO.delete(id);
    }

    @Override
    public boolean existUser(String id) {
        return userDAO.exist(id);
    }

    @Override
    public String generateNextUserId() {
        return userDAO.generateNextId();
    }

    @Override
    public String getUserCount() {
        return userDAO.count();
    }
}
