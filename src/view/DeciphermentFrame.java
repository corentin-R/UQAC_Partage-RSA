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

public class DeciphermentFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private static DeciphermentFrame instance;

	public JTextField tfExponent,tfModule;
	public JTextArea textAreaMessage, textAreaCypher;
	public JButton buttonOK;

	private DeciphermentFrame() {
		this.setTitle("Dechiffrement RSA");
		this.setSize(310, 350);
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
		panelNorth1.add(new JLabel("exposant de dechiffrement"));
		tfExponent = new JTextField(10);
		panelNorth1.add(tfExponent);
		
		JPanel panelNorth2 = new JPanel();
		panelNorth2.setLayout(new FlowLayout(FlowLayout.TRAILING));
		panelNorth2.add(new JLabel("saisir le module n"));
		tfModule = new JTextField(10);
		panelNorth2.add(tfModule);
		
		panelNorth.add(panelNorth1);
		panelNorth.add(panelNorth2);

		//CENTER PANEL
		JPanel panelCenter = new JPanel();
		panelCenter.add(new JLabel("Saisir le message chiffre"));
		textAreaCypher = new JTextArea(3, 24);
		JScrollPane scrollAreaCypher = new JScrollPane(textAreaCypher);
		panelCenter.add(scrollAreaCypher);
		panelCenter.add(new JLabel(""));
		panelCenter.add(new JLabel("Message en clair"));
		textAreaMessage = new JTextArea(3, 24);
		textAreaMessage.setEditable(false);
		JScrollPane scrollAreaMessage = new JScrollPane(textAreaMessage);
		panelCenter.add(scrollAreaMessage);

		//SOUTH PANEL
		JPanel panelSouth = new JPanel();
		buttonOK = new JButton("Dechiffrer");
		buttonOK.addActionListener(MyListener.getInstance());
		panelSouth.add(buttonOK);

		//Assembly
		panel.add(panelNorth, BorderLayout.NORTH);
		panel.add(panelCenter, BorderLayout.CENTER);
		panel.add(panelSouth, BorderLayout.SOUTH);
	}

	public static DeciphermentFrame getInstance() {
		instance = (instance==null)? new DeciphermentFrame():instance;
		return instance;
	}

}
