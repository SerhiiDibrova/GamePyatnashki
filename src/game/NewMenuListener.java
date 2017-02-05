package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * @author Sergio
 *
 *  11.05.2016
 */
public class NewMenuListener implements ActionListener {

	private Pyatnashki app;

	
	
	public NewMenuListener(Pyatnashki app) {
		
		this.app=app;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		  String command = e.getActionCommand();
          if ("exit".equals(command)) {
              System.exit(0);
          }
          if ("new".equals(command)) {
              app.generate();
              app.repaintField();
          }

	}

}
