package br.mackenzie.tgi.test;

public class TSInitialTrafficSolution extends TSSolution {

	public TSInitialTrafficSolution() {}; //clone
	
	public TSInitialTrafficSolution(double[][] distanceMatrix,
			double[][] trafficWeightsMatrix, double trafficIndex) {

		if (trafficIndex == 0)

			this.findSuitableMatrix(distanceMatrix);

		else
			this.findSuitableMatrix(trafficWeightsMatrix);

	}

	private void findSuitableMatrix(double[][] distOrWeighMatrix) {
		int[] avail = new int[distOrWeighMatrix.length];

		tour = new int[distOrWeighMatrix.length];

		for (int i = 0; i < avail.length; i++)
			avail[i] = i;

		for (int i = 1; i < tour.length; i++) {
			int closest = -1;

			double dist = Double.MAX_VALUE;

			for (int j = 1; j < avail.length; j++) {

				double currentDistance = distOrWeighMatrix[tour[i - 1]][j];

				if (currentDistance < dist && (avail[j] >= 0)) {
					dist = currentDistance;
					closest = j;
				}
			}

			tour[i] = closest;
			avail[closest] = -1;
		} // end for
	}

}
