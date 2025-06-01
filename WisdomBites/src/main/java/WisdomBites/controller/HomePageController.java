package WisdomBites.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class HomePageController {

    @FXML private Label welcomeLabel;
    @FXML private ImageView cookieImage;
    @FXML private Label navDateTime;

    @FXML private Button predictionsButton, todoButton, fortuneButton, studyTrackerButton, syllabusButton, dailyTipButton;
    @FXML private Button logOutButton;

    public static void logout()
    {
        StateController.currentUser= null;
        SceneController.switchScene("login_view.fxml");
    }

    @FXML
    public void initialize() {
        // welcome text
        welcomeLabel.setText(String.format("Welcome, %s!", StateController.currentUser.getFirstName()));

        // fortune image
        cookieImage.setImage(new Image(getClass().getResource("/home_page_logo.png").toExternalForm()));

        // Update nav bar time every second
        Timeline navTimeUpdater = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            LocalTime now = LocalTime.now();
            String timeStr = now.format(DateTimeFormatter.ofPattern("h:mm a"));
            String dateStr = LocalDate.now().format(DateTimeFormatter.ofPattern("MMM d"));
            navDateTime.setText("📅 " + dateStr + "  |  ⏰ " + timeStr);

        }
        ));
        navTimeUpdater.setCycleCount(Timeline.INDEFINITE);
        navTimeUpdater.play();

        // button actions
        fortuneButton.setOnAction(e -> SceneController.switchScene("fortune_view.fxml"));
        predictionsButton.setOnAction(e -> {
            SceneController.switchScene("personalised_msg.fxml");
        });
        todoButton.setOnAction((e -> SceneController.switchScene("create_task.fxml")));
        studyTrackerButton.setOnAction((e -> SceneController.switchScene("StudyTracker.fxml")));
        syllabusButton.setOnAction(e -> SceneController.switchScene("syllabus_page.fxml"));
        logOutButton.setOnAction(e -> logout());
    }
}