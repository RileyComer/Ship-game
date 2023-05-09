package Game;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;

public class Gui extends JFrame{ 
	private DisplayShip board;
	private boolean pause=true;
	
	public Gui() {
		super("Title");
		
		board =new DisplayShip();
		add(board,BorderLayout.CENTER);
		
		//Mouse Handler
		HandlerClass handler=new HandlerClass();
		board.addMouseListener(handler);
		
		//Exit key
		addKeyListener(
				new KeyAdapter() {
					public void keyPressed(KeyEvent ke) {  // handler
						if(ke.getKeyCode() == KeyEvent.VK_ESCAPE) {
							System.exit(0);
						}else if(ke.getKeyCode() == KeyEvent.VK_SPACE) {
							if(pause) {
								pause=false;
							}else {
								pause=true;
							}
						}
					} 
				}
		);
	}
	
	public void redraw() {
		board.redraw();
	}
	
	private class HandlerClass implements MouseListener{

		public void mouseClicked(MouseEvent e) {
			
		}

		public void mouseEntered(MouseEvent e) {
			
		}

		public void mouseExited(MouseEvent e) {
			
		}

		public void mousePressed(MouseEvent e) {
			
		}

		public void mouseReleased(MouseEvent e) {
			double x=e.getX();
			double y=e.getY(); 
			int out=board.getLocation(x, y);
			if(out!=-1) {
				
			}
		}
	}		
}
