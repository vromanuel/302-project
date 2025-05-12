package WisdomBites.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.util.Duration;

public class HomePageController {

    @FXML private ImageView cookieImage;
    @FXML private Label welcomeLabel, digitalClockLabel;
    @FXML private Pane analogClockPane;
    @FXML private Label dateLabel;

    @FXML private Button predictionsButton, todoButton, fortuneButton, studyTrackerButton, challengesButton;
    @FXML private Button logOutButton;

    private Line hourHand, minuteHand, secondHand;

    public static void logout()
    {
        StateController.currentUser= null;
        SceneController.switchScene("login_view.fxml");
    }

    @FXML
    public void initialize() {
        // date
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("EEEE, MMMM d");
        dateLabel.setText(LocalDate.now().format(dateFormat));

        // Digital + analog clock setup
        drawAnalogClock();
        startClockUpdates();

        // fortune image
        cookieImage.setImage(new Image(getClass().getResource("/close.cookie.png").toExternalForm()));

        // welcome text
        welcomeLabel.setText(String.format("Welcome, %s!", StateController.currentUser.getFirstName()));

        // button actions
        fortuneButton.setOnAction(e -> {
            System.out.println("Get your fortune clicked!");
        });

        predictionsButton.setOnAction(e -> {
            SceneController.switchScene("personalised_msg.fxml");
        });

        logOutButton.setOnAction(e -> logout());

        todoButton.setOnAction((e -> SceneController.switchScene("create_task.fxml")));

        studyTrackerButton.setOnAction((e -> SceneController.switchScene("StudyTracker.fxml")));
    }


    private void drawAnalogClock() {
        double centerX = 75;
        double centerY = 75;
        double radius = 70;

        Circle clockFace = new Circle(centerX, centerY, radius);
        clockFace.setStyle("-fx-fill: #5a3921; -fx-stroke: #efddbb; -fx-stroke-width: 2;");

        hourHand = new Line(centerX, centerY, centerX, centerY - 30);
        hourHand.setStrokeWidth(4);
        hourHand.setStyle("-fx-stroke: #efddbb;");

        minuteHand = new Line(centerX, centerY, centerX, centerY - 40);
        minuteHand.setStrokeWidth(3);
        minuteHand.setStyle("-fx-stroke: #7b5235;");

        secondHand = new Line(centerX, centerY, centerX, centerY - 50);
        secondHand.setStrokeWidth(1);
        secondHand.setStyle("-fx-stroke: red;");

        analogClockPane.getChildren().addAll(clockFace, hourHand, minuteHand, secondHand);
    }

    private void startClockUpdates() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> updateClock()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        updateClock();
    }

    private void updateClock() {
        LocalTime now = LocalTime.now();
        digitalClockLabel.setText(now.format(DateTimeFormatter.ofPattern("hh:mm:ss a")));

        double centerX = 75;
        double centerY = 75;

        double secondAngle = now.getSecond() * 6;
        double minuteAngle = now.getMinute() * 6 + secondAngle / 60;
        double hourAngle = ((now.getHour() % 12) + now.getMinute() / 60.0) * 30;

        rotateHand(secondHand, secondAngle, 50, centerX, centerY);
        rotateHand(minuteHand, minuteAngle, 40, centerX, centerY);
        rotateHand(hourHand, hourAngle, 30, centerX, centerY);
    }

    private void rotateHand(Line hand, double angle, double length, double centerX, double centerY) {
        double rad = Math.toRadians(angle - 90);
        double endX = centerX + length * Math.cos(rad);
        double endY = centerY + length * Math.sin(rad);
        hand.setEndX(endX);
        hand.setEndY(endY);
    }
}
