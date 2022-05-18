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

public class ChooseDeckController extends MenuOption {
    public Button backToMenu;
    public VBox decksButtons = new VBox();
    public Button review;
    Stage stage;
    Scene scene;
    Parent root;
    private static int idDeck;
    TableView<Deck> table = new TableView<>();


    @FXML
    protected void initialize() {
        Database database = new Database("database.db");
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
    protected void review(ActionEvent event) throws IOException {
        TablePosition pos = table.getSelectionModel().getSelectedCells().get(0);
        int index = pos.getRow();
        int selected = table.getItems().get(index).getIdDeck();

        idDeck = selected;

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
