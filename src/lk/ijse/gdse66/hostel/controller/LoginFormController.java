package lk.ijse.gdse66.hostel.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.gdse66.hostel.util.SessionFactoryConfiguration;
import org.hibernate.Session;

import java.io.IOException;

/**
 * @author : Kavithma Thushal
 * @project : Hostel-Management-System
 * @since : 10:41 PM - 8/24/2023
 **/
public class LoginFormController {

    @FXML
    private AnchorPane root;
    @FXML
    private JFXTextField txtUserName;
    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private void loginOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) this.root.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/lk/ijse/gdse66/hostel/view/dashboard_form.fxml"))));
        stage.setTitle("Dashboard");

        new Thread(() -> {
            Session session = SessionFactoryConfiguration.getInstance().getSession();
            session.close();
        }).start();
    }

    @FXML
    private void cancelOnAction(ActionEvent actionEvent) {
        txtUserName.clear();
        txtPassword.clear();
    }
}
