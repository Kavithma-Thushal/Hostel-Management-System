package lk.ijse.gdse66.hostel.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

/**
 * @author : Kavithma Thushal
 * @project : Hostel-Management-System
 * @since : 9:58 PM - 9/3/2023
 **/
public class UILoader {

    public static void dashboardUILoader(AnchorPane root, String location) throws IOException {
        root.getChildren().clear();
        root.getChildren().add(FXMLLoader.load(UILoader.class.getResource("/lk/ijse/gdse66/hostel/view/" + location + ".fxml")));
    }
}
