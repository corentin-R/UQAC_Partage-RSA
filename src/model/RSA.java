package model;

import java.math.BigInteger;

public class RSA {
	
	private RSA() {}
	
	private static BigInteger toBigInteger(String phrase) {
		byte[] m_byte = phrase.getBytes();
		BigInteger m;
		m = new BigInteger(m_byte);
		
		return m;
	}
	
	public static BigInteger chiffrementRSA(String phrase, BigInteger e, BigInteger n) {
		BigInteger c, m;
		
		m = RSA.toBigInteger(phrase);
		
		c = m.modPow(e, n);
		
		return c;
	}
	
	public static String dechiffrementRSA(BigInteger c, BigInteger d, BigInteger n) {
		BigInteger M;
		M = c.modPow(d,n);
		
		return new String(M.toByteArray());
	}
}