package lk.ijse.gdse66.hostel.view.tm;

import lk.ijse.gdse66.hostel.entity.Reservation;
import lk.ijse.gdse66.hostel.entity.Room;
import lk.ijse.gdse66.hostel.entity.Student;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Kavithma Thushal
 * @project : Hostel-Management-System
 * @since : 11:30 PM - 9/8/2023
 **/
@Data
public class CustomTM {
    //Reservation
    private String resId;
    private String date;
    private String status;
    private String roomQty;
    private Student student;
    private Room room;

    //Student
    //private String studentId;
    private String name;
    //private String gender;
    //private String address;
    //private String contact;
    //private String dob;

    //Room
    //private String roomId;
    private String type;
    private String keyMoney;
    private int qty;
    private List<Reservation> reservationList = new ArrayList<>();

    public CustomTM(String resId, String date, String status, String roomQty, String name, String type, String keyMoney, int qty) {
        this.resId = resId;
        this.date = date;
        this.status = status;
        this.roomQty = roomQty;
        this.name = name;
        this.type = type;
        this.keyMoney = keyMoney;
        this.qty = qty;
    }
}
