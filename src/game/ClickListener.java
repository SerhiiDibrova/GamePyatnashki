package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
/**
 * @author Sergio
 *
 *  11.05.2016
 */
public class ClickListener implements ActionListener {

	private Pyatnashki pyatnashki;
	
	
	public ClickListener(Pyatnashki pyatnashki) {
		this.pyatnashki=pyatnashki;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		JButton button = (JButton) e.getSource();
        button.setVisible(false);
        String name = button.getText();
        pyatnashki.change(Integer.parseInt(name));
	}

}
