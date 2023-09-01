package lk.ijse.gdse66.hostel.dao;

import lk.ijse.gdse66.hostel.dao.custom.impl.ReserveDAOImpl;
import lk.ijse.gdse66.hostel.dao.custom.impl.RoomDAOImpl;
import lk.ijse.gdse66.hostel.dao.custom.impl.StudentDAOImpl;
import lk.ijse.gdse66.hostel.dao.custom.impl.UserDAOImpl;

/**
 * @author : Kavithma Thushal
 * @project : Hostel-Management-System
 * @since : 10:42 PM - 8/26/2023
 **/
public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getDAOFactory() {
        return daoFactory == null ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        STUDENT, ROOM, USER, RESERVE
    }

    public SuperDAO getDAO(DAOTypes daoTypes) {
        switch (daoTypes) {
            case STUDENT:
                return new StudentDAOImpl();
            case ROOM:
                return new RoomDAOImpl();
            case USER:
                return new UserDAOImpl();
            case RESERVE:
                return new ReserveDAOImpl();
            default:
                return null;
        }
    }
}
