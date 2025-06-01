package WisdomBites.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.List;
import java.util.Random;

public class FortuneController {

    @FXML private ImageView cookieImage;
    @FXML private Label fortuneText;
    @FXML private Label subtitleText;
    @FXML private Button crackButton;

    private boolean isCracked = false;

    private final List<String> fortunes = List.of(
            "You will conquer challenges with grace.",
            "Today is a perfect day to learn something new.",
            "Believe in yourself and all that you are.",
            "A small act of kindness can brighten someone’s day.",
            "You’re on your way to something great!"
    );

    private final Image closedCookie = new Image("/close.cookie.png");
    private final Image openCookie = new Image("/open.cookie.png");
    private final Random random = new Random();

    @FXML
    public void initialize() {
        cookieImage.setImage(closedCookie);
        fortuneText.setText("");
        subtitleText.setVisible(false); // Hide subtitle initially
    }

    @FXML
    public void handleCrack() {
        if (!isCracked) {
            cookieImage.setImage(openCookie);
            fortuneText.setText(getRandomFortune());
            crackButton.setText("Crack another one");
            subtitleText.setVisible(true); // Show subtitle when cracked
            isCracked = true;
        } else {
            cookieImage.setImage(closedCookie);
            fortuneText.setText("");
            crackButton.setText("Crack open your cookie");
            subtitleText.setVisible(false);
            isCracked = false;
        }
    }

    @FXML
    public void goHome() {
        SceneController.switchScene("home_page.fxml");
    }

    private String getRandomFortune() {
        return fortunes.get(random.nextInt(fortunes.size()));
    }
}
