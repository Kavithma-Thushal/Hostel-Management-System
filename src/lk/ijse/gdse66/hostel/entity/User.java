package lk.ijse.gdse66.hostel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author : Kavithma Thushal
 * @project : Hostel-Management-System
 * @since : 9:29 PM - 8/25/2023
 **/
@NoArgsConstructor
@AllArgsConstructor
@Data

@Cacheable
@Entity
public class User {
    @Id
    private String id;
    @Column(name = "user_name")
    private String userName;
    private String gender;
    private String address;
    private String contact;
    @Column(name = "user_password")
    private String userPassword;
}
