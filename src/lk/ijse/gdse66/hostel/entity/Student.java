package lk.ijse.gdse66.hostel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : Kavithma Thushal
 * @project : Hostel-Management-System
 * @since : 8:29 PM - 8/25/2023
 **/
@NoArgsConstructor
@AllArgsConstructor
@Data

@Cacheable
@Entity
public class Student {
    @Id
    private String id;
    private String name;
    private String gender;
    private String address;
    private String contact;
    private String dob;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "studentId")
    private List<Reservation> studentList = new ArrayList<>();

    public Student(String id, String name, String gender, String address, String contact, String dob) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.address = address;
        this.contact = contact;
        this.dob = dob;
    }
}
