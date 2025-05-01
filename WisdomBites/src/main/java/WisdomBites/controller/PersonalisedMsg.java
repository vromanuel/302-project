package WisdomBites.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;

public class PersonalisedMsg {

    @FXML
    private TextArea predictionBox;

    @FXML
    private ImageView cookieImage;

    @FXML
    public void handleGenerate() {
        String name = "Tariq";
        String subject = "CAB 123";
        int streakDays = 4;
        int weeksStudied = 3;

        String welcome = PMsgController.generateWelcomeMessage(name, subject, streakDays);
        String prediction = PMsgController.generatePrediction(subject, weeksStudied);

        predictionBox.setText(welcome + "\n\n" + prediction);
    }

    @FXML
    private void handleBack() {
        SceneController.switchScene("home_page.fxml");
    }


    @FXML
    private Button backButton;

    @FXML
    public void initialize() {


        backButton.setOnAction(e -> {
            SceneController.switchScene("home_page.fxml");
        });

    }

}

