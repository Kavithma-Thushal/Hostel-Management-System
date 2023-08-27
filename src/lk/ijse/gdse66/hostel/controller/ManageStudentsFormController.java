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
import javafx.scene.layout.AnchorPane;
import lk.ijse.gdse66.hostel.bo.BOFactory;
import lk.ijse.gdse66.hostel.bo.custom.StudentBO;
import lk.ijse.gdse66.hostel.dto.StudentDTO;
import lk.ijse.gdse66.hostel.view.tm.StudentTM;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * @author : Kavithma Thushal
 * @project : Hostel-Management-System
 * @since : 10:00 AM - 8/25/2023
 **/
public class ManageStudentsFormController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private JFXTextField txtSearch;
    @FXML
    private JFXTextField txtStudentId;
    @FXML
    private JFXTextField txtStudentName;
    @FXML
    private JFXTextField txtContact;
    @FXML
    private JFXTextField txtAddress;
    @FXML
    private JFXDatePicker dpDOB;
    @FXML
    private JFXComboBox cmbGender;
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
        setCellvalue();
        loadAllStudents();
    }

    private void setComboElements() {
        cmbGender.getItems().addAll("Male", "Female", "Other");
    }

    private void setCellvalue() {
        tblStudent.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblStudent.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblStudent.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("gender"));
        tblStudent.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("address"));
        tblStudent.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("contact"));
        tblStudent.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("dob"));
    }

    private void loadAllStudents() {
        ArrayList<StudentDTO> allStudentsDTO = studentBO.getAllStudents();
        for (StudentDTO studentDTO : allStudentsDTO) {
            tblStudent.getItems().add(new StudentTM(studentDTO.getId(), studentDTO.getName(), studentDTO.getGender(), studentDTO.getAddress(), studentDTO.getContact(), studentDTO.getDob()));
        }
    }

    @FXML
    private void addNewOnAction(ActionEvent actionEvent) {

    }

    @FXML
    private void saveOnAction(ActionEvent actionEvent) {
        String id = txtStudentId.getText();
        String name = txtStudentName.getText();
        String gender = String.valueOf(cmbGender.getValue());
        String address = txtAddress.getText();
        String contact = txtContact.getText();
        String dob = String.valueOf(dpDOB.getValue());

        boolean isStudentSaved = studentBO.saveStudent(new StudentDTO(id, name, gender, address, contact, dob));
        tblStudent.getItems().add(new StudentTM(id, name, gender, address, contact, dob));
        if (isStudentSaved) {
            new Alert(Alert.AlertType.INFORMATION, "Student Saved Successfully!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Please Try Again!").show();
        }
    }

    @FXML
    private void searchOnAction(ActionEvent actionEvent) {

    }

    @FXML
    private void deleteOnAction(ActionEvent actionEvent) {

    }
}
