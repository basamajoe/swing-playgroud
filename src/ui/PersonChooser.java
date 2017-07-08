package ui;

import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JFrame;

public class PersonChooser extends JDialog {

	ArrayList<PersonListener> listeners;
	
	public PersonChooser(JFrame owner) {
		super(owner, true);
		
		// Populate dialog with the set of known people in KnownInstances
		
		setVisible(true);
		listeners = new ArrayList<>();
	}
	
	public void addPersonListener(PersonListener listener) {
		listeners.add(listener);
	}
	
	private void whenOkButtonIsPressed() {
		// Determine which person is selected and replace this null value
		for(PersonListener listener : listeners) {
			listener.personSelected(null);
		}
		dispose();
	}
}
