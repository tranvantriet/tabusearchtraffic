package br.mackenzie.tgi.test;
import org.coinor.opents.Move;
import org.coinor.opents.ObjectiveFunction;
import org.coinor.opents.Solution;

import br.mackenzie.tgi.parameters.TSParameters;
import br.mackenzie.tgi.tools.Tools;


public class TSObjectiveFunction implements ObjectiveFunction
{
    public double[][] matrix;
    
    
    public TSObjectiveFunction( double[][] distanceMatrix) 
    {   
    	this.matrix = distanceMatrix;
    }   // end constructor

    public double[] evaluate( Solution solution, Move move )
    {
        int[] tour = ((TSSolution)solution).tour;
        int len = tour.length;
        
        // If move is null, calculate distance from scratch
        if( move == null )
        {
            double dist = 0;
            for( int i = 0; i < len; i++ )
                dist += matrix[ tour[i] ][ i+1 >= len ? 0 : tour[i+1] ];
                
            return new double[]{ dist };
        }   // end if: move == null

        // Else calculate incrementally
        else
        {
            TSSwapMove mv = (TSSwapMove)move;
            int pos1 = -1;
            int pos2 = -1;
            
            // Find positions
            for( int i = 0; i < tour.length; i++ )
                if( tour[i] == mv.customer ) {   
                    pos1 = i;
                    break;
                }
            pos2 = pos1 + mv.movement;
            
            // Logic below requires pos1 < pos2
            if( pos1 > pos2 )
            {
                int temp = pos2;
                pos2 = pos1;
                pos1 = temp;
            }   // end if
            
            // Prior objective value
            double dist = solution.getObjectiveValue()[0];
            
            // Treat a pair swap move differently
            if( pos1 + 1 == pos2 )
            {   
                //     | |
                // A-B-C-D-E: swap C and D, say (works for symmetric matrix only)
                dist -= matrix[ tour[pos1-1] ][ tour[pos1] ];           // -BC
                dist -= matrix[ tour[pos2]   ][ tour[(pos2+1)%len] ];   // -DE
                dist += matrix[ tour[pos1-1] ][ tour[pos2] ];           // +BD
                dist += matrix[ tour[pos1]   ][ tour[(pos2+1)%len] ];   // +CE
                return new double[]{ dist };
            }   // end if: pair swap
            
            // Else the swap is separated by at least one customer
            else
            {   
                //   |     |
                // A-B-C-D-E-F: swap B and E, say
                dist -= matrix[ tour[pos1-1] ][ tour[pos1] ];           // -AB
                dist -= matrix[ tour[pos1]   ][ tour[pos1+1] ];         // -BC
                dist -= matrix[ tour[pos2-1] ][ tour[pos2] ];           // -DE
                dist -= matrix[ tour[pos2]   ][ tour[(pos2+1)%len] ];   // -EF
                
                dist += matrix[ tour[pos1-1] ][ tour[pos2] ];           // +AE
                dist += matrix[ tour[pos2]   ][ tour[pos1+1] ];         // +EC
                dist += matrix[ tour[pos2-1] ][ tour[pos1] ];           // +DB
                dist += matrix[ tour[pos1]   ][ tour[(pos2+1)%len] ];   // +BF
                return new double[]{ dist };
            }   // end else: not a pair swap
        }   // end else: calculate incremental
    }   // end evaluate
    
    

   
   
}   // end class MyObjectiveFunction
