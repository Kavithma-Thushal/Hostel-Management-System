package lk.ijse.gdse66.hostel.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Kavithma Thushal
 * @project : Hostel-Management-System
 * @since : 8:25 PM - 9/8/2023
 **/
@Getter
@Setter
public class Custom {

    //Reservation
    private String resId;
    private String date;
    private String status;
    private String roomQty;
    private Student student;
    private Room room;

    //Student
    private String studentId;
    private String name;
    private String gender;
    private String address;
    private String contact;
    private String dob;

    //Room
    private String roomId;
    private String type;
    private String keyMoney;
    private int qty;
    private List<Reservation> reservationList = new ArrayList<>();
}
