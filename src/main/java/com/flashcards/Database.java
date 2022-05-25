package com.flashcards;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Database {

    public static final String DRIVER = "org.sqlite.JDBC";
    public static final String DB_URL = "jdbc:sqlite:";

    private Connection conn;
    private Statement stat;

    public Database(String database) {
        try {
            Class.forName(Database.DRIVER);
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC not found");
            e.printStackTrace();
        }
        try {
            conn = DriverManager.getConnection(DB_URL+database);
            stat = conn.createStatement();
        } catch (SQLException e) {
            System.err.println("Connecting error");
            e.printStackTrace();
        }
        createTables();
    }

    private boolean createTables() {
        String createDeck = "CREATE TABLE IF NOT EXISTS 'deck' ('id_deck' INTEGER PRIMARY KEY AUTOINCREMENT, name varchar(225));";
        String createCard = "CREATE TABLE IF NOT EXISTS 'card' ('id_card' INTEGER PRIMARY KEY AUTOINCREMENT, id_deck INTEGER, front varchar(255), back varchar(255), last_review date, next_review date, FOREIGN KEY('id_deck') REFERENCES 'deck'('id_deck'));";
        try {
            stat.execute(createDeck);
            stat.execute(createCard);
        } catch (SQLException e) {
            System.err.println("Creating table error");
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean insertDeck(String name) {
        try {
            PreparedStatement prepStmt = conn.prepareStatement(
                    "INSERT INTO deck VALUES(NULL, ?);");
            prepStmt.setString(1,name);
            prepStmt.execute();
        } catch (SQLException e) {
            System.err.println("Deck insert error");
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean deleteDeck(int idDeck) {
        try {
            PreparedStatement prepStmt = conn.prepareStatement(
                    "DELETE FROM deck WHERE id_deck= ?");
            prepStmt.setInt(1,idDeck);
            prepStmt.execute();
        } catch (SQLException e) {
            System.err.println("Deck delete error");
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean updateDeck(int idDeck, String name) {
        try {
            PreparedStatement prepStmt = conn.prepareStatement(
                    "UPDATE deck SET name = ? WHERE id_deck= ?"
            );
            prepStmt.setString(1,name);
            prepStmt.setInt(2,idDeck);
        } catch (SQLException e) {
            System.err.println("Deck update error");
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public ArrayList<Deck> selectDecks() {
        ArrayList<Deck> decks = new ArrayList<>();
        int idDeck;
        String name;
        try {
            ResultSet result = stat.executeQuery("SELECT * FROM deck;");
            while(result.next()) {
                idDeck = result.getInt("id_deck");
                name = result.getString("name");
                decks.add(new Deck(idDeck,name));
            }
        } catch (SQLException e) {
            System.err.println("Deck select error");
            e.printStackTrace();
            return null;
        }
        return decks;
    }
    public Deck findDeck(int idDeck) {
        Deck deck;
        String name = "";
        try {
            ResultSet result = stat.executeQuery("SELECT * FROM deck WHERE id_deck="+idDeck+";");
            while(result.next()) {
                name = result.getString("name");
            }
            deck = new Deck(idDeck,name);
        } catch (SQLException e) {
            System.err.println("Deck find error");
            e.printStackTrace();
            return null;
        }
        return deck;
    }
    public boolean insertCard(int idDeck, String front, String back, LocalDate lastReview, LocalDate nextReview) {
        try {
            PreparedStatement prepStmt = conn.prepareStatement(
                    "INSERT INTO card VALUES(NULL, ?, ?, ?, ?, ?);");
            prepStmt.setInt(1,idDeck);
            prepStmt.setString(2,front);
            prepStmt.setString(3,back);
            prepStmt.setDate(4, Date.valueOf(lastReview));
            prepStmt.setDate(5, Date.valueOf(nextReview));
            prepStmt.execute();
        } catch (SQLException e) {
            System.err.println("Card insert error");
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean deleteCard(int idCard) {
        try {
            PreparedStatement prepStmt = conn.prepareStatement(
                    "DELETE FROM card WHERE id_card= ?;");
            prepStmt.setInt(1,idCard);
            prepStmt.execute();
        } catch (SQLException e) {
            System.err.println("Card delete error");
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public Card findCard(int idCard) {
        Card card;
        int idDeck = 0;
        String front = "";
        String back = "";
        LocalDate lastReview = LocalDate.now();
        LocalDate nextReview = LocalDate.now();
        try {
            ResultSet result = stat.executeQuery("SELECT * FROM card WHERE id_deck="+idCard+";");
            while(result.next()) {
                idCard = result.getInt("id_card");
                front = result.getString("front");
                back = result.getString("back");
                lastReview = result.getDate("last_review").toLocalDate();
                nextReview = result.getDate("next_review").toLocalDate();
            }
            card = new Card(idCard,idDeck,front,back,lastReview,nextReview);
        } catch (SQLException e) {
            System.err.println("Card find error");
            e.printStackTrace();
            return null;
        }
        return card;
    }
    public ArrayList<Card> selectCards(int idDeck) {
        ArrayList<Card> cards = new ArrayList<>();
        try {
            ResultSet result = stat.executeQuery("SELECT * FROM card WHERE id_deck = "+idDeck+";");
            int idCard;
            String front, back;
            LocalDate lastReview;
            LocalDate nextReview;
            while(result.next()) {
                idCard = result.getInt("id_card");
                front = result.getString("front");
                back = result.getString("back");
                lastReview = result.getDate("last_review").toLocalDate();
                nextReview = result.getDate("next_review").toLocalDate();
                cards.add(new Card(idCard,idDeck,front,back,lastReview,nextReview));
            }
        } catch (SQLException e) {
            System.err.println("Cards select error");
            e.printStackTrace();
            return null;
        }
        return cards;
    }
    public ArrayList<Card> selectAllCards() {
        ArrayList<Card> cards = new ArrayList<>();
        try {
            ResultSet result = stat.executeQuery("SELECT * FROM card;");
            int idCard,idDeck;
            String front, back;
            LocalDate lastReview;
            LocalDate nextReview;
            while(result.next()) {
                idCard = result.getInt("id_card");
                idDeck = result.getInt("id_deck");
                front = result.getString("front");
                back = result.getString("back");
                lastReview = result.getDate("last_review").toLocalDate();
                nextReview = result.getDate("next_review").toLocalDate();
                cards.add(new Card(idCard,idDeck,front,back,lastReview,nextReview));
            }
        } catch (SQLException e) {
            System.err.println("Cards select error");
            e.printStackTrace();
            return null;
        }
        return cards;
    }
    public boolean updateCard(int idCard,int idDeck,String front, String back, LocalDate lastReview, LocalDate nextReview) {
        try {
            PreparedStatement prepStmt = conn.prepareStatement(
                    "UPDATE card SET id_deck = ?, front = ?, back = ?, last_review = ?, next_review = ? WHERE id_card = ?"
            );
            prepStmt.setInt(1,idDeck);
            prepStmt.setString(2,front);
            prepStmt.setString(3,back);
            prepStmt.setDate(4, Date.valueOf(lastReview));
            prepStmt.setDate(5, Date.valueOf(nextReview));
            prepStmt.setInt(6,idCard);
        } catch (SQLException e) {
            System.err.println("Card update error");
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
