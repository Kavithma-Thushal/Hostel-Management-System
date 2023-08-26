package lk.ijse.gdse66.hostel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
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
    private LocalDate dob;
    private String gender;

    /*@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "id")
    private List<Reservation> studentList = new ArrayList<>();*/
}
