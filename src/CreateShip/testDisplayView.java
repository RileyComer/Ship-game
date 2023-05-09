package CreateShip;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class testDisplayView extends JPanel{
	String[][][] biom;
	int size=0, posx=0, posy=0;
	int[] b;
	
	public testDisplayView(String[][][]input,int pixSize) {
		biom=input;
		size=(pixSize)*25;//13?----								15px*12=180px		player=(1/3)180px=60px		||		15px*13=195px		player=(1/3)195px=65px		||		15px*14=210px		player=(1/3)210px=70px		||		15px*20=300px		player=(1/3)300px=100px		||		15px*25=375px		player=(1/3)375px=125px
		posx=-size*((input[0].length/2)-2);
		posy=-size*((input[0].length/2)-2);
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
					}else {
						//color=(new Color(255,0,255));
					}
					if(!biom[floor][x][y].equals("n")) {
						g.setColor(color);
						g.fillRect(x*size+posx, y*size+posy, size, size);
						if(floor==0) {
							g.setColor(new Color((int)(b[0]*((floor+1)/3.0)),(int)(b[1]*((floor+1)/3.0)),(int) (b[2]*((floor+1)/3.0))));
							g.fillRect(x*size+posx, y*size+posy, size, size);
						}else if(floor==1) {
							g.setColor(new Color((int)(b[0]*((floor+1)/3.0)),(int)(b[1]*((floor+1)/3.0)),(int) (b[2]*((floor+1)/3.0))));
							g.fillRect(x*size+posx, y*size+posy, size, size);
						}
					}
				}
			}
		}
		g.setColor(new Color(0,0,233));
		g.fillRect((biom[0].length/2)*size+posx+size/2, (biom[0][0].length/2-1)*size+posy+size/2, size/3, size/3);
	}
}
