package view;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;

public class ViewServices {
    private static ResourceBundle bundle;
    public final static int WIDTH_POP_UP = 300, HEIGHT_POP_UP = 150;
    public final static int WIDTH_BASE = 300, HEIGHT_BASE = 300;
    public final static int WIDTH_GAME = 1000, HEIGHT_GAME = 600;

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

    public static void setupFenetre(int width, int height, Stage stage){
        stage.setWidth(width);
        stage.setHeight(height);
        centrerFenetre(stage);
    }

    public static void centrerFenetre(Stage stage){
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((screenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY(((screenBounds.getHeight() - stage.getHeight()) / 2.75));
    }

    public static void setCloseWindow(Button button){
        button.setOnAction(event -> {
            try {
                ((Stage) button.getScene().getWindow()).close();
            }catch (Exception e){
                e.printStackTrace();
            }
        });
    }

    public static void setupNewWindow(Stage stage, AnchorPane mainPane, int width, int height, String title){
        stage.setTitle(title);
        stage.setScene(new Scene(mainPane, width, height));
        stage.getIcons().add(new Image("src/ressources/logo.png"));
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
    }

    public static void setBackToAccueil(Button button, AnchorPane mainPane){
        button.setOnMouseClicked(event -> {
            try {
                AnchorPane pane = FXMLLoader.load(ViewServices.class.getResource("../view/accueil.fxml"), bundle);
                mainPane.getChildren().setAll(pane);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }
}