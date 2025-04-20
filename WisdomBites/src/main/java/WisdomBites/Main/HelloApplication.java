package WisdomBites.Main;

import WisdomBites.model.DBConnection;
import WisdomBites.model.UserDao;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import WisdomBites.controller.SceneController;


public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        // Starts the app

        // Initialises a UserDao object for the app to access the User table and make changes to it
        UserDao userDao = new UserDao();

        SceneController.currentStage = stage;
        SceneController.setStage(stage);
        SceneController.switchScene("login_view.fxml");

    }

}

