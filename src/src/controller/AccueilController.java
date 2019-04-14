package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import view.ViewServices;

import java.io.IOException;

public class AccueilController {
    public Button buttonRules;
    public Button buttonQuit;
    public AnchorPane mainPane;
    public Button buttonLanguage;
    public Button buttonPlay;

    @FXML
    private void initialize() {
        setupButtonPlay();
        setupButtonRules();
        setupButtonLanguage();
        setupButtonQuit();
    }

    private void setupButtonPlay(){
        buttonPlay.setOnAction(event ->{
            try {
                mainPane.getChildren().setAll((AnchorPane)FXMLLoader.load(getClass().getResource("../view/partieLocal.fxml"), ViewServices.getBundle()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void setupButtonRules() {
        buttonRules.setOnAction(event -> {
            try {
                mainPane.getChildren().setAll((AnchorPane)FXMLLoader.load(getClass().getResource("../view/rules.fxml"), ViewServices.getBundle()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void setupButtonLanguage(){
        buttonLanguage.setOnAction(event ->{
            try {
                AnchorPane pane = FXMLLoader.load(getClass().getResource("../view/language.fxml"), ViewServices.getBundle());
                mainPane.getChildren().setAll(pane);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void setupButtonQuit() {
        ViewServices.setCloseWindow(buttonQuit);
    }
}
