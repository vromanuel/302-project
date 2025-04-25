package Fortune_cookie_pages;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class Closecookieview {
    private VBox root;
    private ImageView cookieImage;
    private Button openBtn;

    public Closecookieview() {
        root = new VBox(20);
        root.setAlignment(Pos.CENTER);

        cookieImage = new ImageView(new Image("file:resources/close.cookie.png"));
        cookieImage.setFitWidth(200);
        cookieImage.setPreserveRatio(true);

        openBtn = new Button("Open Another");

        // Just print for now — will link to open page later
        openBtn.setOnAction(e -> System.out.println("TODO: Switch to open cookie page"));

        root.getChildren().addAll(cookieImage, openBtn);
    }

    public VBox getRoot() {
        return root;
    }
}
