package lk.ijse.gdse66.hostel.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.gdse66.hostel.bo.BOFactory;
import lk.ijse.gdse66.hostel.bo.custom.ReserveBO;
import lk.ijse.gdse66.hostel.dto.ReservationDTO;
import lk.ijse.gdse66.hostel.view.tm.ReservationTM;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * @author : Kavithma Thushal
 * @project : Hostel-Management-System
 * @since : 9:57 AM - 8/25/2023
 **/
public class ManageKeymoneyFormController implements Initializable {

    @FXML
    private JFXTextField txtSearch;
    @FXML
    private TableView<ReservationTM> tblReservation;
    private final ReserveBO reserveBO = (ReserveBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.RESERVE);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setCellValue();
        loadAllReservations();
    }

    private void setCellValue() {
        tblReservation.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("reserveId"));
        tblReservation.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("studentId"));
        tblReservation.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("roomId"));
        tblReservation.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("date"));
        tblReservation.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    private void loadAllReservations() {
        ArrayList<ReservationDTO> reservationDTOArrayList = reserveBO.loadAllReservations();
        for (ReservationDTO reservationDTO : reservationDTOArrayList) {
            tblReservation.getItems().add(new ReservationTM(reservationDTO.getReserveId(), reservationDTO.getStudentId(), reservationDTO.getRoomId(), reservationDTO.getDate(), reservationDTO.getStatus()));
        }
    }

    private boolean existReservation(String id) {
        return reserveBO.existReservation(id);
    }

    @FXML
    private void searchOnAction(ActionEvent actionEvent) {
        if (existReservation(txtSearch.getText())) {
            tblReservation.getItems().clear();
            ArrayList<ReservationDTO> reservationDTOArrayList = reserveBO.searchReservation(txtSearch.getText());
            if (reservationDTOArrayList != null) {
                for (ReservationDTO reservationDTO : reservationDTOArrayList) {
                    tblReservation.getItems().add(new ReservationTM(reservationDTO.getReserveId(), reservationDTO.getStudentId(), reservationDTO.getRoomId(), reservationDTO.getDate(), reservationDTO.getStatus()));
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Please Try Again!").show();
            }
        } else {
            tblReservation.getItems().clear();
            new Alert(Alert.AlertType.ERROR, "There is no reservation related to this ID").show();
        }
    }
}
