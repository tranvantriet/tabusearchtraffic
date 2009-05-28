/**
 * 
 */
package br.mackenzie.tgi.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author rodrigo
 *
 */
public class MysqlDAOFactory extends DAOFactory{
		//TODO Read all this information from a XML file
		public static final String DRIVER = "com.mysql.jdbc.Driver";
		public static final String DBURL = "jdbc:mysql://hostname/icoesp";
		public static final String DB = "";
		public static final String SERVER = "hostname";
		public static final String USERNAME = "root";
		public static final String PASSWORD = "";
		
		
		/**
		 * 
		 * @return a new Connection
		 * @throws ClassNotFoundException
		 * @throws SQLException
		 */
		public static Connection createConnection() throws ClassNotFoundException, SQLException {
			Class.forName(MysqlDAOFactory.DRIVER);

			return DriverManager.getConnection(MysqlDAOFactory.DBURL, MysqlDAOFactory.USERNAME, MysqlDAOFactory.PASSWORD);
		}
		
		/**
		 * Get the current DAO
		 */
		public TrafficDAO getTrafficDAO() throws ClassNotFoundException, SQLException {
			return new MysqlTrafficDAO();
		}

}
