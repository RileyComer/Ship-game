package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class DisplayShip extends JPanel{
	Toolkit t=Toolkit.getDefaultToolkit();
	
	public DisplayShip(){
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setBackground(new Color(0,0,0));
		g.setColor(new Color(100,100,100));
		g.fillRect((this.getWidth()/2)-50, (this.getHeight()/2)-50, 100, 100);
	}
	
	public void redraw() {
		this.repaint();
	}
	
	public int getLocation(double x,double y) {
		
		return-1;
		
	}
}
