package lk.ijse.gdse66.hostel.controller;

import com.jfoenix.controls.JFXTextField;
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
 * @since : 9:57 AM - 8/25/2023
 **/
public class ManageKeymoneyFormController {

    @FXML
    private AnchorPane root;
    @FXML
    private JFXTextField txtSearch;

    @FXML
    private void searchOnAction(ActionEvent actionEvent) {

    }

    @FXML
    private void backOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) this.root.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/lk/ijse/gdse66/hostel/view/dashboard_form.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setTitle("Dashboard");
    }
}
