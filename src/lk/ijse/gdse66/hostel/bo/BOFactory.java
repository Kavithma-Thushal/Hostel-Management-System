package lk.ijse.gdse66.hostel.bo;

import lk.ijse.gdse66.hostel.bo.custom.impl.StudentBOImpl;

/**
 * @author : Kavithma Thushal
 * @project : Hostel-Management-System
 * @since : 10:27 PM - 8/26/2023
 **/
public class BOFactory {

    private static BOFactory boFactory;

    private BOFactory() {
    }

    public static BOFactory getBOFactory() {
        return boFactory == null ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOTypes {
        STUDENT
    }

    public SuperBO getBO(BOTypes boTypes) {
        switch (boTypes) {
            case STUDENT:
                return new StudentBOImpl();
            default:
                return null;
        }
    }
}
