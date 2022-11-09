package testing2;

import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;

public class windowCategory1 extends JFrame {

	public windowCategory1() throws HeadlessException {
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);

		// TODO Auto-generated constructor stub
	}

	public windowCategory1(GraphicsConfiguration gc) {
		super(gc);
		// TODO Auto-generated constructor stub
	}

	public windowCategory1(String title) throws HeadlessException {
		super(title);
		// TODO Auto-generated constructor stub
	}

	public windowCategory1(String title, GraphicsConfiguration gc) {
		super(title, gc);
		// TODO Auto-generated constructor stub
	}

}
