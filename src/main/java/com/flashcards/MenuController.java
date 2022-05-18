package com.flashcards;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class MenuController extends MenuOption {
    public VBox menuBox;

    Stage stage;
    Scene scene;
    Parent root;

    @FXML
    protected void initialize() {
        VBox.setMargin(menuBox, new Insets(10, 10, 20, 10));
    }
    @FXML
    protected void deck(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("deck.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, FlashcardsApplication.getWidth(), FlashcardsApplication.getHeight());
        scene.getStylesheets().add((new File("src/style/stylesheet.css")).toURI().toString());
        scene.getStylesheets().add((new File("src/style/addDeleteStyle.css")).toURI().toString());
        stage.setResizable(false);
        stage.setTitle("Flashcards - decks");
        stage.setScene(scene);
        stage.show();
    }

    public void flashcards(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("flashcards.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, FlashcardsApplication.getWidth(), FlashcardsApplication.getHeight());
        scene.getStylesheets().add((new File("src/style/stylesheet.css")).toURI().toString());
        scene.getStylesheets().add((new File("src/style/addDeleteStyle.css")).toURI().toString());
        stage.setResizable(false);
        stage.setTitle("Flashcards - flashcards");
        stage.setScene(scene);
        stage.show();
    }
}
