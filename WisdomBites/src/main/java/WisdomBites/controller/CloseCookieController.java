package WisdomBites.controller; //

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CloseCookieController {

    @FXML
    private ImageView cookieImage;

    @FXML
    public void initialize() {
        cookieImage.setImage(new Image("file:resources/close.cookie.png"));
    }

    @FXML
    private void handleOpenButton() {
        System.out.println("TODO: Switch to open cookie page");
    }
}
