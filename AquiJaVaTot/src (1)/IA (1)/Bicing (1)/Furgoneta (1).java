package IA.Bicing;



import java.util.Random;

public class Furgoneta
{
   private int nb; //num bicis
    private int estacioOrigen;
    private int estacioDesti;

    public void Furgoneta() {
        estacioOrigen = -1;
        estacioDesti = -1;
    }
    public void setPos(int X) {
        estacioOrigen = X;
    }

    public void setDest(int X) {
        estacioDesti = X;
    }

    public int getPos() {
        return estacioOrigen;
    }
    public int getDest() {
        return estacioDesti;
    }

}
