package model;

public class main {
    public static void main(String[] args){
        Grille g = Grille.getInstance();
        Reserve r = Reserve.getInstance();

        System.out.println(g.toString());
        //System.out.println(r.toString());
        System.out.println(g.addPionAt(r.useReservePion(14),0,0));
        System.out.println(g.toStringAt(0,0));
        //System.out.println(r.toString());
        System.out.println(g.checkVictory(1,0,0));
        g.addPionAt(r.useReservePion(14),1,0);
        g.addPionAt(r.useReservePion(13),2,0);
        g.addPionAt(r.useReservePion(12),3,0);
        System.out.println(g.toString());
        System.out.println(g.checkVictory(1,3,0)); //victoire ne marche pas
    }
}
