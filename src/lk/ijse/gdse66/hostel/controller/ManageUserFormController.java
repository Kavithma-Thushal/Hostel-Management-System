package lk.ijse.gdse66.hostel.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.gdse66.hostel.bo.BOFactory;
import lk.ijse.gdse66.hostel.bo.custom.UserBO;
import lk.ijse.gdse66.hostel.dto.UserDTO;
import lk.ijse.gdse66.hostel.view.tm.UserTM;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * @author : Kavithma Thushal
 * @project : Hostel-Management-System
 * @since : 9:59 AM - 8/25/2023
 **/
public class ManageUserFormController implements Initializable {

    @FXML
    private JFXTextField txtUserId;
    @FXML
    private JFXTextField txtUserName;
    @FXML
    private JFXComboBox cmbGender;
    @FXML
    private JFXTextField txtAddress;
    @FXML
    private JFXTextField txtContact;
    @FXML
    private JFXTextField txtPassword;
    @FXML
    private JFXTextField txtSearch;
    @FXML
    private TableView<UserTM> tblUser;
    @FXML
    private JFXButton btnAddNew;
    @FXML
    private JFXButton btnSave;
    @FXML
    private JFXButton btnDelete;
    private final UserBO userBO = (UserBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.USER);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setComboElements();
        setCellValue();
        selectTableElements();
        initUI();
        loadAllUsers();
    }

    private void setComboElements() {
        cmbGender.getItems().addAll("Male", "Female", "Other");
    }

    private void setCellValue() {
        tblUser.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblUser.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblUser.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("gender"));
        tblUser.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("address"));
        tblUser.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("contact"));
        tblUser.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("password"));
    }

    private void selectTableElements() {
        tblUser.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnSave.setText(newValue != null ? "Update" : "Save");

            if (newValue != null) {
                txtUserId.setText(newValue.getId());
                txtUserName.setText(newValue.getUserName());
                cmbGender.setValue(newValue.getGender());
                txtAddress.setText(newValue.getAddress());
                txtContact.setText(newValue.getContact());
                txtPassword.setText(newValue.getUserPassword());

                txtSearch.clear();
                enableTextFields();
                btnSave.setDisable(false);
                btnDelete.setDisable(false);
            }
        });
    }

    private void enableTextFields() {
        txtUserId.setDisable(false);
        txtUserName.setDisable(false);
        cmbGender.setDisable(false);
        txtAddress.setDisable(false);
        txtContact.setDisable(false);
        txtPassword.setDisable(false);
    }

    private void disableTextFields() {
        txtUserId.setDisable(true);
        txtUserName.setDisable(true);
        cmbGender.setDisable(true);
        txtAddress.setDisable(true);
        txtContact.setDisable(true);
        txtPassword.setDisable(true);
    }

    private void clearTextFields() {
        txtUserId.clear();
        txtUserName.clear();
        cmbGender.getSelectionModel().clearSelection();
        txtAddress.clear();
        txtContact.clear();
        txtPassword.clear();
    }

    private void initUI() {
        clearTextFields();
        txtSearch.clear();
        disableTextFields();
        btnSave.setDisable(true);
        btnDelete.setDisable(true);
    }

    private boolean existStudent(String id) {
        return userBO.existUser(id);
    }

    private void generateNextStudentId() {
        String nextId = userBO.generateNextUserId();
        txtUserId.setText(nextId);
    }

    private void loadAllUsers() {
        ArrayList<UserDTO> userDTOArrayList = userBO.loadAllUsers();
        for (UserDTO userDTO : userDTOArrayList) {
            tblUser.getItems().add(new UserTM(userDTO.getId(), userDTO.getUserName(), userDTO.getGender(), userDTO.getAddress(), userDTO.getContact(), userDTO.getUserPassword()));
        }
    }

    @FXML
    private void addNewOnAction(ActionEvent actionEvent) {
        clearTextFields();
        txtSearch.clear();
        enableTextFields();
        txtUserName.requestFocus();
        btnSave.setDisable(false);
        btnSave.setText(true ? "Save" : "Update");
        generateNextStudentId();
    }

    @FXML
    private void saveOnAction(ActionEvent actionEvent) {
        String id = txtUserId.getText();
        String name = txtUserName.getText();
        String gender = String.valueOf(cmbGender.getValue());
        String address = txtAddress.getText();
        String contact = txtContact.getText();
        String password = txtPassword.getText();

        if (!name.matches("^([A-Z a-z]{4,40})$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid User Name").show();
            txtUserName.requestFocus();
            return;
        } else if (!address.matches("^([A-Z a-z]{4,40})$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid User Address").show();
            txtAddress.requestFocus();
            return;
        } else if (!contact.matches("^(07(0|1|2|4|5|6|7|8)|091)[0-9]{7}$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid User Contact").show();
            txtContact.requestFocus();
            return;
        } else if (!password.matches("^([A-Z a-z 0-9]{4,40})$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid User Password").show();
            txtPassword.requestFocus();
            return;
        }

        if (btnSave.getText().equalsIgnoreCase("Save")) {
            /*Save User*/
            boolean isSaved = userBO.saveUser(new UserDTO(id, name, gender, address, contact, password));
            tblUser.getItems().add(new UserTM(id, name, gender, address, contact, password));
            initUI();
            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "User Saved Successfully!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Please Try Again!").show();
            }

        } else {
            /*Update User*/
            boolean isUpdated = userBO.updateUser(new UserDTO(id, name, gender, address, contact, password));
            btnSave.setText(isUpdated ? "Save" : "Update");
            initUI();
            if (isUpdated) {
                new Alert(Alert.AlertType.INFORMATION, "User Updated Successfully!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Please Try Again!").show();
            }

            UserTM selectedItem = tblUser.getSelectionModel().getSelectedItem();
            selectedItem.setId(id);
            selectedItem.setUserName(name);
            selectedItem.setGender(gender);
            selectedItem.setAddress(address);
            selectedItem.setContact(contact);
            selectedItem.setUserPassword(password);

            tblUser.refresh();
            tblUser.getSelectionModel().clearSelection();
        }
    }

    @FXML
    private void searchOnAction(ActionEvent actionEvent) {
        if (existStudent(txtSearch.getText())) {
            tblUser.getItems().clear();
            ArrayList<UserDTO> userDTOArrayList = userBO.searchUser(txtSearch.getText());
            initUI();
            if (userDTOArrayList != null) {
                for (UserDTO userDTO : userDTOArrayList) {
                    tblUser.getItems().add(new UserTM(userDTO.getId(), userDTO.getUserName(), userDTO.getGender(), userDTO.getAddress(), userDTO.getContact(), userDTO.getUserPassword()));
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Please Try Again!").show();
            }
        } else {
            tblUser.getItems().clear();
            new Alert(Alert.AlertType.ERROR, "There is no user related to this ID").show();
        }
    }

    @FXML
    private void deleteOnAction(ActionEvent actionEvent) {
        String code = tblUser.getSelectionModel().getSelectedItem().getId();

        boolean isDeleted = userBO.deleteUser(code);
        tblUser.getItems().remove(tblUser.getSelectionModel().getSelectedItem());
        tblUser.getSelectionModel().clearSelection();
        initUI();
        if (isDeleted) {
            new Alert(Alert.AlertType.INFORMATION, "User Deleted Successfully!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Please Try Again!").show();
        }
    }
}
