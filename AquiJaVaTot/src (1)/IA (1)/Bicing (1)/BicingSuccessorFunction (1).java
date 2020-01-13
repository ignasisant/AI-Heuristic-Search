package IA.Bicing;

import aima.search.framework.Successor;
import aima.search.framework.SuccessorFunction;
import java.util.ArrayList;
import java.util.List;



public class BicingSuccessorFunction implements SuccessorFunction {
    public List getSuccessors(Object aState) {
        ArrayList                retVal = new ArrayList();
        BicingBoard             board  = (BicingBoard) aState;
        BicingHeuristicFunction BHF  = new BicingHeuristicFunction();
        int F = board.getF();
        Furgoneta copia[] = new Furgoneta[F];
        copia = board.getFlota();
        Furgoneta Flota[] = new Furgoneta[F];
        Furgoneta c[] = new Furgoneta[F];
        for (int i = 0; i< F; i++) {
            Flota[i] = new Furgoneta();
            Flota[i].setDest(copia[i].getDest());
            Flota[i].setPos(copia[i].getPos());
            c[i] = new Furgoneta();
            c[i].setDest(copia[i].getDest());
            c[i].setPos(copia[i].getPos());
        }
        int Nest = board.getNest();
        int PosAct;
        for (int i = 0; i < F; i++) {
            for (int j = 0; j < Nest; j++) {
                String S = "Un moviment";
                if (j != Flota[i].getPos()) {
                    Flota[i].setPos(j);
                    BicingBoard newBoard = new BicingBoard(F, Nest, board.getNbic(), board.getDem(), board.getEst(), Flota, board.getSeed());
                    retVal.add(new Successor(S, newBoard));
                }
                if (j != c[i].getPos()) {
                    c[i].setDest(j);
                    BicingBoard newBoard2 = new BicingBoard(F, Nest, board.getNbic(), board.getDem(), board.getEst(), c, board.getSeed());
                    retVal.add(new Successor(S, newBoard2));
                }

            }
        }
        return retVal;
    }
}
