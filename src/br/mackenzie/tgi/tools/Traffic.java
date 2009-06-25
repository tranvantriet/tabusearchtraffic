package br.mackenzie.tgi.tools;

import java.io.BufferedReader;
import java.io.CharArrayReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Essa classe � respons�vel por gerar o �ndice de tr�nsito que ser�
 * utilizado na corre��o da matriz de dist�ncia
 * 
 * Modo de utilizar:
 * Traffic traffic = new Traffic();
 * traffic.setTrafficIndex(10) aqui dizemos para a classe que queremos um �ndice manual
 * ou
 * traffic.updateTrafficIndex() esse m�todo faz com que o �ndice seja o mesmo �ndice
 * que a CET gera, pois busca as informa��es do �ndice de tr�nsito pelo site
 * 
 * @author rodrigo
 * 
 */
public class Traffic {

	private static String site = "http://sponlinetraffic.appspot.com/onlinetraffic?lentidao"; 
	private double TRAFFIC_INDEX = 0;

	/**
	 * Faz o update do index
	 * 
	 * @return um n�mero representando o �ndice de tr�nsito online
	 */
	public double updateTrafficIndex() throws Exception  {

		try {
			this.TRAFFIC_INDEX = this.ConnectAndGetResults();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception("Cheque a conex�o com a internet");
		}

		return TRAFFIC_INDEX;
	}

	/**
	 * Esse m�todo � respons�vel por conectar no site que prov� o XML, ler o XML
	 * e atribuir o valor estipulado no XML para a vari�vel TRAFFIC_INDEX
	 * 
	 * @return um valor double do �ndice de tr�nsito
	 * @throws MalformedURLException
	 *             exce��o no caso de URL mal interpretada
	 * @throws IOException
	 *             Erros de Input/Output
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 *             Erros no parse do XML
	 */
	private double ConnectAndGetResults() throws MalformedURLException,
			IOException, ParserConfigurationException, SAXException {

		URL url = new URL(Traffic.site);

		URLConnection connection = url.openConnection();

		connection.setUseCaches(false);

		InputStream inputstream = connection.getInputStream();

		InputStreamReader inputreader = new InputStreamReader(inputstream);

		BufferedReader reader = new BufferedReader(inputreader);

		String input = "";
		String xmlText = "";

		while ((input = reader.readLine()) != null) {
			xmlText += input;

		}

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		DocumentBuilder builder = factory.newDocumentBuilder();

		Reader charArray = new CharArrayReader(xmlText.toCharArray());

		Document doc = builder.parse(new org.xml.sax.InputSource(charArray));

		NodeList nList = doc.getChildNodes();

		Node node = nList.item(0);

		NodeList innerList = node.getChildNodes();
		
		Node innerNode = innerList.item(0);
		
		Element elmt = (Element)innerNode;
		
		return Double.parseDouble(elmt.getTextContent());
		
	}
	/**
	 * @return retorna o valor corrente da vari�vel TRAFFIC_INDEX 
	 */
	public double getLastTrafficIndex()
	{
		return this.TRAFFIC_INDEX;
	}
	
	/**
	 * Corrige uma matriz de dist�ncias fazendo 
	 * 
	 * formula principal:
	 * novaDistancia = distanciaAtual[][] * ( 1 + (TRAFFIC_INDEX * matrizPesos[][]));
	 * 
	 * @param currentDistanceMatrix a matriz de dist�ncias atual
	 * @param trafficWeightMatrix a matriz de pesos 
	 * @return uma nova matriz de dist�ncias corrigidas
	 */
	public double[][] correctMatrixDistanceFromTrafficWeights(double[][] currentDistanceMatrix, double[][] trafficWeightMatrix)
	{
		
		//check if matrix is equal
		if((currentDistanceMatrix.length == trafficWeightMatrix.length) && (currentDistanceMatrix[0].length == trafficWeightMatrix[0].length))
		{
			double[][] correctedDistanceMatrix = new double[currentDistanceMatrix.length][currentDistanceMatrix[0].length];
			//if its equal, start loop process	
			for(int i=0;i<currentDistanceMatrix.length;i++)
			{
				for(int j=0;j<currentDistanceMatrix[0].length;j++)
				{
					//calculate the distance as: new distance[][] = currentdistance[][] * (1 + TRAFFIC_WEIGHT * trafficWeightMatrix[][])
					correctedDistanceMatrix[i][j] = currentDistanceMatrix[i][j] * ( 1 + (this.TRAFFIC_INDEX * trafficWeightMatrix[i][j]));
				}
			}
				
			//return the new distance matrix
			return correctedDistanceMatrix;
		}
		else
		{
			//matrixes are not equal, so return a new object
			return new double[0][0];
		}
		
	}
	/**
	 * atribui um valor para a vari�vel TRAFFIC_INDEX
	 * @param um novo valor para o �ndice de tr�nsito
	 */
	public void setTrafficIndex(double indexValue) {
		this.TRAFFIC_INDEX = indexValue;
	}
}
