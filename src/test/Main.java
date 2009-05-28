package test;

import java.awt.Color;

import org.coinor.opents.BestEverAspirationCriteria;
import org.coinor.opents.MoveManager;
import org.coinor.opents.ObjectiveFunction;
import org.coinor.opents.SimpleTabuList;
import org.coinor.opents.SingleThreadedTabuSearch;
import org.coinor.opents.Solution;
import org.coinor.opents.TabuList;
import org.coinor.opents.TabuSearch;

import br.mackenzie.tgi.graphics.TSPlotter;

public class Main 
{

    public static void main (String args[]) 
    {
        // Initialize our objects
        java.util.Random r = new java.util.Random( 12345 );
        double[][] customers = new double[1000][2];
        for( int i = 0; i < 1000; i++ )
            for( int j = 0; j < 2; j++ )
                customers[i][j] = r.nextDouble()*200;
        
        ObjectiveFunction objFunc = new MyObjectiveFunction( customers );
        Solution initialSolution  = new MyGreedyStartSolution( customers );
        MoveManager   moveManager = new MyMoveManager();
        TabuList         tabuList = new SimpleTabuList( 10 ); // In OpenTS package
        
        // Create Tabu Search object
        TabuSearch tabuSearch = new SingleThreadedTabuSearch(
                initialSolution,
                moveManager,
                objFunc,
                tabuList,
                new BestEverAspirationCriteria(), // In OpenTS package
                false ); // maximizing = yes/no; false means minimizing
        
        // Start solving
        tabuSearch.setIterationsToGo( 100 );
        tabuSearch.startSolving();
        
        // Show solution
        MySolution best = (MySolution)tabuSearch.getBestSolution();
        System.out.println( "Best Solution:\n" + best );

        int[] tour = best.tour;
        for( int i = 0; i < tour.length; i++ )
            System.out.println( 
             customers[tour[i]][0] + "\t" + customers[tour[i]][1] );
        
        TSPlotter.test(tour, customers, Color.blue, "teste", "teste");
    }   // end main

}   // end class Main
