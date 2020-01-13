
package IA.Bicing;
import java.util.*;
import java.util.Random;

import aima.search.framework.Problem;
import aima.search.framework.Search;
import aima.search.framework.SearchAgent;
import aima.search.informed.HillClimbingSearch;
import aima.search.informed.SimulatedAnnealingSearch;


public class Solucio
{


    public static void main(String[] args) {
        Scanner S = new Scanner(System.in);
        System.out.println("Num furgos:");
        int F = S.nextInt();
        System.out.println("Semilla?:");
        int seed = S.nextInt();
        System.out.println("Demanda? 0 = equilibrada, 1 = hora punta");
        int dem = S.nextInt();
        System.out.println("Hill o Simulated? 0 = Hill 1 = simulated");
        int HS = S.nextInt();
        BicingBoard Board = new BicingBoard(F, 25, 1250, dem, seed);
        long time = System.currentTimeMillis();
        if (HS == 0){
            Search search = new HillClimbingSearch();
            BicingHillClimbingSearch(Board);
        }
        else {
            Search search = new SimulatedAnnealingSearch();
            BicingSimulatedAnnealingSearch(Board);
        }

        System.out.println("tiempo: " + (System.currentTimeMillis() - time));
    }



    private static void BicingSimulatedAnnealingSearch(BicingBoard Board) {
        try {
            Problem problem =  new Problem(Board,new BicingSuccessorFunctionSA(), new BicingGoalTest(),new BicingHeuristicFunction());
            SimulatedAnnealingSearch search =  new SimulatedAnnealingSearch(2000,100,5,0.001);
            SearchAgent agent = new SearchAgent(problem,search);
            BicingBoard b = (BicingBoard) search.getGoalState();
            System.out.println("lol " + b.getBenefici());


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void BicingHillClimbingSearch(BicingBoard Board) {
        try {
            Problem problem =  new Problem(Board,new BicingSuccessorFunction(), new BicingGoalTest(),new BicingHeuristicFunction());
            Search search =  new HillClimbingSearch();
            SearchAgent agent = new SearchAgent(problem,search);
            BicingBoard b = (BicingBoard) search.getGoalState();
            System.out.println("lol " + b.getBenefici());
            printActions(agent.getActions());
            printInstrumentation(agent.getInstrumentation());
        } catch (Exception e) {
        }
    }

    private static void printInstrumentation(Properties properties) {
        Iterator keys = properties.keySet().iterator();
        while (keys.hasNext()) {
            String key = (String) keys.next();
            String property = properties.getProperty(key);
            System.out.println(key + " : " + property);
        }

    }

    private static void printActions(List actions) {
        for (int i = 0; i < actions.size(); i++) {
            String action = (String) actions.get(i);
            System.out.println(action);
        }
    }


}
