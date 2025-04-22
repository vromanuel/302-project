package WisdomBites.controller;

import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneController {
    // stores the current stage displayed
    public static Stage currentStage;

    public static void setStage(Stage stage) {
        currentStage = stage;
    }

    public static void switchScene(String fileName) {
        // Switches the scene displayed to the user

        // Attempt to access the FXML file for the desired scene, switch to it, and show it to the user
        try {
            Parent root = FXMLLoader.load(SceneController.class.getResource("/WisdomBites/" + fileName));
            currentStage.setScene(new Scene(root));
            currentStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
