package com.flashcards;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.time.LocalDate;

public class AddFlashcardController extends MenuOption {
    public Button backToMenu;
    public VBox addFlashcard;
    public TextArea front;
    public TextArea back;
    public HBox deck;
    Database db = new Database("database.db");
    public Button save;

    @FXML
    ChoiceBox<Deck> cb;
    @FXML
    protected void initialize() {
        cb = new ChoiceBox<>(FXCollections.observableArrayList(
                db.selectDecks())
        );
        deck.getChildren().add(cb);
    }

    @FXML
    protected void save(ActionEvent event) {
        Deck deck;
        if (cb.getValue() == null) {
            deck = db.selectDecks().get(0);
        } else {
            deck = cb.getValue();
        }
        db.insertCard(deck.getIdDeck(),front.getText(),back.getText(), LocalDate.now(),LocalDate.now());
        front.setText("");
        back.setText("");
    }
}
