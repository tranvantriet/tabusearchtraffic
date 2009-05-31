package br.mackenzie.tgi.test;

import java.awt.Color;

import org.coinor.opents.BestEverAspirationCriteria;
import org.coinor.opents.MoveManager;
import org.coinor.opents.ObjectiveFunction;
import org.coinor.opents.SimpleTabuList;
import org.coinor.opents.SingleThreadedTabuSearch;
import org.coinor.opents.Solution;
import org.coinor.opents.TabuList;
import org.coinor.opents.TabuSearch;
import org.jfree.chart.plot.PlotOrientation;

import br.mackenzie.tgi.graphics.TSPlotter;
import br.mackenzie.tgi.parameters.TSParameters;
import br.mackenzie.tgi.tools.Tools;
import br.mackenzie.tgi.tools.Traffic;

public class TSCore {

	int trafficIndex, tabuIterations, costumers, tenure;
	double[][] trafficweights, customerPoints, matrixDistance, matrixDistance2;

	public TSCore(int tabuIterations, int trafficIndex, int costumers,
			int tenure, double[][] matrixDistance, double[][] trafficweights,
			double[][] customerPoints) {
		super();
		this.costumers = costumers;
		this.tenure = tenure;
		this.matrixDistance = matrixDistance;
		this.tabuIterations = tabuIterations;
		this.trafficIndex = trafficIndex;
		this.trafficweights = trafficweights;
		this.customerPoints = customerPoints;
	}

	public void generate() {

		// this parameter sets customersize
		TSParameters parameters = new TSParameters();

		// sets traffic
		Traffic traffic = new Traffic();
		traffic.setTrafficIndex(trafficIndex);
		// traffic.updateTrafficIndex();

		parameters.setEnableRandomCustomerPoints(true);
		parameters.setCustomerSize(costumers);

		parameters.setTabuSearchIterations(tabuIterations);
		parameters.setTabuTenure(tenure);

		//customerPoints = parameters.generateRandomPoints();

		if (matrixDistance == null)
			matrixDistance = Tools
					.createDistanceMatrixFromCoordinates(customerPoints);
		matrixDistance2 = matrixDistance.clone();

		if (trafficweights == null)
			trafficweights = parameters.generateRandomEdgeWeigts(
					matrixDistance.length, matrixDistance[0].length, 0.01);

		matrixDistance = traffic.correctMatrixDistanceFromTrafficWeights(
				matrixDistance, trafficweights);

		ObjectiveFunction objFunc = new TSObjectiveFunction(matrixDistance);
		Solution initialSolution = new TSInitialSolution(matrixDistance);
		MoveManager moveManager = new TSMoveManager();
		TabuList tabuList = new SimpleTabuList(parameters.getTabuTenure()); // In
		// OpenTS
		// package

		// Create Tabu Search object
		TabuSearch tabuWithTraffic = new SingleThreadedTabuSearch(
				initialSolution, moveManager, objFunc, tabuList,
				new BestEverAspirationCriteria(), // In OpenTS package
				false); // maximizing = yes/no; false means minimizing

		// Start solving
		tabuWithTraffic.setIterationsToGo(parameters.getTabuSearchIterations());
		tabuWithTraffic.startSolving();

		ObjectiveFunction objectiveFunction = new TSObjectiveFunction(
				matrixDistance2);
		Solution solution = new TSInitialSolution(matrixDistance2);
		MoveManager move = new TSMoveManager();
		TabuList tabu = new SimpleTabuList(parameters.getTabuTenure()); // In
		// OpenTS
		// package

		TabuSearch tabuWithoutTraffic = new SingleThreadedTabuSearch(solution,
				move, objectiveFunction, tabu,
				new BestEverAspirationCriteria(), // In OpenTS package
				false); // maximizing = yes/no; false means minimizing

		tabuWithoutTraffic.setIterationsToGo(parameters
				.getTabuSearchIterations());
		tabuWithoutTraffic.startSolving();

		// Show solution
		TSSolution best = (TSSolution) tabuWithTraffic.getBestSolution();

		TSSolution normal = (TSSolution) tabuWithoutTraffic.getBestSolution();

		int[] trafficTour = best.tour;
		int[] normalTour = normal.tour;
		int[] besttour;

		TSRouteEvaluation routeEvaluation = new TSRouteEvaluation(
				matrixDistance, matrixDistance2, trafficTour, normalTour, 20);
		besttour = routeEvaluation.getBestTour();

		// System.out.println("Best Solution:\n" + besttour);
		//		  
		// for (int i = 0; i < trafficTour.length; i++)
		// System.out.println(customers[besttour[i]][0] + "\t" +
		// customers[besttour[i]][1]);

		// for (int i = 0; i < besttour.length; i++) //
		// System.out.println(customers[besttour[i]][0] + "\t" // +
		// customers[besttour[i]][1]);

		TSPlotter.getXYDataAndPlot(customerPoints, "Clientes",
				"Pontos a serem visitados", "X", "Y", PlotOrientation.VERTICAL,
				800, 600);

		TSPlotter.test(trafficTour, customerPoints, Color.blue,
				"Considerando tr�nsito", "Caminho com tr�nsito");

		TSPlotter.test(normalTour, customerPoints, Color.red,
				"Sem usar Tr�nsito", "Caminho sem tr�nsito");

	} // end main

} // end class Main
