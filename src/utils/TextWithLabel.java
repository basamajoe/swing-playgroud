package utils;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TextWithLabel extends JPanel {

	private JTextField textField;
	
	public TextWithLabel(String label, int textLength) {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		add(new JLabel(label));
		textField = new JTextField("", textLength);
		add(textField);
	}

	public TextWithLabel(String label, int textLength, String textValue) {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		add(new JLabel(label));
		textField = new JTextField(textValue, textLength);
		add(textField);
	}

public String getText() {
	return textField.getText();
}


}
