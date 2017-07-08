package ui;

import java.awt.BorderLayout;

import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

import data.KnownInstances;
import model.Person;
import utils.WindowUtility;

public class MainWindow extends JFrame {

	public MainWindow() {
		super("My discography organizer");
		
		// Definimos qué pasará con la ventana cuando la cerremos
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Set an appropriate size for the window and center it
		setSize(800, 800);
		WindowUtility.centerWindow(this);
		
		setLayout(new BorderLayout());
		
		// In the center panel, add buttons for:
		//   1) creating a Person (open a PersonCreator frame)
		//   2) listing all known Person (open a PersonLister frame)
        addCenterPanel();
		
		addDataPanel();
	}
	
	private void addCenterPanel() {
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(1, 2));
		
		JButton createPerson = new JButton("Create a Person");
		createPerson.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PersonCreator pc = new PersonCreator();
				utils.WindowUtility.createFrame(pc);
			}
		});
		centerPanel.add(createPerson);

		JButton listPerson = new JButton("List all known Person");
		listPerson.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PersonLister pl = new PersonLister();
				utils.WindowUtility.createFrame(pl);
			}
		});
		centerPanel.add(listPerson);

		add(centerPanel, BorderLayout.CENTER);
	}
	
	private void addDataPanel() {
		JPanel dataPanel = new JPanel();
		dataPanel.setLayout(new GridLayout(1, 2));
		
		// This button should load known instances from serialized file
		JButton loadButton = new JButton("Load data");
		loadButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String loadPath;
				loadPath= getFilePathToLoad();
				KnownInstances.loadKnownInstances(loadPath);
			}
		});
		dataPanel.add(loadButton);
		
		// This button should save all known instances to a serialized file
		JButton saveButton = new JButton("Save data");
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String savePath;
				savePath= getFilePathToSave();
				KnownInstances.saveKnownInstances(savePath);
			}
		});
		dataPanel.add(saveButton);
		
		// Use the getFilePathToLoad and getFilePathToSave functions to ask the user for a file to load or save
		
		add(dataPanel, BorderLayout.SOUTH);
	}
	
	private String getFilePathToLoad() {
		JFileChooser chooser = new JFileChooser();
		int result = chooser.showOpenDialog(this);
		if(result == JFileChooser.APPROVE_OPTION) {
			return chooser.getSelectedFile().getAbsolutePath();
		}
		return null;
	}
	
	private String getFilePathToSave() {
		JFileChooser chooser = new JFileChooser();
		int result = chooser.showSaveDialog(this);
		if(result == JFileChooser.APPROVE_OPTION) {
			return chooser.getSelectedFile().getAbsolutePath();
		}
		return null;
	}
	
	public static void main(String[] args) {
		MainWindow mainWindow = new MainWindow();
		mainWindow.setVisible(true);
	}

}
