package ui;

import data.KnownInstances;
import model.LegalPerson;
import model.Person;
import model.PhysicalPerson;
import test.PersistenceTests;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class PersonLister extends JFrame implements ListSelectionListener {

    private static final String EDIT = "Edit";
    private JList list;
    private DefaultListModel listModel;
    private JButton editButton;
    final JFrame jf;

	public PersonLister() {
		
		super("Person Lister");
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3, 1));

        jf = this;

		
        KnownInstances.generateKnownPeople();

	
        listModel = new DefaultListModel();
        //mostramos todas las personas creadas en una llista
        for(Person person: KnownInstances.knownPeople){
            listModel.addElement(person);
        }
   
        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.setVisibleRowCount(5);
        JScrollPane listScrollPane = new JScrollPane(list);

        add(listScrollPane, BorderLayout.CENTER);

       
        editButton = new JButton(EDIT);
        editButton.setActionCommand(EDIT);
        editButton.addActionListener(new EditListener());

     
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane,
                BoxLayout.LINE_AXIS));
        buttonPane.add(editButton);
        buttonPane.add(Box.createHorizontalStrut(5));

  
        JButton goBackButton = new JButton("Salir");
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jf.dispose();
            }
        });

        buttonPane.add(goBackButton);

        add(buttonPane, BorderLayout.PAGE_END);
	}

    @Override
    public void valueChanged(ListSelectionEvent e) {
        System.out.println("event" + e.getValueIsAdjusting());
    }

    class EditListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.out.println("action performed...");

            int index = list.getSelectedIndex();

            Person persona = (Person) listModel.get(index);

            //elegir para editar si es una persona fisica o legal una pantalla o otra
            if(persona instanceof PhysicalPerson){
                System.out.println("open physical person");
                PhysicalPersonEditor ppe = new PhysicalPersonEditor((PhysicalPerson) persona);
                utils.WindowUtility.createFrame(ppe);
                jf.dispose();
            }else if(persona instanceof LegalPerson){
                System.out.println("open legal person");
                LegalPersonEditor lpe = new LegalPersonEditor((LegalPerson) persona);
                utils.WindowUtility.createFrame(lpe);
                jf.dispose();
            }
        }
    }
}
