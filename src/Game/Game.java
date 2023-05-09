package Game;

import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;

public class Game {
	
	public static void main(String[] args)  {
		// Creates Window
		Gui window = new Gui ();
		window.setExtendedState(JFrame.MAXIMIZED_BOTH); window.setUndecorated(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		while(true) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException ie){
				System.out.println("Scanning...");
			}
			window.redraw();
		}
	}
	
	private static void playSound(File Sound) {
		try {
			/*
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(Sound));
			clip.start();
			*/
		}catch(Exception e) {
			
		}
	}

}
