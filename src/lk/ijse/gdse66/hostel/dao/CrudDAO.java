package lk.ijse.gdse66.hostel.dao;

import java.util.ArrayList;

/**
 * @author : Kavithma Thushal
 * @project : Hostel-Management-System
 * @since : 10:41 PM - 8/26/2023
 **/
public interface CrudDAO<Entity> extends SuperDAO {

    ArrayList<Entity> loadAll();

    boolean save(Entity entity);

    Entity search(String id);

    boolean update(Entity entity);

    boolean delete(String id);

    boolean exist(String code);

    String generateNextId();
}
