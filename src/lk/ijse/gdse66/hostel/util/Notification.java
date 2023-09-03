package lk.ijse.gdse66.hostel.util;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * @author : Kavithma Thushal
 * @project : Hostel-Management-System
 * @since : 1:43 PM - 9/3/2023
 **/
public class Notification {

    public static void userLogin(String location,String title,String text,int duration){
        Image img = new Image("lk/ijse/gdse66/hostel/view/assets/images/"+location, 90, 90, false, false);
        Notifications notificationBuilder = Notifications.create()
                .title(title)
                .text(text)
                .graphic(new ImageView(img))
                .hideAfter(Duration.seconds(duration))
                .position(Pos.BOTTOM_RIGHT);
        notificationBuilder.darkStyle();
        notificationBuilder.show();
    }
}
