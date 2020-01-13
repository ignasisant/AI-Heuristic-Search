package IA.Bicing;

import aima.search.framework.Successor;
import aima.search.framework.SuccessorFunction;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;



public class BicingSuccessorFunctionSA implements SuccessorFunction {
    public List getSuccessors(Object aState) {
        ArrayList                retVal = new ArrayList();
        BicingBoard             board  = (BicingBoard) aState;
        BicingHeuristicFunction BHF  = new BicingHeuristicFunction();
        Random random = new Random();
        int rand, rand2;
        Furgoneta Flota[] = board.getFlota();
        rand = random.nextInt(board.getF());
        int posOrig = Flota[rand].getPos();
        while(Flota[rand].getPos() == posOrig) {
            rand2 = random.nextInt(board.getNest());
            if (rand2%2 == 0){
                rand2 = random.nextInt(board.getNest());
                Flota[rand].setPos(rand2);
            }
            else {
                rand2 = random.nextInt(board.getNest());
                Flota[rand].setDest(rand2);
            }
        }

        BicingBoard newBoard = new BicingBoard(board.getF(), board.getNest(), board.getNbic(), board.getDem(), board.getEst(), Flota, board.getSeed());


        double   v = BHF.getHeuristicValue(newBoard);
        String S = "Canvi " ;

        retVal.add(new Successor(S, newBoard));

        return retVal;
    }
}
