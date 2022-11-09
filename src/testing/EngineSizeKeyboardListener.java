package testing;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class EngineSizeKeyboardListener implements KeyListener {
	Main obj = new Main();
	public static double engineSize;
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void keyReleased(KeyEvent e) {
		try {
			engineSize = Double.parseDouble(Main.engineSize.getText());
			
			if (engineSize < Main.minEngineSize || engineSize > Main.maxEngineSize) {
				Main.engineSize.setForeground(Color.red);
				Main.isEngineSizeSafe = false;
			}
			
			if (engineSize > Main.minEngineSize && engineSize < Main.maxEngineSize) {
				Main.engineSize.setForeground(Color.black);
				Main.isEngineSizeSafe = true;
			}
			
			if (Main.isEngineSizeSafe && Main.isPriceSafe) {
				obj.doMath(engineSize, PriceKeyboardListener.price);
			}
			
			
		} catch (Exception e2) {
			System.out.println("Error in EngineSizeKeyboardListener!");
			Main.engineSize.setForeground(Color.red);
		}
	}
}
