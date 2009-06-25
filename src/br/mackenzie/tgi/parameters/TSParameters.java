package br.mackenzie.tgi.parameters;

/**
 * Essa classe � respons�vel por guardar todos os par�metros que s�o passados
 * para o Tabu Search e outros objetos na aplica��o.
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
	 * Par�metro que configura o tamanho da lista de clientes
	 * 
	 * @param customerSize
	 *            tamanho int de clientes que queremos
	 */
	public void setCustomerSize(int customerSize) {
		this.customerSize = customerSize;
	}

	/**
	 * Retorna o n�mero de clientes atribuido a vari�vel customerSize
	 * 
	 * @return inteiro representando o n�mero de clientes
	 */
	public int getCustomerSize() {
		return customerSize;
	}

	/**
	 * M�todo que atribui a vari�vel privada enableTrafficWEights, respons�vel
	 * por dizer se queremos usar os pesos de tr�nsito ou n�o
	 * 
	 * @param enableTrafficWeights
	 *            uma vari�vel booleana
	 */
	public void setEnableTrafficWeights(boolean enableTrafficWeights) {
		this.enableTrafficWeights = enableTrafficWeights;
	}

	/**
	 * Verifica o estado do par�metro. Modo de usar:
	 * if(parameters.isEnableTrafficWeights()) true action else false action
	 * 
	 * @return uma vari�vel booleana
	 */
	public boolean isEnableTrafficWeights() {
		return enableTrafficWeights;
	}

	/**
	 * Configura se queremos ou n�o gerar randomicamente os pontos x,y de um
	 * n�mero Z de clientes.
	 * 
	 * @param enableRandomCustomerPoints
	 */
	public void setEnableRandomCustomerPoints(boolean enableRandomCustomerPoints) {
		this.enableRandomCustomerPoints = enableRandomCustomerPoints;
	}

	/**
	 * Verifica qual o estado da vari�vel enableRandomCustomerPoints
	 * 
	 * @return uma vari�vel booleana representando o estado da vari�vel privada
	 *         enablerandomCustomerPoints
	 */

	public boolean isEnableRandomCustomerPoints() {
		return enableRandomCustomerPoints;
	}

	/**
	 * Recebe e atribui para a vari�vel interna customerPoints uma matrix
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
	 * Se o par�metro de gera��o rand�mica da malha de clientes estiver
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
	 * Respons�vel por gerar uma matriz de peso a partir das dimens�es de uma
	 * matriz de dist�ncias
	 * 
	 * @param fromDistanceMatrix
	 *            a matriz de dist�ncias que ser� copiada
	 * @param randomValueWeight
	 *            um valor que ser� multiplicado por Math.Random().
	 *            Math.Random() * 5 trar� n�meros de 0 a 5
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
	 * Define o n�mero de itera��es que ser�o passadas para o objeto Tabu
	 * 
	 * @param tabuSearchIterations
	 *            inteiro
	 */
	public void setTabuSearchIterations(int tabuSearchIterations) {
		this.tabuSearchIterations = tabuSearchIterations;
	}

	/**
	 * busca o �ltimo valor para a vari�vel tabusearchIterations
	 * @return
	 */
	public int getTabuSearchIterations() {
		return tabuSearchIterations;
	}

	/**
	 * Define o n�mero do tamanho da lista tabu, o quanto essa solu��o fica na lista at� virar tabu
	 * @param tabuTenure inteiro que define o tenure
	 * 
	 */
	public void setTabuTenure(int tabuTenure) {
		this.tabuTenure = tabuTenure;
	}

	/**
	 * busca o �ltimo valor da vari�vel tabuTenure
	 * @see setTabuTenure
	 * @return inteiro representando tabuTenure
	 */
	public int getTabuTenure() {
		return tabuTenure;
	}
	/**
	 * Atribui para a vari�vel privada uma matriz de dist�ncias
	 * @param distanceMatrix uma matriz de dist�ncia double[][]
	 */

	public void setDistanceMatrix(double[][] distanceMatrix) {
		this.distanceMatrix = distanceMatrix;
	}

	/**
	 * Busca a matriz de dist�ncias
	 * @return a matriz de dist�ncias atual do objeto
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
	 * Atribui para a vari�vel privada uma matriz de pesos
	 * @param trafficweights uma matriz de pesos double[][]
	 */
	public void setTrafficweights(double[][] trafficweights) {
		this.trafficweights = trafficweights;
	}
}
