package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import model.Assembly;
import model.RSA;
import model.Share;
import view.AssemblyFrame;
import view.DeciphermentFrame;
import view.EncryptionFrame;
import view.MainWindow;
import view.SharingFrame;

public class MyListener implements ActionListener {

	private static MyListener instance;

	private final static int PRIME_LENGHT = 1024;

	private MyListener() {}

	public static MyListener getInstance() {
		instance = (instance==null)?new MyListener():instance;
		return instance;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		try {
			if(src==MainWindow.getInstance().buttonEncryption) {
				EncryptionFrame.getInstance().setVisible(true);
			} else if(src==MainWindow.getInstance().buttonDecipherment) {
				DeciphermentFrame.getInstance().setVisible(true);
			} else if(src==MainWindow.getInstance().buttonSharing) {
				SharingFrame.getInstance().setVisible(true);
			} else if(src==MainWindow.getInstance().buttonAssembly) {
				AssemblyFrame.getInstance().setVisible(true);
			} else if(src==EncryptionFrame.getInstance().buttonOK) {
				handleEncryption();
			} else if(src==DeciphermentFrame.getInstance().buttonOK) {
				handleDecipherment();
			} else if(src==SharingFrame.getInstance().buttonRandom) {
				SharingFrame.getInstance().tfPrime.setText(new String(BigInteger.probablePrime(PRIME_LENGHT, new Random()).toString()));
			} else if(src==SharingFrame.getInstance().buttonOK) {
				handleSharing();
			} else if(src==AssemblyFrame.getInstance().buttonOK) {
				handleAssembly();
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	private static void handleEncryption() {
		BigInteger exponent=null, module=null;
		try {
			exponent = new BigInteger(EncryptionFrame.getInstance().tfExponent.getText());
			if(exponent==null || exponent.compareTo(new BigInteger("0"))<=0) throw new IllegalStateException();
		} catch(Exception e) {
			JOptionPane.showMessageDialog(EncryptionFrame.getInstance(), "Exposant incorrect!","Erreur!",JOptionPane.ERROR_MESSAGE);
			return;
		}
		try {
			module = new BigInteger(EncryptionFrame.getInstance().tfModule.getText());
			if(module==null || module.compareTo(new BigInteger("0"))<=0) throw new IllegalStateException();
		} catch(Exception e) {
			JOptionPane.showMessageDialog(EncryptionFrame.getInstance(), "Module incorrect!","Erreur!",JOptionPane.ERROR_MESSAGE);
			return;
		}

		String message = EncryptionFrame.getInstance().textAreaMessage.getText();
		if(message==null || message.length()==0) {
			JOptionPane.showMessageDialog(EncryptionFrame.getInstance(), "Message incorrect!","Erreur!",JOptionPane.ERROR_MESSAGE);
			return;
		}
		if(new BigInteger(message.getBytes()).compareTo(module)>=0) {
			JOptionPane.showMessageDialog(EncryptionFrame.getInstance(), "Le message est trop long pour ce module!","Erreur!",JOptionPane.ERROR_MESSAGE);
			return;
		}
		BigInteger cypher = RSA.chiffrementRSA(message, exponent, module);
		EncryptionFrame.getInstance().textAreaCypher.setText(cypher.toString());
	}

	private static void handleDecipherment() {
		BigInteger exponent=null, module=null, cypher=null;
		try {
			exponent = new BigInteger(DeciphermentFrame.getInstance().tfExponent.getText());
			if(exponent==null || exponent.compareTo(new BigInteger("0"))<=0) throw new IllegalStateException();
		} catch(Exception e) {
			JOptionPane.showMessageDialog(DeciphermentFrame.getInstance(), "Exposant incorrect!","Erreur!",JOptionPane.ERROR_MESSAGE);
			return;
		}
		try {
			module = new BigInteger(DeciphermentFrame.getInstance().tfModule.getText());
			if(module==null || module.compareTo(new BigInteger("0"))<=0) throw new IllegalStateException();
		} catch(Exception e) {
			JOptionPane.showMessageDialog(DeciphermentFrame.getInstance(), "Module incorrect!","Erreur!",JOptionPane.ERROR_MESSAGE);
			return;
		}
		try {
			cypher = new BigInteger(DeciphermentFrame.getInstance().textAreaCypher.getText());
			if(cypher==null || cypher.compareTo(new BigInteger("0"))<=0) throw new IllegalStateException();
		} catch(Exception e) {
			JOptionPane.showMessageDialog(DeciphermentFrame.getInstance(), "Cypher incorrect!","Erreur!",JOptionPane.ERROR_MESSAGE);
			return;
		}
		String message = RSA.dechiffrementRSA(cypher, exponent, module);
		DeciphermentFrame.getInstance().textAreaMessage.setText(message);
	}

	private static void handleSharing() {
		BigInteger primeNumber=null;
		int totalShares=0, minimalShares=0;
		try {
			primeNumber = new BigInteger(SharingFrame.getInstance().tfPrime.getText());
			if(!primeNumber.isProbablePrime(1000)) {
				JOptionPane.showMessageDialog(SharingFrame.getInstance(), "Le nombre saisi ne semble pas premier!","Erreur!",JOptionPane.ERROR_MESSAGE);
				return;
			}
		} catch(Exception e) {
			JOptionPane.showMessageDialog(SharingFrame.getInstance(), "Nombre premier incorrect!","Erreur!",JOptionPane.ERROR_MESSAGE);
			return;
		}

		try {
			totalShares = Integer.parseInt(SharingFrame.getInstance().tfTotalShares.getText());
			if(totalShares<=1) throw new IllegalArgumentException();
		} catch(Exception e) {
			JOptionPane.showMessageDialog(SharingFrame.getInstance(), "Nombre de personnes total incorrect!","Erreur!",JOptionPane.ERROR_MESSAGE);
			return;
		}

		try {
			minimalShares = Integer.parseInt(SharingFrame.getInstance().tfMinimalShares.getText());
			if(minimalShares<=1) throw new IllegalArgumentException();
		} catch(Exception e) {
			JOptionPane.showMessageDialog(SharingFrame.getInstance(), "Nombre de personnes minimal incorrect!","Erreur!",JOptionPane.ERROR_MESSAGE);
			return;
		}

		if(minimalShares>totalShares) {
			JOptionPane.showMessageDialog(SharingFrame.getInstance(), "Nombre de personnes minimal doit etre inferieur ou egal au nombre de personnes total!","Erreur!",JOptionPane.ERROR_MESSAGE);
			return;
		}

		String message = SharingFrame.getInstance().textAreaMessage.getText();
		if (message.length()==0) {
			JOptionPane.showMessageDialog(SharingFrame.getInstance(), "Message incorrect!","Erreur!",JOptionPane.ERROR_MESSAGE);
			return;
		}

		if(new BigInteger(message.getBytes()).compareTo(primeNumber)>=0) {
			JOptionPane.showMessageDialog(SharingFrame.getInstance(), "Le message est trop long!","Erreur!",JOptionPane.ERROR_MESSAGE);
			return;
		}

		Map<Integer,BigInteger> res = Share.shareMessage(primeNumber, totalShares, minimalShares, message);
		SharingFrame.getInstance().textAreaKeys.setText("");
		for(Integer key : res.keySet()) {
			SharingFrame.getInstance().textAreaKeys.append("cle: "+key+" || valeur: "+res.get(key)+"\n");
		}
	}

	private static void handleAssembly() {
		String keys = AssemblyFrame.getInstance().textAreaKeys.getText();
		if (keys.split("\n").length<2) {
			JOptionPane.showMessageDialog(SharingFrame.getInstance(), "Liste cles/valeurs incorrect!","Erreur!",JOptionPane.ERROR_MESSAGE);
			return;
		}

		BigInteger primeNumber = null;
		try {
			primeNumber = new BigInteger(AssemblyFrame.getInstance().tfPrimeNumber.getText());
		} catch(Exception e) {
			JOptionPane.showMessageDialog(AssemblyFrame.getInstance(), "Nombre premier incorrect!","Erreur!",JOptionPane.ERROR_MESSAGE);
			return;
		}

		Map<Integer, BigInteger> mapKV = new HashMap<Integer, BigInteger>();
		for(String s : keys.split("\n")) {
			Pattern p = Pattern.compile("\\d+");
			Matcher m = p.matcher(s);
			boolean swtch = false;
			Integer key=null;
			while (m.find()) {
				if (!swtch) key = new Integer(Integer.parseInt(m.group()));
				else mapKV.put(key, new BigInteger(m.group()));
				swtch=!swtch;
			}
		}



		String message = Assembly.assembler(mapKV,primeNumber);
		AssemblyFrame.getInstance().textAreaMessage.setText(message);
	}
}
