package net.mastrgamr.dodgeem;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class DodgeEm extends JPanel implements Runnable{
	
	private Thread animator;
	private ControlledSprite dodger;
	private Vector<AutomatedSprite> trolls = new Vector<AutomatedSprite>(6);
	private final int DELAY = 50;
	
	public DodgeEm(){
		setSize(800, 600);
		setBackground(Color.GRAY);
		addKeyListener(new KAdapter());
		setFocusable(true);
		setDoubleBuffered(true);
		
		dodger = new ControlledSprite("images/shock_face.png", this.getSize().width / 2, this.getSize().height / 2, 0);
		
		animator = new Thread(this);
		animator.start();
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D)g;
		g2d.drawImage(dodger.getImage(), dodger.getX(), dodger.getY(), null);
		
		for(Sprite troll : trolls)
			g2d.drawImage(troll.getImage(), troll.getX(), troll.getY(), null);
		
		g2d.drawString(new Integer(collision).toString(), 10, 10);
		
		Toolkit.getDefaultToolkit().sync();
		g.dispose();
	}
	
	public void checkTrolls(){
		for(Iterator<AutomatedSprite> itr = trolls.iterator(); itr.hasNext(); ){
			AutomatedSprite nextElement = itr.next();
			if(nextElement.getX() < 0 - nextElement.getImage().getWidth() ||
					nextElement.getY() < 0 - nextElement.getImage().getWidth() || 
					nextElement.getX() > 800 || 
					nextElement.getY() > 600){
				itr.remove();
			}
		}
	}
	int collision = 0;
	@Override
	public void run() {
		long beforeTime, timeDiff, sleep;
		
		beforeTime = System.currentTimeMillis();
		
		while(true){
			dodger.update(); //update sprite
			if(trolls.size() != trolls.capacity()){
				trolls.add(new AutomatedSprite("images/troll_face.png"));
			}
			for(Sprite troll : trolls){
				troll.update();
			}
			checkTrolls();
			
			if(trolls.get(0).rect().intersects(dodger.rect()))
				collision++;
			
			repaint();
			
			for(Sprite troll : trolls){
				System.out.println("X: " + troll.getX());
				System.out.println("Y: " + troll.getY());
			}
			
			timeDiff = System.currentTimeMillis() - beforeTime;
			sleep = timeDiff - DELAY;
			
			if(sleep < 0)
				sleep = 5;
			
			try {
				Thread.sleep(sleep);
			} catch (InterruptedException e) { e.printStackTrace(); }
			
			beforeTime = System.currentTimeMillis();
		}
	}
	
	private class KAdapter extends KeyAdapter{
		public void keyReleased(KeyEvent e) {
            dodger.keyReleased(e);
        }

        public void keyPressed(KeyEvent e) {
            dodger.keyPressed(e);
        }
	}
}
