package IA.Bicing;
import java.util.*;

public class BicingBoard {

    private int solucio[]; //bicis sobrants
    private Furgoneta flota[];
    private Estaciones est;
    private int nest, F, nbic, dem, dist, solIni, solFi, seed;
    public int benefici;

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
    public int calculaDist(int i, int j) {  //calcul cost
        return (Math.abs(est.get(i).getCoordX()- est.get(j).getCoordX()) + Math.abs(est.get(i).getCoordY()- est.get(j).getCoordY()));
    }
    public int getSeed() {
        return seed;
    }
    public void imprimeix(){
        for (int i = 0; i < nest; i++) {
            System.out.println("Estacio " + i + " posicio: " +  est.get(i).getCoordX() + ", " + est.get(i).getCoordY() + ". Sobren " + solucio[i] + " bicis");
        }
        for (int i = 0; i < F; i++) {
            System.out.println("Furgoneta " + i + " en estacio " + flota[i].getPos() + " desti " + flota[i].getDest() + "\n");
        }
    }

    private int min (int x, int y) {
        if (x < y) return x;
        else return y;
    }

    public void Calcula(int i) {
        Random random = new Random(seed);
        int rand = random.nextInt(nest);
        int disponible, cost;
        disponible = est.get(flota[i].getPos()).getNumBicicletasNoUsadas();
        cost = (calculaDist(flota[i].getPos(), flota[i].getDest())/1000) * calculaCost(disponible);
        benefici -= cost;
        if (cost <= min(solucio[flota[i].getDest()], disponible)) {
            if (disponible < solucio[flota[i].getDest()]) { // mes demanda que bicis disponibles
                solucio[flota[i].getDest()] -= disponible;
            } else {  //mes bicis disponiles que demanda
                disponible -= solucio[flota[i].getDest()];
                solucio[flota[i].getDest()] = 0;

                benefici -= cost;
                for (int k = 0; k < nest; k++) {
                    cost = (calculaDist(flota[i].getDest(), k)/1000) * calculaCost(disponible);
                    if (cost <= min(solucio[k],disponible)) k = nest;
                }
                benefici -= cost;
                solucio[i] -= disponible;
            }
        }
    }

    // funcio de generacio aleatoria
    public BicingBoard(int F1,int nest1, int nbic1, int dem1, int seed1) {
        seed = seed1;
        benefici = 0;
        nest = nest1;
        F = F1;
        nbic = nbic1;
        dem = dem1;
        est = new Estaciones(nest, nbic, dem, seed);
        flota = new Furgoneta[F];
        for (int i = 0; i < F; i++) {//Inicialitzem
            flota[i] = new Furgoneta();
        }
        solucio = new int[nest]; //vector amb solucio
        for (int i = 0; i < nest; i++) {
            solucio[i] = est.get(i).getDemanda() - est.get(i).getNumBicicletasNext();
            if (solucio[i] < 0) solucio[i] = 0;
            solIni += solucio[i];
        }
        int ocupat[] = new int[nest]; //vector per saber si en una estacio hi ha una furgoneta
        Random random = new Random(seed);
        int rand, rand2;
        for (int i = 0; i < F; i++) {
            rand2 = random.nextInt();
           // if (rand2%2 == 0) { //escollim si desplacem la furgoneta (aleatoriament)
                do {
                    rand = random.nextInt(nest);
                    flota[i].setPos(rand);
                }
                while (ocupat[rand] == 1); //Inicialitzem furgos en estacions randoms pero no en la mateixa estacio
                ocupat[rand] = 1;
                do {
                    rand = random.nextInt(nest);
                    flota[i].setDest(rand);
                } while (rand == flota[i].getPos()); //desti diferent a origen
                Calcula(i);
           // }
        }
        for (int i = 0; i < nest; i++) {
            solFi += solucio[i];
        }
        benefici = solIni -solFi;
    }


    // funcio de generacio de mapa copia del passat per parametre
    public BicingBoard(int F1,int nest1, int nbic1, int dem1, Estaciones est1, Furgoneta[] flota1, int seed1) {
        seed = seed1;
        benefici = 0;
        nest = nest1;
        F = F1;
        nbic = nbic1;
        dem = dem1;
        est = est1;
        flota = new Furgoneta[F];
        for (int i = 0; i < F; i++) {//Inicialitzem
            flota[i] = new Furgoneta();
            flota[i] = flota1[i];
        }
        solucio = new int[nest]; //vector amb solucio
        for (int i = 0; i < nest; i++) {
            solucio[i] = est.get(i).getDemanda() - est.get(i).getNumBicicletasNext();
            if (solucio[i] < 0) solucio[i] = 0;
            solIni += solucio[i];
        }
        for (int i = 0; i < F; i++) {
            Calcula(i);
        }
        for (int i = 0; i < nest; i++) {
            solFi += solucio[i];
        }
        benefici = solIni - solFi;
    }
}
