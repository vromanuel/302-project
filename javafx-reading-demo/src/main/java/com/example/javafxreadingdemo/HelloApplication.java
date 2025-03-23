package com.example.javafxreadingdemo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        VBox root = new VBox();
        root.setAlignment(javafx.geometry.Pos.CENTER);
        root.setSpacing(10.0);
        root.setFillWidth(false);

        TextField textField = new TextField();
        textField.setText("Type shit");
        Label label = new Label("Welcome to our project, Code Crafters!");

        root.getChildren().addAll(label, textField);

        Scene scene = new Scene(root, 320, 180);
        stage.setScene(scene);
        stage.setTitle("Code Crafters!");
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}