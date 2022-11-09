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
	static String root = "C:\\Users\\janis\\eclipse-workspace\\VehlibCalculator\\src\\testing\\";
	
	static JFrame frame;
	static JPanel panel;
	static JTextField engineSize;
	static JTextField priceField;
	
	public static boolean isEngineSizeSafe;
	public static boolean isPriceSafe;
	public static double lastCat; // double instead of int, otherwise I will need to code more in one of the methods
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
		initializeStart();
		startGUI();	
		
//		for (String i : categoryValues.keySet()) {
//			  System.out.println("key: " + i + " value: " + categoryValues.get(i));
//			}
		
	}
	
	static void addLabels() {
		
		lblOriginalPrice = new JLabel("Original Price");
		lblOriginalPrice.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblOriginalPrice.setBounds(42, 123, 144, 13);
		panel.add(lblOriginalPrice);
		
		lblGamePrice = new JLabel("In-Game Price");
		lblGamePrice.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblGamePrice.setBounds(42, 146, 144, 13);
		panel.add(lblGamePrice);
		
		lblTax = new JLabel("Tax");
		lblTax.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblTax.setBounds(42, 169, 144, 13);
		panel.add(lblTax);
		
		lblTopSpeed = new JLabel("Top Speed");
		lblTopSpeed.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblTopSpeed.setBounds(42, 191, 144, 13);
		panel.add(lblTopSpeed);
		
		lblAcceleration = new JLabel("Acceleration");
		lblAcceleration.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblAcceleration.setBounds(42, 214, 144, 13);
		panel.add(lblAcceleration);
		
		lblEngineInertia = new JLabel("Engine Inertia");
		lblEngineInertia.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblEngineInertia.setBounds(42, 237, 144, 13);
		panel.add(lblEngineInertia);

		lblPrice = new JLabel("Car price:");
		lblPrice.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblPrice.setBounds(42, 81, 144, 13);
		panel.add(lblPrice);
		
		lblEngineSize = new JLabel("Engine size (*.*L):");
		lblEngineSize.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblEngineSize.setBounds(42, 52, 144, 13);
		panel.add(lblEngineSize);

//		##################################################################
		
		lblOriginalPriceValue = new JLabel("$ "+String.valueOf(originalPrice));
		lblOriginalPriceValue.setFont(new Font("Tahoma", Font.ITALIC, 10));
		lblOriginalPriceValue.setBounds(171, 123, 144, 13);
		panel.add(lblOriginalPriceValue);
		
		lblGamePriceValue = new JLabel("$ "+String.valueOf(gamePrice));
		lblGamePriceValue.setFont(new Font("Tahoma", Font.ITALIC, 10));
		lblGamePriceValue.setBounds(171, 146, 144, 13);
		panel.add(lblGamePriceValue);
		
		lblTaxValue = new JLabel("$ "+String.valueOf(tax));
		lblTaxValue.setFont(new Font("Tahoma", Font.ITALIC, 10));
		lblTaxValue.setBounds(171, 169, 144, 13);
		panel.add(lblTaxValue);
		
		lblTopSpeedValue = new JLabel(String.valueOf(topSpeed)+" KM/h");
		lblTopSpeedValue.setFont(new Font("Tahoma", Font.ITALIC, 10));
		lblTopSpeedValue.setBounds(171, 191, 144, 13);
		panel.add(lblTopSpeedValue);
		
		lblAccelerationValue = new JLabel(String.valueOf(acceleration));
		lblAccelerationValue.setFont(new Font("Tahoma", Font.ITALIC, 10));
		lblAccelerationValue.setBounds(171, 214, 144, 13);
		panel.add(lblAccelerationValue);
		
		lblEngineInertiaValue = new JLabel(String.valueOf(engineInertia));
		lblEngineInertiaValue.setFont(new Font("Tahoma", Font.ITALIC, 10));
		lblEngineInertiaValue.setBounds(171, 237, 144, 13);
		panel.add(lblEngineInertiaValue);
		
	
	}

	static void addComboBox() {
//		ComboBoxMouseListener listener = new ComboBoxMouseListener();
		ComboBoxActionListener listener = new ComboBoxActionListener();
		comboBox = new JComboBox(comboBoxElements);
		comboBox.setBounds(42, 18, 273, 21);
		panel.add(comboBox);
//		comboBox.addMouseListener(listener);
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
			engineInertia = (minInertia + ((maxInertia - minInertia) * internalPercent));
			
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
123
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
				if (line.equals("[Category "+(int)(catNum+1)+"]")) {
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
				
				priceField.setForeground(Color.red);		
				isPriceSafe = false;
				return;
			}
			
			if (intPrice >= minPrice &&
				intPrice <= maxPrice) {
				
//				System.out.println("PRICE: "+price);
				priceField.setForeground(Color.black);
				isPriceSafe = true;
			}
			
			if (isEngineSizeSafe && isPriceSafe) {
				doMath(intEngineSize, intPrice);
			}
			
		} catch (Exception e2) {
			System.out.println("Invalid char in Price!");
			priceField.setForeground(Color.red);		
		}
	}
	
	static void checkEngine() {
		try {
			intEngineSize = Double.parseDouble(engineSize.getText());
			
			if (intEngineSize < minEngineSize ||
				intEngineSize > maxEngineSize ||
				isDoubleRegex(engineSize.getText())) {
				engineSize.setForeground(Color.red);
				isEngineSizeSafe = false;
				return;
			}
			
			if (intEngineSize > minEngineSize && intEngineSize < maxEngineSize) {
				engineSize.setForeground(Color.black);
				isEngineSizeSafe = true;
			}
			
			if (isEngineSizeSafe && isPriceSafe) {
				doMath(intEngineSize, intPrice);
			}
			
			
		} catch (Exception e2) {
			System.out.println("Invalid char in Engine!");
			engineSize.setForeground(Color.red);
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
