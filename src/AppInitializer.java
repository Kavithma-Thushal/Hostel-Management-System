import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author : Kavithma Thushal
 * @project : Hostel-Management-System
 * @since : 10:05 PM - 8/24/2023
 **/
public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("lk/ijse/gdse66/hostel/view/login_form.fxml"))));
        primaryStage.setTitle("Login");
        primaryStage.setResizable(false);
        //primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
    }
}
