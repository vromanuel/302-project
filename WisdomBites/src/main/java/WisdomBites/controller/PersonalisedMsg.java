package WisdomBites.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PersonalisedMsg {

    @FXML private TextArea predictionBox;
    @FXML private ImageView cookieImage;
    @FXML private Button backButton;
    @FXML private TextField nameField;
    @FXML private TextField subjectField;


    @FXML
    public void handleGenerate() {
        String name = nameField.getText().isBlank() ? "Student" : nameField.getText();
        String subject = subjectField.getText().isBlank() ? "your subject" : subjectField.getText();
        int streakDays = 4;
        int weeksStudied = 3;

        // Run LLM calls in a background thread
        new Thread(() -> {
            String welcome = PMsgController.generateWelcomeMessage(name, subject, streakDays);
            String prediction = PMsgController.generatePrediction(subject, weeksStudied);

            String finalMessage = welcome + "\n\n" + prediction;

            // Update the TextArea on the JavaFX Application Thread
            Platform.runLater(() -> predictionBox.setText(finalMessage));
        }).start();
    }

    @FXML
    private void handleBack() {
        SceneController.switchScene("home_page.fxml");
    }

    @FXML
    public void initialize() {
    }
}

