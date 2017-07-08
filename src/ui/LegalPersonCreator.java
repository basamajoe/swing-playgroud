package ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import data.KnownInstances;
import model.Gender;
import model.PhysicalPerson;
import utils.OptionChooser;
import utils.TextWithLabel;

public class LegalPersonCreator extends JFrame {
	
	
	
	private TextWithLabel name;
	private TextWithLabel artisticName;
	private TextWithLabel founded;

	//private OptionChooser<Gender> gender;

	public LegalPersonCreator() {
		

		
		super("Legal Person Creator");
		
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(6, 2));
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(1,2));
		
		final JFrame jf = this;


		name = new TextWithLabel("Nombre: ", 30);
		topPanel.add(name);
	
		artisticName = new TextWithLabel("Nombre art.: ", 30);
		topPanel.add(artisticName);
		founded = new TextWithLabel("Fecha nacimiento(dd/mm/yyyy): ", 30);
		topPanel.add(founded);
		

		JButton Ok = new JButton("Crear");
		Ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			    whenOkButtonIsPressed();
                jf.dispose();
			}
		});
		bottomPanel.add(Ok);

		// Add a button to go back to the main window
		JButton goBack = new JButton("Salir");
		goBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			jf.dispose();
			}
		});
		bottomPanel.add(goBack);

		add(topPanel, BorderLayout.NORTH);
		add(bottomPanel, BorderLayout.SOUTH);
		
	
	}
	
	private void whenOkButtonIsPressed() {
	
				String nameValue = name.getText();
				
				String artisticNameValue = artisticName.getText();
				String foundedValue = founded.getText();
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				Date founded;
				try {
					founded = df.parse(foundedValue);
				} catch (ParseException e) {
					e.printStackTrace();
					founded = new Date();
				}
				
				
				
				KnownInstances.knownPeople.add(
						new PhysicalPerson()
							.setName(nameValue)
							
							.setArtisticName(artisticNameValue)
							.setBorn(founded)
							
				);
			}
		
		
	}

