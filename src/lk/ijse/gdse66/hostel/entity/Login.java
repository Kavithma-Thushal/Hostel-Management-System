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
@Data
@NoArgsConstructor
@AllArgsConstructor

@Cacheable
@Entity
public class Login {
    @Id
    @Column(name = "user_id")
    private String id;
    @Column(name = "user_name")
    private String name;
    private String contact;
    private String address;
    private String password;
    private String gender;
}
