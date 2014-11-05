package model.test;

import java.math.BigInteger;
import java.util.Map;
import java.util.Random;

import model.Share;

/**
 * Created by coco on 14-10-22.
 */
public class ShareTest {


	public static void main (String[] args){
		System.out.println("Partage");

		Random rnd= new Random();
		BigInteger nb_premier;
		nb_premier = BigInteger.probablePrime(1024, rnd);
		System.out.println("nombre premier: "+nb_premier);
		int w = 3;
		int t = 2;
		
		String message = "Hello";
		
		Map<Integer,BigInteger> ret = Share.shareMessage(nb_premier, w, t, message);
		
		for(Integer i : ret.keySet()) {
			System.out.println("cle: "+i+"\tvaleur: "+ret.get(i));
		}
		
		
		
	}

}
