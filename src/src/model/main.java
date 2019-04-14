package model;

public class main {
    public static void main(String[] args){
        Grille g = new Grille();
        Reserve r = Reserve.getInstance();

        System.out.println(g.toString());
        //System.out.println(r.toString());
        System.out.println(g.addPionAt(r.useReservePion(14),1,0));
        System.out.println(g.toStringAt(1,0));
        //System.out.println(r.toString());
        g.addPionAt(r.useReservePion(14),1,1);
        g.addPionAt(r.useReservePion(13),2,0);
        //g.addPionAt(r.useReservePion(12),2,1);
        System.out.println(g.toString());
        System.out.println(g.checkVictory(NomForme.CARRE,1,1));
        System.out.println(r.toString());
        System.out.println(g.checkIfMoveCanWin(NomForme.CARRE)[0]);
        g.addPionAt(r.useReservePion(4),2,1);
        System.out.println(g.checkVictory(NomForme.CARRE,2,1));
    }
}
