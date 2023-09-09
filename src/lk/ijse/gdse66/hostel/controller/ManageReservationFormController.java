package lk.ijse.gdse66.hostel.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import jakarta.mail.MessagingException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import lk.ijse.gdse66.hostel.bo.BOFactory;
import lk.ijse.gdse66.hostel.bo.custom.ReserveBO;
import lk.ijse.gdse66.hostel.dto.CustomDTO;
import lk.ijse.gdse66.hostel.dto.ReservationDTO;
import lk.ijse.gdse66.hostel.dto.RoomDTO;
import lk.ijse.gdse66.hostel.dto.StudentDTO;
import lk.ijse.gdse66.hostel.util.EmailSend;
import lk.ijse.gdse66.hostel.view.tm.CustomTM;
import lk.ijse.gdse66.hostel.view.tm.ReservationTM;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
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
        //loadAllReservations();
        loadStudentIds();
        loadRoomIds();
        initUI();
    }

    private void initUI() {
        clearTextFields();
        disableTextFields();
        //btnReserve.setDisable(true);
    }

    private void clearTextFields() {
        //cmbStudentId.getSelectionModel().clearSelection();
        txtStudentName.clear();
        txtGender.clear();
        txtAddress.clear();
        txtContact.clear();
        txtDOB.clear();

        //cmbRoomId.getSelectionModel().clearSelection();
        txtRoomType.clear();
        txtKeyMoney.clear();
        txtQty.clear();
        cmbStatus.getSelectionModel().clearSelection();
        txtRoomQty.clear();
    }

    private void disableTextFields() {
        //cmbStudentId.setDisable(true);
        txtStudentName.setDisable(true);
        txtGender.setDisable(true);
        txtAddress.setDisable(true);
        txtContact.setDisable(true);
        txtDOB.setDisable(true);

        //cmbRoomId.setDisable(true);
        txtRoomType.setDisable(true);
        txtKeyMoney.setDisable(true);
        txtQty.setDisable(true);
        cmbStatus.setDisable(true);
        txtRoomQty.setDisable(true);
    }

    private void enableStudentTextFields() {
        cmbStudentId.setDisable(false);
        txtStudentName.setDisable(false);
        txtGender.setDisable(false);
        txtAddress.setDisable(false);
        txtContact.setDisable(false);
        txtDOB.setDisable(false);
    }

    private void enableRoomTextFields() {
        cmbRoomId.setDisable(false);
        txtRoomType.setDisable(false);
        txtKeyMoney.setDisable(false);
        txtQty.setDisable(false);
        cmbStatus.setDisable(false);
        txtRoomQty.setDisable(false);
    }

    private void generateNextReservationId() {
        String nextId = reserveBO.generateNextReservationId();
        lblResId.setText(nextId);
    }

    private void loadAllReservations() {
        ArrayList<CustomDTO> customDTOArrayList = reserveBO.getAllReservations();
        for (CustomDTO c : customDTOArrayList) {
            System.out.println("ABC : " + new CustomTM(c.getResId(), c.getDate(), c.getStatus(), c.getRoomQty(), c.getName(), c.getType(), c.getKeyMoney(), c.getQty()));
        }
    }

    private void loadStudentIds() {
        ObservableList<String> observableList = FXCollections.observableArrayList();
        List<String> studentIds = reserveBO.loadStudentIds();

        for (String id : studentIds) {
            observableList.add(id);
        }
        cmbStudentId.setItems(observableList);
    }

    private void
    loadRoomIds() {
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

        enableStudentTextFields();
    }

    @FXML
    private void cmbRoomIdOnAction(ActionEvent actionEvent) {
        String roomId = cmbRoomId.getValue();
        //cmbStudentId.setDisable(true);

        RoomDTO roomDTO = reserveBO.searchByRoomId(roomId);
        txtRoomType.setText(roomDTO.getType());
        txtKeyMoney.setText(roomDTO.getKeyMoney());
        txtQty.setText(roomDTO.getQty());

        enableRoomTextFields();
    }

    @FXML
    private void ReserveOnAction(ActionEvent actionEvent) throws MessagingException {
        String reservationId = lblResId.getText();
        String studentId = cmbStudentId.getValue();
        String roomId = cmbRoomId.getValue();
        String date = String.valueOf(LocalDate.now());
        String status = String.valueOf(cmbStatus.getValue());
        String roomQty = txtRoomQty.getText();

        if (cmbStudentId.getValue() != null && cmbRoomId.getValue() != null && cmbStatus.getValue() != null && txtRoomQty != null) {
            boolean isPlacedReservation = reserveBO.placeReservation(new ReservationDTO(reservationId, studentId, roomId, date, status, roomQty));
            generateNextReservationId();
            initUI();
            if (isPlacedReservation) {
                new Alert(Alert.AlertType.INFORMATION, "Room Booked Successfully!").show();
                EmailSend.mail("Room Booked Successfully...!");
            } else {
                new Alert(Alert.AlertType.ERROR, "Please Fill All TextFields!").show();
            }
        }
    }
}
