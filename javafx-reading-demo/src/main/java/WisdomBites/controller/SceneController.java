package WisdomBites.controller;

import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneController {
    private static Stage currentStage;

    public static void setStage(Stage stage) {
        currentStage = stage;
    }

    public static void switchScene(String fileName) {
        try {
            Parent root = FXMLLoader.load(SceneController.class.getResource("/WisdomBites/view/" + fileName));
            currentStage.setScene(new Scene(root));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
