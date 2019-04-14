package model;

import java.util.ArrayList;
import java.util.Arrays;

public class Forme {
    private String imgPath;
    private static ArrayList<Forme> listeFormes = new ArrayList<>(Arrays.asList(
            new Forme("src/src//ressources/formes/LCD.png"),
            new Forme("src/src/ressources/formes/CARRE.png"),
            new Forme("src/src/ressources/formes/T.png"),
            new Forme("src/src/ressources/formes/L.png"),
            new Forme("src/src/ressources/formes/L_R.png"),
            new Forme("src/src/ressources/formes/Z.png"),
            new Forme("src/src/ressources/formes/S.png"))
    );

    public Forme(String imgPath) {
        this.imgPath = imgPath;
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
}
