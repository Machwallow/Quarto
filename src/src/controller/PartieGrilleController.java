package controller;

import model.Forme;

public class PartieGrilleController {
    private static boolean ia;
    private static Forme forme;

    public static void setIa(boolean ia) {
        PartieGrilleController.ia = ia;
    }

    public static void setForme(Forme forme) {
        PartieGrilleController.forme = forme;
    }
}
