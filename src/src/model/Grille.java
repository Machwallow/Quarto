package model;

import java.util.ArrayList;

public class Grille {
    private static Grille ourInstance = new Grille();
    private Pion[][] grillePions;

    public static Grille getInstance() {
        return ourInstance;
    }

    private Grille() {
        grillePions = new Pion[4][4];
        fillGrille();
    }

    public void fillGrille(){
        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++)
                grillePions[i][j] = new Pion();
    }

    public Pion[][] getGrillePions() {
        return grillePions;
    }

    public Pion getPionAt(int x, int y){
        return grillePions[x][y];
    }

    /* formeVic = 1 -> Ligne
       formeVic = 2 -> Colonne
       formeVic = 3 -> Diag
       formeVic = 4 -> Carré
     */
    //TODO : debug this function, check how to handle empty cases
    public boolean checkVictory(int formeVic, int x, int y){
        ArrayList<Pion> pionToTest = new ArrayList<Pion>();

        switch(formeVic) {
            case 1: {
                for (Pion pTemp : grillePions[x]) {
                    pionToTest.add(pTemp);
                }
                return testPions(pionToTest.get(1), pionToTest.get(2), pionToTest.get(3), pionToTest.get(4));
            }
            case 2: {
                for (int i = 0; i < 4; i++) {
                    pionToTest.add(grillePions[i][y]);
                }
                return testPions(pionToTest.get(1), pionToTest.get(2), pionToTest.get(3), pionToTest.get(4));
            }
            case 3: {
                if (x == y)
                    return testPions(grillePions[0][0], grillePions[1][1], grillePions[2][2], grillePions[3][3]);
                else if ((x == 3 && y == 0) || (x == 2 && y == 1) || (x == 1 && y == 2) || (x == 0 && y == 3))
                    return testPions(grillePions[3][0], grillePions[2][1], grillePions[1][2], grillePions[0][3]);

            }
            //9 carrés possibles
            case 4: {
                return testCarre(x, y);
            }
        }

        return false;
    }

    public boolean addPionAt(Pion p, int x, int y){
        if(grillePions[x][y] == null)
            grillePions[x][y] = p;
        else
            return false;
        return true;
    }

    private boolean testPions(Pion p1,Pion p2,Pion p3,Pion p4){

        if(p1.isPlein() || p2.isPlein() || p3.isPlein() || p4.isPlein())
            return false;

        if(p1.isBleu() == p2.isBleu() && p2.isBleu() == p3.isBleu() && p3.isBleu() == p4.isBleu())
            return true;
        else if(p1.isCarre() == p2.isCarre() && p2.isCarre() == p3.isCarre() && p3.isCarre() == p4.isCarre())
            return true;
        else if(p1.isGrand() == p2.isGrand() && p2.isGrand() == p3.isGrand() && p3.isGrand() == p4.isGrand())
            return true;
        else if(p1.isTroue() == p2.isTroue() && p2.isTroue() == p3.isTroue() && p3.isTroue() == p4.isTroue())
            return true;

        return false;
    }

    private boolean testCarre(int x,int y){
        if(x-1<0){
            // cas 2 puis cas 3 sur feuille de note
            if((y+1<4) && (y-1>-1)){
                return ((testPions(grillePions[x][y],grillePions[x+1][y-1],grillePions[x+1][y],grillePions[x][y-1]))
                        ||
                        (testPions(grillePions[x][y],grillePions[x+1][y+1],grillePions[x][y+1],grillePions[x-1][y])));
            } else if(y-1<0) // cas 3
                return testPions(grillePions[x][y],grillePions[x+1][y+1],grillePions[x][y+1],grillePions[x-1][y]);
            else //cas 2
                return testPions(grillePions[x][y],grillePions[x+1][y-1],grillePions[x+1][y],grillePions[x][y-1]);
        }
        //cas 1 puis cas 4
        else if(x+1>3) {
            if ((y+1<4) && (y-1>-1)) {
                return ((testPions(grillePions[x][y],grillePions[x-1][y-1],grillePions[x-1][y],grillePions[x][y-1]))
                        ||
                        (testPions(grillePions[x][y],grillePions[x-1][y+1],grillePions[x-1][y],grillePions[x][y+1])));
            } else if (y-1< 0) // cas 4
                return testPions(grillePions[x][y],grillePions[x-1][y+1],grillePions[x-1][y],grillePions[x][y+1]);
            else // cas 1
                return testPions(grillePions[x][y],grillePions[x-1][y-1],grillePions[x-1][y],grillePions[x][y-1]);
        }
        //cas 3 puis cas 4
        else if(y-1<0){
            if((x+1<4) && (x-1>0)){
                return ((testPions(grillePions[x][y],grillePions[x+1][y+1],grillePions[x][y+1],grillePions[x-1][y]))
                        ||
                        (testPions(grillePions[x][y],grillePions[x-1][y+1],grillePions[x-1][y],grillePions[x][y+1])));
            }
        }
        //cas 1 puis cas 2
        else if(y+1>3){
            if((x+1<4) && (x-1>-1)){
                return ((testPions(grillePions[x][y],grillePions[x-1][y-1],grillePions[x-1][y],grillePions[x][y-1]))
                        ||
                        (testPions(grillePions[x][y],grillePions[x+1][y-1],grillePions[x+1][y],grillePions[x][y-1])));
            }
        }
        else{
            return ((testPions(grillePions[x][y],grillePions[x-1][y-1],grillePions[x-1][y],grillePions[x][y-1]))
                    ||
                    (testPions(grillePions[x][y],grillePions[x+1][y-1],grillePions[x+1][y],grillePions[x][y-1])))
                    ||
                    (testPions(grillePions[x][y],grillePions[x+1][y+1],grillePions[x][y+1],grillePions[x-1][y]))
                    ||
                    (testPions(grillePions[x][y],grillePions[x-1][y+1],grillePions[x-1][y],grillePions[x][y+1]));
        }
        return false;
    }
}
