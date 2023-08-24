package lk.ijse.gdse66.hostel.controller;

import com.jfoenix.controls.JFXPasswordField;
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
 * @since : 10:41 PM - 8/24/2023
 **/
public class LoginFormController {

    public AnchorPane root;
    @FXML
    private JFXTextField txtUserName;
    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private void loginOnAction(ActionEvent actionEvent) {
        if (txtUserName.getText().equals("user") && txtPassword.getText().equals("1234")) {
            Stage stage = (Stage) this.root.getScene().getWindow();
            try {
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/lk/ijse/gdse66/hostel/view/user_dashboard_form.fxml"))));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage.setTitle("User Dashboard");
            stage.setResizable(false);
            stage.show();
        }
    }
}