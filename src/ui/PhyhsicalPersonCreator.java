package ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
//import java.time.LocalDateTime;
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

public class PhyhsicalPersonCreator extends JFrame {

	private TextWithLabel name;
	private TextWithLabel middleName;
	private TextWithLabel surnames;
	private TextWithLabel artisticName;
	private TextWithLabel born;
	private OptionChooser<Gender> gender;


	public PhyhsicalPersonCreator() {
		
		super("Physical Person Creator");
		
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(6, 2));
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(1,2));
		
		final JFrame jf = this;

		
		name = new TextWithLabel("Nombre: ", 30);
		topPanel.add(name);
		middleName = new TextWithLabel("Segundo nombre: ", 30);
		topPanel.add(middleName);
		surnames = new TextWithLabel("Apellidos: ", 80);
		topPanel.add(surnames);
		
		
		
		artisticName = new TextWithLabel("Nombre artistico: ", 30);
		topPanel.add(artisticName);
		born = new TextWithLabel("Fecha nacimiento(dd/mm/yyyy): ", 30);
		topPanel.add(born);
		ArrayList<Gender> options = new ArrayList<>();
		options.add(Gender.MALE);
		options.add(Gender.FEMALE);
		gender = new OptionChooser<Gender>("Sexo:", options);
		topPanel.add(gender);

		JButton Ok = new JButton("Crear persona fisica");
		Ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			    whenOkButtonIsPressed();
                jf.dispose();
			}
		});
		bottomPanel.add(Ok);

		
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
		String middleNameValue = middleName.getText();
		String surnamesValue = surnames.getText();
		
		
		String artisticNameValue = artisticName.getText();
		String bornValue = born.getText();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date born;
		try {
			born = df.parse(bornValue);
		} catch (ParseException e) {
			e.printStackTrace();
			born = new Date();
		}
		
		PhysicalPerson pperson = new PhysicalPerson()
				.setName(nameValue)
				.setMiddleName(middleNameValue)
				.setArtisticName(artisticNameValue)
				.setBorn(born)
				.setGender(gender.getSelected());

		String[] surnames = surnamesValue.split("\\s+");
        pperson.setSurname("");
		for(String surname: surnames){
			pperson.addSurname(surname);
		}

		KnownInstances.knownPeople.add(pperson);
	}
}
