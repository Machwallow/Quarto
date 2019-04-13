package controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.util.Locale;

public class LanguageControlleur {

    public ImageView ivFR, ivUS;
    public Button buttonBack;
    public AnchorPane mainPane;

    @FXML
    public void initialize(){
        setupivFR();
        setupivUS();
        setupButtonBack();
    }

    private void setupivFR(){
        ivFR.setOnMouseClicked(event -> {
            System.out.println((new File(".").getAbsolutePath()));
            AccueilController.changeBundle(Locale.FRANCE);
            System.out.println("language changed to fr_FR");
            try {
                AnchorPane pane = FXMLLoader.load(getClass().getResource("../view/accueil.fxml"), AccueilController.getBundle());
                mainPane.getChildren().setAll(pane);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void setupivUS(){
        ivUS.setOnMouseClicked(event -> {
            System.out.println((new File(".").getAbsolutePath()));
            AccueilController.changeBundle(Locale.FRANCE);
            System.out.println("language changed to en_US");
            try {
                AnchorPane pane = FXMLLoader.load(getClass().getResource("../view/accueil.fxml"), AccueilController.getBundle());
                mainPane.getChildren().setAll(pane);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void setupButtonBack() {
        buttonBack.setOnAction(event -> {
            try {
                System.out.println((new File(".").getAbsolutePath()));
                AnchorPane pane = FXMLLoader.load(getClass().getResource("../view/accueil.fxml"), AccueilController.getBundle());
                mainPane.getChildren().setAll(pane);
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
        //Services.setBackToAccueil(buttonBack, mainPane);
    }
}