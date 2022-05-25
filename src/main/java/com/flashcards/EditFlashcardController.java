package com.flashcards;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.time.LocalDate;
/*
public class EditFlashcardController {
    public TextArea front;
    public TextArea back;
    public HBox deck;
    private static int selected;
    Database db = new Database("database.db");

    @FXML
    ChoiceBox<Deck> cb;

    @FXML
    protected void initialize() {
        Card card = db.findCard(selected);
        front.setText(card.getFront());
        back.setText(card.getBack());

        cb = new ChoiceBox<>(FXCollections.observableArrayList(
                db.selectDecks())
        );

        cb.setValue(db.findDeck(card.getIdDeck()));
        deck.getChildren().add(cb);
    }

    @FXML
    protected void save(ActionEvent event) throws IOException {
        Deck deck;
        deck = cb.getValue();

        db.updateCard(selected,deck.getIdDeck(),front.getText(),back.getText(), LocalDate.now(),LocalDate.now());
        back(event);
    }

    public void back(ActionEvent event) throws IOException {
        MenuController mc = new MenuController();
        mc.flashcards(event);
    }

    public static void setSelected(int s) {
        selected = s;
    }
}
*/