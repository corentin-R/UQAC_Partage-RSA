package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controller.MyListener;

public class SharingFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private static SharingFrame instance;
	
	public JTextField tfPrime, tfMinimalShares, tfTotalShares;
	public JTextArea textAreaMessage, textAreaKeys;
	public JButton buttonOK,buttonRandom;

	private SharingFrame() {
		this.setTitle("Schema de partage");
		this.setSize(450, 400);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setAlwaysOnTop(true);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		JPanel panel = new JPanel();
		BorderLayout layout = new BorderLayout(30, 10);
		panel.setLayout(layout);

		this.setContentPane(panel);

		//NORTH
		JPanel panelNorth = new JPanel();
		panelNorth.setLayout(new GridLayout(3, 1));
		
		JPanel panelNorth1 = new JPanel();
		panelNorth1.setLayout(new FlowLayout(FlowLayout.TRAILING));
		buttonRandom = new JButton("Aleatoire");
		buttonRandom.addActionListener(MyListener.getInstance());
		panelNorth1.add(buttonRandom);
		panelNorth1.add(new JLabel("saisir un grand nombre premier"));
		tfPrime = new JTextField(6);
		panelNorth1.add(tfPrime);
		
		JPanel panelNorth2 = new JPanel();
		panelNorth2.setLayout(new FlowLayout(FlowLayout.TRAILING));
		panelNorth2.add(new JLabel("nombre de personnes qui recevront un fragment"));
		tfTotalShares = new JTextField(6);
		panelNorth2.add(tfTotalShares);
		
		JPanel panelNorth3 = new JPanel();
		panelNorth3.setLayout(new FlowLayout(FlowLayout.TRAILING));
		panelNorth3.add(new JLabel("nombre minimal de personnes requises"));
		tfMinimalShares = new JTextField(6);
		panelNorth3.add(tfMinimalShares);
		
		panelNorth.add(panelNorth1);
		panelNorth.add(panelNorth2);
		panelNorth.add(panelNorth3);
		
		//CENTER
		JPanel panelCenter = new JPanel();
		panelCenter.setLayout(new GridLayout(2, 1));
		
		//--CENTER1
		JPanel panelCenter1 = new JPanel();
		panelCenter1.add(new JLabel("saisir le message en clair",JLabel.CENTER));
		textAreaMessage = new JTextArea(4, 35);
		JScrollPane scrollAreaMessage = new JScrollPane(textAreaMessage);
		panelCenter1.add(scrollAreaMessage);
		
		//--CENTER2
		JPanel panelCenter2 = new JPanel();
		panelCenter2.add(new JLabel("Liste des couples (argument,valeur)",JLabel.CENTER));
		textAreaKeys = new JTextArea(4, 35);
		textAreaKeys.setEditable(false);
		JScrollPane scrollAreaCypher = new JScrollPane(textAreaKeys);
		panelCenter2.add(scrollAreaCypher);
		
		panelCenter.add(panelCenter1);
		panelCenter.add(panelCenter2);
		
		//SOUTH
		JPanel panelSouth = new JPanel();
		buttonOK = new JButton("Partager");
		buttonOK.addActionListener(MyListener.getInstance());
		panelSouth.add(buttonOK);


		//Assembly
		panel.add(panelNorth, BorderLayout.NORTH);
		panel.add(panelCenter, BorderLayout.CENTER);
		panel.add(panelSouth, BorderLayout.SOUTH);
	}

	public static SharingFrame getInstance() {
		instance = (instance==null)? new SharingFrame():instance;
		return instance;
	}

}
