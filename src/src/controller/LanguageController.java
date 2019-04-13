package controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import view.ViewServices;

import java.io.File;
import java.util.Locale;

public class LanguageController {

    public ImageView ivFR, ivUS;
    public Button buttonBack;
    public AnchorPane mainPane;

    @FXML
    public void initialize(){
        System.out.println(new File(".").getAbsolutePath());
        setupivFR();
        setupivUS();
        setupButtonBack();
    }

    private void setupivFR(){
        ivFR.setOnMouseClicked(event -> {
            System.out.println((new File(".").getAbsolutePath()));
            ViewServices.changeBundle(Locale.FRANCE);
            System.out.println("language changed to fr_FR");
            try {
                mainPane.getChildren().setAll((AnchorPane) FXMLLoader.load(getClass().getResource("../view/accueil.fxml"), ViewServices.getBundle()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void setupivUS(){
        ivUS.setOnMouseClicked(event -> {
            System.out.println((new File(".").getAbsolutePath()));
            ViewServices.changeBundle(Locale.US);
            System.out.println("language changed to en_US");
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