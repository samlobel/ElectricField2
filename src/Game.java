/**
 * CIS 120 HW10
 * (c) University of Pennsylvania
 * @version 2.0, Mar 2013
 */

// imports necessary libraries for Java swing
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/** 
 * Game
 * Main class that specifies the frame and widgets of the GUI
 */
public class Game implements Runnable {
    public void run(){
        // NOTE : recall that the 'final' keyword notes inmutability
		  // even for local variables. 

        // Top-level frame in which game components live
		  // Be sure to change "TOP LEVEL FRAME" to the name of your game
        final JFrame frame = new JFrame("TOP LEVEL FRAME");
        frame.setLocation(300,300);

		  // Status panel
        final JPanel status_panel = new JPanel();
        frame.add(status_panel, BorderLayout.WEST);
        final JLabel status = new JLabel("Running...");
        status_panel.add(status);

        // Main playing area
        final GameCourt court = new GameCourt(status);
        frame.add(court, BorderLayout.CENTER);
        
        final ButtonGroup charge_button_group = new ButtonGroup();
        
        final JPanel charge_buttons = new JPanel();
        frame.add(charge_buttons,BorderLayout.SOUTH);
        

        final ChargeButton one = new ChargeButton(1, court);
        charge_button_group.add(one);
        charge_buttons.add(one);
        final ChargeButton two = new ChargeButton(2, court);
        charge_button_group.add(two);
        charge_buttons.add(two);
        final ChargeButton three = new ChargeButton(3, court);
        charge_button_group.add(three);
        charge_buttons.add(three);
        final ChargeButton four = new ChargeButton(4, court);
        charge_button_group.add(four);
        charge_buttons.add(four);
        final ChargeButton neg_four = new ChargeButton(-4, court);
        charge_button_group.add(neg_four);
        charge_buttons.add(neg_four);
        final ChargeButton neg_three = new ChargeButton(-3, court);
        charge_button_group.add(neg_three);
        charge_buttons.add(neg_three);
        final ChargeButton neg_two = new ChargeButton(-2, court);
        charge_button_group.add(neg_two);
        charge_buttons.add(neg_two);
        final ChargeButton neg_one = new ChargeButton(-1, court);
        charge_button_group.add(neg_one);
        charge_buttons.add(neg_one);
        
        
        // Reset button
        final JPanel control_panel = new JPanel();
        frame.add(control_panel, BorderLayout.NORTH);

        // Note here that when we add an action listener to the reset
        // button, we define it as an anonymous inner class that is 
        // an instance of ActionListener with its actionPerformed() 
        // method overridden. When the button is pressed,
        // actionPerformed() will be called.
        final JButton reset = new JButton("Reset");
        reset.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    court.reset();
                }
            });
        control_panel.add(reset);
        

        // Put the frame on the screen
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Start game
        court.reset();
    }

    /*
     * Main method run to start and run the game
     * Initializes the GUI elements specified in Game and runs it
     * NOTE: Do NOT delete! You MUST include this in the final submission of your game.
     */
    public static void main(String[] args){
        SwingUtilities.invokeLater(new Game());
    }
}
