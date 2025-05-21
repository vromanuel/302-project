package WisdomBites.Main;

import WisdomBites.controller.LoginRegisterController;
import WisdomBites.model.DBConnection;
import WisdomBites.model.TaskDao;
import WisdomBites.model.User;
import WisdomBites.model.UserDao;
import WisdomBites.tracker.StudySession;
import WisdomBites.tracker.StudySessionDAO;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import WisdomBites.controller.SceneController;


public class HelloApplication extends Application {


    @Override
    public void start(Stage stage) throws IOException {
        // Starts the app
        HelloApplication helloApplication = this;

        // Initialises a UserDao object for the app to access the User table and make changes to it
        UserDao userDao = new UserDao(helloApplication);
        TaskDao taskDao = new TaskDao();
        StudySessionDAO studySessionDAO = new StudySessionDAO();

        // Set fixed stage size ONCE
        stage.setWidth(900);
        stage.setHeight(650);
        stage.setResizable(false); // Prevent resizing through the app
        stage.centerOnScreen(); // Centre the window

        SceneController.currentStage = stage;
        SceneController.setStage(stage);
        SceneController.switchScene("SplashScreen.fxml");

    }

}
