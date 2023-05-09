package CreateShip;


import java.awt.*;
import javax.swing.*;


public class testDisplayShape extends JPanel{
	boolean[][][] ship;
	int size=0;
	int[] b;
	
	public testDisplayShape(boolean[][][]input,int pixSize) {
		ship=input;
		size=pixSize;
		b= new int[3];
		while(((b[0]+b[1]+b[2])<140) || ((b[0]+b[1]+b[2])>700)) {
			for(int k=0;k<3;k++) {
				b[k]=(int)(Math.random()*255);
			}
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setBackground(Color.BLACK);
		for(int floor=0;floor<ship.length;floor++) {
			for(int x=0; x<ship[0].length;x++) {
				for(int y=0;y<ship[0][0].length;y++) {
					Color color=(new Color(0,0,0));
					if(ship[floor][x][y]) {
						color=(new Color(b[0],b[1],b[2]));
						g.setColor(color);
						g.fillRect(x*size, y*size+((2-floor)*300), size, size);
					}
				}
			}
		}
		
		for(int floor=0;floor<ship.length;floor++) {
			for(int x=0; x<ship[0].length;x++) {
				for(int y=0;y<ship[0][0].length;y++) {
					Color color=(new Color(0,0,0));
					if(ship[floor][x][y]) {
						color=(new Color((int)(b[0]*((floor+1)/3.0)),(int)(b[1]*((floor+1)/3.0)),(int) (b[2]*((floor+1)/3.0))));
						g.setColor(color);
						g.fillRect(x*size+400, y*size+300, size, size);
					}
				}
			}
		}
	}
}