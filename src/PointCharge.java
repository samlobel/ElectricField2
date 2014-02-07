import java.awt.Color;
import java.awt.Graphics;


public class PointCharge {
	private int x_pos;
	private int y_pos;
	private int charge;
	
	public PointCharge(int x, int y, int ch) {
		x_pos = x;
		y_pos = y;
		charge = ch;
	}
	
	public int getX() {
		return x_pos;
	}

	public int getY() {
		return y_pos;
	}
	
	public int getCharge() {
		return charge;
	}
	
	public double getDistanceSqrt(int x_click, int y_click) {
		double d_x =  Math.pow((x_click - x_pos), 2);
		double d_y =  Math.pow((y_click - y_pos), 2);
		return (Math.sqrt(d_x + d_y));
	}
	
	public double getPotential(int x_click, int y_click, double time) {
		if(getDistanceSqrt(x_click, y_click) < (charge/2)) {return 0.0;}
		double dist = (getDistanceSqrt(x_click, y_click));
		double pot = ((double) charge * 3000 / (getDistanceSqrt(x_click, y_click)));
		if(pot <= 0) {pot = pot * (1+(0.3)*Math.sin((time/100)-dist/100));}
		if(pot > 0) {pot = pot * (1+(0.3)*Math.cos((time/100)-dist/100));}
		return pot;
	}
	
	
	
	public void draw(Graphics g) {
		if(charge > 0) { g.setColor(Color.BLUE);}
		if(charge < 0) { g.setColor(Color.RED);}
		if(charge == 0) {g.setColor(Color.BLACK);}
		g.fillOval(x_pos-(Math.abs(10*charge)/2), y_pos-(Math.abs(10*charge)/2), Math.abs(10*charge), Math.abs(10*charge));
		g.setColor(Color.BLACK);
		g.drawOval(x_pos-(Math.abs(10*charge)/2), y_pos-(Math.abs(10*charge)/2), Math.abs(10*charge), Math.abs(10*charge));
		
	}
	
}
