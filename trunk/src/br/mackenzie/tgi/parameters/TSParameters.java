package br.mackenzie.tgi.parameters;

/**
 * Essa classe é responsável por guardar todos os parâmetros que são passados
 * para o Tabu Search e outros objetos na aplicação.
 * 
 * @author rodrigo
 * 
 */
public class TSParameters {

	public static int POINTS_SIZE = 2;

	/**
	 * Customers size used
	 */
	private int customerSize;
	private double[][] distanceMatrix;
	private double[][] trafficweights;
	private boolean enableTrafficWeights = false;
	private boolean enableRandomCustomerPoints = true;
	private double[][] customerPoints;
	private int tabuSearchIterations;
	private int tabuTenure;
	private int trafficIndex;

	public int getTrafficIndex() {
		return trafficIndex;
	}

	public void setTrafficIndex(int trafficIndex) {
		this.trafficIndex = trafficIndex;
	}

	/**
	 * Parâmetro que configura o tamanho da lista de clientes
	 * 
	 * @param customerSize
	 *            tamanho int de clientes que queremos
	 */
	public void setCustomerSize(int customerSize) {
		this.customerSize = customerSize;
	}

	/**
	 * Retorna o número de clientes atribuido a variável customerSize
	 * 
	 * @return inteiro representando o número de clientes
	 */
	public int getCustomerSize() {
		return customerSize;
	}

	/**
	 * Método que atribui a variável privada enableTrafficWEights, responsável
	 * por dizer se queremos usar os pesos de trânsito ou não
	 * 
	 * @param enableTrafficWeights
	 *            uma variável booleana
	 */
	public void setEnableTrafficWeights(boolean enableTrafficWeights) {
		this.enableTrafficWeights = enableTrafficWeights;
	}

	/**
	 * Verifica o estado do parâmetro. Modo de usar:
	 * if(parameters.isEnableTrafficWeights()) true action else false action
	 * 
	 * @return uma variável booleana
	 */
	public boolean isEnableTrafficWeights() {
		return enableTrafficWeights;
	}

	/**
	 * Configura se queremos ou não gerar randomicamente os pontos x,y de um
	 * número Z de clientes.
	 * 
	 * @param enableRandomCustomerPoints
	 */
	public void setEnableRandomCustomerPoints(boolean enableRandomCustomerPoints) {
		this.enableRandomCustomerPoints = enableRandomCustomerPoints;
	}

	/**
	 * Verifica qual o estado da variável enableRandomCustomerPoints
	 * 
	 * @return uma variável booleana representando o estado da variável privada
	 *         enablerandomCustomerPoints
	 */

	public boolean isEnableRandomCustomerPoints() {
		return enableRandomCustomerPoints;
	}

	/**
	 * Recebe e atribui para a variável interna customerPoints uma matrix
	 * double[x][2] contendo coordenadas x,y
	 * 
	 * @param customerPoints
	 *            matriz double[][2] (x,y)
	 */
	public void setCustomerPoints(double[][] customerPoints) {
		this.customerPoints = customerPoints;
	}

	/**
	 * 
	 * @return Retorna o valor corrente de customerPoints
	 */
	public double[][] getCustomerPoints() {
		return customerPoints;
	}

	/**
	 * Se o parâmetro de geração randômica da malha de clientes estiver
	 * habilitado, gere pontos x,y aleatoriamente
	 * 
	 * @return uma matriz x,y representando os clientes
	 */
	public double[][] generateRandomPoints() {

		double[][] randomCustomers = new double[this.getCustomerSize()][TSParameters.POINTS_SIZE];

		for (int i = 0; i < this.getCustomerSize(); i++)
			for (int j = 0; j < 2; j++)
				randomCustomers[i][j] = Math.random() * 10;

		return randomCustomers;
	}

	/**
	 * Responsável por gerar uma matriz de peso a partir das dimensões de uma
	 * matriz de distâncias
	 * 
	 * @param fromDistanceMatrix
	 *            a matriz de distâncias que será copiada
	 * @param randomValueWeight
	 *            um valor que será multiplicado por Math.Random().
	 *            Math.Random() * 5 trará números de 0 a 5
	 * @return uma matriz de pesos
	 */
	public double[][] generateRandomEdgeWeigts(int columns, int lines,
			double randomValueWeight) {

		double[][] returnvalue = new double[columns][lines]; // customers.clone();

		for (int i = 0; i < columns; i++) {
			for (int j = 0; j < lines; j++) {
				
				if(returnvalue[j][i] != 0)
					returnvalue[i][j] = returnvalue[j][i];
				else
					returnvalue[i][j] = Math.random() * randomValueWeight;
			}
		}
		return returnvalue;
	}

	/**
	 * Define o número de iterações que serão passadas para o objeto Tabu
	 * 
	 * @param tabuSearchIterations
	 *            inteiro
	 */
	public void setTabuSearchIterations(int tabuSearchIterations) {
		this.tabuSearchIterations = tabuSearchIterations;
	}

	/**
	 * busca o último valor para a variável tabusearchIterations
	 * @return
	 */
	public int getTabuSearchIterations() {
		return tabuSearchIterations;
	}

	/**
	 * Define o número do tamanho da lista tabu, o quanto essa solução fica na lista até virar tabu
	 * @param tabuTenure inteiro que define o tenure
	 * 
	 */
	public void setTabuTenure(int tabuTenure) {
		this.tabuTenure = tabuTenure;
	}

	/**
	 * busca o último valor da variável tabuTenure
	 * @see setTabuTenure
	 * @return inteiro representando tabuTenure
	 */
	public int getTabuTenure() {
		return tabuTenure;
	}
	/**
	 * Atribui para a variável privada uma matriz de distâncias
	 * @param distanceMatrix uma matriz de distância double[][]
	 */

	public void setDistanceMatrix(double[][] distanceMatrix) {
		this.distanceMatrix = distanceMatrix;
	}

	/**
	 * Busca a matriz de distâncias
	 * @return a matriz de distâncias atual do objeto
	 * @see setDistanceMatrix
	 */
	public double[][] getDistanceMatrix() {
		return distanceMatrix;
	}
	/**
	 * 
	 * @return matriz de pesos
	 */
	public double[][] getTrafficweights() {
		return trafficweights;
	}
	/**
	 * Atribui para a variável privada uma matriz de pesos
	 * @param trafficweights uma matriz de pesos double[][]
	 */
	public void setTrafficweights(double[][] trafficweights) {
		this.trafficweights = trafficweights;
	}
}
