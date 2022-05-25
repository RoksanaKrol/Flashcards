package com.flashcards;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class FlashcardController extends MenuOption {
    public Button remove;
    public Label error;
    @FXML
    protected VBox vbox;
    MenuController mc = new MenuController();

    TableView<Card> table = new TableView<>();
    Database db = new Database("database.db");
    ObservableList<Card> data = FXCollections.observableArrayList(db.selectAllCards());

    @FXML
    protected void initialize() {
        error.setText("");
        error.setManaged(false);
        TableColumn<Card, String>  front = new TableColumn<>("front");
        front.setCellValueFactory(new PropertyValueFactory<>("front"));
        front.setPrefWidth(90);

        TableColumn<Card, String> back = new TableColumn<>("back");
        back.setCellValueFactory(new PropertyValueFactory<>("back"));
        back.setPrefWidth(90);

        TableColumn<Card, String> deck = new TableColumn<>("deck");
        deck.setCellValueFactory(new PropertyValueFactory<>("nameDeck"));
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
    protected void remove(ActionEvent event) {
        try {
            TablePosition pos = table.getSelectionModel().getSelectedCells().get(0);
            int index = pos.getRow();
            int selected = table.getItems().get(index).getId();

            db.deleteCard(selected);
            mc.flashcards(event);

        } catch (Exception e) {
            error.setManaged(true);
            error.setText("Choose flashcard to delete");
        }
    }

    @FXML
    protected void editFlashcard(ActionEvent event) {
        Stage stage;
        Scene scene;
        Parent root;

        try {
            TablePosition pos = table.getSelectionModel().getSelectedCells().get(0);
            int index = pos.getRow();
            int selected = table.getItems().get(index).getId();

            EditFlashcardController.setSelected(selected);

            root = FXMLLoader.load(getClass().getResource("edit_flashcard.fxml"));
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root, FlashcardsApplication.getWidth(), FlashcardsApplication.getHeight());
            scene.getStylesheets().add((new File("src/style/stylesheet.css")).toURI().toString());
            scene.getStylesheets().add((new File("src/style/addDeleteStyle.css")).toURI().toString());
            stage.setResizable(false);
            stage.setTitle("Flashcards - edit flashcard");
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            error.setManaged(true);
            error.setText("Choose flashcard to edit");
        }
    }

}
