package com.flashcards;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class FlashcardsApplication extends Application {
    private static final int width = 580;
    private static final int height = 500;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(FlashcardsApplication.class.getResource("menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), width, height);
        scene.getStylesheets().add((new File("src/style/stylesheet.css")).toURI().toString());
        scene.getStylesheets().add((new File("src/style/menuStyle.css")).toURI().toString());
        stage.setResizable(false);
        stage.setTitle("Flashcards");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }
}