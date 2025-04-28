package WisdomBites.Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HomePageTest extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/WisdomBites/home_page.fxml"));
        Scene scene = new Scene(root, 1600, 900); // match your prefWidth and prefHeight
        stage.setScene(scene);
        stage.setTitle("Home Page Test");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
