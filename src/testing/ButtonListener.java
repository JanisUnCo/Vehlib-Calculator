package testing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ButtonListener implements ActionListener  {

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();
		if (button == Main.btnEditBack);
		if (button == Main.btnEdit);
		if (button == Main.btnEditRemoveCategory);
		if (button == Main.btnEditBack);
	
	}

}
