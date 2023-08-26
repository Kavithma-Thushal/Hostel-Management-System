package lk.ijse.gdse66.hostel.dto;

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
public class StudentDTO {
    private String id;
    private String name;
    private String contact;
    private String address;
    private LocalDate dob;
    private String gender;
}
