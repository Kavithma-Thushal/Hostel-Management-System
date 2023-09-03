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
import lk.ijse.gdse66.hostel.bo.custom.RoomBO;
import lk.ijse.gdse66.hostel.dto.RoomDTO;
import lk.ijse.gdse66.hostel.view.tm.RoomTM;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * @author : Kavithma Thushal
 * @project : Hostel-Management-System
 * @since : 9:36 AM - 8/25/2023
 **/
public class ManageRoomsFormController implements Initializable {

    @FXML
    private JFXTextField txtRoomId;
    @FXML
    private JFXComboBox cmbRoomType;
    @FXML
    private JFXTextField txtKeyMoney;
    @FXML
    private JFXTextField txtQty;
    @FXML
    private JFXTextField txtSearch;
    @FXML
    private TableView<RoomTM> tblRoom;
    @FXML
    private JFXButton btnAddNew;
    @FXML
    private JFXButton btnSave;
    @FXML
    private JFXButton btnDelete;
    private final RoomBO roomBO = (RoomBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.ROOM);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cmbRoomType.getItems().addAll("AC", "AC/Food", "Non-AC", "Non-AC/Food");
        setCellValue();
        selectTableElements();
        initUI();
        loadAllRooms();
    }

    private void setCellValue() {
        tblRoom.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblRoom.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("type"));
        tblRoom.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("keyMoney"));
        tblRoom.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("qty"));
    }

    private void selectTableElements() {
        tblRoom.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnSave.setText(newValue != null ? "Update" : "Save");

            if (newValue != null) {
                txtRoomId.setText(newValue.getId());
                cmbRoomType.setValue(newValue.getType());
                txtKeyMoney.setText(newValue.getKeyMoney());
                txtQty.setText(newValue.getQty());

                txtSearch.clear();
                enableTextFields();
                btnSave.setDisable(false);
                btnDelete.setDisable(false);
            }
        });
    }

    private void enableTextFields() {
        txtRoomId.setDisable(false);
        cmbRoomType.setDisable(false);
        txtKeyMoney.setDisable(false);
        txtQty.setDisable(false);
    }

    private void disableTextFields() {
        txtRoomId.setDisable(true);
        cmbRoomType.setDisable(true);
        txtKeyMoney.setDisable(true);
        txtQty.setDisable(true);
    }

    private void clearTextFields() {
        txtRoomId.clear();
        cmbRoomType.getSelectionModel().clearSelection();
        txtKeyMoney.clear();
        txtQty.clear();
    }

    private void initUI() {
        clearTextFields();
        txtSearch.clear();
        disableTextFields();
        btnSave.setDisable(true);
        btnDelete.setDisable(true);
    }

    private boolean existRoom(String id) {
        return roomBO.existRoom(id);
    }

    private void generateNextRoomId() {
        String nextId = roomBO.generateNextRoomId();
        txtRoomId.setText(nextId);
    }

    private void loadAllRooms() {
        ArrayList<RoomDTO> roomDTOArrayList = roomBO.loadAllRooms();
        for (RoomDTO roomDTO : roomDTOArrayList) {
            tblRoom.getItems().add(new RoomTM(roomDTO.getId(), roomDTO.getType(), roomDTO.getKeyMoney(), roomDTO.getQty()));
        }
    }

    @FXML
    private void addNewOnAction(ActionEvent actionEvent) {
        clearTextFields();
        txtSearch.clear();
        enableTextFields();
        cmbRoomType.requestFocus();
        btnSave.setDisable(false);
        btnSave.setText(true ? "Save" : "Update");
        generateNextRoomId();
    }

    @FXML
    private void saveOnAction(ActionEvent actionEvent) {
        String id = txtRoomId.getText();
        String type = String.valueOf(cmbRoomType.getValue());
        String keyMoney = txtKeyMoney.getText();
        String qty = txtQty.getText();

        /*if (!keyMoney.matches("^([A-Z a-z]{4,40})$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Room Key Money").show();
            txtKeyMoney.requestFocus();
            return;
        } else if (!qty.matches("^(07(0|1|2|4|5|6|7|8)|091)[0-9]{7}$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Room Quantity").show();
            txtQty.requestFocus();
            return;
        }*/

        if (btnSave.getText().equalsIgnoreCase("Save")) {
            /*Save Room*/
            boolean isSaved = roomBO.saveRoom(new RoomDTO(id, type, keyMoney, qty));
            tblRoom.getItems().add(new RoomTM(id, type, keyMoney, qty));
            initUI();
            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Room Saved Successfully!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Please Try Again!").show();
            }

        } else {
            /*Update Room*/
            boolean isUpdated = roomBO.updateRoom(new RoomDTO(id, type, keyMoney, qty));
            btnSave.setText(isUpdated ? "Save" : "Update");
            initUI();
            if (isUpdated) {
                new Alert(Alert.AlertType.INFORMATION, "Room Updated Successfully!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Please Try Again!").show();
            }

            RoomTM selectedItem = tblRoom.getSelectionModel().getSelectedItem();
            selectedItem.setId(id);
            selectedItem.setType(type);
            selectedItem.setKeyMoney(keyMoney);
            selectedItem.setQty(qty);

            tblRoom.refresh();
            tblRoom.getSelectionModel().clearSelection();
        }
    }

    @FXML
    private void searchOnAction(ActionEvent actionEvent) {
        if (existRoom(txtSearch.getText())) {
            tblRoom.getItems().clear();
            ArrayList<RoomDTO> roomDTOArrayList = roomBO.searchRoom(txtSearch.getText());
            initUI();
            if (roomDTOArrayList != null) {
                for (RoomDTO roomDTO : roomDTOArrayList) {
                    tblRoom.getItems().add(new RoomTM(roomDTO.getId(), roomDTO.getType(), roomDTO.getKeyMoney(), roomDTO.getQty()));
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Please Try Again!").show();
            }
        } else {
            tblRoom.getItems().clear();
            new Alert(Alert.AlertType.ERROR, "There is no room related to this ID").show();
        }
    }

    @FXML
    private void deleteOnAction(ActionEvent actionEvent) {
        String code = tblRoom.getSelectionModel().getSelectedItem().getId();

        boolean isDeleted = roomBO.deleteRoom(code);
        tblRoom.getItems().remove(tblRoom.getSelectionModel().getSelectedItem());
        tblRoom.getSelectionModel().clearSelection();
        initUI();
        if (isDeleted) {
            new Alert(Alert.AlertType.INFORMATION, "Room Deleted Successfully!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Please Try Again!").show();
        }
    }
}
