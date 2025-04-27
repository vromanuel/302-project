package Fortune_cookie_pages;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Fortuneapp extends Application {

    private Stage primaryStage; // Stage를 멤버변수로 저장

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage; // Stage 저장

        // Closecookieview 생성
        Closecookieview closeView = new Closecookieview();

        // 뒤로가기 버튼에 동작 추가
        closeView.getBackButton().setOnAction(e -> {
            System.out.println("Back button pressed - Closing app...");
            primaryStage.close(); // 지금은 간단하게 앱을 종료
            // 나중에 여기에 다른 화면으로 전환하는 코드 넣을 수도 있어
        });

        // Scene 생성 (배경색 지정)
        Scene scene = new Scene(closeView.getRoot(), 540, 960, Color.web("#F5E8C7"));

        primaryStage.setTitle("Fortune Cookie");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false); // 창 크기 고정
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
