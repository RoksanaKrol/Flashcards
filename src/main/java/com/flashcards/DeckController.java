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

public class DeckController extends MenuOption {
    public VBox decksButtons = new VBox();
    public Label error;
    MenuController mc = new MenuController();

    Stage stage;
    Scene scene;
    Parent root;

    private static int idDeck;
    TableView<Deck> table = new TableView<>();
    Database database = new Database("database.db");


    @FXML
    protected void initialize() {
        error.setText("");
        error.setManaged(false);
        ObservableList<Deck> data = FXCollections.observableArrayList(database.selectDecks());
        table.setMaxSize(400,300);
        table.setItems(data);

        TableColumn<Deck, String> name = new TableColumn<>("name");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        name.setPrefWidth(400);

        table.getColumns().add(name);

        decksButtons.getChildren().add(table);
    }

    public static int getIdDeck() {
        return idDeck;
    }

    @FXML
    protected void review(ActionEvent event) {

            try {
                TablePosition pos = table.getSelectionModel().getSelectedCells().get(0);
                int index = pos.getRow();
                idDeck = table.getItems().get(index).getIdDeck();

                root = FXMLLoader.load(getClass().getResource("review.fxml"));
                stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root, FlashcardsApplication.getWidth(), FlashcardsApplication.getHeight());
                scene.getStylesheets().add((new File("src/style/stylesheet.css")).toURI().toString());
                scene.getStylesheets().add((new File("src/style/reviewStyle.css")).toURI().toString());
                stage.setResizable(false);
                stage.setTitle("Flashcards - review");
                stage.setScene(scene);
                stage.show();
            } catch (Exception e) {
                error.setManaged(true);
                error.setText("Choose deck to review");
            }
    }
    @FXML
    protected void remove(ActionEvent event) {
        try {
            TablePosition pos = table.getSelectionModel().getSelectedCells().get(0);
            if (pos != null) {
                int index = pos.getRow();
                idDeck = table.getItems().get(index).getIdDeck();
                database.deleteDeck(idDeck);
                mc.deck(event);
            }
        } catch (Exception e) {
            error.setText("Choose deck to remove");
        }
    }
}
