package controller;

import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.Forme;
import model.Grille;
import model.Pion;
import model.Reserve;
import view.ViewServices;

import java.io.File;
import java.io.IOException;

public class PartieGrilleController {

    public AnchorPane mainPane;
    public Label labelScoreJ1, labelScoreJ2;
    public BorderPane mainBorder;
    public Button buttonEnd;
    public Label title;
    public GridPane mainGrid;
    private static int difficuly = 0;
    public TableView<Pion> tableView;
    public TableColumn<Pion, ImageView> imageColumn;
    public TableColumn<Pion, Button> pickColumn;
    public ImageView imgJ1, imgJ2;
    public Label labelSelectingJ1, labelSelectingJ2;
    private int jActuel = 1;
    private static boolean confirm = false;
    private static boolean isPiecePicked = false;
    private static Pion pickedPiece;
    private static Reserve r;
    private static Grille g;

    private static final int GRIDSIZE = 4;
    private static boolean ia;
    private static Forme forme;


    @FXML
    private void initialize() {
        g = new Grille();
        r = Reserve.getInstance();
        if (r.getReservePions().size() != 16)
            r.refill();
        genererGrille();
        labelSelectingJ2.setVisible(false);
        if (ia){
            //TODO: Remplir pour initialiser l'IA
            labelScoreJ2.setText("Mr. Robot");
            //ia = new IA(difficuly);
        }
        setupButtonEnd();
        tableFill();
    }

    private void setupButtonEnd() {
        //TODO: bouton quitter la partie
        buttonEnd.setOnAction(event -> {
            Stage stageNewWindow = new Stage();
            Stage currentStage = (Stage) mainPane.getScene().getWindow();
            try {
                AnchorPane root = FXMLLoader.load(getClass().getResource("../vue/popUpConfirm.fxml"), ViewServices.getBundle());
                ViewServices.setupNewWindow(stageNewWindow, root, ViewServices.WIDTH_POP_UP, ViewServices.HEIGHT_POP_UP, ViewServices.getBundle().getString("closeConfirm"));
                stageNewWindow.showAndWait();
                System.out.println(confirm);
                if (confirm) {
                    AnchorPane pane = FXMLLoader.load(getClass().getResource("../view/accueil.fxml"), ViewServices.getBundle());
                    ViewServices.setupFenetre(ViewServices.WIDTH_BASE + 10, ViewServices.HEIGHT_BASE + 40, currentStage);
                    mainPane.getChildren().setAll(pane);
                    confirm = false;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    private void genererGrille() {
        for (int i = 0; i < GRIDSIZE; i++)
            for (int j = 0; j < GRIDSIZE; j++) {
                addPane(i, j);
            }
    }

    private void addPane(int indexLigne, int indexColonne) {
        StackPane stackPane = new StackPane();
        stackPane.setOnMouseClicked(e -> {
            if (isPiecePicked) {

                //gestion Image
                File tempFile = new File(pickedPiece.getImageName());
                Image img = null;
                try{
                    img = new Image(tempFile.toURI().toURL().toExternalForm(), 75, 75, true, true);
                }catch (Exception eImg){
                    eImg.printStackTrace();
                }
                ImageView jetonJActuel = new ImageView(img);
                jetonJActuel.setFitWidth(75);
                jetonJActuel.setFitHeight(75);

                StackPane coupPane = (StackPane) mainGrid.getChildren().get((indexLigne * GRIDSIZE) + 1 + indexColonne);
                coupPane.getChildren().add(jetonJActuel);
                StackPane.setAlignment(coupPane.getChildren().get(0), Pos.CENTER);

                int resultatCoup;

                //TODO: Finir la gestion du coup proprement, y ajouter le traitement de l'image
                resultatCoup = g.addPionAt(pickedPiece, indexColonne, indexLigne, forme);
                isPiecePicked = false;
                pickedPiece = null;
                removeImage();
                if (jActuel == 1) {
                    jActuel++;
                    labelSelectingJ2.setVisible(true);
                }
                 else {
                    jActuel--;
                    labelSelectingJ1.setVisible(true);
                }

                if (r.getReservePions().size() == 0 && resultatCoup == 1)
                    resultatCoup = 11;
                System.out.println("rc = " + resultatCoup);
                testResultatCoup(resultatCoup);
/*


                //TODO: Coup de l'IA
                if (difficuly != 0 && resultatCoup ==0){
                    int indexColonne2 = ia.choisirCoup(jeuActuel.getPartie(), -1);
                    jetonJActuel = new ImageView(new Image(joueurs[jActuel].getImg()));
                    jetonJActuel.setFitWidth(Services.WIDTH_TOKEN);
                    jetonJActuel.setFitHeight(Services.HEIGHT_TOKEN);

                    StackPane coupPane2 = (StackPane) mainGrid.getChildren().get(((grille[indexColonne2]) * nbColonnes) + 1 + indexColonne2);
                    System.out.println(mainGrid.getChildren().indexOf(mainGrid.getChildren().get(((grille[indexColonne2]) * nbColonnes) + 1 + indexColonne2)));
                    coupPane2.getChildren().add(jetonJActuel);
                    StackPane.setAlignment(coupPane2.getChildren().get(0), Pos.CENTER);

                    grille[indexColonne2]--;
                    System.out.println(jActuel);
                    System.out.println("IA JOUE");
                    int resultatCoupIA = jeuActuel.getPartie().ajouterJeton(-1, indexColonne2) + 5;
                    jActuel--;
                    System.out.println("rc IA=" +  resultatCoupIA);
                    testResultatCoup(resultatCoupIA);
                }
                */
            }
        });
        mainGrid.add(stackPane, indexColonne, indexLigne);
    }

    private void popUpWinner(String joueur) {
        labelSelectingJ1.setVisible(false);
        labelSelectingJ2.setVisible(false);
        Stage stageNewWindow = new Stage();
        Stage currentStage = (Stage) mainPane.getScene().getWindow();
        WinnerController.setWinner(joueur);
        try {
            AnchorPane root = FXMLLoader.load(getClass().getResource("../view/winner.fxml"), ViewServices.getBundle());
            ViewServices.setupNewWindow(stageNewWindow, root, ViewServices.WIDTH_POP_UP, ViewServices.HEIGHT_POP_UP, ViewServices.getBundle().getString("weHaveAWinner"));
            stageNewWindow.showAndWait();
            AnchorPane pane = FXMLLoader.load(getClass().getResource("../view/accueil.fxml"), ViewServices.getBundle());
            ViewServices.setupFenetre(ViewServices.WIDTH_BASE+10, ViewServices.HEIGHT_BASE+40, currentStage);
            mainPane.getChildren().setAll(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setConfirm(boolean status){
        confirm = status;
    }

    private void testResultatCoup(int resultatCoup){
        //TODO: à remplir en fonction de la fonction metier

        switch (resultatCoup) {
            case 1:
                break;
            case 2:
                if (jActuel == 1)
                    popUpWinner(ViewServices.getBundle().getString("playerTwo"));
                else
                    popUpWinner(ViewServices.getBundle().getString("playerOne"));
                break;
            case 11:
                try {
                    mainPane.getChildren().setAll((AnchorPane)FXMLLoader.load(getClass().getResource("../view/partieGrille.fxml"), ViewServices.getBundle()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    public static void setIa(boolean ia) {
        PartieGrilleController.ia = ia;
    }

    public static void setForme(Forme forme) {
        PartieGrilleController.forme = forme;
    }

    private void tableFill(){
        tableView.getColumns().setAll(imageColumn, pickColumn);
        tableView.setEditable(false);

        //prevents the user from moving the columns around
        ViewServices.preventMove(tableView, imageColumn, pickColumn);

        imageColumn.setCellValueFactory(param -> {
            Pion pion = param.getValue();
            File tempFile = new File(pion.getImageName());
            Image img = null;
            try{
                img = new Image(tempFile.toURI().toURL().toExternalForm(), ViewServices.WIDTH_TOKEN, ViewServices.HEIGHT_TOKEN, true, true);
            }catch (Exception e){
                e.printStackTrace();
            }
            return new SimpleObjectProperty<>(new ImageView(img));
        });
        pickColumn.setCellFactory(ActionButtonTableCell.forTableColumn(ViewServices.getBundle().getString("pick"), (Pion p) -> {
            if(!isPiecePicked) {
                pickedPiece = r.useReservePion(p);
                isPiecePicked = true;
                setupSelectedPiece();
                tableView.getItems().setAll(r.getReservePions());
            }
            return p;
        }));

        tableView.getItems().setAll(r.getReservePions());
    }

    private void setupSelectedPiece() {
        ImageView imgToSet;
        Image imgTemp = null;
        if(jActuel == 1)
            imgToSet = imgJ2;

        else
            imgToSet = imgJ1;
        try {
            imgTemp = new Image(new File(pickedPiece.getImageName()).toURI().toURL().toExternalForm(), ViewServices.WIDTH_TOKEN, ViewServices.HEIGHT_TOKEN, true, true);
        } catch (Exception e) {
            e.printStackTrace();
        }

        labelSelectingJ1.setVisible(false);
        labelSelectingJ2.setVisible(false);
        imgToSet.setFitWidth(75);
        imgToSet.setFitHeight(75);
        imgToSet.setImage(imgTemp);
    }

    private void removeImage(){
        if(jActuel == 1)
            imgJ2.setImage(null);
        else
            imgJ1.setImage(null);
    }
}

