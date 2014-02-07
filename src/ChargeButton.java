import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButton;


@SuppressWarnings("serial")
public class ChargeButton extends JRadioButton {

	public ChargeButton(final int i, final GameCourt g) {
		super(Integer.toString(i));

        this.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	g.changeCharge(i);                
            }
        });
		
	}
	

}
