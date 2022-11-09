package testing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ComboBoxActionListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		int currentIndex = Main.comboBox.getSelectedIndex();
		if ((int)Main.lastCat != currentIndex) {
			Main.changeCategories();
		}
	}

}
