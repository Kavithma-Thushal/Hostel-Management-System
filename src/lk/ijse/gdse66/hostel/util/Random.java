package lk.ijse.gdse66.hostel.util;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.File;

/**
 * @author : Kavithma Thushal
 * @project : Hostel-Management-System
 * @since : 9:42 PM - 9/3/2023
 **/
public class Random {
    private static final String IMAGE_DIR_PATH = "/D:\\IJSE\\Workspace\\2nd Sem Repo\\Hostel-Management-System\\src\\lk\\ijse\\gdse66\\hostel\\view\\assets\\random";
    private static final Duration IMAGE_CHANGE_INTERVAL = Duration.seconds(4);

    private final java.util.Random random = new java.util.Random();

    private File[] imageFiles;
    private AnchorPane root;
    private Timeline imageChangeTimeline;

    public Random(AnchorPane root) {
        // Load images from directory
        File imageDir = new File(IMAGE_DIR_PATH);
        if (!imageDir.isDirectory()) {
            System.err.println("Invalid image directory path: " + IMAGE_DIR_PATH);
            System.exit(1);
        }
        imageFiles = imageDir.listFiles((dir, name) -> name.endsWith(".jpg") || name.endsWith(".png"));

        // Set root AnchorPane
        this.root = root;

        // Start timeline to change images periodically
        imageChangeTimeline = new Timeline(new KeyFrame(IMAGE_CHANGE_INTERVAL, event -> setRandomImage()));
        imageChangeTimeline.setCycleCount(Timeline.INDEFINITE);
        imageChangeTimeline.play();
    }

    private void setRandomImage() {
        // Select a random image file
        int randomIndex = random.nextInt(imageFiles.length);
        File randomImageFile = imageFiles[randomIndex];

        // Load image and set as background of AnchorPane
        Image randomImage = new Image(randomImageFile.toURI().toString());
        ImageView imageView = new ImageView(randomImage);
        imageView.fitWidthProperty().bind(root.widthProperty());
        imageView.fitHeightProperty().bind(root.heightProperty());
        root.getChildren().clear();
        root.getChildren().add(imageView);
    }
}
