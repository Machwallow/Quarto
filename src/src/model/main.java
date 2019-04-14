package src.model;

public class main {
    public static void main(String[] args){
        Grille g = Grille.getInstance();
        System.out.println(g.getPionAt(1,1));
    }
}
