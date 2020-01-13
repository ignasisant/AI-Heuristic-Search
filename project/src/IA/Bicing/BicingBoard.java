package IA.Bicing;
import java.util.*;

public class BicingBoard {

    private int solucio[]; //bicis sobrants
    private Furgoneta flota[];
    private Estaciones est;
    private int nest, F, nbic, dem, benefici, dist;

    public int getDist() {return dist;}
    public int getNest() {
        return nest;
    }
    public int getF() {
        return F;
    }
    public int getNbic() {
        return nbic;
    }
    public int getBenefici() {return benefici;}
    public int getDem() {
        return dem;
    }
    public Furgoneta[] getFlota() {
        return flota;
    }
    public Estaciones getEst() {
        return est;
    }
    public int calculaCost(int nb) {  //calcul cost
        return ((nb + 9)/10);
    }
    public int[] getSol() {
        return solucio;
    }
    public int getDest() {
        Random random = new Random();
        int rand;
        int dem;
        do {
            rand = random.nextInt(nest);
            dem = solucio[rand];
        }
        while(dem >= 0);
        //System.out.println(rand);
        //System.out.println(dem);
        //System.out.println(solucio[rand]);
        return rand;
    }
    public int calculaDist(Estacion X, Estacion Y) {  //calcul cost
        return (Math.abs(X.getCoordX()- Y.getCoordX()) + Math.abs(X.getCoordY()- Y.getCoordY()));
    }

    public void imprimeix(){
        for (int i = 0; i < nest; i++) {
            System.out.println("Estacio " + i + " posicio: " +  est.get(i).getCoordX() + ", " + est.get(i).getCoordY() + ". Sobren " + solucio[i] + " bicis");
        }
        for (int i = 0; i < F; i++) {
            System.out.println("Furgoneta " + i + " en estacio " + flota[i].getPos() + " desti " + flota[i].getDest() + "\n");
        }
    }

    public void AssignaDesti() {
        int DemandOrig;
        for (int i = 0; i < F; i++) {
            DemandOrig = solucio[flota[i].getPos()]; //retorna les bicis sobrants en l'Estacionorigen
            if (DemandOrig > 0) {
                //agafem bicis sobrants.
                if (DemandOrig <= 30) {
                    flota[i].nb = DemandOrig;
                    solucio[flota[i].getPos()] -= DemandOrig;
                }
                else {
                    flota[i].nb = 30;
                    solucio[flota[i].getPos()] -= 30;
                }

                //Escollim desti i deixem bicis(desti aleatori(relativament))
                int rand = getDest();
                while(rand == flota[i].getPos()) rand = getDest();
                flota[i].setDest(rand);
                //System.out.println("posicio " + flota[i].getDest() + '\n');
                int DemandDest = solucio[rand];

                if (flota[i].nb > (-DemandDest)) {
                    flota[i].nb -= (-DemandDest);
                    benefici += (-DemandDest);
                    solucio[rand] += (-DemandDest);
                } else {
                    solucio[rand] += flota[i].nb;
                    benefici += flota[i].nb;
                    flota[i].nb = 0;
                }
                dist += calculaDist(est.get(rand), est.get(flota[i].getPos()));
                if (flota[i].nb > 0) { //Segon Desti?
                    rand = getDest();
                    while(rand == flota[i].getPos()) rand = getDest();
                    flota[i].setDest(rand);
                    DemandDest = solucio[rand];

                    if (flota[i].nb > (-DemandDest)) {
                        flota[i].nb -= (-DemandDest);
                        solucio[rand] += (-DemandDest);
                    } else {
                        solucio[rand] += flota[i].nb;
                        flota[i].nb = 0;
                    }
                    dist += calculaDist(est.get(rand), est.get(flota[i].getDest()));
                }
            }
        }
    }


    // funcio de generacio aleatoria
    public BicingBoard(int F1,int nest1, int nbic1, int dem1, int seed) {
        benefici = 0;
        nest = nest1;
        F = F1;
        nbic = nbic1;
        dem = dem1;
        est = new Estaciones(nest, nbic, dem, seed);
        flota = new Furgoneta[F];
        for (int i = 0; i < F; i++) {
            flota[i] = new Furgoneta();
            flota[i].Furgoneta();
        }
        solucio = new int[nest]; //vector amb solucio
        for (int i = 0; i < nest; i++) {
            solucio[i] = est.get(i).getNumBicicletasNext() - est.get(i).getDemanda(); //estacions que mes els hi falten bicis(necessaries) => numero mes petit
            //System.out.println("numero bicis que sobren inici en estacio " + i + ": " + (solucio[i]));
        }
        Random random = new Random();
        int rand;
        for (int i =  0; i < F; i++) {
                rand = random.nextInt(nest);
                flota[i].setPos(rand);
        }
        AssignaDesti();
    }


    // funcio de generacio de mapa copia del passat per parametre
    public BicingBoard(int F1,int nest1, int nbic1, int dem1, Estaciones est1, Furgoneta[] flota1) {
        benefici = 0;
        nest = nest1;
        F = F1;
        nbic = nbic1;
        dem = dem1;
        est = est1;
        flota = flota1;
        solucio = new int[nest]; //vector amb solucio
        for (int i = 0; i < nest; i++) {
            solucio[i] = est.get(i).getNumBicicletasNext() - est.get(i).getDemanda(); //estacions que els hi falta + bicis(necessaries) = numero mes petit
        }
        AssignaDesti();
    }
}
