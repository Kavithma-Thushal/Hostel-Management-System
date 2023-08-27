package lk.ijse.gdse66.hostel.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * @author : Kavithma Thushal
 * @project : Hostel-Management-System
 * @since : 9:34 AM - 8/25/2023
 **/
public class ManageReservationFormController {

    @FXML
    private AnchorPane root;
    @FXML
    private Label lblResId;
    @FXML
    private JFXComboBox cmbStudentId;
    @FXML
    private JFXTextField txtStudentName;
    @FXML
    private JFXTextField txtContact;
    @FXML
    private JFXTextField txtAddress;
    @FXML
    private JFXTextField txtDOB;
    @FXML
    private JFXTextField txtGender;
    @FXML
    private JFXComboBox cmbRoomId;
    @FXML
    private JFXTextField txtRoomType;
    @FXML
    private JFXTextField txtKeyMoney;
    @FXML
    private JFXTextField txtQty;
    @FXML
    private JFXTextField txtAdvance;
    @FXML
    private JFXButton btnReserve;

    @FXML
    private void ReserveOnAction(ActionEvent actionEvent) {

    }
}
