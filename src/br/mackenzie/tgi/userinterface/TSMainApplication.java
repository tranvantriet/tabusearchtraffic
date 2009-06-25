package br.mackenzie.tgi.userinterface;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import br.mackenzie.tgi.dao.DAOFactory;
import br.mackenzie.tgi.dao.TrafficDAO;
import br.mackenzie.tgi.parameters.TSParameters;
import br.mackenzie.tgi.test.TSEvidencia;
import br.mackenzie.tgi.tools.Traffic;

public class TSMainApplication extends JFrame {

	private static final long serialVersionUID = 1L;

	GridBagLayout layout;
	GridBagConstraints c;


	public static void main(String[] args) {
		TSMainApplication app = new TSMainApplication();
		app.createMenuBar();
		app.addFields();
		
		app.init();
	}

	public TSMainApplication() throws HeadlessException {
		super();
		layout = new GridBagLayout();
		c = new GridBagConstraints();
	    c.anchor = GridBagConstraints.NORTHWEST;  
	    c.fill = GridBagConstraints.NONE;

		
		this.getContentPane().setLayout(layout);  
	}

	/**
	 * +- Method used to initialize the JFrame
	 */
	public void init() {
		this.setTitle("TabuSearch Mackenzie");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setSize(500,175);
		this.setVisible(true);
	}

	/**
	 * 
	 */
	public void addFields() {
		this.addLabels();
		this.addTextBox();
		//this.addCheckBox();
		this.addButtons();
		
	}
	
	/**
	 * 
	 */
	
	public void addLabels(){
		JLabel jLabel01 = new JLabel();
		jLabel01.setText("Número de clientes: ");
		jLabel01.setVisible(true);
		c.gridy = 0;	//	Linha
		c.gridx = 0;	//	Coluna

		layout.setConstraints(jLabel01, c);
		this.getContentPane().add(jLabel01);
		
		
		JLabel jLabel02 = new JLabel();
		jLabel02.setText("Nível Congestionamento: ");
		jLabel02.setVisible(true);
		c.gridy = 1;	//	Linha
		c.gridx = 0;	//	Coluna[
		layout.setConstraints(jLabel02, c);
		this.getContentPane().add(jLabel02);
		/*
		JLabel jLabel03 = new JLabel();
		jLabel03.setText("Gerar tráfico randomicamente: ");
		jLabel03.setVisible(true);
		c.gridy = 3;	//	Linha
		c.gridx = 0;	//	Coluna[
		layout.setConstraints(jLabel03, c);
		this.getContentPane().add(jLabel03);
		
		JLabel jLabel04 = new JLabel();
		jLabel04.setText("Gerar pesos randomicamente: ");
		jLabel04.setVisible(true);
		c.gridy = 4;	//	Linha
		c.gridx = 0;	//	Coluna[
		layout.setConstraints(jLabel04, c);
		this.getContentPane().add(jLabel04);
		*/
		JLabel jLabel05 = new JLabel();
		jLabel05.setText("Nº Iterações TABU: ");
		jLabel05.setVisible(true);
		c.gridy = 2;	//	Linha
		c.gridx = 0;	//	Coluna[
		layout.setConstraints(jLabel05, c);
		this.getContentPane().add(jLabel05);
		
		JLabel jLabel06 = new JLabel();
		jLabel06.setText("Tenure da lista tabu: ");
		jLabel06.setVisible(true);
		c.gridy = 3;	//	Linha
		c.gridx = 0;	//	Coluna
		layout.setConstraints(jLabel06, c);
		this.getContentPane().add(jLabel06);
	}

	/**
	 * 
	 */
	
	public void addButtons(){
		
		JButton botao0 = new JButton("B.I. Histórico");
		c.gridy = 1;	//	Linha
		c.gridx = 3;	//	Coluna
		layout.setConstraints(botao0, c);
		this.getContentPane().add(botao0);
		
		
		ActionListener alGetHistoricIndex = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				
				try{
					Traffic traffic = new Traffic();
					//System.out.println(traffic.updateTrafficIndex());
					if (getContentPane().getComponent(5) instanceof JTextField) {
						
						DAOFactory factory = DAOFactory.getFactory(DAOFactory.MYSQL);
						
						//get a new DAO traffic object
						TrafficDAO trafficDAO = factory.getTrafficDAO();
						
						Calendar calendar = new GregorianCalendar();

						JTextField jtField = ((JTextField)getContentPane().getComponent(5));
						System.out.println(calendar.getTime());
                    	int trafficIndex = (int) trafficDAO.getAverageTrafficByWeekHour(calendar.get(Calendar.DAY_OF_WEEK), calendar.get(Calendar.HOUR_OF_DAY));
                    	jtField.setText(String.valueOf(trafficIndex));
                    }
					
				}catch (Exception e) {
					JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Procura de Índice Histórico",
					        JOptionPane.ERROR_MESSAGE);
				}
			}
		};
		
		botao0.addActionListener(alGetHistoricIndex);
		
		JButton botao1 = new JButton("B. I. Online");
		c.gridy = 1;	//	Linha
		c.gridx = 2;	//	Coluna
		layout.setConstraints(botao1, c);
		this.getContentPane().add(botao1);
		
		
		ActionListener alGetTrafficIndex = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				
				try{
					Traffic traffic = new Traffic();
					//System.out.println(traffic.updateTrafficIndex());
					if (getContentPane().getComponent(5) instanceof JTextField) {
                    	JTextField jtField = ((JTextField)getContentPane().getComponent(5));
                    	int trafficIndex = (int) traffic.updateTrafficIndex();
                    	jtField.setText(String.valueOf(trafficIndex));
                    }
					
				}catch (Exception e) {
					JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Procura de Índice Online",
					        JOptionPane.ERROR_MESSAGE);
				}
			}
		};
		botao1.addActionListener(alGetTrafficIndex);
		
		JButton botao3 = new JButton("Resolver");
		
		c.gridy = 4;	//	Linha
		c.gridx = 3;	//	Coluna
		layout.setConstraints(botao3, c);
		
		ActionListener alResolver = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				
				try {
					TSParameters tsp = new TSParameters();
					
					JTextField jtTIndex = ((JTextField)getContentPane().getComponent(5));
					JTextField jtCustomers = ((JTextField)getContentPane().getComponent(4));
					JTextField jtTBTenure = ((JTextField)getContentPane().getComponent(7));
					JTextField jtIte = ((JTextField)getContentPane().getComponent(6));
					
					
					tsp.setTrafficIndex(Integer.parseInt(jtTIndex.getText()));
					tsp.setCustomerSize(Integer.parseInt(jtCustomers.getText()));
					tsp.setTabuTenure(Integer.parseInt(jtTBTenure.getText()));
					tsp.setTabuSearchIterations(Integer.parseInt(jtIte.getText()));
					
					TSEvidencia.init(tsp);
					
					/*	FRAGA - AQUi È A AÇÂO DO BOTÂO RESOLVER -- Fiz um for que varre todos os componentes e só mostra os TextField e os CheckBox
					//	Tambem printo o numero deles para vc conseguir pegar eles via getContentPane().getComponenet(NUMERO)
					for (int i=0; i < getContentPane().getComponentCount(); i++) {  
					    //varre todos os componentes  
  
					    if (getContentPane().getComponent(i) instanceof JTextField) {
					    	JTextField jtField = ((JTextField)getContentPane().getComponent(i));
					       System.out.println(jtField.getName()+"- id "+i+" valor ="+jtField.getText());
					    } else if (getContentPane().getComponent(i) instanceof JCheckBox) {
					    	JCheckBox jcBox = ((JCheckBox)getContentPane().getComponent(i));
					    	System.out.println("JCheckBox - id "+i+" está selecionado ? = "+jcBox.isSelected());
					    }
					}  
					*/
				} catch (Exception e) {
					JOptionPane.showMessageDialog(new JFrame(), "Valores inválidos para inicialização do programa", "Dialog",
					        JOptionPane.ERROR_MESSAGE);
				}
				
			}
		};
		
		botao3.addActionListener(alResolver);
		this.getContentPane().add(botao3);		
		
	}
	
	/**
	 * 
	 */
	public void addTextBox() {
		JTextField jtfCustomers = new JTextField(10);
		jtfCustomers.setName("Customers");
//		jtfCustomers.setText("10");
//		jtfCustomers.setEditable(false);
		c.gridy = 0;	//	Linha
		c.gridx = 1;	//	Coluna
		layout.setConstraints(jtfCustomers, c);
		this.getContentPane().add(jtfCustomers);
		
		JTextField jtfTrafficIndex = new JTextField(10);
		jtfTrafficIndex.setName("TrafficIndex");
		c.gridy = 1;	//	line
		c.gridx = 1;	//	column
		layout.setConstraints(jtfTrafficIndex, c);
		this.getContentPane().add(jtfTrafficIndex);
		
		JTextField jtfTabuSearchIterations = new JTextField(10);
		jtfTabuSearchIterations.setName("Iterações");
		c.gridy = 2;	//	line
		c.gridx = 1;	//	column
		layout.setConstraints(jtfTabuSearchIterations, c);
		this.getContentPane().add(jtfTabuSearchIterations);
		
		JTextField jtfTenure = new JTextField(3);
		jtfTenure.setName("Tenure");
		c.gridy = 3;	//	line
		c.gridx = 1;	//	column
		layout.setConstraints(jtfTenure, c);
		this.getContentPane().add(jtfTenure);
		
	}

	/**
	 * 
	 */
	public void addCheckBox() {
		JCheckBox jcbDistanteMatriz = new JCheckBox();

		jcbDistanteMatriz.setVisible(true);

		ActionListener alDistanceMatriz = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				System.out.println("Action do alDistanceMatriz iniciada.");
			}
		};

		jcbDistanteMatriz.addActionListener(alDistanceMatriz);
		
		c.gridy = 3;	//	line
		c.gridx = 1;	//	column
		layout.setConstraints(jcbDistanteMatriz, c);
		this.getContentPane().add(jcbDistanteMatriz);
		
		
		JCheckBox jcbTrafficWeight = new JCheckBox();

		jcbTrafficWeight.setVisible(true);

		ActionListener alTrafficWeight = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				System.out.println("Action do alTrafficWeight iniciada.");
			}
		};

		jcbTrafficWeight.addActionListener(alTrafficWeight);
		
		c.gridy = 4;	//	Linha
		c.gridx = 1;	//	Coluna
		layout.setConstraints(jcbTrafficWeight, c);
		this.getContentPane().add(jcbTrafficWeight);
	}

	/**
	 * Method used to create the MenuBar for the JFrame
	 */
	public void createMenuBar() {

		// Menu Bar Session
		JMenuBar menubar = new JMenuBar();

		// Menu Session
		JMenu menuFile = new JMenu("Arquivo");
		JMenu menuHelp = new JMenu("Ajuda");

		// Menu Item Session
		JMenuItem menuQuit = new JMenuItem("Sair");
		JMenuItem menuAbout = new JMenuItem("Sobre");

		menuFile.addSeparator();
		menuFile.add(menuQuit);

		menuHelp.add(menuAbout);

		menubar.add(menuFile);
		menubar.add(menuHelp);

		this.setJMenuBar(menubar);

	}
}
