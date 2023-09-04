package lk.ijse.gdse66.hostel.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lk.ijse.gdse66.hostel.bo.BOFactory;
import lk.ijse.gdse66.hostel.bo.custom.UserBO;
import lk.ijse.gdse66.hostel.dto.UserDTO;
import lk.ijse.gdse66.hostel.util.Notification;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * @author : Kavithma Thushal
 * @project : Hostel-Management-System
 * @since : 10:41 PM - 8/24/2023
 **/
public class LoginFormController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private JFXTextField txtUserName;
    @FXML
    private JFXPasswordField txtPassword;
    @FXML
    private JFXTextField txtShowPassword;
    @FXML
    private ImageView imgShowPassword;
    @FXML
    private ImageView imgHidePassword;
    private String password;
    private final UserBO userBO = (UserBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.USER);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtShowPassword.setVisible(false);
        imgHidePassword.setVisible(false);
    }

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
        password = txtPassword.getText();
        imgShowPassword.setVisible(false);
        imgHidePassword.setVisible(true);
        txtPassword.setVisible(false);
        txtShowPassword.setVisible(true);
        txtShowPassword.setText(password);
    }

    @FXML
    private void hidePasswordOnAction(MouseEvent mouseEvent) {
        imgShowPassword.setVisible(true);
        imgHidePassword.setVisible(false);
        txtPassword.setVisible(true);
        txtShowPassword.setVisible(false);
        txtPassword.setText(password);
    }

    @FXML
    private void createAccountOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/lk/ijse/gdse66/hostel/view/create_account_form.fxml"))));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();

        txtUserName.clear();
        txtPassword.clear();
    }

    @FXML
    private void closeOnAction(MouseEvent mouseEvent) {
        System.exit(0);
    }
}
