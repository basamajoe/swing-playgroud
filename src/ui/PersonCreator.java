package ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PersonCreator extends JFrame {

	public PersonCreator() {

		super("Person Creator");
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3, 1));

		final JFrame jf = this;
		
		// Add a button that opens the physical person creator
		JButton physicalPerson = new JButton("Persona Fisica");
		physicalPerson.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PhyhsicalPersonCreator ppc = new PhyhsicalPersonCreator();
				utils.WindowUtility.createFrame(ppc);
				jf.dispose();
			}
		});
		panel.add(physicalPerson);
		
		// Add a button that opens the legal person creator
		JButton legalPerson = new JButton("Persona Legal");
		legalPerson.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LegalPersonCreator lpc = new LegalPersonCreator();
				utils.WindowUtility.createFrame(lpc);
				jf.dispose();
			}
		});
		panel.add(legalPerson);
		
		// Add a button to go back to the main window
		JButton goBack = new JButton("Salir");
		goBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			jf.dispose();
			}
		});
		panel.add(goBack);
		
		// When either button is pressed, dispose this window and open the new one
		add(panel, BorderLayout.CENTER);
	
	}
	
}
