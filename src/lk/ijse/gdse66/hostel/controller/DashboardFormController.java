package lk.ijse.gdse66.hostel.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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
    private void studentsOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) this.root.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/lk/ijse/gdse66/hostel/view/manage_students_form.fxml"))));
        stage.setTitle("Manage Students");
    }

    @FXML
    private void roomsOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) this.root.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/lk/ijse/gdse66/hostel/view/manage_rooms_form.fxml"))));
        stage.setTitle("Manage Rooms");
    }

    @FXML
    private void reservationOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) this.root.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/lk/ijse/gdse66/hostel/view/manage_reservation_form.fxml"))));
        stage.setTitle("Reservation");
    }

    @FXML
    private void keyMoneyOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) this.root.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/lk/ijse/gdse66/hostel/view/manage_keymoney_form.fxml"))));
        stage.setTitle("Key Money");
    }

    @FXML
    private void userOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) this.root.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/lk/ijse/gdse66/hostel/view/manage_user_form.fxml"))));
        stage.setTitle("Manage User");
    }
}
