/**
 * CIS 120 HW10
 * (c) University of Pennsylvania
 * @version 2.0, Mar 2013
 */

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.util.ArrayList;
import java.util.LinkedList;


import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;
/**
 * GameCourt
 * 
 * This class holds the primary game logic of how different objects 
 * interact with one another.  Take time to understand how the timer 
 * interacts with the different methods and how it repaints the GUI 
 * on every tick().
 *
 */
@SuppressWarnings("serial")
public class GameCourt extends JPanel {
	


	// the state of the game logic

	private LinkedList<PointCharge> charges;
	private double angle;
	private double phase_sin;
	private double phase_cos;
	
	
	public boolean playing = false;  // whether the game is running
	private JLabel status;       // Current status text (i.e. Running...)
	// Game constants
	public static final int COURT_WIDTH  = 400;
	public static final int COURT_HEIGHT = 400;
	// Update interval for timer in milliseconds 
	public static final int INTERVAL = 15; 
	private int charge;
	private int[][] potentials;
	private PointCharge mostRecent;
	private double time;
	public double period;
	
	
	int bogus;
	double alpha;
	public GameCourt(JLabel status){
		// creates border around the court area, JComponent method
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
        charge = 0;
		charges = new LinkedList<PointCharge>();
		potentials = new int[COURT_WIDTH][COURT_HEIGHT];
		time = 0;
	    for(int x = 0; x < potentials.length; x++) {
	    	for(int y = 0; y < potentials[0].length; y++) {
	    		potentials[x][y] = 0;
	    		
	    	}
	    }
	    period = 300;
        // The timer is an object which triggers an action periodically
        // with the given INTERVAL. One registers an ActionListener with
        // this timer, whose actionPerformed() method will be called 
        // each time the timer triggers. We define a helper method
        // called tick() that actually does everything that should
        // be done in a single timestep.

		// Enable keyboard focus on the court area
		// When this component has the keyboard focus, key
		// events will be handled by its key listener.

		// this key listener allows the square to move as long
		// as an arrow key is pressed, by changing the square's
		// velocity accordingly. (The tick method below actually 
		// moves the square.)
		addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent me) {
			    int screenX = me.getX();
			    int screenY = me.getY();
			    mostRecent = new PointCharge(screenX, screenY, charge); 
			    charges.add(mostRecent);
			    for(int x = 0; x < potentials.length; x++) {
			    	for(int y = 0; y < potentials[0].length; y++) {
			    		potentials[x][y] += mostRecent.getPotential(x, y, time);
			    	}
			    }
			    
			    repaint();
			    
			    
			    
			}
		});
		
		Timer timer = new Timer(INTERVAL, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tick();
			}
		});
		timer.start();
		setFocusable(true);


		this.status = status;
	}

	
	public void tick() {
		time  = time + INTERVAL;
		repaint();
		
	}
	

	/** (Re-)set the state of the game to its initial state.
	 */
	public void reset() {

		playing = true;
		status.setText("Running...");
		charges = new LinkedList<PointCharge>();
	    for(int x = 0; x < potentials.length; x++) {
	    	for(int y = 0; y < potentials[0].length; y++) {
	    		potentials[x][y] = 0;
	    	}
	    }
		

		// Make sure that this component has the keyboard focus
		requestFocusInWindow();
		repaint();
	}
	

	
    
	/**
     * This method is called every time the timer defined
     * in the constructor triggers.
     */


	@Override 
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		double angle = ((time / period)*3.14159);
		phase_sin = Math.abs(Math.sin(angle));
		phase_cos = Math.abs(Math.cos(angle));
		for(PointCharge p: charges) {
			p.draw(g);
		}
		
		
		

		for(int x = 0; x < COURT_WIDTH; x++) {
			for(int y = 0; y < COURT_HEIGHT; y++) {
				potentials[x][y] = 0;
				for(PointCharge p: charges) {
					potentials[x][y] += p.getPotential(x, y, time);
				}
				
				if(potentials[x][y] <= 0) {
					alpha = 0;
					bogus = 0;
					if(potentials[x][y] == Integer.MIN_VALUE) {potentials[x][y] += 20;}
					bogus = Math.abs(potentials[x][y]);
					bogus = Math.min(bogus, 255);
					alpha = Math.sin((double)(bogus*3.14159)/(255*2));
					alpha = Math.pow(alpha, 0.25);
					g.setColor(new Color((int) (Math.abs(255*Math.cos(2*bogus*3.14159/255))), (int) (Math.abs(255*Math.sin(2*bogus*3.14159/255))), 0, (int)(alpha*255)));

					
				
				}
				if(potentials[x][y] > 0) {
					bogus = 0;
					alpha = 0;
					bogus = Math.abs(potentials[x][y]);
					bogus = Math.min(bogus, 255);
					alpha = Math.sin((double)(bogus*3.14159)/(255*2));
					alpha = Math.pow(alpha, 0.25);
					g.setColor(new Color(0, (int) (Math.abs(255*Math.sin(2*bogus*3.14159/255))), (int) (Math.abs(255*Math.cos(2*bogus*3.14159/255))), (int)(alpha*255)));
				}
				g.drawLine(x, y, x+1, y+1);

			}
		}
	
		
	}

	@Override
	public Dimension getPreferredSize(){
		return new Dimension(COURT_WIDTH,COURT_HEIGHT);
	}
	
	public void changeCharge(int newCharge) {
		charge = newCharge;
	}
	public int getCharge(){
		return charge;
	}
	
}
