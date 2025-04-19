package WisdomBites.controller;

import WisdomBites.model.UserDao;
import javafx.fxml.FXML;

import javafx.scene.Scene;
import javafx.scene.control.TextField;
import WisdomBites.model.User;

public class LoginRegisterController {
    @FXML private TextField emailField;
    @FXML private TextField passWordField;
    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;

    @FXML
    public void loginButtonHandle()
    {
        String email = emailField.getText();
        String passWord = passWordField.getText();
        User user = UserDao.login(email, passWord);

        if(user != null)
        {
            System.out.printf(user.getFirstName());
            System.out.printf("Successful!");
        }
        else
        {
            System.out.printf("unsuccessful");
        }


    }

    public void registerButtonHandle()
    {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String email = emailField.getText();
        String passWord = passWordField.getText();


        boolean registrationSuccessful = UserDao.registerUser(firstName, lastName, email, passWord);

        if(registrationSuccessful)
        {
            System.out.printf("Successful!");
        }
        else
        {
            System.out.printf("unsuccessful");
        }
    }

    public void switchToRegister()
    {
        SceneController.switchScene("register_view.fxml");

    }

}
