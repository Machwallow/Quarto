package controller;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ListChangeListener;
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
    public Label labelScoreJ1;
    public Label labelScoreJ2;
    public BorderPane mainBorder;
    public Button buttonEnd;
    public Label title;
    public GridPane mainGrid;
    private static int difficuly = 0;
    public TableView<Pion> tableView;
    public TableColumn<Pion, ImageView> imageColumn;
    public TableColumn<Pion, Button> pickColumn;
    private int[] grille;
    private int jActuel = 0;
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
        genererGrille();
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
                System.out.println(grille[indexColonne]);

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
                resultatCoup = g.addPionAt(r.useReservePion(pickedPiece), indexColonne, indexLigne, forme);
                if (jActuel == 0)
                    jActuel++;
                 else
                    jActuel--;


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
        /*
        int resultatPartie = 0;
        switch (resultatCoup) {
            case 1:
                resultatPartie = jeuActuel.victoireJ1();
                break;
            case 2:
                resultatPartie = -1;
                break;
            case 6:
                resultatPartie = jeuActuel.victoireJ2();
                break;
            case 7:
                resultatPartie = -1;
                break;
        }*/
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
            System.out.println(pion.getImageName());
            System.out.println(new File(".").getAbsoluteFile());
            System.out.println(tempFile.exists());
            Image img = null;
            try{
                img = new Image(tempFile.toURI().toURL().toExternalForm(), ViewServices.WIDTH_TOKEN, ViewServices.HEIGHT_TOKEN, true, true);
            }catch (Exception e){
                e.printStackTrace();
            }
            return new SimpleObjectProperty<>(new ImageView(img));
        });
        pickColumn.setCellFactory(ActionButtonTableCell.forTableColumn(ViewServices.getBundle().getString("pick"), (Pion p) -> {
            //TODO: Traitement du clique sur le bouton
            return p;
        }));

        tableView.getItems().setAll(r.getReservePions());
    }
}

