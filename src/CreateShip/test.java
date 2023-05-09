package CreateShip;

import javax.swing.JFrame;

public class test {

	public static void main(String[] args) {//											small:25-40-40-80,		medium:40-90-60-100,		large:60-120-140-200,		span:25-120-40-200
		ShipRandom rand=new ShipRandom(null);
		int w= 34,h=34, dots=(int)(rand.nextDouble()*120)+50,pixSize=15, min=25, max=120, shipMin=40, shipMax=200, roomMax=25,roomMin=5;
		
		String[][][] ship=null;
		while(ship==null) {
		boolean[][][] shape=new boolean[3][34][34];
			shape[2]=VoronoiTessellation.shipGen(w, h, dots,min-20,max-20,null);
			shape[1]=VoronoiTessellation.shipGen(w, h, dots,min-10,max-10,shape[2]);
			shape[0]=VoronoiTessellation.shipGen(w, h, dots,min,max,shape[1]);
			ship=Interior.Layout(w, h, shape, roomMax,roomMin, shipMin, shipMax);
		}
		JFrame window= new JFrame ("Title");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setExtendedState(JFrame.MAXIMIZED_BOTH);
		//testDisplayShape l= new testDisplayShape(shape,pixSize);
		//testDisplayView l= new testDisplayView(ship,pixSize);
		testDisplayInterior l= new testDisplayInterior(ship,pixSize);
		window.add(l);
		window.setVisible(true);
	}

}
