package IA.Bicing;

import aima.search.framework.HeuristicFunction;

public class BicingHeuristicFunction implements HeuristicFunction  {

    public boolean equals(Object obj) {
        boolean retValue;

        retValue = super.equals(obj);
        return retValue;
    }

    public double getHeuristicValue(Object state) {
        BicingBoard board=(BicingBoard)state;
        /*int sum = 0;
        int nest = board.getNest();
        int[] sol = board.getSol();
        for(int i=0; i < nest; i++) {
            if (sol[i]  < 0) sum -= sol[i];
        }*/
        //System.out.println(board.getBenefici() + " " + board.getDist());
        return -(board.getBenefici());
    }

}
