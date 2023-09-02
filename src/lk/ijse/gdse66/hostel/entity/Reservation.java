package lk.ijse.gdse66.hostel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * @author : Kavithma Thushal
 * @project : Hostel-Management-System
 * @since : 9:11 PM - 8/25/2023
 **/
@NoArgsConstructor
@AllArgsConstructor
@Data

@Cacheable
@Entity
public class Reservation {
    @Id
    @Column(name = "res_id")
    private String reserveId;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student studentId;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room roomId;

    private String date;
    private String status;
}
