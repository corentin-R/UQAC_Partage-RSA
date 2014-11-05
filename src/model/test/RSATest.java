package model.test;
import java.math.BigInteger;

import model.RSA;


public class RSATest {

	public static void main(String[] args) {
		BigInteger n, p, q, e, c, d;
		String phrase, M;
			
		phrase = "jean ob";
		
		p = new BigInteger("885320963"); 						// Premier nombre premier		- Priv�
		q = new BigInteger("238855417");						// Deuxieme nombre premier		- Priv�
		n = p.multiply(q);										// p*q							- Public
		e = new BigInteger("9007");								// Exposant de chiffrement		- Public
		c = RSA.chiffrementRSA(phrase, e, n);					// Message chiffr�				- Public
		d = new BigInteger("116402471153538991");				// Exposant de d�chiffrement	- Priv�
		M = RSA.dechiffrementRSA(c, d, n);						// Message d�chiffr� 			- Priv�
		
		/* Autre exemple
		p = new BigInteger("29"); 								// Premier nombre premier		- Priv�
		q = new BigInteger("37");								// Deuxieme nombre premier		- Priv�
		n = p.multiply(q);										// p*q							- Public
		e = new BigInteger("71");								// Exposant de chiffrement		- Public
		c = RSA.chiffrementRSA(phrase, e, n);					// Message chiffr�				- Public
		d = new BigInteger("1079");								// Exposant de d�chiffrement	- Priv�
		M = RSA.dechiffrementRSA(c, d, n);						// Message d�chiffr� 			- Priv�*/
		
		System.out.println("Message en clair\t\t: " + phrase);
		System.out.println("Nombre premier p\t\t: " + p);
		System.out.println("Nombre premier q\t\t: " + q);
		System.out.println("Nombre premier n\t\t: " + n);
		System.out.println("Exposant de chiffrement e\t: " + e);
		System.out.println("message chiffre c\t\t: " + c);
		System.out.println("Exposant de dechiffrement\t: " + d);
		System.out.println("Message d�crypt� M\t\t: " + M);
	}
}
