package WisdomBites.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;

public class DailyTipController {
    @FXML private TextArea tipBox;
    @FXML private Button backButton;

    @FXML
    public void initialize() {
        handleTip();  // auto-load tip on open
    }

    @FXML
    public void handleTip() {
        new Thread(() -> {
            String prompt = "Give a short motivational tip for a student who’s studying today. No longer than 2 sentences.";
            String tip = PMsgController.getAIResponse(prompt, "Stay focused and believe in your ability to learn and grow.");
            Platform.runLater(() -> tipBox.setText(tip));
        }).start();
    }

    @FXML
    public void handleBack() {
        SceneController.switchScene("home_page.fxml");
    }
}