package net.mastrgamr.dodgeem;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public abstract class Sprite {
	
	protected BufferedImage img;
	protected int x;
	protected int y;
	protected int speed;
	protected int speedX;
	protected int speedY;
	protected Vector<Sprite> spriteList = new Vector<Sprite>();
	
	public Sprite(){ }
	
	public Sprite(String fileLoc){
		loadImg(fileLoc);
	}
	
	public Sprite(String fileLoc, int x, int y, int speed){
		this.x = x;
		this.y = y;
		this.speed = speed;
		loadImg(fileLoc);
	}
	
	public Sprite(String fileLoc, int x, int y, int speedX, int speedY){
		this.x = x;
		this.y = y;
		this.speedX = speedX;
		this.speedY = speedY;
		loadImg(fileLoc);
	}
	
	public void loadImg(String fileLoc){
		try {
			img = ImageIO.read(new File(fileLoc));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Fatal Error: Sprites can not be located.");
			System.exit(0);
		}
	}
	
	public Rectangle rect(){
		return new Rectangle(this.x, this.y, this.getImage().getWidth(), this.getImage().getHeight());
	}
	
	public abstract void update();
	
	public BufferedImage getImage(){ return this.img; }
	public int getX(){ return this.x; }
	public int getY(){ return this.y; }

}
