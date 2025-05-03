package WisdomBites.controller;

import WisdomBites.Main.HelloApplication;
import WisdomBites.model.UserDao;
import WisdomBites.model.User;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.animation.TranslateTransition;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.layout.StackPane;

import java.io.IOException;


public class LoginRegisterController {

    @FXML private TextField loginEmailField;
    @FXML private TextField loginPassWordField;
    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;

    @FXML private TextField registerEmailField;
    @FXML private TextField confirmEmailField;
    @FXML private TextField registerPassWordField;

    @FXML private VBox loginPane;
    @FXML private VBox registerPane;

    @FXML private Label registerStatusLabel;
    @FXML private Label loginStatusLabel;


    @FXML
    public void loginButtonHandle() {
        String email = loginEmailField.getText();
        String passWord = loginPassWordField.getText();

        if (email.isEmpty() || passWord.isEmpty())
        {
            loginStatusLabel.setText("Registration unsuccessful! Fields must not be left blank");
            loginStatusLabel.setTextFill(Color.RED);
            return;
        }



        User user = UserDao.login(email, passWord);
        StateController.setCurrentUser(user);


        if(user != null) {
            loginStatusLabel.setText("Registration successful! you have logged in!");
            loginStatusLabel.setTextFill(Color.GREEN);
            SceneController.switchScene("home_page.fxml");

        } else {
            loginStatusLabel.setText("Registration unsuccessful! Your credentials do not match our system!");
            loginStatusLabel.setTextFill(Color.RED);
           
        }


    }

    @FXML
    public void registerButtonHandle() {

        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String email = registerEmailField.getText();
        String passWord = registerPassWordField.getText();
        String confirmEmail = confirmEmailField.getText();

        if (!email.equals(confirmEmail))
        {
            registerStatusLabel.setText("Emails do not match!!");
            registerStatusLabel.setTextFill(Color.RED);
            return;
        }

        if (email.length() <= 8)
        {
            registerStatusLabel.setText("Email too short!");
            registerStatusLabel.setTextFill(Color.RED);
            return;
        }

        if (passWord.length() <=8)
        {
            registerStatusLabel.setText("Password must be more than 8 characters!");
            registerStatusLabel.setTextFill(Color.RED);
            return;
        }

        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || passWord.isEmpty())
        {
            registerStatusLabel.setText("Please fill all fields!");
            registerStatusLabel.setTextFill(Color.RED);
            return;
        }

        boolean registrationSuccessful = UserDao.registerUser(firstName, lastName, email, passWord);



        if(registrationSuccessful) {
            registerStatusLabel.setText("Registration successful! you can login now!");
            registerStatusLabel.setTextFill(Color.GREEN);

            firstNameField.clear();
            lastNameField.clear();
            registerEmailField.clear();
            registerPassWordField.clear();
            confirmEmailField.clear();

            SceneController.switchScene("login_view.fxml");

        } else {
            registerStatusLabel.setText("Registration failed. Email may already exist");
            registerStatusLabel.setTextFill(Color.RED);
        }


    }


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
