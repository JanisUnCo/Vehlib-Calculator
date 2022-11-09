package testing1;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class WindowMain implements ItemListener {
	
	static JFrame frame;
	static JPanel cards;
	final static String CATEGORYPANEL1 = "Category 1";
	final static String CATEGORYPANEL2 = "Category 2";
	final static String CATEGORYPANEL3 = "Category 3";
	final static String CATEGORYPANEL4 = "Category 4";
	
	public static void main(String[] args) {
		
        //Create and set up the window.
        frame = new JFrame("Kursadarbs");
        frame.setBounds(400, 200, 800, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Create and set up the content pane.
        WindowMain content = new WindowMain();
        content.addComponentToPane(frame.getContentPane());
        
        //Display the window.
//        frame.pack();
        frame.setVisible(true);
		
	}
	
	public void addComponentToPane(Container pane) {
		JPanel comboBoxPane = new JPanel();
		String comboBoxItems[] = {CATEGORYPANEL1, CATEGORYPANEL2, CATEGORYPANEL3, CATEGORYPANEL4};
		JComboBox cb = new JComboBox(comboBoxItems);
		cb.setEditable(false);
		cb.addItemListener(this);
		comboBoxPane.add(cb);
		
		JPanel card1 = new JPanel();
		card1.add(new JTextField("Kategorija 1", 20));
		
		JPanel card2 = new JPanel();
		card2.add(new JTextField("Kategorija 2", 20));
		
		JPanel card3 = new JPanel();
		card3.add(new JTextField("Kategorija 3", 20));
		
		JPanel card4 = new JPanel();
		card4.add(new JTextField("Kategorija 4", 20));
		
		cards = new JPanel(new CardLayout());
		cards.add(card1, CATEGORYPANEL1);
		cards.add(card2, CATEGORYPANEL2);
		cards.add(card3, CATEGORYPANEL3);
		cards.add(card4, CATEGORYPANEL4);
		
	}

	public void itemStateChanged(ItemEvent e) {
        CardLayout cl = (CardLayout)(cards.getLayout());
        cl.show(cards, (String)e.getItem());
	}
}
	
