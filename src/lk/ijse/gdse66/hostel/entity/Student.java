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
    private String contact;
    private String address;
    private String dob;
    private String gender;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "studentId")
    private List<Reservation> studentList = new ArrayList<>();

    public Student(String id, String name, String contact, String address, String dob, String gender) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.address = address;
        this.dob = dob;
        this.gender = gender;
    }
}
