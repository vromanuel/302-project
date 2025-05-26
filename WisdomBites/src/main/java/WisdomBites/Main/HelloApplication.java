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
import javafx.scene.image.Image;

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

        var logoURL = getClass().getResource("/WisdomBites/logo.png");
        System.out.println("Logo resource URL: " + logoURL);  // Debug line

        if (logoURL != null) {
            stage.getIcons().add(new Image(logoURL.toString()));
        } else {
            System.err.println("⚠ ERROR: Logo not found at /WisdomBites/logo.png");
        }

        // Set window properties
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/WisdomBites/logo.png")));
        stage.setTitle("Wisdom Bites"); // Set the app window title
        stage.setWidth(900);
        stage.setHeight(650);
        stage.setResizable(false); // Prevent resizing through the app
        stage.centerOnScreen(); // Centre the window

        SceneController.currentStage = stage;
        SceneController.setStage(stage);
        SceneController.switchScene("SplashScreen.fxml");

    }

}
