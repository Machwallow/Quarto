package controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import view.ViewServices;

import java.util.Locale;

public class LanguageController {

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
            ViewServices.changeBundle(Locale.FRANCE);
            try {
                mainPane.getChildren().setAll((AnchorPane) FXMLLoader.load(getClass().getResource("../view/accueil.fxml"), ViewServices.getBundle()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void setupivUS(){
        ivUS.setOnMouseClicked(event -> {
            ViewServices.changeBundle(Locale.US);
            try {
                mainPane.getChildren().setAll((AnchorPane) FXMLLoader.load(getClass().getResource("../view/accueil.fxml"), ViewServices.getBundle()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void setupButtonBack() {
        ViewServices.setBackToAccueil(buttonBack, mainPane);
    }
}