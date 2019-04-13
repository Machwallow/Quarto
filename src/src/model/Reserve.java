package model;

import java.util.ArrayList;

public class Reserve {
    private static Reserve ourInstance = new Reserve();
    private ArrayList<Pion> reservePions;

    public static Reserve getInstance() {
        return ourInstance;
    }

    private Reserve(){
        reservePions=new ArrayList<>();
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
}
