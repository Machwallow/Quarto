package model;

public class main {
    public static void main(String[] args){
        Grille g = new Grille();
        Reserve r = Reserve.getInstance();
        Pion pickedPiece;
        Forme f=Forme.getAllFormes().get(1); //LCD=0 CARRE=1

        System.out.println(g.toString());
        //System.out.println(r.toString());
        pickedPiece=r.getReservePions().get(14);
        System.out.println(g.addPionAt(r.useReservePion(pickedPiece),1,0,f));
        System.out.println(g.toStringAt(1,0));
        System.out.println(r.toString());

        pickedPiece=r.getReservePions().get(14);
        g.addPionAt(r.useReservePion(pickedPiece),1,1,f);
        pickedPiece=r.getReservePions().get(13);
        g.addPionAt(r.useReservePion(pickedPiece),2,0,f);
        //g.addPionAt(r.useReservePion(12),2,1);
        System.out.println(g.toString());
        System.out.println(g.checkVictory(f,1,1));
        System.out.println(r.toString());

        int[] tmp=g.checkIfMoveCanWin(f);
        if(tmp[0]!=-1){
            pickedPiece=r.getReservePions().get(tmp[0]);
            g.addPionAt(r.useReservePion(pickedPiece),tmp[1],tmp[2],f);
        }

        System.out.println("Victory : "+g.checkVictory(f,2,1));
    }
}
