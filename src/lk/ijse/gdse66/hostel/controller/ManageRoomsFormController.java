package lk.ijse.gdse66.hostel.controller;

import com.jfoenix.controls.JFXButton;
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
 * @since : 9:36 AM - 8/25/2023
 **/
public class ManageRoomsFormController {

    @FXML
    private AnchorPane root;
    @FXML
    private JFXTextField txtRoomId;
    @FXML
    private JFXTextField txtRoomType;
    @FXML
    private JFXTextField txtKeyMoney;
    @FXML
    private JFXTextField txtQty;
    @FXML
    private JFXTextField txtSearch;
    @FXML
    private JFXButton btnAddNew;
    @FXML
    private JFXButton btnSave;
    @FXML
    private JFXButton btnDelete;

    @FXML
    private void addNewOnAction(ActionEvent actionEvent) {

    }

    @FXML
    private void saveOnAction(ActionEvent actionEvent) {

    }

    @FXML
    private void searchOnAction(ActionEvent actionEvent) {

    }

    @FXML
    private void deleteOnAction(ActionEvent actionEvent) {

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
