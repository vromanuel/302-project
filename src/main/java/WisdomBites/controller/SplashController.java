package WisdomBites.controller;

import javafx.application.Platform;
import javafx.animation.FadeTransition;
import javafx.animation.SequentialTransition;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class SplashController {
    @FXML private ImageView logoView;
    @FXML private StackPane root;

    @FXML
    private void initialize() {
        // Load the logo image
        logoView.setImage(new Image(SplashController.class.getResource("/WisdomBites/logo.png").toExternalForm()));

        // Fade-in animation
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(1.5), root);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);

        // Pause after fade-in
        FadeTransition pause = new FadeTransition(Duration.seconds(1), root);
        pause.setFromValue(1.0);
        pause.setToValue(1.0); // no visual change, just delay

        // Fade-out animation
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(1.5), root);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);

        fadeOut.setOnFinished(event -> {
            Platform.runLater(() -> SceneController.switchScene("login_view.fxml"));
        });

        new SequentialTransition(fadeIn, pause, fadeOut).play();
    }
}

