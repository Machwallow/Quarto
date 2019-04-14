package model;

public class main {
    public static void main(String[] args){
        Grille g = new Grille();
        Reserve r = Reserve.getInstance();
        Forme f=Forme.getAllFormes().get(1); //LCD=0 CARRE=1

        System.out.println(g.toString());
        //System.out.println(r.toString());
        System.out.println(g.addPionAt(r.useReservePion(14),1,0,f));
        System.out.println(g.toStringAt(1,0));
        //System.out.println(r.toString());
        g.addPionAt(r.useReservePion(14),1,1,f);
        g.addPionAt(r.useReservePion(13),2,0,f);
        //g.addPionAt(r.useReservePion(12),2,1);
        System.out.println(g.toString());
        System.out.println(g.checkVictory(f,1,1));
        System.out.println(r.toString());
        System.out.println(g.checkIfMoveCanWin(f)[0]);
        g.addPionAt(r.useReservePion(4),2,1,f);
        System.out.println(g.checkVictory(f,2,1));
    }
}
