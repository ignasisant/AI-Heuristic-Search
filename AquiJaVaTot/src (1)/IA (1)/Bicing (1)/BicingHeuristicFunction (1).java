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
        return -(board.getBenefici());
    }

}
