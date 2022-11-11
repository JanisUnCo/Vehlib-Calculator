package testing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ComboBoxActionListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		int currentIndex = Main.comboBox.getSelectedIndex();
		System.out.println(currentIndex);
		System.out.println(Main.lastCat);
		if ((int)Main.lastCat != currentIndex) 
			System.out.println("Change detected!");
			Main.changeCategories();
		}
	}
