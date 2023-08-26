package lk.ijse.gdse66.hostel.view.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Kavithma Thushal
 * @project : Hostel-Management-System
 * @since : 4:10 PM - 8/26/2023
 **/
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoomTM {
    private String id;
    private String type;
    private double keyMoney;
    private int qty;
}
