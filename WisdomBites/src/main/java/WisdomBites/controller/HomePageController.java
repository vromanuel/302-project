package WisdomBites.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class HomePageController {

    @FXML
    private ImageView cookieImage;

    @FXML private Label welcomeLabel;
    @FXML
    private Button quotesButton, predictionsButton, todoButton, fortuneButton, progressButton, challengesButton;

    @FXML
    public void initialize() {
        cookieImage.setImage(new Image(getClass().getResource("/close.cookie.png").toExternalForm()));
        welcomeLabel.setText(String.format("Welcome, %s!", StateController.currentUser.getFirstName()));
        fortuneButton.setOnAction(e -> {
            System.out.println("Get your fortune clicked!");
        });
    }
}
