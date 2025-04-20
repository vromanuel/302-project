package WisdomBites.controller;

import WisdomBites.model.UserDao;
import WisdomBites.model.User;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;         // ✅ Correctly placed import
import javafx.scene.layout.AnchorPane;  // Optional if you're still using AnchorPane elsewhere
import javafx.scene.shape.Circle;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;
import javafx.scene.layout.StackPane;


public class LoginRegisterController {

    @FXML private TextField emailField;
    @FXML private TextField passWordField;
    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;

    @FXML private VBox loginPane;
    @FXML private VBox registerPane;

    @FXML
    public void loginButtonHandle() {
        String email = emailField.getText();
        String passWord = passWordField.getText();
        User user = UserDao.login(email, passWord);

        if(user != null) {
            System.out.println(user.getFirstName());
            System.out.println("Successful!");
        } else {
            System.out.println("unsuccessful");
        }
    }

    @FXML
    public void registerButtonHandle() {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String email = emailField.getText();
        String passWord = passWordField.getText();

        boolean registrationSuccessful = UserDao.registerUser(firstName, lastName, email, passWord);

        if(registrationSuccessful) {
            System.out.println("Successful!");
        } else {
            System.out.println("unsuccessful");
        }
    }

    public void switchToRegister() {
        SceneController.switchScene("register_view.fxml");
    }

    //tariq

    @FXML
    public void initialize() {
        toggleThumb.setTranslateX(-15); // move left initially
    }

    @FXML
    private void flipCard() {
        isLogin = !isLogin;

        loginPane.setVisible(isLogin);
        registerPane.setVisible(!isLogin);

        TranslateTransition slide = new TranslateTransition(Duration.millis(200), toggleThumb);
        slide.setToX(isLogin ? -15 : 15); // slide left or right
        slide.play();
    }

    @FXML private StackPane toggleSwitch;
    @FXML private Circle toggleThumb;

    private boolean isLogin = true;

}
