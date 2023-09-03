package lk.ijse.gdse66.hostel.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import lk.ijse.gdse66.hostel.bo.BOFactory;
import lk.ijse.gdse66.hostel.bo.custom.UserBO;
import lk.ijse.gdse66.hostel.dto.UserDTO;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author : Kavithma Thushal
 * @project : Hostel-Management-System
 * @since : 5:18 PM - 9/1/2023
 **/
public class CreateAccountFormController implements Initializable {

    public JFXTextField txtUserId;
    public JFXTextField txtUserName;
    public JFXComboBox cmbGender;
    public JFXTextField txtAddress;
    public JFXTextField txtContact;
    public JFXTextField txtPassword;
    private final UserBO userBO = (UserBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.USER);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cmbGender.getItems().addAll("Male", "Female", "Other");
        generateNextUserId();
    }

    private void generateNextUserId() {
        String nextId = userBO.generateNextUserId();
        txtUserId.setText(nextId);
    }

    private void clearTextFields() {
        txtUserId.clear();
        txtUserName.clear();
        cmbGender.getSelectionModel().clearSelection();
        txtAddress.clear();
        txtContact.clear();
        txtPassword.clear();
    }

    public void createNewAccountOnAction(ActionEvent actionEvent) {
        String id = txtUserId.getText();
        String name = txtUserName.getText();
        String gender = String.valueOf(cmbGender.getValue());
        String address = txtAddress.getText();
        String contact = txtContact.getText();
        String password = txtPassword.getText();

        if (!name.matches("^([A-Z a-z 0-9]{4,40})$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid User Name").show();
            txtUserName.requestFocus();
            return;
        } else if (!address.matches("^([A-Z a-z 0-9]{4,40})$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid User Address").show();
            txtAddress.requestFocus();
            return;
        } else if (!contact.matches("^(07(0|1|2|4|5|6|7|8)|091)[0-9]{7}$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid User Contact").show();
            txtContact.requestFocus();
            return;
        } else if (!password.matches("^([A-Z a-z 0-9 \\W]{4,40})$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid User Password").show();
            txtPassword.requestFocus();
            return;
        }

        boolean isSaved = userBO.saveUser(new UserDTO(id, name, gender, address, contact, password));
        clearTextFields();
        if (isSaved) {
            new Alert(Alert.AlertType.INFORMATION, "Account Created Successfully!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Please Try Again!").show();
        }
    }
}
