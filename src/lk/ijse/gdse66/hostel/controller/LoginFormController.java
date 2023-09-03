package lk.ijse.gdse66.hostel.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.gdse66.hostel.bo.BOFactory;
import lk.ijse.gdse66.hostel.bo.custom.UserBO;
import lk.ijse.gdse66.hostel.dto.UserDTO;
import lk.ijse.gdse66.hostel.util.Notification;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.util.ArrayList;

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
    private final UserBO userBO = (UserBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.USER);

    @FXML
    private void loginOnAction(ActionEvent actionEvent) throws IOException {
        boolean validLogin = false;
        ArrayList<UserDTO> userDTOArrayList = userBO.loadAllUsers();
        for (UserDTO userDTO : userDTOArrayList) {
            if (txtUserName.getText().equals(userDTO.getUserName()) && txtPassword.getText().equals(userDTO.getUserPassword())) {
                Stage stage = (Stage) this.root.getScene().getWindow();
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/lk/ijse/gdse66/hostel/view/dashboard_form.fxml"))));
                stage.setTitle("Dashboard");

                Notification.userLogin("tick.gif", "Admin login", "Admin login Successful!", 2);

                validLogin = true;
                break;
            }
        }

        if (!validLogin) {
            Notification.userLogin("error.gif", "Error", "Admin login Unsuccessful!", 4);
        }
    }

    @FXML
    private void cancelOnAction(ActionEvent actionEvent) {
        txtUserName.clear();
        txtPassword.clear();
    }

    @FXML
    private void showPasswordOnAction(MouseEvent mouseEvent) {
        txtPassword.setPromptText(txtPassword.getText());
        txtPassword.setText("");
    }

    @FXML
    private void hidePasswordOnAction(MouseEvent mouseEvent) {
        txtPassword.setText(txtPassword.getPromptText());
        txtPassword.setPromptText("");
    }

    @FXML
    private void createAccountOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/lk/ijse/gdse66/hostel/view/create_account_form.fxml"))));
        stage.setTitle("Create Account");
        stage.show();

        txtUserName.clear();
        txtPassword.clear();
    }
}
