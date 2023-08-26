package lk.ijse.gdse66.hostel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Kavithma Thushal
 * @project : Hostel-Management-System
 * @since : 4:18 PM - 8/26/2023
 **/
@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginDTO {
    private String id;
    private String name;
    private String contact;
    private String address;
    private String password;
    private String gender;
}
