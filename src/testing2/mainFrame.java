package testing2;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.ButtonGroup;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JTextPane;
import javax.swing.JTextField;

public class mainFrame extends JFrame {
	
	private final ButtonGroup mainFrameButtonGroup = new ButtonGroup();
	private JTextField textField;
	public mainFrame() {
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JPanel categoryPanel = new JPanel();
		System.out.println("something");
		textField = new JTextField();
		textField.setBounds(412, 49, 96, 19);
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnCategory1 = new JButton("Kategorija 1");
		mainFrameButtonGroup.add(btnCategory1);

		btnCategory1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				getContentPane().add(categoryPanel, BorderLayout.CENTER);
				categoryPanel.setLayout(null);
			}
		});
		
		btnCategory1.setBounds(48, 103, 828, 76);
		panel.add(btnCategory1);
		
		JButton btnCategory2 = new JButton("Kategorija 2");
		mainFrameButtonGroup.add(btnCategory2);
		btnCategory2.setBounds(48, 200, 828, 76);
		panel.add(btnCategory2);
		
		JButton btnCategory3 = new JButton("Kategorija 3");
		mainFrameButtonGroup.add(btnCategory3);
		btnCategory3.setBounds(48, 302, 828, 76);
		panel.add(btnCategory3);
		
		JButton btnCategory4 = new JButton("Kategorija 4");
		mainFrameButtonGroup.add(btnCategory4);
		btnCategory4.setBounds(48, 404, 828, 76);
		panel.add(btnCategory4);
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
