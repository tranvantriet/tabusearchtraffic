package br.mackenzie.tgi.parameters;

/**
 * this class holds all the parameters within TS
 * problem we will use
 * @author rodrigo
 *
 */
public class TSParameters {
	
	public static int POINTS_SIZE = 2;

	/**
	 * Customers size used
	 */
	private int customerSize;
	private boolean enableTrafficWeights = false;
	private boolean enableRandomCustomerPoints = true;
	private double[][] customerPoints;
	private int tabuSearchIterations;
	private int tabuTenure;

	/**
	 * set the customer size parameter
	 * @param customerSize
	 */
	public void setCustomerSize(int customerSize) {
		this.customerSize = customerSize;
	}

	/**
	 * get customer size parameter
	 * @return int customersize
	 */
	public int getCustomerSize() {
		return customerSize;
	}

	public void setEnableTrafficWeights(boolean enableTrafficWeights) {
		this.enableTrafficWeights = enableTrafficWeights;
	}

	public boolean isEnableTrafficWeights() {
		return enableTrafficWeights;
	}

	public void setEnableRandomCustomerPoints(boolean enableRandomCustomerPoints) {
		this.enableRandomCustomerPoints = enableRandomCustomerPoints;
	}

	public boolean isEnableRandomCustomerPoints() {
		return enableRandomCustomerPoints;
	}

	public void setCustomerPoints(double[][] customerPoints) {
		this.customerPoints = customerPoints;
	}

	public double[][] getCustomerPoints() {
		return customerPoints;
	}
	
	public double[][] generateRandomPoints() {
		
		double[][] randomCustomers = new double[this.getCustomerSize()][TSParameters.POINTS_SIZE];
				
		for( int i = 0; i < this.getCustomerSize(); i++ )
    		for( int j = 0; j < 2; j++ )
    			randomCustomers[i][j] = Math.random() * 10;
		
		return randomCustomers;
	}
	
	public double[][] generateRandomEdgeWeigts(double[][] fromDistanceMatrix) {
		double[][] returnvalue = null;
		
		//TODO set return value and generate RandomEdgeWeigths
		return returnvalue;
	}

	public void setTabuSearchIterations(int tabuSearchIterations) {
		this.tabuSearchIterations = tabuSearchIterations;
	}

	public int getTabuSearchIterations() {
		return tabuSearchIterations;
	}

	public void setTabuTenure(int tabuTenure) {
		this.tabuTenure = tabuTenure;
	}

	public int getTabuTenure() {
		return tabuTenure;
	}
	
	
}
