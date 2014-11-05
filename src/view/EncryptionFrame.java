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

public class EncryptionFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private static EncryptionFrame instance;
	
	public JTextField tfExponent, tfModule;
	public JTextArea textAreaMessage, textAreaCypher;
	public JButton buttonOK;
	
	private EncryptionFrame() {
		this.setTitle("Chiffrement RSA");
		this.setSize(300, 350);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setAlwaysOnTop(true);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		JPanel panel = new JPanel();
		BorderLayout layout = new BorderLayout(30, 10);
		panel.setLayout(layout);
		
		this.setContentPane(panel);
		
		//NORTH PANEL
		JPanel panelNorth = new JPanel();
		panelNorth.setLayout(new GridLayout(2, 1));
		
		JPanel panelNorth1 = new JPanel();
		panelNorth1.setLayout(new FlowLayout(FlowLayout.TRAILING));
		panelNorth1.add(new JLabel("exposant de chiffrement \t"));
		tfExponent = new JTextField(10);
		panelNorth1.add(tfExponent);
		
		JPanel panelNorth2 = new JPanel();
		panelNorth2.setLayout(new FlowLayout(FlowLayout.TRAILING));
		panelNorth2.add(new JLabel("saisir le module n \t"));
		tfModule = new JTextField(10);
		panelNorth2.add(tfModule);
		
		panelNorth.add(panelNorth1);
		panelNorth.add(panelNorth2);
		
		//CENTER PANEL
		JPanel panelCenter = new JPanel();
		panelCenter.add(new JLabel("saisir le message en clair"));
		textAreaMessage = new JTextArea(3, 24);
		JScrollPane scrollAreaMessage = new JScrollPane(textAreaMessage);
		panelCenter.add(scrollAreaMessage);
		panelCenter.add(new JLabel(""));
		panelCenter.add(new JLabel("Message chiffre"));
		textAreaCypher = new JTextArea(3, 24);
		textAreaCypher.setEditable(false);
		JScrollPane scrollAreaCypher = new JScrollPane(textAreaCypher);
		panelCenter.add(scrollAreaCypher);
		
		//SOUTH PANEL
		JPanel panelSouth = new JPanel();
		buttonOK = new JButton("Chiffrer");
		buttonOK.addActionListener(MyListener.getInstance());
		panelSouth.add(buttonOK);
		
		//Assembly
		panel.add(panelNorth, BorderLayout.NORTH);
		panel.add(panelCenter, BorderLayout.CENTER);
		panel.add(panelSouth, BorderLayout.SOUTH);
	}
	
	public static EncryptionFrame getInstance() {
		instance = (instance==null)? new EncryptionFrame():instance;
		return instance;
	}
	
}
