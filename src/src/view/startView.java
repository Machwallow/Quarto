package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;

public class startView extends Application {
    public static Stage stage1;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage1 = primaryStage;
        AnchorPane root = FXMLLoader.load(getClass().getResource("accueil.fxml"), ResourceBundle.getBundle("bundles.bundle", Locale.FRANCE));
        primaryStage.setTitle("Poly Quarto");
        primaryStage.setScene(new Scene(root,300, 300));
        primaryStage.setResizable(false);
       // primaryStage.getIcons().add(new Image("ressources/logo.png"));
        primaryStage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}
