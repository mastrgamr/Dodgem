package net.mastrgamr.dodgeem;

import java.awt.event.KeyEvent;

public class ControlledSprite extends Sprite {
	
	public ControlledSprite(){ }
	
	public ControlledSprite(String fileLoc, int x, int y, int speed){
		super(fileLoc, x, y, speed);
	}
	
	public void update(){
		x += speedX;
		y += speedY;
	}
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_LEFT)
			speedX = -2;
		if(key == KeyEvent.VK_RIGHT)
			speedX = 2;
		if(key == KeyEvent.VK_UP)
			speedY = -2;
		if(key == KeyEvent.VK_DOWN)
			speedY = 2;
	}
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_LEFT)
			speedX = 0;
		if(key == KeyEvent.VK_RIGHT)
			speedX = 0;
		if(key == KeyEvent.VK_UP)
			speedY = 0;
		if(key == KeyEvent.VK_DOWN)
			speedY = 0;
	}
}
