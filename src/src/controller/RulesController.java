package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import view.ViewServices;

public class RulesController {

    public Button buttonBack;
    public AnchorPane mainPane;

    @FXML
    public void initialize(){
        ViewServices.setBackToAccueil(buttonBack, mainPane);
    }
}
