/**
 * 
 */
package br.mackenzie.tgi.dao;

import java.sql.SQLException;

/**
 * @author rodrigo
 *
 */
public interface TrafficDAO {
	/**
	 * 
	 * @param hour - hour
	 * @return - average traffic for the specified hour
	 */
	public double getAverageTrafficByHour(int hour) throws TrafficDAOException, SQLException;
	/**
	 * 
	 * @param week - Calendar.WEEK (Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday)
	 * @return average traffic by week
	 */
	public double getAverageTrafficByWeek(int week) throws TrafficDAOException, SQLException;
	
	/**
	 * 
	 * @param week - Calendar.WEEK (Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday)
	 * @param hour - hour
	 * @return - average traffic for this week day/hour
	 */
	public double getAverageTrafficByWeekHour(int week, int hour) throws TrafficDAOException, SQLException;

}
