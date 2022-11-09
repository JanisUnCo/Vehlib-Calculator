package testing;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PriceKeyboardListener implements KeyListener {
	Main obj = new Main();
	public static double price;
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		try {
			price = Double.parseDouble(Main.priceField.getText());

			
			if (price < Main.minPrice || 
				price > Main.maxPrice || 
				Main.isDoubleRegex(Main.priceField.getText())) {
				
				Main.priceField.setForeground(Color.red);		
				Main.isPriceSafe = false;
			}
			
			if (price >= Main.minPrice &&
				price <= Main.maxPrice) {
				
//				System.out.println("PRICE: "+price);
				Main.priceField.setForeground(Color.black);
				Main.isPriceSafe = true;
			}
			
			if (Main.isEngineSizeSafe && Main.isPriceSafe) {
				obj.doMath(EngineSizeKeyboardListener.engineSize, price);
			}
			
		} catch (Exception e2) {
			System.out.println("Error in PriceKeyboardListener!");
			Main.priceField.setForeground(Color.red);		
		}
	}

}

