package model;

import java.util.ArrayList;

public class Grille {
    private Pion[][] grillePions;
    private ArrayList<Pion> pionToTest = new ArrayList<>();

    public Grille() {
        grillePions = new Pion[4][4];
        fillGrille();
    }

    public void fillGrille(){
        for(int i=0;i<4;i++)
            for(int j=0;j<4;j++)
                grillePions[i][j] = new Pion();
    }

    public Pion[][] getGrillePions() {
        return grillePions;
    }

    public Pion getPionAt(int x, int y){
        return grillePions[x][y];
    }

    /* formeVic = 1 -> Colonne
       formeVic = 2 -> Ligne
       formeVic = 3 -> Diag
       formeVic = 4 -> Carré
     */
    //TODO : debug this function, check how to handle empty cases
    public boolean checkVictory(Forme forme, int x, int y){

        switch(forme.getNomForme()) {
            //ligne colonne diag
            case LCD: {
                return testLCD(x,y);
            }
            case CARRE: {
                return testCarre(x, y);
            }
            case T: {
                //return testT(x,y);
            }
        }

        return false;
    }

    public int addPionAt(Pion p, int x, int y, Forme forme){
        if(!grillePions[x][y].isPlein()){
            grillePions[x][y] = p;
            if(checkVictory(forme,x,y)){
                return 2; //si on a ajouté un pion et que c'est gagné
            }
        }
        else
            return -1; //si on a pas pu ajouté un pion
        return 1; //si on a ajouté un pion
    }

    public String toStringAt(int x, int y){
        String text="";
        text="Pion en coordonnes ("+x+", "+y+") : "+grillePions[x][y].getImageName();
        return text;
    }

    public String toString(){
        String txt="";
        for(int i=0;i<4;i++){
            txt+="|";
            for(int j=0;j<4;j++){
                if(grillePions[j][i].isPlein())
                    txt+=grillePions[j][i].getImageName().substring(0,4)+"|";
                else
                    txt+=grillePions[j][i].getImageName()+"|";
            }
            txt+="\n\n";
        }
        return txt;
    }

    private boolean testPions(Pion p1,Pion p2,Pion p3,Pion p4){

        if(!p1.isPlein() || !p2.isPlein() || !p3.isPlein() || !p4.isPlein())
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
                        (testPions(grillePions[x][y],grillePions[x+1][y+1],grillePions[x][y+1],grillePions[x+1][y])));
            } else if(y-1<0) // cas 3
                return testPions(grillePions[x][y],grillePions[x+1][y+1],grillePions[x][y+1],grillePions[x+1][y]);
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
                return ((testPions(grillePions[x][y],grillePions[x+1][y+1],grillePions[x][y+1],grillePions[x+1][y]))
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
                    (testPions(grillePions[x][y],grillePions[x+1][y+1],grillePions[x][y+1],grillePions[x+1][y]))
                    ||
                    (testPions(grillePions[x][y],grillePions[x-1][y+1],grillePions[x-1][y],grillePions[x][y+1]));
        }
        return false;
    }

    //test ligne colonne diagonale
    private boolean testLCD(int x, int y){
        boolean temp1;
        boolean temp2;
        boolean temp3 = false;

        for (Pion pTemp : grillePions[x]) {
            pionToTest.add(pTemp);
        }
        temp1 = testPions(pionToTest.get(0), pionToTest.get(1), pionToTest.get(2), pionToTest.get(3));
        pionToTest.clear();

        for (int i = 0; i < 4; i++) {
            pionToTest.add(grillePions[i][y]);
        }
        temp2 = testPions(pionToTest.get(0), pionToTest.get(1), pionToTest.get(2), pionToTest.get(3));
        pionToTest.clear();

        if (x == y)
            temp3 = testPions(grillePions[0][0], grillePions[1][1], grillePions[2][2], grillePions[3][3]);
        else if ((x == 3 && y == 0) || (x == 2 && y == 1) || (x == 1 && y == 2) || (x == 0 && y == 3))
            temp3 = testPions(grillePions[3][0], grillePions[2][1], grillePions[1][2], grillePions[0][3]);


        return(temp1||temp2||temp3);
    }

    //checkIfMoveCanWin retourne l'index n du pion gagnant dans reserve, a placer dans grille aux coord x,y si n!=-1
    public int[] checkIfMoveCanWin(Forme forme){
        Grille grilleIA=this;
        ArrayList<Pion> reserveIA=Reserve.getInstance().getReservePions();
        int[] position={-1,-1,-1};
        for(int n=0;n<reserveIA.size();n++){
            System.out.println("test avec piece index "+n);
            for(int i=0;i<4;i++){
                for(int j=0;j<4;j++){
                    if(!grilleIA.grillePions[i][j].isPlein()){
                        grilleIA.grillePions[i][j]=reserveIA.get(n);
                        System.out.println("test en ("+i+", "+j+")");
                        if(grilleIA.checkVictory(forme,i,j)){
                            position[0]=n;
                            position[1]=i;
                            position[2]=j;
                            System.out.println("Victoire possible avec reserve index "+n+" en ("+i+", "+j+")");
                            return position;
                        }
                        grilleIA.grillePions[i][j]=new Pion();
                    }

                }
            }
        }
        return position;
    }
}
