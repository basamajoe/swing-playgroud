package utils;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Window;

import javax.swing.JFrame;

public class WindowUtility {

	public static void centerWindow(Window window) {
		GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		
		// Altura y anchura de la pantalla
		int deviceHeight = device.getDisplayMode().getHeight();
		int deviceWidth  = device.getDisplayMode().getWidth();
		
		// Altura y anchrua de la ventana
		int windowHeight = window.getHeight();
		int windowWidth  = window.getWidth();
		
		window.setLocation((deviceWidth - windowWidth) / 2, (deviceHeight - windowHeight) / 2);
	}

	public static void createFrame(JFrame frame) {
		frame.setSize(600, 600);
		centerWindow(frame);
		frame.setVisible(true);
	}	
	
}
