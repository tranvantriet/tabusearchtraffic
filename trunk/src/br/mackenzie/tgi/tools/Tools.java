/**
 * 
 */
package br.mackenzie.tgi.tools;
/**
 * @author rodrigo
 * @category Tools
 * This class will hold all static methods that can be used 
 * as tools to our project
 *
 */
public class Tools {
	/**
	 * Calculates the distance between two points (origin -> destination)
	 * @param x1: origin x value
	 * @param y1: origin y value
	 * @param x2: destination x value
	 * @param y2: destination y value
	 * @return distance between two points
	 */
	public static double CalcDist(double x1, double y1, double x2, double y2) {
		return Math.sqrt(Math.pow(x2-x1,2) + Math.pow(y2-y1, 2));
	}
	
	/**
	 * Overriden method so we can calculate a distance from a distance
	 * matrix
	 * @param matr 
	 * @param a
	 * @param b
	 * @return a calculated distance between 2 points
	 */
    public static double CalcDist( double[][]matr, int a, int b )
    {
    	return Tools.CalcDist(matr[b][0], matr[b][1], matr[a][0], matr[a][1]); 
    }
	
	/**
	 * This method receives a set of points defined (double[][])
	 * and returns a corresponding distance matrix
	 * @param points
	 * @return a double[][] calculated distance matrix
	 */
    public static double[][] createDistanceMatrixFromCoordinates( double[][] points )
    {
        int len = points.length;
        double[][] matrix = new double[len][len];
        
        for( int i = 0; i < len; i++ )
            for( int j = i+1; j < len; j++ )
                matrix[i][j] = matrix[j][i] = Tools.CalcDist(
                		points[i][0], points[i][1],
                		points[j][0], points[j][1] );
        return matrix;
    }
    
    
	
	

}
