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
        Furgoneta Flota[] = board.getFlota();
        int Nest = board.getNest();
        int F  = board.getF();
        int PosAct;
        for (int i = 0; i < F; i++) {
            PosAct = Flota[i].getPos();
            for (int j = 0; j < Nest; j++) {
                if (j != PosAct) Flota[i].setPos(j);
                BicingBoard newBoard = new BicingBoard(F, Nest, board.getNbic(), board.getDem(), board.getEst(), Flota);
                double   v = BHF.getHeuristicValue(newBoard);
                String S = "Canviem furgoneta numero " + i + " de la Estacion numero " + PosAct + " a  la Estacion numero " + j;
                retVal.add(new Successor(S, newBoard));
            }
        }

        return retVal;
    }
}
