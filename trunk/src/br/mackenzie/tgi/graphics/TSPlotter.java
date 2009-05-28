package br.mackenzie.tgi.graphics;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import aw.gui.chart.Chart2D;
import aw.gui.chart.ITrace2D;
import aw.gui.chart.Trace2DSimple;

/**
 * 
 */
public class TSPlotter {

	/**
	 * 
	 * @param xyData
	 * @param chartName
	 * @param chartTitle
	 * @param xAxisTitle
	 * @param yAxisTitle
	 * @param orientation
	 * @param xSize
	 * @param ySize
	 */
	public static void getXYDataAndPlot(double[][] xyData, String chartName,
			String chartTitle, String xAxisTitle, String yAxisTitle,
			PlotOrientation orientation, int xSize, int ySize) {

		XYSeries plot = new XYSeries(chartName);

		for (int i = 0; i < xyData.length; i++) {
			plot.add(xyData[i][0], xyData[i][1]);
		}

		XYDataset dataset = new XYSeriesCollection(plot);

		JFreeChart chart = ChartFactory.createScatterPlot(chartTitle,
				xAxisTitle, yAxisTitle, dataset, orientation, true, false,
				false);

		// here we can set lines if we want
		// final XYPlot plotter = chart.getXYPlot();
		// final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
		//		
		// renderer.setSeriesLinesVisible(0, true);
		//        
		// plotter.setRenderer(renderer);

		ChartFrame plotFrame = new ChartFrame(chartName, chart);
		plotFrame.setVisible(true);
		plotFrame.setSize(xSize, ySize);
	}

	/**
	 * 
	 * @param tour
	 * @param chartName
	 * @param chartTitle
	 * @param xAxisTitle
	 * @param yAxisTitle
	 * @param orientation
	 * @param xSize
	 * @param ySize
	 */
	public static void getPathAndPlot(int[] tour, double[][] customers,
			String chartName, String chartTitle, String xAxisTitle,
			String yAxisTitle, PlotOrientation orientation, int xSize, int ySize) {

		XYSeries plot = new XYSeries(chartName);
		XYLineAndShapeRenderer lines = new XYLineAndShapeRenderer();

		XYDataset dataset = new XYSeriesCollection(plot);

		JFreeChart chart = ChartFactory.createScatterPlot(chartTitle,
				xAxisTitle, yAxisTitle, dataset, orientation, true, false,
				false);

		ChartFrame plotFrame = new ChartFrame(chartName, chart);

		// for( int i = 0; i < tour.length; i++ )
		// lines.drcustomers[tour[i]][0], customers[tour[i]][1]

		XYPlot xyplot;

		plotFrame.setVisible(true);
		plotFrame.setSize(xSize, ySize);
	}

	public static void test(int[] tour, double[][] customers, Color color,
			String label, String frameLabel) {

		// Create a chart:
		Chart2D chart = new Chart2D();
		// Create an ITrace:
		ITrace2D trace = new Trace2DSimple();
		// Add all points, as it is static:

		for (int i = 0; i < tour.length; i++)
			trace.addPoint(customers[tour[i]][0], customers[tour[i]][1]);

		trace.setColor(color);
		trace.setLabel(label);
		
		// Add the trace to the chart:
		chart.addTrace(trace);
		

		chart.setBounds(300, 300, 800, 600);
		// Make it visible:
		// Create a frame.
		JFrame frame = new JFrame(frameLabel);
		// add the chart to the frame:
		frame.getContentPane().add(chart);
		frame.setSize(800, 600);
		// Enable the termination button [cross on the upper right edge]:
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		frame.setVisible(true);
	}

}
