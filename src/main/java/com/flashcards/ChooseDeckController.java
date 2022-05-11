package com.flashcards;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ChooseDeckController extends MenuOption {
    public Button backToMenu;
    public VBox decksButtons = new VBox();
    Stage stage;
    Scene scene;
    Parent root;
    private static int idDeck;

    @FXML
    protected void initialize() {
        Database database = new Database("database.db");
        displayDecks(database);
    }

    @FXML
    protected void displayDecks(Database db) {
        ArrayList<Deck> decks = db.selectDecks();
        for (Deck deck : decks) {
            Button deckButton = new Button();
            deckButton.setText(deck.getName());
            deckButton.setOnAction((ActionEvent)->{
                idDeck = deck.getIdDeck();
                try {
                    review(ActionEvent);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            decksButtons.getChildren().add(deckButton);
        }
    }

    public static int getIdDeck() {
        return idDeck;
    }

    @FXML
    protected void review(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("review.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, FlashcardsApplication.getWidth(), FlashcardsApplication.getHeight());
        scene.getStylesheets().add((new File("src/style/stylesheet.css")).toURI().toString());
        scene.getStylesheets().add((new File("src/style/reviewStyle.css")).toURI().toString());
        stage.setResizable(false);
        stage.setTitle("Flashcards - review");
        stage.setScene(scene);
        stage.show();
    }
}
