package net.mastrgamr.dodgeem;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.JPanel;

/**
 * This class is where all the game logic goes, the extra classes will be added to this
 * for use in the game. Each of those classes have their on logic.
 * 
 * @author mastrgamr (via mastrgamr.net)
 *
 */
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
		
		//begin game timer
		animator = new Thread(this);
		animator.start();
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D)g;
		g2d.drawImage(dodger.getImage(), dodger.getX(), dodger.getY(), null);
		
		//Draw every instance of a troll that is in the Vector collection
		for(Sprite troll : trolls)
			g2d.drawImage(troll.getImage(), troll.getX(), troll.getY(), null);
		
		Toolkit.getDefaultToolkit().sync();
		g.dispose();
	}
	
	/**
	 * This method checks each troll to see if they left the game screen. If they leave the screen
	 * completely they will be removed and be spawned elsewhere randomly (which is handled elsewhere).
	 * I had help with this algorithm: 
	 * http://stackoverflow.com/questions/8829370/pause-a-thread-to-prevent-concurrentmodificationexception
	 */
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
	
	/**
	 * Where the game loop takes place, uses simple time step method, most frames may 
	 * be executed every 5 milliseconds, if not the game will freeze and pick up where
	 * it froze at. I learned at: http://zetcode.com/tutorials/javagamestutorial/animation/
	 */
	@Override
	public void run() {
		long beforeTime, timeDiff, sleep;
		
		beforeTime = System.currentTimeMillis();
		
		while(true){
			dodger.update(); //update sprite
			checkTrolls(); //check troll bounds
			//add a new troll to the Vector collection if it's not full
			//Trolls get removed once they leave the screen (via checkTrolls()).
			if(trolls.size() != trolls.capacity()){
				trolls.add(new AutomatedSprite("images/troll_face.png"));
			}
			//update all trolls in the Vector
			for(Sprite troll : trolls){ troll.update(); }
			repaint();
			
			timeDiff = System.currentTimeMillis() - beforeTime;
			sleep = timeDiff - DELAY;
			
			if(sleep < 0)
				sleep = 5;
			
			try {
				Thread.sleep(sleep);
			} catch (InterruptedException e) { e.printStackTrace(); } //there should not be an Exception thrown
			
			beforeTime = System.currentTimeMillis();
		}
	}
	
	//Private class used to handle user input which is taken care of in the ControlledSprite class
	//aka the dodger.
	private class KAdapter extends KeyAdapter{
		public void keyReleased(KeyEvent e) {
            dodger.keyReleased(e);
        }

        public void keyPressed(KeyEvent e) {
            dodger.keyPressed(e);
        }
	}
}
