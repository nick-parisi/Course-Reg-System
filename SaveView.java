import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;


public class SaveView extends JFrame {
	private SaveController controller;
	private JButton save;
	
	public SaveView(final SaveController controller) {
		this.controller = controller;
		save = new JButton("SAVE AND EXIT");
		save.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	try {
					controller.save();
				} catch (IOException e1) {
					System.out.println("Something went wrong.");
				}
	        }
	    });
		setTitle("Save");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(save);
		setVisible(true);
	}
	
	
}
