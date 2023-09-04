package lk.ijse.gdse66.hostel.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import lk.ijse.gdse66.hostel.bo.BOFactory;
import lk.ijse.gdse66.hostel.bo.custom.RoomBO;
import lk.ijse.gdse66.hostel.bo.custom.StudentBO;
import lk.ijse.gdse66.hostel.bo.custom.UserBO;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author : Kavithma Thushal
 * @project : Hostel-Management-System
 * @since : 11:54 AM - 9/4/2023
 **/
public class ReportsFormController implements Initializable {

    @FXML
    private Label lblStudentCount;
    @FXML
    private Label lblRoomCount;
    @FXML
    private Label lblUserCount;

    private final StudentBO studentBO = (StudentBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.STUDENT);
    private final RoomBO roomBO = (RoomBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.ROOM);
    private final UserBO userBO = (UserBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.USER);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        studentCount();
        roomCount();
        userCount();
    }

    private void studentCount() {
        String count = studentBO.getStudentCount();
        lblStudentCount.setText(count);
    }

    private void roomCount() {
        String count = roomBO.getRoomCount();
        lblRoomCount.setText(count);
    }

    private void userCount() {
        String count = userBO.getUserCount();
        lblUserCount.setText(count);
    }
}