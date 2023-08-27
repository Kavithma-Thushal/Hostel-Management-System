package lk.ijse.gdse66.hostel.dao;

import java.util.ArrayList;

/**
 * @author : Kavithma Thushal
 * @project : Hostel-Management-System
 * @since : 10:41 PM - 8/26/2023
 **/
public interface CrudDAO<Entity, Type> extends SuperDAO {
    boolean save(Entity entity);

    ArrayList<Entity> getAll();
}
