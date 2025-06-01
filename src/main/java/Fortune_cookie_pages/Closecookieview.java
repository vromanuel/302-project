package Fortune_cookie_pages;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class Closecookieview {
    private StackPane root;
    private VBox mainVBox;
    private StackPane cardPane;
    private VBox cardContent;
    private ImageView cookieImage;
    private Button openBtn;

    private boolean isCookieOpened = false; // checking the cookie is open or not

    public Closecookieview() {
        root = new StackPane();
        root.setPrefSize(1600, 900);
        root.setStyle("-fx-background-color: #f5e1c0;");

        mainVBox = new VBox(30);
        mainVBox.setAlignment(Pos.TOP_CENTER);
        mainVBox.setPadding(new Insets(30));

        cardPane = new StackPane();
        cardPane.setPrefSize(1000, 500);
        cardPane.setMaxSize(1000, 500);
        cardPane.setStyle(
                "-fx-background-color: #fcd38b;" +
                        "-fx-background-radius: 30;" +
                        "-fx-padding: 60;" +
                        "-fx-border-color: #ffc766;" +
                        "-fx-border-width: 2;" +
                        "-fx-border-style: solid;" +
                        "-fx-border-radius: 30;"
        );

        cardContent = new VBox(20);
        cardContent.setAlignment(Pos.CENTER);


        cookieImage = new ImageView(new Image("file:resources/close.cookie.png"));


        cookieImage.setFitWidth(300);
        cookieImage.setPreserveRatio(true);

        openBtn = new Button("Open Another");
        openBtn.setStyle(
                "-fx-background-color: black;" +
                        "-fx-text-fill: white;" +
                        "-fx-pref-height: 50;" +
                        "-fx-font-size: 16;" +
                        "-fx-background-radius: 20;" +
                        "-fx-padding: 10 30 10 30;"
        );

        // 버튼 클릭 시 이미지 변경
        openBtn.setOnAction(e -> {
            if (!isCookieOpened) {
                cookieImage.setImage(new Image("file:resources/open.cookie.png")); // changing cookie image and showing the results
                openBtn.setText("Get Another Fortune");
            } else {

                System.out.println("Preparing new coookies");
            }
        });

        cardContent.getChildren().addAll(cookieImage, openBtn);
        cardPane.getChildren().add(cardContent);

        mainVBox.getChildren().addAll(cardPane);

        root.getChildren().add(mainVBox);
    }

    public StackPane getRoot() {
        return root;
    }

    public Button getOpenButton() {
        return openBtn;
    }
}
