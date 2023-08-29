package lk.ijse.gdse66.hostel.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.gdse66.hostel.bo.BOFactory;
import lk.ijse.gdse66.hostel.bo.custom.StudentBO;
import lk.ijse.gdse66.hostel.dto.StudentDTO;
import lk.ijse.gdse66.hostel.view.tm.StudentTM;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * @author : Kavithma Thushal
 * @project : Hostel-Management-System
 * @since : 10:00 AM - 8/25/2023
 **/
public class ManageStudentsFormController implements Initializable {

    @FXML
    private JFXTextField txtStudentId;
    @FXML
    private JFXTextField txtStudentName;
    @FXML
    private JFXComboBox cmbGender;
    @FXML
    private JFXTextField txtAddress;
    @FXML
    private JFXTextField txtContact;
    @FXML
    private JFXDatePicker dpDOB;
    @FXML
    private JFXTextField txtSearch;
    @FXML
    private TableView<StudentTM> tblStudent;
    @FXML
    private JFXButton btnAddNew;
    @FXML
    private JFXButton btnSave;
    @FXML
    private JFXButton btnDelete;
    private final StudentBO studentBO = (StudentBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.STUDENT);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setComboElements();
        setCellValue();
        loadAllStudents();
        selectTableElements();
        initUI();
    }

    private void setComboElements() {
        cmbGender.getItems().addAll("Male", "Female", "Other");
    }

    private void setCellValue() {
        tblStudent.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblStudent.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblStudent.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("gender"));
        tblStudent.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("address"));
        tblStudent.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("contact"));
        tblStudent.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("dob"));
    }

    private void loadAllStudents() {
        ArrayList<StudentDTO> studentDTOArrayList = studentBO.loadAllStudents();
        for (StudentDTO studentDTO : studentDTOArrayList) {
            tblStudent.getItems().add(new StudentTM(studentDTO.getId(), studentDTO.getName(), studentDTO.getGender(), studentDTO.getAddress(), studentDTO.getContact(), studentDTO.getDob()));
        }
    }

    private void selectTableElements() {
        tblStudent.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnSave.setText(newValue != null ? "Update" : "Save");

            if (newValue != null) {
                txtStudentId.setText(newValue.getId());
                txtStudentName.setText(newValue.getName());
                cmbGender.setValue(newValue.getGender());
                txtAddress.setText(newValue.getAddress());
                txtContact.setText(newValue.getContact());
                dpDOB.setValue(LocalDate.parse(newValue.getDob()));

                enableTextFields();
                btnSave.setDisable(false);
                btnDelete.setDisable(false);
            }
        });
    }

    private void enableTextFields() {
        txtStudentId.setDisable(false);
        txtStudentName.setDisable(false);
        cmbGender.setDisable(false);
        txtAddress.setDisable(false);
        txtContact.setDisable(false);
        dpDOB.setDisable(false);
    }

    private void disableTextFields() {
        txtStudentId.setDisable(true);
        txtStudentName.setDisable(true);
        cmbGender.setDisable(true);
        txtAddress.setDisable(true);
        txtContact.setDisable(true);
        dpDOB.setDisable(true);
    }

    private void clearTextFields() {
        txtStudentId.clear();
        txtStudentName.clear();
        cmbGender.getSelectionModel().clearSelection();
        txtAddress.clear();
        txtContact.clear();
        dpDOB.getEditor().clear();
    }

    private void initUI() {
        clearTextFields();
        disableTextFields();
        btnSave.setDisable(true);
        btnDelete.setDisable(true);
    }

    @FXML
    private void addNewOnAction(ActionEvent actionEvent) {
        clearTextFields();
        enableTextFields();
        txtStudentId.requestFocus();
        btnSave.setDisable(false);
        btnSave.setText(true ? "Save" : "Update");
    }

    @FXML
    private void saveOnAction(ActionEvent actionEvent) {
        String id = txtStudentId.getText();
        String name = txtStudentName.getText();
        String gender = String.valueOf(cmbGender.getValue());
        String address = txtAddress.getText();
        String contact = txtContact.getText();
        String dob = String.valueOf(dpDOB.getValue());

        if (!id.matches("^S[0-9]{3}$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Student ID").show();
            txtStudentId.requestFocus();
            return;
        } else if (!name.matches("^([A-Z a-z]{4,40})$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Student Name").show();
            txtStudentName.requestFocus();
            return;
        } else if (!address.matches("^([A-Z a-z]{4,40})$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Student Address").show();
            txtAddress.requestFocus();
            return;
        } else if (!contact.matches("^(07(0|1|2|4|5|6|7|8)|091)[0-9]{7}$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Student Contact").show();
            txtContact.requestFocus();
            return;
        }

        if (btnSave.getText().equalsIgnoreCase("save")) {
            /*Save Student*/
            boolean isSaved = studentBO.saveStudent(new StudentDTO(id, name, gender, address, contact, dob));
            tblStudent.getItems().add(new StudentTM(id, name, gender, address, contact, dob));
            initUI();
            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Student Saved Successfully!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Please Try Again!").show();
            }

        } else {
            /*Update Student*/
            boolean isUpdated = studentBO.updateStudent(new StudentDTO(id, name, gender, address, contact, dob));
            btnSave.setText(isUpdated ? "Save" : "Update");
            initUI();
            if (isUpdated) {
                new Alert(Alert.AlertType.INFORMATION, "Student Updated Successfully!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Please Try Again!").show();
            }

            StudentTM selectedItem = tblStudent.getSelectionModel().getSelectedItem();
            selectedItem.setId(id);
            selectedItem.setName(name);
            selectedItem.setGender(gender);
            selectedItem.setAddress(address);
            selectedItem.setContact(contact);
            selectedItem.setDob(dob);
            tblStudent.refresh();
        }
    }

    private boolean existStudent(String id) {
        return studentBO.existStudent(id);
    }

    @FXML
    private void searchOnAction(ActionEvent actionEvent) {
        if (existStudent(txtSearch.getText())) {
            tblStudent.getItems().clear();
            ArrayList<StudentDTO> studentDTOArrayList = studentBO.searchStudent(txtSearch.getText());
            initUI();
            if (studentDTOArrayList != null) {
                for (StudentDTO studentDTO : studentDTOArrayList) {
                    tblStudent.getItems().add(new StudentTM(studentDTO.getId(), studentDTO.getName(), studentDTO.getGender(), studentDTO.getAddress(), studentDTO.getContact(), studentDTO.getDob()));
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Please Try Again!").show();
            }
        } else {
            tblStudent.getItems().clear();
            new Alert(Alert.AlertType.ERROR, "There is no student related to this ID").show();
        }
    }

    @FXML
    private void deleteOnAction(ActionEvent actionEvent) {
        String code = tblStudent.getSelectionModel().getSelectedItem().getId();

        boolean isDeleted = studentBO.deleteStudent(code);
        tblStudent.getItems().remove(tblStudent.getSelectionModel().getSelectedItem());
        tblStudent.getSelectionModel().clearSelection();
        initUI();
        if (isDeleted) {
            new Alert(Alert.AlertType.INFORMATION, "Student Deleted Successfully!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Please Try Again!").show();
        }
    }
}
