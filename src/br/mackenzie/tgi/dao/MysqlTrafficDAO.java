/**
 * 
 */
package br.mackenzie.tgi.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author rodrigo
 *
 */
public class MysqlTrafficDAO implements TrafficDAO{
	

	private Connection 	connection;
	private ResultSet	resultset;
	private Statement	statement;
	
	public MysqlTrafficDAO() throws ClassNotFoundException, SQLException {
		connection = MysqlDAOFactory.createConnection();
		
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#finalize()
	 */
	@Override
	protected void finalize() throws Throwable {
		resultset.close();
		statement.close();
		connection.close();
		super.finalize();
	}
	
	/* (non-Javadoc)
	 * @see br.mackenzie.tgi.dao.TrafficDAO#getAverageTrafficByHour(int)
	 */
	@Override
	public double getAverageTrafficByHour(int hour) throws TrafficDAOException, SQLException {
		
		statement = connection.createStatement();
		resultset = statement.executeQuery("SELECT AVG(lentidao) as avg from ICOESP WHERE DATE_FORMAT(dataindice, '%H') = " + String.valueOf(hour) + ";");
		if(resultset.next())
			return resultset.getDouble(1);
		else
			return 0;
		
	}

	/* (non-Javadoc)
	 * @see br.mackenzie.tgi.dao.TrafficDAO#getAverageTrafficByWeek(int)
	 */
	@Override
	public double getAverageTrafficByWeek(int week) throws TrafficDAOException, SQLException{
		statement = connection.createStatement();
		resultset = statement.executeQuery("SELECT AVG(lentidao) as avg from ICOESP WHERE DATE_FORMAT(dataindice, '%w') = " + String.valueOf(week) + ";");
		
		if(resultset.next())
			return resultset.getDouble(1);
		else
			return 0;
	}

	/* (non-Javadoc)
	 * @see br.mackenzie.tgi.dao.TrafficDAO#getAverageTrafficByWeekHour(int, int)
	 */
	@Override
	public double getAverageTrafficByWeekHour(int week, int hour)
			throws TrafficDAOException, SQLException {
		statement = connection.createStatement();
		resultset = statement.executeQuery("SELECT AVG(lentidao) as avg from ICOESP WHERE DATE_FORMAT(dataindice, '%w') = " + String.valueOf(week) + " AND DATE_FORMAT(dataindice, '%H') = " + String.valueOf(hour) + ";");
		
		if(resultset.next())
			return resultset.getDouble(1);
		else
			return 0;
	}

}
