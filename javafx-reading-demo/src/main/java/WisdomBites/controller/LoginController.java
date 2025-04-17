package WisdomBites.controller;

import WisdomBites.model.UserDao;
import javafx.fxml.FXML;

import javafx.scene.control.TextField;
import WisdomBites.model.User;

public class LoginController {
    @FXML private TextField userNameField;
    @FXML private TextField passWordField;


    @FXML
    private void loginButtonHandle()
    {
        String userName = userNameField.getText();
        String passWord = passWordField.getText();
        User user = UserDao.login(userName, passWord);

        if(userName != null)
        {
            System.out.printf("Successful!");
        }
        else
        {
            System.out.printf("unsuccessful");
        }


    }


}
