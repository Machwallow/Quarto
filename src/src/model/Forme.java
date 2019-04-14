package model;

import java.util.ArrayList;
import java.util.Arrays;

public class Forme {
    private String imgPath;
    private static ArrayList<Forme> listeFormes = new ArrayList<>(Arrays.asList(
            new Forme(),
            new Forme(),
            new Forme(),
            new Forme(),
            new Forme(),
            new Forme(),
            new Forme())
    );

    public Forme(){}
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
