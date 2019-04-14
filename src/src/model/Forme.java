package model;

import java.util.ArrayList;
import java.util.Arrays;

public class Forme {
    private String imgPath;
    private NomForme nomForme;
    private static ArrayList<Forme> listeFormes = new ArrayList<>(Arrays.asList(
            new Forme("src/src//ressources/formes/LCD.png", NomForme.LCD),
            new Forme("src/src/ressources/formes/CARRE.png", NomForme.CARRE),
            new Forme("src/src/ressources/formes/T.png",NomForme.T),
            new Forme("src/src/ressources/formes/L.png",NomForme.L),
            new Forme("src/src/ressources/formes/L_R.png",NomForme.L_R),
            new Forme("src/src/ressources/formes/Z.png",NomForme.Z),
            new Forme("src/src/ressources/formes/S.png",NomForme.S))
    );

    public Forme(String imgPath, NomForme n) {
        this.imgPath = imgPath;
        this.nomForme=n;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public static ArrayList<Forme> getAllFormes(){
        return listeFormes;
    }

    public NomForme getNomForme() {
        return nomForme;
    }
}
