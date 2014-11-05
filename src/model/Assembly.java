package model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Map;

import model.exception.NoSquareException;

/**
 * Created by coco on 14-10-26.
 */
public class Assembly {

	/**
	 * Constructeur
	 */
	private Assembly(){}

	/**
	 * créer une matrice C avec les bons coefficients
	 * @param t taille de la matrice
	 * @return
	 */
	private static BigInteger[][] createC(int t) {
		BigInteger[][] mat = new BigInteger[t][t];
		for(int i = 0 ; i < t ; i++){
			for(int j = 0 ; j < t ; j++){
				mat[i][j] =  BigInteger.valueOf(i+1);
				mat[i][j] =  mat[i][j].pow(j);
			}
		}
		return mat;
	}

	/**
	 * créer un string à partir des t couples
	 * @param couples
	 * @return
	 */
	public static String assembler(Map<Integer,BigInteger> mapKV, BigInteger n){
		int t = mapKV.keySet().size();
		BigDecimal[][] Cinverse = null;
		
		Matrix C1 = new Matrix(createC(t));
		try {
			if(MatrixMathematics.determinant(C1) != BigInteger.valueOf(0)) {
				Cinverse = MatrixMathematics.inverse(C1);
			}
		} catch (NoSquareException e) {
			e.printStackTrace();
		}

		BigDecimal msg = new BigDecimal("0.0");
		
		for(Integer key : mapKV.keySet()){
			msg=msg.add(Cinverse[0][key.intValue()-1].multiply(new BigDecimal(mapKV.get(key))));//mod
		}
		
		String message = new String(msg.toBigInteger().mod(n).toByteArray());
		return message;
	}

}
