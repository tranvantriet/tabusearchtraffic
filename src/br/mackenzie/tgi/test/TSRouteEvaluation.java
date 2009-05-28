package br.mackenzie.tgi.test;

import br.mackenzie.tgi.tools.Tools;

/**
 * Classe respons�vel por validar uma solu��o da melhor rota Crit�rio para
 * escolher a melhor rota: Quando o tempo que eu levei para percorrer a matriz
 * de dist�ncias corrigida for menor que o tempo que levou para percorrer os
 * pontos sem tr�nsito
 * 
 * @author rodrigo
 * 
 */
public class TSRouteEvaluation {

	private double[][] correctedDistanceMatrix;
	private double[][] distanceMatrix;
	private int[] finalTour;
	private double mediumVelocity = 10;

	/**
	 * Construtor p�blico da classe TSrouteEvaluation para configurar as devidas vari�veis internas.
	 * O m�todo n�o se preocupa qual roteiro � com tr�nsito ou sem tr�nsito, simplesmente 
	 * calcula a dist�ncia e tempo total e depois avalia qual roteiro � o melhor levando em considera��o
	 * o tempo e depois a dist�ncia
	 * 
	 * @param correctedDistanceMatrix Matriz de dist�ncia corrigida
	 * @param distanceMatrix Matriz de dist�ncia original
	 * @param firstTour O primeiro roteiro
	 * @param secondTour O secondo roteiro
	 * @param mediumVelocity uma velocidade m�dia especificada
	 */
	public TSRouteEvaluation(double[][] correctedDistanceMatrix,
			double[][] distanceMatrix, int[] firstTour, int[] secondTour,
			double mediumVelocity) {

		this.correctedDistanceMatrix = correctedDistanceMatrix;
		this.distanceMatrix = distanceMatrix;
		this.mediumVelocity = mediumVelocity;

		double firstTourTotalTime = Tools.calculateTourTotalDistance(firstTour,
				correctedDistanceMatrix)/ this.mediumVelocity;
		double firstTourTotalDistance = Tools.calculateTourTotalDistance(
				firstTour, distanceMatrix);

		double secondTourTotalTime = Tools.calculateTourTotalDistance(secondTour,
				correctedDistanceMatrix) / this.mediumVelocity;
		double secondTourTotalDistance = Tools.calculateTourTotalDistance(
				secondTour, distanceMatrix);
		
		System.out.println(String.valueOf(firstTourTotalTime).replace(".", ",") + "\t" + String.valueOf(firstTourTotalDistance).replace(".", ","));
		System.out.println(String.valueOf(secondTourTotalTime).replace(".", ",") + "\t" + String.valueOf(secondTourTotalDistance).replace(".", ","));

		if (firstTourTotalTime < secondTourTotalTime) {
			this.finalTour = firstTour;
		} else if (firstTourTotalTime == secondTourTotalTime) {
			if (firstTourTotalDistance < secondTourTotalDistance)
				this.finalTour = firstTour;
			else
				this.finalTour = secondTour;

		} else
			this.finalTour = secondTour;

	}

	/**
	 * M�todo espec�fico que retorna o melhor roteiro. Deve ser chamado depois de instanciado
	 * o objeto TSRouteEvaluation
	 * @see TSRouteEvaluation
	 * @return um array de inteiros contendo o melhor percurso
	 */
	public int[] getBestTour() {
		return this.finalTour;
	}

}
