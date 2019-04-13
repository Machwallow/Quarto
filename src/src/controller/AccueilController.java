package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class AccueilController {

    private static ResourceBundle bundle;
    public Button buttonRules;
    public Button buttonQuit;
    public AnchorPane mainPane;
    public Button buttonLocal;
    public Button buttonLanguage;

    @FXML
    private void initialize() {
        setupBundle();
        setupButtonLanguage();
        setupButtonQuit();
    }

    private void setupButtonLocal(){
        /*buttonLocal.setOnAction(event ->{
            try {
                AnchorPane pane = FXMLLoader.load(getClass().getResource("../vue/partieLocal.fxml"), Services.getBundle());
                mainPane.getChildren().setAll(pane);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });*/
    }

    private void setupButtonRules() {
       /* buttonRules.setOnAction(event -> {
            try {
                AnchorPane pane = FXMLLoader.load(getClass().getResource("../vue/rules.fxml"), Services.getBundle());
                mainPane.getChildren().setAll(pane);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });*/
    }

    private void setupButtonLanguage(){
        buttonLanguage.setOnAction(event ->{
            try {

                AnchorPane pane = FXMLLoader.load(getClass().getResource("../view/language.fxml"), bundle);
                mainPane.getChildren().setAll(pane);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void setupButtonQuit() {
        buttonQuit.setOnAction(event -> {
            try {
                ((Stage) buttonQuit.getScene().getWindow()).close();
            }catch (Exception e){
                e.printStackTrace();
            }
        });
    }

    public static void setupBundle(){
        Locale.setDefault(Locale.FRANCE);
        bundle = ResourceBundle.getBundle("bundles.bundle", Locale.getDefault());
    }
    public static void changeBundle(Locale l){
        Locale.setDefault(l);
        bundle = ResourceBundle.getBundle("bundles.bundle", l);
    }
    public static ResourceBundle getBundle(){
        return bundle;
    }

}
