package com.flashcards;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class SearchFlashcardController extends MenuOption {
    public Button remove;
    @FXML
    protected VBox vbox;
    MenuController mc = new MenuController();

    TableView<Card> table = new TableView<>();
    Database db = new Database("database.db");
    ObservableList<Card> data = FXCollections.observableArrayList(db.selectAllCards());

    @FXML
    protected void initialize() {
        TableColumn<Card, String>  front = new TableColumn<>("front");
        front.setCellValueFactory(new PropertyValueFactory<>("front"));
        front.setPrefWidth(90);

        TableColumn<Card, String> back = new TableColumn<>("back");
        back.setCellValueFactory(new PropertyValueFactory<>("back"));
        back.setPrefWidth(90);

        TableColumn<Card, String> deck = new TableColumn<>("deck");
        deck.setCellValueFactory(new PropertyValueFactory<>("idDeck"));
        deck.setPrefWidth(90);

        TableColumn<Card, String>  lastReview = new TableColumn<>("last review");
        lastReview.setCellValueFactory(new PropertyValueFactory<>("lastReview"));
        lastReview.setPrefWidth(120);

        TableColumn<Card, String>  nextReview = new TableColumn<>("next review");
        nextReview.setCellValueFactory(new PropertyValueFactory<>("nextReview"));
        nextReview.setPrefWidth(140);

        table.setItems(data);
        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        table.getColumns().addAll(front, back, deck, lastReview, nextReview);
        vbox.getChildren().addAll(table);
    }

    @FXML
    protected void remove(ActionEvent event) throws IOException {
        TablePosition pos = table.getSelectionModel().getSelectedCells().get(0);
        int index = pos.getRow();
        String selected = table.getItems().get(index).toString();
        selected = selected.substring(1,selected.indexOf(","));
        System.out.println(selected);
        db.deleteCard(Integer.parseInt(selected));
        mc.searchFlashcards(event);
    }
}
