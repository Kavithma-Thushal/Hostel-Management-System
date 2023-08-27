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
 * @since : 9:07 PM - 8/25/2023
 **/
@NoArgsConstructor
@AllArgsConstructor
@Data

@Cacheable
@Entity
public class Room {
    @Id
    private String id;
    private String type;
    @Column(name = "key_money")
    private double keyMoney;
    @Column(name = "quantity")
    private int qty;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "roomId")
    private List<Reservation> roomList = new ArrayList<>();

    public Room(String id, String type, double keyMoney, int qty) {
        this.id = id;
        this.type = type;
        this.keyMoney = keyMoney;
        this.qty = qty;
    }
}
