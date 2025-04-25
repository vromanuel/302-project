package Fortune_cookie_pages;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Fortuneapp extends Application {
    @Override
    public void start(Stage primaryStage) {
        Closecookieview view = new Closecookieview();
        Scene scene = new Scene(view.getRoot(), 400, 300);

        primaryStage.setTitle("Fortune Cookie");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
