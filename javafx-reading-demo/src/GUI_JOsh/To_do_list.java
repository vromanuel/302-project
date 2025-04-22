package GUI_JOsh;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class To_do_list extends Application {

    @Override
    public void start(Stage primaryStage) {
        // 타이틀 레이블
        Label titleLabel = new Label("To-Do-List");
        titleLabel.setFont(Font.font("Arial", 30));
        titleLabel.setTextFill(Color.BLACK);

        StackPane titlePane = new StackPane(titleLabel);
        titlePane.setStyle("-fx-background-color: #e8b76d;");
        titlePane.setPrefHeight(100);

        // 포춘 쿠키 이미지
        ImageView fortuneImage = new ImageView(new Image("file:fortune.png")); // 파일명을 6.png로 하면 file:6.png
        fortuneImage.setFitWidth(120);
        fortuneImage.setPreserveRatio(true);

        // 체크박스
        CheckBox box1 = new CheckBox("Open a fortune cookie");
        CheckBox box2 = new CheckBox("Read My Prediction");
        CheckBox box3 = new CheckBox("Get inspired by a quote");

        VBox checklist = new VBox(20, box1, box2, box3);
        checklist.setAlignment(Pos.CENTER_LEFT);
        checklist.setPadding(new Insets(10, 0, 0, 30));

        // 중앙 콘텐츠
        VBox centerLayout = new VBox(30, fortuneImage, checklist);
        centerLayout.setAlignment(Pos.TOP_CENTER);
        centerLayout.setPadding(new Insets(40));

        // 전체 레이아웃
        BorderPane root = new BorderPane();
        root.setTop(titlePane);
        root.setCenter(centerLayout);
        root.setStyle("-fx-background-color: #f7e2c2;");

        Scene scene = new Scene(root, 800, 450);
        primaryStage.setTitle("To-Do List");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
