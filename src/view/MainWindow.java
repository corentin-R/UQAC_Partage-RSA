package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import controller.MyListener;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private static MainWindow instance;
	
	public JButton buttonEncryption, buttonDecipherment, buttonSharing, buttonAssembly;
	
	private MainWindow() {
		super();
		
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {}
		
		this.setTitle("Implementation de RSA");
		
		JPanel panel = new JPanel();
		GridLayout layout = new GridLayout(2, 2, 10, 10);
		panel.setLayout(layout);
		
		buttonEncryption = new JButton("Chiffrement");
		buttonDecipherment = new JButton("Dechiffrement");
		buttonSharing = new JButton("Partage");
		buttonAssembly = new JButton("Assemblage");
		
		buttonEncryption.addActionListener(MyListener.getInstance());
		buttonDecipherment.addActionListener(MyListener.getInstance());
		buttonSharing.addActionListener(MyListener.getInstance());
		buttonAssembly.addActionListener(MyListener.getInstance());
		
		panel.add(buttonEncryption);
		panel.add(buttonDecipherment);
		panel.add(buttonSharing);
		panel.add(buttonAssembly);
		
		JPanel panelSud = new JPanel();
		JTextField tfNames = new JTextField();
		tfNames.setText("Mathieu GRONDIN, Christophe MENICHELLI, Corentin RAOULT");
		tfNames.setEditable(false);
		panelSud.add(tfNames);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(panel, BorderLayout.CENTER);
		mainPanel.add(panelSud, BorderLayout.SOUTH);
		this.setContentPane(mainPanel);
		this.setSize(400, 250);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	};

	public static MainWindow getInstance() {
		instance = (instance==null)?new MainWindow():instance;
		return instance;
	}

}
