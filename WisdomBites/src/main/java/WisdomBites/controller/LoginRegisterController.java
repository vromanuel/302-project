package WisdomBites.controller;

import WisdomBites.model.UserDao;
import javafx.fxml.FXML;

import javafx.scene.Scene;
import javafx.scene.control.TextField;
import WisdomBites.model.User;

public class LoginRegisterController {
    // Controls the login and register views for the application

    // every TextField in each view
    @FXML private TextField emailField;
    @FXML private TextField passWordField;
    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;


    @FXML
    public void loginButtonHandle()
    {
        // Handles the event where the user presses the login button

        // Inputs: none
        // Outputs: none

        // retrieve the data from each text field and pass them into the UserDao login function
        String email = emailField.getText();
        String passWord = passWordField.getText();
        User user = UserDao.login(email, passWord);

        // If the UserDao login function returns a non-null user, print the user's firstname and that the login was
        // successful, or otherwise unsuccessful
        if(user != null)
        {
            System.out.println(user.getFirstName());
            System.out.println("Successful!");
        }
        else
        {
            System.out.println("unsuccessful");
        }


    }

    public void registerButtonHandle()
    {
        // Handles the event where the user presses the register button

        // Inputs: none
        // Outputs: none

        // get the texts from each TextField and store them in according variables
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String email = emailField.getText();
        String passWord = passWordField.getText();

        // Call the UserDao user registration function to insert the values into the table and returns a boolean value
        // representing whether insertion was successful
        boolean registrationSuccessful = UserDao.registerUser(firstName, lastName, email, passWord);

        // Print whether the registration was successful or not
        if(registrationSuccessful)
        {
            System.out.println("Successful!");
        }
        else
        {
            System.out.println("unsuccessful");
        }
    }

    public void switchToRegister()
    {
        // Switches to register view when pressed
        SceneController.switchScene("register_view.fxml");

    }

}
