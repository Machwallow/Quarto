package controller;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Forme;
import view.ViewServices;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class shapePickerController {
    public final static int WIDTH_TOKEN = 55, HEIGHT_TOKEN = 36;
    private static boolean ia;
    public TableView<Forme> tableView;
    public TableColumn<Forme, ImageView> imageColumn;
    public TableColumn<Forme, Button> pickColumn;
    public Button buttonBack;
    public AnchorPane mainPane;
    public static ArrayList<Forme> formes;

    @FXML
    public void initialize() {
        tableFill();
        setupButtonBack();
    }

    private void setupButtonBack() {
        buttonBack.setOnMouseClicked(event -> {
            try {
                mainPane.getChildren().setAll((AnchorPane)FXMLLoader.load(getClass().getResource("../view/partie.fxml"), ViewServices.getBundle()));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    private void tableFill() {
        formes = Forme.getAllFormes();
        tableView.getColumns().setAll(imageColumn, pickColumn);
        tableView.setEditable(false);

        //prevents the user from moving the columns around
        tableView.getColumns().addListener(new ListChangeListener() {
            public boolean suspended;
            @Override
            public void onChanged(Change change) {
                change.next();
                if (change.wasReplaced() && !suspended) {
                    this.suspended = true;
                    tableView.getColumns().setAll(imageColumn, pickColumn);
                    this.suspended = false;
                }
            }
        });

        imageColumn.setCellValueFactory(param -> {
            Forme forme = param.getValue();
            File tempFile = new File(forme.getImgPath());
            Image img = null;
            try{
               img = new Image(tempFile.toURI().toURL().toExternalForm(), WIDTH_TOKEN, HEIGHT_TOKEN, true, true);
            }catch (Exception e){
                e.printStackTrace();
            }
            return new SimpleObjectProperty<>(new ImageView(img));
        });
        pickColumn.setCellFactory(ActionButtonTableCell.forTableColumn(ViewServices.getBundle().getString("pick"), (Forme f) -> {
            System.out.println(f);
            if (ia)
                PartieGrilleController.setIa(true);
            else
                PartieGrilleController.setIa(false);
            PartieGrilleController.setForme(f);
            Stage stage = (Stage) mainPane.getScene().getWindow();
            ViewServices.setupFenetre(ViewServices.WIDTH_GAME, ViewServices.HEIGHT_GAME, stage);
            try {
                mainPane.getChildren().setAll((AnchorPane)FXMLLoader.load(getClass().getResource("../view/partieGrille.fxml"), ViewServices.getBundle()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return f;
        }));

        tableView.getItems().setAll(formes);
    }

    public static void setIA(boolean ia){
        shapePickerController.ia = ia;
    }
}