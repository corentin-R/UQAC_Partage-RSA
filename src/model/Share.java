package model;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by coco on 14-10-22.
 */
public class Share {
	
	private Share() {}
	
	/**
	 * créer tab de nb alétoire de taille n
	 *
	 * @param n - Nombre total des éléments de la séquence à générer.
	 * @ return - Retourne la séquence des nombres aléatoires générée.
	 */
	private static BigInteger[] generateRandomFactors(int n) {
		BigInteger[] returnArray = new BigInteger[n];
		Random random = new Random();
		for (int index = 0; index < n; index++) {
			returnArray[index] =  new BigInteger(1024, random);
		}
		return returnArray;
	}

	/**
	 * polynôme S(x)
	 *
	 * @return la valeure de S(x)
	 */
	private static BigInteger computeS(int cle, BigInteger msg, int minimalShare, BigInteger primeNumber, BigInteger[] randomFactors){
		BigInteger ret = msg;

		for(int i=1; i<=minimalShare-1;i++)
		{
			BigInteger puissance = new BigInteger(String.valueOf((int)Math.pow(cle, i)));
			ret= ret.add(randomFactors[i-1].multiply(puissance));
		}
		return ret.mod(primeNumber);
	}

	/**
	 * affiche S(x)
	 */
	private static void displaySx(BigInteger[] randomFactors, BigInteger msg, int minimalShares){
		System.out.print("S(x) = " + msg +" +");
		for(int i=0; i<minimalShares-2;i++){
			System.out.print(" "+randomFactors[i]+"*x^"+(i+1)+" +");
		}
		System.out.println(" "+randomFactors[(minimalShares-2)]+"*x^"+(minimalShares-1));
	}

	/**
	 * génère les couples
	 *
	 * @return la valeure de S(x)
	 */
	private static Map<Integer,BigInteger> generateCouples(BigInteger primeNumber, int totalShares, int minimalShares, BigInteger msg){
		Map<Integer,BigInteger> ret = new HashMap<Integer, BigInteger>();
		BigInteger[] randomFactors = generateRandomFactors(minimalShares-1);
		for(int cle=1; cle<=totalShares;cle++){
			ret.put(new Integer(cle), computeS(cle,msg,minimalShares,primeNumber,randomFactors));
		}
		displaySx(randomFactors, msg, minimalShares);
		return  ret;
	}
	
	
	public static Map<Integer,BigInteger> shareMessage(BigInteger primeNumber, int totalShares, int minimalShares, String message) {
		Map<Integer,BigInteger> ret = new HashMap<Integer, BigInteger>();
		if(totalShares<minimalShares) throw new Error("Attention : nombre minimum de partage superieur au nombre total.");
		BigInteger msg = new BigInteger(message.getBytes());
		ret = generateCouples(primeNumber, totalShares, minimalShares, msg);
		return ret;
	}
	
}
