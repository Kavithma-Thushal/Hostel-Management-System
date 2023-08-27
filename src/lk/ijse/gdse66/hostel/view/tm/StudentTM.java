package lk.ijse.gdse66.hostel.view.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @author : Kavithma Thushal
 * @project : Hostel-Management-System
 * @since : 4:01 PM - 8/26/2023
 **/
@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentTM {
    private String id;
    private String name;
    private String gender;
    private String address;
    private String contact;
    private LocalDate dob;
}
