package lk.ijse.gdse66.hostel.bo.custom;

import lk.ijse.gdse66.hostel.bo.SuperBO;

import java.util.List;

/**
 * @author : Kavithma Thushal
 * @project : Hostel-Management-System
 * @since : 1:52 PM - 9/1/2023
 **/
public interface ReserveBO extends SuperBO {

    List<String> loadStudentIds();
}
