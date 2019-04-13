package model;

public class Grille {
    private static Grille ourInstance = new Grille();
    private Pion[][] grillePions;

    public static Grille getInstance() {
        return ourInstance;
    }

    private Grille() {
        grillePions = new Pion[4][4];
    }

    public Pion[][] getGrillePions() {
        return grillePions;
    }

    public Pion getPionAt(int x, int y){
        return grillePions[x][y];
    }



}
