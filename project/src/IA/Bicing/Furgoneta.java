package IA.Bicing;



import java.util.Random;

public class Furgoneta
{
    int nb; //num bicis
    int estacioOrigen;
    int estacioDesti;
    int estacioDesti2;

    void Furgoneta() {
        estacioOrigen = -1;
        estacioDesti = -1;
        estacioDesti2 = -1;
        nb = 0;
    }
    void setPos(int X) {
        estacioOrigen = X;
    }

    void setDest(int X) {
        if (estacioDesti == -1) estacioDesti = X;
        else estacioDesti2 = X;
    }

    int getPos() {
        return estacioOrigen;
    }
    int getDest() {
        return estacioDesti;
    }
    int getDest2() {
        return estacioDesti2;
    }


}
