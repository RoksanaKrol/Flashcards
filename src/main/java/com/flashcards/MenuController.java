package com.flashcards;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class MenuController {
    public Button review;
    public Button addFlashcard;
    public Button addDeck;
    public VBox menuBox;
    public Button searchFlashcards;

    @FXML
    private Label menuLabel;
    Stage stage;
    Scene scene;
    Parent root;

    @FXML
    protected void initialize() {
        VBox.setMargin(menuBox, new Insets(10, 10, 20, 10));
    }
    @FXML
    protected void review(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("choose_deck.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, FlashcardsApplication.getWidth(), FlashcardsApplication.getHeight());
        scene.getStylesheets().add((new File("src/style/stylesheet.css")).toURI().toString());
        scene.getStylesheets().add((new File("src/style/addDeleteStyle.css")).toURI().toString());
        scene.getStylesheets().add((new File("src/style/reviewStyle.css")).toURI().toString());
        stage.setResizable(false);
        stage.setTitle("Flashcards - decks");
        stage.setScene(scene);
        stage.show();
    }

    public void addFlashcard(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("add_flashcard.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, FlashcardsApplication.getWidth(), FlashcardsApplication.getHeight());
        scene.getStylesheets().add((new File("src/style/stylesheet.css")).toURI().toString());
        scene.getStylesheets().add((new File("src/style/reviewStyle.css")).toURI().toString());
        scene.getStylesheets().add((new File("src/style/addDeleteStyle.css")).toURI().toString());
        stage.setResizable(false);
        stage.setTitle("Flashcards - add flashcard");
        stage.setScene(scene);
        stage.show();
    }

    public void addDeck(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("add_deck.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, FlashcardsApplication.getWidth(), FlashcardsApplication.getHeight());
        scene.getStylesheets().add((new File("src/style/stylesheet.css")).toURI().toString());
        scene.getStylesheets().add((new File("src/style/addDeleteStyle.css")).toURI().toString());
        stage.setResizable(false);
        stage.setTitle("Flashcards - add deck");
        stage.setScene(scene);
        stage.show();
    }

    public void searchFlashcards(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("search_flashcards.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, FlashcardsApplication.getWidth(), FlashcardsApplication.getHeight());
        scene.getStylesheets().add((new File("src/style/stylesheet.css")).toURI().toString());
        scene.getStylesheets().add((new File("src/style/addDeleteStyle.css")).toURI().toString());
        stage.setResizable(false);
        stage.setTitle("Flashcards - search flashcards");
        stage.setScene(scene);
        stage.show();
    }
}
