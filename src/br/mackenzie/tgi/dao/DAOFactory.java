/**
 * 
 */
package br.mackenzie.tgi.dao;

import java.sql.SQLException;

/**
 * @author rodrigo
 * @category DataAccess
 * 
 */
public abstract class DAOFactory {
	
	//implement another database here
	public static final int MYSQL = 1;
	//public static final int ORACLE = 2;

	public abstract TrafficDAO getTrafficDAO() throws ClassNotFoundException, SQLException;
	/**
	 * 
	 * @param factory: current database we're using
	 * @return a DAO specific database object
	 */
	public static DAOFactory getFactory(int factory) {
		switch(factory)
		{
		case 1:
			return new MysqlDAOFactory();
		//case 2:
			//return new OracleDAOFactory();
		default:
			return null;
		}
	}

}
