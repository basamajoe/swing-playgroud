package ui;

import javax.swing.JFrame;

import model.Person;

public class PersonEditor extends JFrame {

	public PersonEditor(Person person) {
		// Add components to edit a person
		// These components will be different for a physical person than for a legal person
		// If it is a physical person, employer data will be shown here using a JList
		//   The user can add (use PersonChooser) or remove employers from the list 
		// Add a button to go back to the main window
	}
	
	private void whenSaveButtonIsPressed() {
		// Set the values that the user has modified
	}
}
