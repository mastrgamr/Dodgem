package net.mastrgamr.dodgeem;

import javax.swing.JFrame;

public class Main extends JFrame {
	
	public Main(){
		super("Dodge Em!");
		
		this.add(new DodgeEm());
		
		//setup game window
		this.setSize(800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		new Main();
	}
}
