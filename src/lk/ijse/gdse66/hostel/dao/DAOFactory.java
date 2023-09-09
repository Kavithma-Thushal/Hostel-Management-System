package lk.ijse.gdse66.hostel.dao;

import lk.ijse.gdse66.hostel.dao.custom.impl.*;

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
        STUDENT, ROOM, USER, RESERVE, QUERY
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
            case QUERY:
                return new QueryDAOImpl();
            default:
                return null;
        }
    }
}
