package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import view.ViewServices;

public class PartieController {

    public Button buttonSplit;
    public Button buttonIA;
    public Button buttonBack;
    public AnchorPane mainPane;
    public Label title;

    @FXML
    private void initialize() {
        setupButtonSplit();
        setupButtonBack();
        setupButtonIA();
    }

    private void setupButtonSplit(){
        buttonSplit.setOnMouseClicked(event -> {
            try {
                shapePickerController.setIA(false);
                mainPane.getChildren().setAll((AnchorPane) FXMLLoader.load(getClass().getResource("../view/shapePicker.fxml"), ViewServices.getBundle()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void setupButtonIA(){
        /*buttonIA.setOnMouseClicked(event -> {
            Stage stage = Main.stage1;
            Services.setupFenetre(Services.WIDTH_SETUP, Services.HEIGHT_SETUP, stage);
            try {
                AnchorPane pane = FXMLLoader.load(getClass().getResource("../vue/setupLocalIA.fxml"), Services.getBundle());
                mainPane.getChildren().setAll(pane);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });*/
    }

    private void setupButtonBack(){
        ViewServices.setBackToAccueil(buttonBack, mainPane);
    }


}