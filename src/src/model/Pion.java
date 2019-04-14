package model;

public class Pion {
    private boolean bleu;
    private boolean carre;
    private boolean grand;
    private boolean troue;
    private boolean plein = false;
    private String imageName;

    public Pion(boolean bleu, boolean carre, boolean grand, boolean troue){
        this.bleu=bleu;
        this.carre=carre;
        this.grand=grand;
        this.troue=troue;
        this.imageName=setImageName();
        this.plein=true;
    }

    //Use this when creating empty grid
    public Pion(){
    }

    public boolean isBleu() {
        return bleu;
    }

    public boolean isCarre() {
        return carre;
    }

    public boolean isGrand() {
        return grand;
    }

    public boolean isTroue() {
        return troue;
    }

    public boolean isPlein() { return plein; }

    public String getImageName() {
        return imageName;
    }

    public String setImageName(){
        String tmp="";
        if(this.bleu)
            tmp+="b";
        else
            tmp+="r";
        if(this.carre)
            tmp+="c";
        else
            tmp+="r";
        if(this.grand)
            tmp+="g";
        else
            tmp+="p";
        if(this.troue)
            tmp+="t";
        else
            tmp+="p";
        tmp+=".png";
        return tmp;
    }

    public String toString(){
        return imageName;
    }
}
