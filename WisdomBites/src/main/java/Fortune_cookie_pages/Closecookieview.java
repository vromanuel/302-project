package Fortune_cookie_pages;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class Closecookieview {
    private VBox root;
    private HBox header;
    private ImageView cookieImage;
    private Button openBtn;
    private Button backButton;
    private Label titleLabel;

    public Closecookieview() {
        root = new VBox(20);
        root.setAlignment(Pos.TOP_CENTER);
        root.setStyle("-fx-background-color: #F5E8C7;");

        // ------ 상단바 (Header) ------
        header = new HBox();
        header.setAlignment(Pos.CENTER_LEFT);
        header.setPadding(new Insets(10));
        header.setSpacing(10);
        header.setStyle("-fx-background-color: #E2B165; -fx-background-radius: 0 0 20 20;");

        // 뒤로가기 버튼
        backButton = new Button("←");
        backButton.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-font-size: 18px;" +
                        "-fx-text-fill: black;"
        );

        // 타이틀 (중앙 정렬을 위해 HBox를 가운데로도 맞춰야 함)
        titleLabel = new Label("Fortune");
        titleLabel.setStyle(
                "-fx-font-size: 20px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: black;"
        );
        HBox.setHgrow(titleLabel, Priority.ALWAYS);
        titleLabel.setMaxWidth(Double.MAX_VALUE);
        titleLabel.setAlignment(Pos.CENTER);

        header.getChildren().addAll(backButton, titleLabel);

        // ------ 메인 콘텐츠 ------
        cookieImage = new ImageView(new Image("file:resources/close.cookie.png"));
        cookieImage.setFitWidth(200);
        cookieImage.setPreserveRatio(true);

        openBtn = new Button("Open Another");
        openBtn.setStyle(
                "-fx-background-color: #D19E5A;" +
                        "-fx-text-fill: black;" +
                        "-fx-font-size: 14px;" +
                        "-fx-background-radius: 20;" +
                        "-fx-padding: 5 20 5 20;"
        );

        // 버튼 클릭 액션 (TODO)
        openBtn.setOnAction(e -> System.out.println("TODO: Switch to open cookie page"));

        // 최종 root에 상단바 + 이미지 + 버튼 추가
        root.getChildren().addAll(header, cookieImage, openBtn);
    }

    public VBox getRoot() {
        return root;

    }

    public Button getBackButton() {
        return backButton;
    }
}
