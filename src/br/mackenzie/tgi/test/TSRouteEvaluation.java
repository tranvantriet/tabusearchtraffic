package br.mackenzie.tgi.test;

import br.mackenzie.tgi.tools.Tools;

/**
 * Classe responsável por validar uma solução da melhor rota Critério para
 * escolher a melhor rota: Quando o tempo que eu levei para percorrer a matriz
 * de distâncias corrigida for menor que o tempo que levou para percorrer os
 * pontos sem trânsito
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
	 * Construtor público da classe TSrouteEvaluation para configurar as devidas variáveis internas.
	 * O método não se preocupa qual roteiro é com trânsito ou sem trânsito, simplesmente 
	 * calcula a distância e tempo total e depois avalia qual roteiro é o melhor levando em consideração
	 * o tempo e depois a distância
	 * 
	 * @param correctedDistanceMatrix Matriz de distância corrigida
	 * @param distanceMatrix Matriz de distância original
	 * @param firstTour O primeiro roteiro
	 * @param secondTour O secondo roteiro
	 * @param mediumVelocity uma velocidade média especificada
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
	 * Método específico que retorna o melhor roteiro. Deve ser chamado depois de instanciado
	 * o objeto TSRouteEvaluation
	 * @see TSRouteEvaluation
	 * @return um array de inteiros contendo o melhor percurso
	 */
	public int[] getBestTour() {
		return this.finalTour;
	}

}
