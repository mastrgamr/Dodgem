package net.mastrgamr.dodgeem;

import java.util.Random;
import javax.swing.JOptionPane;

/**
 * These are sprites not controlled by the user, they are automatically spawned during the game
 * and at random locations off the screen. 
 * @author mastrgamr
 *
 */
public class AutomatedSprite extends Sprite {
	private Random rnd = new Random();
	private int spawns = 0;
	
	public AutomatedSprite(){ }
	
	public AutomatedSprite(String fileLoc){
		super(fileLoc);
	}
	
	public AutomatedSprite(String fileLoc, int x, int y, int speed){
		super(fileLoc, x, y, speed);
	}
	
	public void update(){
		if(spawns != 1){
			spawns++;
			spawn();
		}
		x += speedX;
		y += speedY;
	}
	
	//The trolls will spawn randomly on any side of the screen and run to the opposite side of the 
	//screen they came from.
	public void spawn(){
		switch(rnd.nextInt(4)){
		//run right to left
		case 0:
			this.x = 800;
			this.y = rnd.nextInt(600 - this.getImage().getHeight());
			speedX = (rnd.nextInt(3) + 1) * -1;
			break;
		//left to right
		case 1:
			this.x = -this.getImage().getWidth();
			this.y = rnd.nextInt(600 - this.getImage().getHeight());
			speedX = rnd.nextInt(3) + 1;
			break;
		//top to bottom
		case 2:
			this.x = rnd.nextInt(800 - this.getImage().getWidth());
			this.y = -this.getImage().getHeight();
			speedY = rnd.nextInt(3) + 1;
			break;
		//bottom to top
		case 3:
			this.x = rnd.nextInt(800 - this.getImage().getWidth());
			this.y = 600;
			speedY = (rnd.nextInt(3) + 1) * -1;
			break;
		default: //this shouldn't happen.
			JOptionPane.showMessageDialog(null, "Error: Unable to spawn next sprite.");
			System.exit(0);
			break;
		}
	}

}
