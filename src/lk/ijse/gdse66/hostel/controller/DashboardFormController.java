package lk.ijse.gdse66.hostel.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

/**
 * @author : Kavithma Thushal
 * @project : Hostel-Management-System
 * @since : 11:37 PM - 8/24/2023
 **/
public class DashboardFormController {

    @FXML
    private AnchorPane root;
    @FXML
    private Label lblHeader;
    @FXML
    private Label lblDate;
    @FXML
    private Label lblTime;

    @FXML
    private void studentsOnAction(ActionEvent actionEvent) throws IOException {
        root.getChildren().clear();
        root.getChildren().add(FXMLLoader.load(getClass().getResource("/lk/ijse/gdse66/hostel/view/manage_students_form.fxml")));
        lblHeader.setText("Manage Students");
    }

    @FXML
    private void roomsOnAction(ActionEvent actionEvent) throws IOException {
        root.getChildren().clear();
        root.getChildren().add(FXMLLoader.load(getClass().getResource("/lk/ijse/gdse66/hostel/view/manage_rooms_form.fxml")));
        lblHeader.setText("Manage Rooms");
    }

    @FXML
    private void reservationOnAction(ActionEvent actionEvent) throws IOException {
        root.getChildren().clear();
        root.getChildren().add(FXMLLoader.load(getClass().getResource("/lk/ijse/gdse66/hostel/view/manage_reservation_form.fxml")));
        lblHeader.setText("Reservation");
    }

    @FXML
    private void keyMoneyOnAction(ActionEvent actionEvent) throws IOException {
        root.getChildren().clear();
        root.getChildren().add(FXMLLoader.load(getClass().getResource("/lk/ijse/gdse66/hostel/view/manage_keymoney_form.fxml")));
        lblHeader.setText("Find Key Money");
    }

    @FXML
    private void userOnAction(ActionEvent actionEvent) throws IOException {
        root.getChildren().clear();
        root.getChildren().add(FXMLLoader.load(getClass().getResource("/lk/ijse/gdse66/hostel/view/manage_user_form.fxml")));
        lblHeader.setText("Manage User");
    }
}
