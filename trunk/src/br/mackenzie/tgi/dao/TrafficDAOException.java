/**
 * 
 */
package br.mackenzie.tgi.dao;

/**
 * @author rodrigo
 *
 */
@SuppressWarnings("serial")
public class TrafficDAOException extends Exception{
	
	public TrafficDAOException(String s) {
		super(s);
	}
	
	public TrafficDAOException(Throwable t) {
		super(t);
	}
	
	public TrafficDAOException(String s, Throwable t) {
		super(s,t);
	}
	
}
