package model;

public class main {
    public static void main(String[] args){
        Grille g = Grille.getInstance();
        Reserve r = Reserve.getInstance();
        System.out.println(r.toString());
        //g.addPionAt(r)
        System.out.println(g.getPionAt(1,1));
    }
}
