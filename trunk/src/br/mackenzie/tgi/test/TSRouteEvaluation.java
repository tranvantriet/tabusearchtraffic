package br.mackenzie.tgi.test;

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

	public TSRouteEvaluation(double[][] correctedDistanceMatrix,
			double[][] distanceMatrix, int[] firstTour, int[] secondTour,
			double mediumVelocity) {

		this.correctedDistanceMatrix = correctedDistanceMatrix;
		this.distanceMatrix = distanceMatrix;
		this.mediumVelocity = mediumVelocity;

		double firstTourTotalTime = this.calculateTourTotalTime(firstTour,
				correctedDistanceMatrix);
		double firstTourTotalDistance = this.calculateTourTotalDistance(
				firstTour, distanceMatrix);

		double secondTourTotalTime = this.calculateTourTotalTime(secondTour,
				correctedDistanceMatrix);
		double secondTourTotalDistance = this.calculateTourTotalDistance(
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

	public int[] getBestTour() {
		return this.finalTour;
	}

	private double calculateTourTotalTime(int[] tour,
			double[][] localDistanceMatrix) {
		int lastTour = 0;
		double totalTime = 0.0;

		for (int i = 0; i < tour.length; i++) {
			totalTime += localDistanceMatrix[tour[i]][lastTour]
					/ mediumVelocity;
			lastTour = tour[i];
		}

		return totalTime;
	}

	private double calculateTourTotalDistance(int tour[],
			double[][] localDistanceMatrix) {
		int lastTour = 0;
		double totalDistance = 0.0;

		for (int i = 0; i < tour.length; i++) {
			totalDistance += localDistanceMatrix[tour[i]][lastTour];
			lastTour = tour[i];
		}

		return totalDistance;
	}

}
