package net.mastrgamr.dodgeem;

import javax.swing.JFrame;

/** 
 * Simple game I created to test out and learn certain techniques of game programming
 * and java in general. Comments placed throughout the code, the game may become
 * tutorialized on my dev site: mastrgamr.net.
 * 
 * @author Stuart (mastrgamr) 
 */
public class Main extends JFrame {
	
	public Main(){
		super("Dodge Em!"); //set the title of window
		
		this.add(new DodgeEm());
		
		//setup game window
		this.setSize(800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null); //centers the game area
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		new Main();
	}
}
