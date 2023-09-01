package lk.ijse.gdse66.hostel.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import lk.ijse.gdse66.hostel.bo.BOFactory;
import lk.ijse.gdse66.hostel.bo.custom.ReserveBO;
import lk.ijse.gdse66.hostel.bo.custom.StudentBO;
import lk.ijse.gdse66.hostel.dto.StudentDTO;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author : Kavithma Thushal
 * @project : Hostel-Management-System
 * @since : 9:34 AM - 8/25/2023
 **/
public class ManageReservationFormController implements Initializable {

    @FXML
    private Label lblResId;
    @FXML
    private JFXComboBox<String> cmbStudentId;
    @FXML
    private JFXTextField txtStudentName;
    @FXML
    private JFXTextField txtGender;
    @FXML
    private JFXTextField txtAddress;
    @FXML
    private JFXTextField txtContact;
    @FXML
    private JFXTextField txtDOB;
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
    private final ReserveBO reserveBO = (ReserveBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.RESERVE);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadStudentIds();
    }

    private void loadStudentIds() {
        ObservableList<String> observableList = FXCollections.observableArrayList();
        List<String> studentIds = reserveBO.loadStudentIds();

        for (String id : studentIds) {
            observableList.add(id);
        }
        cmbStudentId.setItems(observableList);
    }

    @FXML
    private void cmbStudentIdOnAction(ActionEvent actionEvent) {
        String studentId = cmbStudentId.getValue();
        //cmbStudentId.setDisable(true);

        StudentDTO studentDTO = reserveBO.searchByStudentId(studentId);
        txtStudentName.setText(studentDTO.getName());
        txtGender.setText(studentDTO.getGender());
        txtAddress.setText(studentDTO.getAddress());
        txtContact.setText(studentDTO.getContact());
        txtDOB.setText(studentDTO.getDob());
    }

    @FXML
    private void cmbRoomIdOnAction(ActionEvent actionEvent) {

    }

    @FXML
    private void ReserveOnAction(ActionEvent actionEvent) {

    }
}
