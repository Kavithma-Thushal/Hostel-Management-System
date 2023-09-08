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
import lk.ijse.gdse66.hostel.dto.ReservationDTO;
import lk.ijse.gdse66.hostel.dto.RoomDTO;
import lk.ijse.gdse66.hostel.dto.StudentDTO;

import java.net.URL;
import java.time.LocalDate;
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
    private JFXComboBox<String> cmbRoomId;
    @FXML
    private JFXTextField txtRoomType;
    @FXML
    private JFXTextField txtKeyMoney;
    @FXML
    private JFXTextField txtQty;
    @FXML
    private JFXComboBox cmbStatus;
    @FXML
    private JFXTextField txtRoomQty;
    @FXML
    private JFXButton btnReserve;
    private final ReserveBO reserveBO = (ReserveBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.RESERVE);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cmbStatus.getItems().addAll("Paid", "Non-Paid");
        generateNextReservationId();
        loadStudentIds();
        loadRoomIds();
    }

    private void generateNextReservationId() {
        String nextId = reserveBO.generateNextReservationId();
        lblResId.setText(nextId);
    }

    private void loadStudentIds() {
        ObservableList<String> observableList = FXCollections.observableArrayList();
        List<String> studentIds = reserveBO.loadStudentIds();

        for (String id : studentIds) {
            observableList.add(id);
        }
        cmbStudentId.setItems(observableList);
    }

    private void loadRoomIds() {
        ObservableList<String> observableList = FXCollections.observableArrayList();
        List<String> roomIds = reserveBO.loadRoomIds();

        for (String id : roomIds) {
            observableList.add(id);
        }
        cmbRoomId.setItems(observableList);
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
        String roomId = cmbRoomId.getValue();
        //cmbStudentId.setDisable(true);

        RoomDTO roomDTO = reserveBO.searchByRoomId(roomId);
        txtRoomType.setText(roomDTO.getType());
        txtKeyMoney.setText(roomDTO.getKeyMoney());
        txtQty.setText(roomDTO.getQty());
    }

    @FXML
    private void ReserveOnAction(ActionEvent actionEvent) {
        String reservationId = lblResId.getText();
        String studentId = cmbStudentId.getValue();
        String roomId = cmbRoomId.getValue();
        String date = String.valueOf(LocalDate.now());
        String status = String.valueOf(cmbStatus.getValue());
        String roomQty = txtRoomQty.getText();

        boolean isPlacedReservation = reserveBO.placeReservation(new ReservationDTO(reservationId, studentId, roomId, date, status, roomQty));
        if (isPlacedReservation) {
            new Alert(Alert.AlertType.INFORMATION, "Room Booked Successfully!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Please Try Again!").show();
        }
    }
}
