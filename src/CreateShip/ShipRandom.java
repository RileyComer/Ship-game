package CreateShip;

import java.util.Random;

public class ShipRandom{
	private static String key;
	private static int keyLong;
	private static Random rand;
	
	public ShipRandom(String key) {
		if(key==null) {
			rand=new Random();
		}else {
			key=key.toLowerCase();
			for(int i=0; i<key.length();i++) {
				keyLong+=key.charAt(i)*Math.pow(5,key.length()-(i));
			}
			rand=new Random((long) (keyLong));
		}
	}
	
	public static double nextDouble() {
		return rand.nextDouble();
	}
}
