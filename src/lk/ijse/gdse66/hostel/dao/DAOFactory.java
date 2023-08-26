package lk.ijse.gdse66.hostel.dao;

import lk.ijse.gdse66.hostel.dao.custom.impl.StudentDAOImpl;

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

    public SuperDAO getDAO(DAOTypes daoTypes) {
        switch (daoTypes) {
            case STUDENT:
                return new StudentDAOImpl();
            default:
                return null;
        }
    }

    public enum DAOTypes {
        STUDENT
    }
}
