package br.mackenzie.tgi.dao;

import java.sql.SQLException;
/**
 * 
 * @author rodrigo
 * @category Test
 * This class was created only for tests purposes
 */
public class DAOTester {
	public static void main(String args[]) throws ClassNotFoundException, SQLException, TrafficDAOException { 
		//get a new factory
		DAOFactory factory = DAOFactory.getFactory(DAOFactory.MYSQL);
		
		//get a new DAO traffic object
		TrafficDAO traffic = factory.getTrafficDAO();
		
		//acess the database and print the results
		try {
			System.out.println(String.valueOf(traffic.getAverageTrafficByHour(19)));
			System.out.println(String.valueOf(traffic.getAverageTrafficByWeekHour(5,7)));
			System.out.println(String.valueOf(traffic.getAverageTrafficByWeek(1)));				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
