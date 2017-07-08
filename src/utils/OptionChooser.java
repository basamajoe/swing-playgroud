package utils;
import model.Gender;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class OptionChooser<T> extends JPanel {

	private List<T> options;
	private ButtonGroup buttonGroup;
	private int selected;
	
	public OptionChooser(String label, List<T> options) {
		this.options = options;
		addOptions(label);
	}
	
	private void addOptions(String label) {
		setLayout(new GridLayout(options.size() +1, 1));
		
		add(new JLabel(label));
		
		buttonGroup = new ButtonGroup();
		
		for (int i = 0; i < options.size(); i++) {
			JRadioButton radioButton = new JRadioButton(options.get(i).toString());

			// A cada botón le añadimos un ActionListener
			final int buttonOption = i;
			radioButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					selected = buttonOption;
				}
			});
			
			buttonGroup.add(radioButton);
			add(radioButton);
		}
	}
	
	public T getSelected() {
		return options.get(selected);
	}

	public void setSelected(Gender gender){
		for (int i = 0; i < options.size(); i++) {
			if(options.get(i) == gender) {
				selected = i;
			}
			break;
		}
	}
}
