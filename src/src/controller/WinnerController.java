package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import view.ViewServices;

public class WinnerController {

    public Label label;
    public Button button;
    private static String winner;

    @FXML
    public void initialize(){
        label.setText(winner+" "+label.getText());
        setupButtonMain();
    }

    private void setupButtonMain() {
        ViewServices.setCloseWindow(button);
    }

    public static void setWinner(String winner) {
        WinnerController.winner = winner;
    }
}