package CreateShip;


import java.awt.*;
import javax.swing.*;


public class testDisplayInterior extends JPanel{
	ShipRandom rand =new ShipRandom(null);
	String[][][] biom;
	int size=0;
	int[] b;
	
	public testDisplayInterior(String[][][]input,int pixSize) {
		biom=input;
		size=(pixSize/3)*2;
		b= new int[3];
		while(((b[0]+b[1]+b[2])<140) || ((b[0]+b[1]+b[2])>700)) {
			for(int k=0;k<3;k++) {
				b[k]=(int)(rand.nextDouble()*255);
			}
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setBackground(Color.BLACK);
		for(int floor=0; floor<biom.length;floor++) {
			for(int y=0; y<biom[0][0].length;y++) {
				for(int x=0;x<biom[0].length;x++) {
					Color color=(new Color(255,255,255));
					if(biom[floor][x][y].equals("ship")) {
						color=(new Color(b[0],b[1],b[2]));
					}else if(biom[floor][x][y].equals("floor")) {
						color=(new Color(255,255,255));
					}else if(biom[floor][x][y].equals("door")) {
						color=(new Color(255/2,255/2,255/2));
					}else if(biom[floor][x][y].equals("down")) {
						color=(new Color(255,0,0));
					}else if(biom[floor][x][y].equals("up")) {
						color=(new Color(0,255,0));
					}else if(biom[floor][x][y].equals("0")) {
						color=(new Color(255,255,0));
					}else if(biom[floor][x][y].equals("1")) {
						color=(new Color(0,225,225));
					}else if(biom[floor][x][y].equals("2")) {
						color=(new Color(225,0,225));
					}else {
						//color=(new Color(255,0,255));
					}
					if(!biom[floor][x][y].equals("n")) {
						g.setColor(color);
						g.fillRect(x*size+((2-floor)*600), y*size, size, size);
						if(floor==0) {
							g.setColor(new Color((int)(b[0]*((floor+1)/3.0)),(int)(b[1]*((floor+1)/3.0)),(int) (b[2]*((floor+1)/3.0))));
							g.fillRect(x*size, y*size, size, size);
							
							g.fillRect(x*size+600, y*size, size, size);
						}else if(floor==1) {
							g.setColor(new Color((int)(b[0]*((floor+1)/3.0)),(int)(b[1]*((floor+1)/3.0)),(int) (b[2]*((floor+1)/3.0))));
							g.fillRect(x*size, y*size, size, size);
						}
						g.setColor(new Color((int)(b[0]*((floor+1)/3.0)),(int)(b[1]*((floor+1)/3.0)),(int) (b[2]*((floor+1)/3.0))));
						g.fillRect(x*size+600, y*size+450, size, size);
					}
				}
			}
		}
	}
}