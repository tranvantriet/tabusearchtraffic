package br.mackenzie.tgi.test;

import br.mackenzie.tgi.tools.Tools;

/**
 * A classe TSInitialSolution extende a classe TSSolution e provê uma solução inicial,
 * que no final funciona como uma organizadora dos arrays - ela recebe uma matriz de distância
 * e organiza os pontos a serem visitados pela menor distância.
 * Interessante observar que aqui podemos colocar também a matriz de pesos e fazer uma solução inicial
 * de acordo com os menores pesos.
 * 
 * @author rodrigo
 *
 */
public class TSInitialSolution extends TSSolution{

	public TSInitialSolution() {}; //clone
	
	/**
	 * O método recebe uma matriz de distâncias e atribui para a variável "tour" 
	 * a solução inicial
	 * @param distanceMatrix
	 */
	public TSInitialSolution( double[][] distanceMatrix )
    {
        // Greedy neighbor initialize
		int[] avail = new int[ distanceMatrix.length ];
		
        tour = new int[ distanceMatrix.length ];
        
        for( int i = 0; i < avail.length; i++ )
            avail[i] = i;
        
        for( int i = 1; i < tour.length; i++ )
        {
            int closest = -1;
            
            double dist = Double.MAX_VALUE;
            
            for( int j = 1; j < avail.length; j++ )
            {
            
            	double currentDistance = distanceMatrix[tour[i-1]][j];
            
            	if( currentDistance < dist && (avail[j] >= 0) )
                {   
                    dist = currentDistance;
                    closest = j;
                }   
            }
            
            tour[i] = closest;
            avail[closest] = -1;
        }   // end for
	}


}
