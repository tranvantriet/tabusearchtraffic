package br.mackenzie.tgi.userinterface;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;

import br.mackenzie.tgi.parameters.TSParameters;

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
		this.setSize(600,400);
		this.pack();
		this.setVisible(true);
		
	}

	/**
	 * 
	 */
	public void addFields() {
		this.addLabels();
		this.addTextBox();
		this.addCheckBox();
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
		jLabel02.setText("Índice de Tráfico: ");
		jLabel02.setVisible(true);
		c.gridy = 1;	//	Linha
		c.gridx = 0;	//	Coluna[
		layout.setConstraints(jLabel02, c);
		this.getContentPane().add(jLabel02);
		
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
		
		JLabel jLabel05 = new JLabel();
		jLabel05.setText("Número de iterações: ");
		jLabel05.setVisible(true);
		c.gridy = 2;	//	Linha
		c.gridx = 0;	//	Coluna[
		layout.setConstraints(jLabel05, c);
		this.getContentPane().add(jLabel05);
	}

	/**
	 * 
	 */
	
	public void addButtons(){
		JButton botao1 = new JButton("Buscar Índice Online");
		c.gridy = 1;	//	Linha
		c.gridx = 2;	//	Coluna
		layout.setConstraints(botao1, c);
		this.getContentPane().add(botao1);
		
		JButton botao3 = new JButton("Resolver");
		
		c.gridy = 5;	//	Linha
		c.gridx = 3;	//	Coluna
		layout.setConstraints(botao3, c);
		
		ActionListener alResolver = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				
				TSParameters parameters = new TSParameters();
				
				//	FRAGA - AQUi È A AÇÂO DO BOTÂO RESOLVER -- Fiz um for que varre todos os componentes e só mostra os TextField e os CheckBox
				//	Tambem printo o numero deles para vc conseguir pegar eles via getContentPane().getComponenet(NUMERO)
				for (int i=0; i < getContentPane().getComponentCount(); i++) {  
                    //varre todos os componentes  
  
                    if (getContentPane().getComponent(i) instanceof JTextField) {
                    	JTextField jtField = ((JTextField)getContentPane().getComponent(i));
                       System.out.println("JTField - id "+i+" valor ="+jtField.getText());
                    } else if (getContentPane().getComponent(i) instanceof JCheckBox) {
                    	JCheckBox jcBox = ((JCheckBox)getContentPane().getComponent(i));
                    	System.out.println("JCheckBox - id "+i+" está selecionado ? = "+jcBox.isSelected());
                    }
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
		
		c.gridy = 0;	//	Linha
		c.gridx = 1;	//	Coluna
		layout.setConstraints(jtfCustomers, c);
		this.getContentPane().add(jtfCustomers);
		
		JTextField jtfTrafficIndex = new JTextField(10);
		
		c.gridy = 1;	//	line
		c.gridx = 1;	//	column
		layout.setConstraints(jtfTrafficIndex, c);
		this.getContentPane().add(jtfTrafficIndex);
		
		JTextField jtfTabuSearchIterations = new JTextField(10);
		
		c.gridy = 2;	//	line
		c.gridx = 1;	//	column
		layout.setConstraints(jtfTabuSearchIterations, c);
		this.getContentPane().add(jtfTabuSearchIterations);
		
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
