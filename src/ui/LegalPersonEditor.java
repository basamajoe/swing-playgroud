package ui;

import data.KnownInstances;
import model.Gender;
import model.LegalPerson;
import model.Person;
import model.PhysicalPerson;
import utils.OptionChooser;
import utils.TextWithLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class LegalPersonEditor extends JFrame {

	private TextWithLabel name;
	private TextWithLabel middleName;
	private TextWithLabel surnames;
	private TextWithLabel artisticName;
	private TextWithLabel founded;

	private LegalPerson ppersonLegal;



    public LegalPersonEditor(LegalPerson p) {
		
		super("Physical Person Editor");
		ppersonLegal = p;

		JPanel topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(6, 2));
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(1,2));
		
		final JFrame jf = this;


		name = new TextWithLabel("Nombre: ", 30, p.getName());
		topPanel.add(name);
		
	
		artisticName = new TextWithLabel("Nombre artistico: ", 30, p.getArtisticName());
		topPanel.add(artisticName);
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        founded = new TextWithLabel("Fecha Inscripcion (dd/mm/yyyy): ", 30, df.format(p.getFounded()));
		topPanel.add(founded);
		

		JButton Ok = new JButton("Guardar modificaciones");
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

		//Desam els nous valors
		ppersonLegal.setName(nameValue)
             
                .setArtisticName(artisticNameValue)
                .setFounded(founded)
                
                ;
		
		
		KnownInstances.knownPeople.remove(ppersonLegal);
        KnownInstances.knownPeople.add(ppersonLegal);
	}
}
