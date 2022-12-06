package testing;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class ElementSetting {
	JPanel panel;
	Font font;
	Bound bound;
	int period;
	// maybe I could use objects inside objects in this case hmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm?
	Main listener;
	
	ArrayList<ElementSetting> all = new ArrayList<ElementSetting>();
	
	public ElementSetting(JPanel panel, Font font, Bound bound, int period) {
		createObject(panel, font, bound, period);
	}
	
	public ElementSetting(JPanel panel, Font font, Bound bound, int period,
						  Main listener) {
		this.listener = listener;
		createObject(panel, font, bound, period);
	}

	
	void createObject (JPanel panel, Font font, Bound bound, int period) {
		this.panel = panel;
		this.font = font;
		this.bound = bound;
		this.period = period;
		
	}

}
