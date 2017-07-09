package ui;

import data.KnownInstances;
import model.Gender;
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

public class PhysicalPersonEditor extends JFrame {

	private TextWithLabel name;
	private TextWithLabel middleName;
	private TextWithLabel surnames;
	private TextWithLabel artisticName;
	private TextWithLabel born;
	private OptionChooser<Gender> gender;
	private PhysicalPerson pperson;



    public PhysicalPersonEditor(PhysicalPerson p) {
		
		super("Physical Person Editor");
		pperson = p;

		JPanel topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(6, 2));
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(1,2));
		
		final JFrame jf = this;

		name = new TextWithLabel("Nombre: ", 30, p.getName());
		topPanel.add(name);
		middleName = new TextWithLabel("Segundo nombre: ", 30, p.getMiddleName());
		topPanel.add(middleName);
		//surnames = new TextWithLabel("Apellidos: ", 80, p.getSurnames());
		surnames = new TextWithLabel("Apellidos: ", 80);
		topPanel.add(surnames);
		artisticName = new TextWithLabel("Nombre artistico: ", 30, p.getArtisticName());
		topPanel.add(artisticName);
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		born = new TextWithLabel("Fecha nacimiento(dd/mm/yyyy): ", 30, df.format(p.getBorn()));
		topPanel.add(born);
		ArrayList<Gender> options = new ArrayList<>();
		options.add(Gender.MALE);
		options.add(Gender.FEMALE);
		gender = new OptionChooser<Gender>("Sexo:", options);
        gender.setSelected(p.getGender());
		topPanel.add(gender);

		JButton Ok = new JButton("Guardar modificaciones");
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

		//Desam els nous valors
        pperson.setName(nameValue)
                .setMiddleName(middleNameValue)
                .addSurname(surnamesValue)
                .setArtisticName(artisticNameValue)
                .setBorn(born)
                .setGender(gender.getSelected());
		
		
		KnownInstances.knownPeople.remove(pperson);
        KnownInstances.knownPeople.add(pperson);
	}
}
