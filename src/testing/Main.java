package testing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import java.io.File;

import javax.swing.JComboBox;

public class Main {
	static String root = System.getProperty("user.dir") + "\\src\\testing\\";

	public static Font tahomaBold = new Font("Tahoma", Font.BOLD, 10);
	public static Font tahomaItalic = new Font("Tahoma", Font.ITALIC, 10);
	
	public static Bound leftColLowerLblBound = new Bound(42, 123, 144, 13);
	public static Bound leftColUpperLblBound = new Bound(42, 52, 144, 13);
	public static Bound rightColLowerLblBound = new Bound(171, 123, 144, 13);
	
	public static Color invalidTextColor = Color.red;
	public static Color generalTextColor = Color.black;

	public static boolean isEngineSizeSafe;
	public static boolean isPriceSafe;
	public static double lastCat; // double instead of int, otherwise I will need to code more in one of the methods
	
	// GUI
	
	static JFrame frame;
	static JPanel panel;
	static JTextField engineSize;
	static JTextField priceField;
	
	public static JComboBox comboBox; 
	
	private static JLabel lblOriginalPrice;
	private static JLabel lblGamePrice;
	private static JLabel lblTax;
	private static JLabel lblTopSpeed;
	private static JLabel lblAcceleration;
	private static JLabel lblEngineInertia;
	private static JLabel lblOriginalPriceValue;
	private static JLabel lblGamePriceValue;
	private static JLabel lblTaxValue;
	private static JLabel lblTopSpeedValue;
	private static JLabel lblAccelerationValue;
	private static JLabel lblEngineInertiaValue;
	private static JLabel lblPrice;
	private static JLabel lblEngineSize;
	
	public static double intPrice;
	public static double intEngineSize;
	
	private static double originalPrice = 0;
	private static double gamePrice = 0;
	private static double tax = 0;
	private static double topSpeed = 0;
	private static double acceleration = 0;
	private static double engineInertia = 0;
	
	static HashMap<String, Double> categoryValues = new HashMap<String, Double>();
	static ArrayList<String> categoryNameList = new ArrayList<String>();
	
	static double engineKoef;
	static double maxEngineSize;
	static double minEngineSize;
	
	static double weight;
	static double priceKoef;
	static double minPrice;
	static double maxPrice;
	static double minTax;
	static double maxTax;
	static double minTopSpeed;
	static double maxTopSpeed;
	static double minAcc;
	static double maxAcc;
	static double minDrag;
	static double maxDrag;
	static double minInertia;
	static double maxInertia;
	
	static String[] comboBoxElements;

	
	public static void main(String[] args) {
		System.out.println(root);
		initializeStart();
		startGUI();	
		
//		for (String i : categoryValues.keySet()) {
//			  System.out.println("key: " + i + " value: " + categoryValues.get(i));
//			}
		
	}
	
	static void addLabels() {
		int yUpperLblPeriod = 29;
		int yLowerLblPeriod = 23;
		
		createLabel(panel, lblPrice, "Car price:", tahomaBold, leftColUpperLblBound, yUpperLblPeriod, 0);
		createLabel(panel, lblEngineSize, "Engine size (*.*L):", tahomaBold, leftColUpperLblBound, yUpperLblPeriod, 1);
		
		createLabel(panel, lblOriginalPrice, "Original Price", tahomaBold, leftColLowerLblBound, yLowerLblPeriod, 0);
		createLabel(panel, lblGamePrice, "In-game Price", tahomaBold, leftColLowerLblBound, yLowerLblPeriod, 1);
		createLabel(panel, lblTax, "Tax", tahomaBold, leftColLowerLblBound, yLowerLblPeriod, 2);
		createLabel(panel, lblTopSpeed, "Top Speed", tahomaBold, leftColLowerLblBound, yLowerLblPeriod, 3);
		createLabel(panel, lblAcceleration, "Acceleration", tahomaBold, leftColLowerLblBound, yLowerLblPeriod, 4);
		createLabel(panel, lblEngineInertia, "Engine inertia", tahomaBold, leftColLowerLblBound, yLowerLblPeriod, 5);
		
//		##################################################################
//		You might ask shy the names so weird? In case I decide to make a saving system,
//		where you could see the old values after closing the app.
		createLabel(panel, lblOriginalPriceValue, "$ "+String.valueOf(originalPrice), tahomaItalic, rightColLowerLblBound, yLowerLblPeriod, 0);
		createLabel(panel, lblGamePriceValue, "$ "+String.valueOf(gamePrice), tahomaItalic, rightColLowerLblBound, yLowerLblPeriod, 1);
		createLabel(panel, lblTaxValue, "$ "+String.valueOf(tax), tahomaItalic, rightColLowerLblBound, yLowerLblPeriod, 2);
		createLabel(panel, lblTopSpeedValue, String.valueOf(topSpeed)+" KM/h", tahomaItalic, rightColLowerLblBound, yLowerLblPeriod, 3);
		createLabel(panel, lblAccelerationValue, String.valueOf(acceleration), tahomaItalic, rightColLowerLblBound, yLowerLblPeriod, 4);
		createLabel(panel, lblEngineInertiaValue, String.valueOf(acceleration), tahomaItalic, rightColLowerLblBound, yLowerLblPeriod, 5);
	
	}
	
	static void createLabel (JPanel parentPanel, JLabel lblName, String lblText, 
							 Font lblFont, Bound bounds, int yPeriod, int position) {
		lblName = new JLabel(lblText);
		lblName.setFont(lblFont);
		lblName.setBounds(bounds.x, bounds.y + yPeriod * position, 
				bounds.width, bounds.height);
		parentPanel.add(lblName);
	}

	static void addComboBox() {
		ComboBoxActionListener listener = new ComboBoxActionListener();
		comboBox = new JComboBox(comboBoxElements);
		comboBox.setBounds(42, 18, 273, 21);
		panel.add(comboBox);
		comboBox.addActionListener(listener);
		
	}
	
	static void addFields() {
		KeyboardListener listener = new KeyboardListener();
		
		engineSize = new JTextField(0);
		engineSize.setBounds(171, 49, 144, 19);
		panel.add(engineSize);
		engineSize.setColumns(10);
		engineSize.addKeyListener(listener);

		priceField = new JTextField(0);
		priceField.setBounds(171, 78, 144, 19);
		panel.add(priceField);
		priceField.setColumns(10);
		priceField.addKeyListener(listener);
	
	}
	
	static void addPanel() {
		panel = new JPanel();
		panel.setBounds(0, 0, 360, 263);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
	}
	
	static void makeFrame() {
		frame = new JFrame("VehLib Calculator");
        frame.setBounds(0,0, 374, 300);
        frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
	}

	static void startGUI() {
		makeFrame();
		addPanel();
		addFields();
		addLabels();
		addComboBox();
		frame.setVisible(true);
		lastCat = comboBox.getSelectedIndex();
	}
	
	public static void doMath(double engineSize2, double price2 ) {
		System.out.println("Called!");
		double internalPrice = 0;
		double internalEki = 0;
		double internalPercent = 0;
		double gamePrice = 0;
		try {
			internalPrice = price2 - minPrice;
			internalEki = engineSize2 * (price2 * engineKoef / weight);
			internalPercent = internalPrice / (maxPrice - minPrice) * (internalEki);
			
			tax = (minTax + (maxTax - minTax) * internalPercent);
			topSpeed = (minTopSpeed + ((maxTopSpeed - minTopSpeed) * internalPercent));
			acceleration = (minAcc + ((maxAcc - minAcc) * internalPercent));
			engineInertia = (minInertia + ((maxInertia - minInertia) * internalPercent / 2));
			
			gamePrice = price2 + (price2 / 100 * priceKoef);
			lblOriginalPriceValue.setText("$ "+String.valueOf(price2));
			lblGamePriceValue.setText("$ "+String.valueOf(gamePrice));
			lblTaxValue.setText("$ "+String.valueOf(tax));
			lblTopSpeedValue.setText(String.valueOf(topSpeed)+" KM/h");
			lblAccelerationValue.setText(String.valueOf(acceleration));
			lblEngineInertiaValue.setText(String.valueOf(engineInertia));
			
		} catch (Exception e) {
			System.out.println("Error in mathematics!");
		}
		
	}
	
	static Double getConfigValue(String key) {
		return categoryValues.get(key);
	}
	
	static void setConfigValuesToIntegers() {
		engineKoef = getConfigValue("engineKoef");
		maxEngineSize  = getConfigValue("maxEngineSize");
		minEngineSize  = getConfigValue("minEngineSize");

		weight = getConfigValue("weight");
		priceKoef = getConfigValue("priceKoef");
		minPrice = getConfigValue("minPrice");
		maxPrice = getConfigValue("maxPrice");
		minTax = getConfigValue("minTax");
		maxTax = getConfigValue("maxTax");
		minTopSpeed = getConfigValue("minTopSpeed");
		maxTopSpeed = getConfigValue("maxTopSpeed");
		minAcc = getConfigValue("minAcc");
		maxAcc = getConfigValue("maxAcc");
		minDrag = getConfigValue("minDrag");
		maxDrag = getConfigValue("maxDrag");
		minInertia = getConfigValue("minInertia");
		maxInertia = getConfigValue("maxInertia");

//		for (String i : categoryValues.keySet()) {
//		  System.out.println("key: " + i + " value: " + categoryValues.get(i));
//		}
//		System.out.println();
	}
	
	public static boolean isDoubleRegex(String elem) {
		String regex = ".*\\d.{1}\\..*\\d";
		return Pattern.compile(regex).matcher(elem).find();
	}

	static void initializeStart () {	
		// TODO - file existance; safety flag, if something does not exist
		// experimenting with forloops and ways to make them 
		// stop w/o nums but empty lines
		File file = new File(root+"values.conf");
		int counter = 0;
		try {
			Scanner scan = new Scanner(file);
			while(scan.hasNextLine()) {
				String line = scan.nextLine();
				if (line.equals("[Global settings]")) {
					for (int i = 0; i < 4; i++) {
						String[] _line = scan.nextLine().split("=");
						String key = _line[0].trim();
						Double value = Double.parseDouble(_line[1].trim());
						categoryValues.put(key, value);		
					}
				}
				double lastCat = categoryValues.get("lastCat");
				// why dont I implement another while loop here? Because something is wrong with the way how lines
				// are counted, and I cant figure out what the hell is wrong and cant even think anymore oh god please!!!
				if (line.contains("[Category")) {
					counter++;
					categoryNameList.add(line.substring(1, line.length()-1));
				}
				
				if (line.contains("[Category "+(int) lastCat)) {
					for (int i = 0; i < 14; i++) {
						String []_line = scan.nextLine().split("=");
						String key = _line[0].trim();
						Double value = Double.parseDouble(_line[1].trim());
						categoryValues.put(key, value);
					}
				}
			}
			comboBoxElements = new String[counter];
			for (int i = 0; i < comboBoxElements.length; i++) {
				comboBoxElements[i] = categoryNameList.get(i);
			}
			scan.close();
			setConfigValuesToIntegers();
			} catch (Exception e) {
			System.out.println();
			System.out.println(e);
			System.out.println(e.getStackTrace()[0]);
			System.out.println();
			System.out.println("Something went south in initializing!");
		}	
	}
	
	static void changeCategoryIntegers(double catNum) {
		File file = new File(root+"values.conf");
		try {
			Scanner scan = new Scanner(file);
			while (scan.hasNextLine()) {
				String line = scan.nextLine();
				if (line.contains("[Category "+(int)(catNum+1))) {
					System.out.println("Found line for cat!");
					for (int i = 0; i < 14; i++) {
						String []_line = scan.nextLine().split("=");
						String key = _line[0].trim();
						Double value = Double.parseDouble(_line[1].trim());
						categoryValues.replace(key, value);
					}
				}
			}
			setConfigValuesToIntegers();
			scan.close();
		} catch (Exception e) {

		}
	
	}

	static void checkPrice() {

		try {
			intPrice = Double.parseDouble(priceField.getText());

			
			if (intPrice < minPrice || 
				intPrice > maxPrice || 
				isDoubleRegex(priceField.getText())) {
				
				priceField.setForeground(invalidTextColor);		
				isPriceSafe = false;
				return;
			}
			
			if (intPrice >= minPrice &&
				intPrice <= maxPrice) {
				
//				System.out.println("PRICE: "+price);
				priceField.setForeground(generalTextColor);
				isPriceSafe = true;
			}
			
			if (isEngineSizeSafe && isPriceSafe) {
				doMath(intEngineSize, intPrice);
			}
			
		} catch (Exception e2) {
			System.out.println("Invalid char in Price!");
			priceField.setForeground(invalidTextColor);		
		}
	}
	
	static void checkEngine() {
		try {
			intEngineSize = Double.parseDouble(engineSize.getText());
			
			if (intEngineSize < minEngineSize ||
				intEngineSize > maxEngineSize ||
				isDoubleRegex(engineSize.getText())) {
				engineSize.setForeground(invalidTextColor);
				isEngineSizeSafe = false;
				return;
			}
			
			if (intEngineSize > minEngineSize && intEngineSize < maxEngineSize) {
				engineSize.setForeground(generalTextColor);
				isEngineSizeSafe = true;
			}
			
			if (isEngineSizeSafe && isPriceSafe) {
				doMath(intEngineSize, intPrice);
			}
			
			
		} catch (Exception e2) {
			System.out.println("Invalid char in Engine!");
			engineSize.setForeground(invalidTextColor);
		}
	}

	public static void changeCategories() {
		changeCategoryIntegers(comboBox.getSelectedIndex());
//		priceField.setText("");
//		engineSize.setText("");
//		intPrice = 0;
//		intEngineSize = 0;
		checkEngine();
		checkPrice();
		lastCat = (double)comboBox.getSelectedIndex();
	}
}
