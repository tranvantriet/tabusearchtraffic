
package br.mackenzie.tgi.test;

import org.coinor.opents.*;


public class TSMoveManager implements MoveManager
{
    
    public Move[] getAllMoves( Solution solution )
    {   
        int[] tour = ((TSSolution)solution).tour;
        Move[] buffer = new Move[ tour.length*tour.length ];
        int nextBufferPos = 0;
        
        // Generate moves that move each customer
        // forward and back up to five spaces.
        for( int i = 1; i < tour.length; i++ )
            for( int j = -5; j <= 5; j++ )
                if( (i+j >= 1) && (i+j < tour.length) && (j != 0) )
                    buffer[nextBufferPos++] = new TSSwapMove( tour[i], j );
         
        // Trim buffer
        Move[] moves = new Move[ nextBufferPos];
        System.arraycopy( buffer, 0, moves, 0, nextBufferPos );
        
        return moves;
    }   // end getAllMoves
    
}   // end class MyMoveManager
