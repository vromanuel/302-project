package WisdomBites.Main;

import WisdomBites.controller.SceneController;
import javafx.application.Application;
import javafx.stage.Stage;

public class PMsgTest extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        SceneController.currentStage = stage;
        SceneController.setStage(stage);
        SceneController.switchScene("personalised_msg.fxml"); // ✅ this assumes file is in the same folder as login_view.fxml
    }

    public static void main(String[] args) {
        launch(args);
    }
}
