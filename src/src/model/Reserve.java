package model;

import java.util.ArrayList;

public class Reserve {
    private static Reserve ourInstance = new Reserve();
    private ArrayList<Pion> reservePions;

    public static Reserve getInstance() {
        return ourInstance;
    }

    private Reserve(){
        reservePions=new ArrayList<>(16);

        reservePions.add(new Pion(true,true,true,true));
        reservePions.add(new Pion(true,true,true,false));
        reservePions.add(new Pion(true,true,false,true));
        reservePions.add(new Pion(true,true,false,false));

        reservePions.add(new Pion(true,false,true,true));
        reservePions.add(new Pion(true,false,true,false));
        reservePions.add(new Pion(true,false,false,true));
        reservePions.add(new Pion(true,false,false,false));

        reservePions.add(new Pion(false,true,true,true));
        reservePions.add(new Pion(false,true,true,false));
        reservePions.add(new Pion(false,true,false,true));
        reservePions.add(new Pion(false,true,false,false));

        reservePions.add(new Pion(false,false,true,true));
        reservePions.add(new Pion(false,false,true,false));
        reservePions.add(new Pion(false,false,false,true));
        reservePions.add(new Pion(false,false,false,false));
    }

    @Override
    public String toString() {
        String text="Pions reserve : \n\n";
        for(int i=0;i<reservePions.size();i++){
            text+="indice "+i+" : "+reservePions.get(i).getImageName()+"\n";
        }
        return text;
    }

    public Pion useReservePion(int n){
        Pion toUse=reservePions.get(n);
        reservePions.remove(n);
        return toUse;
    }
    
    public int indexOf(Pion p){
        return reservePions.indexOf(p);
    }

    public ArrayList<Pion> getReservePions() {
        return reservePions;
    }
}
