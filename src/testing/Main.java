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
import java.io.FileWriter;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main {
	static String root = System.getProperty("user.dir") + "\\src\\testing\\";

	public static Font tahomaBold = new Font("Tahoma", Font.BOLD, 10);
	public static Font tahomaItalic = new Font("Tahoma", Font.ITALIC, 10);
	
	public static Bound leftColLowerLblBound = new Bound(42, 123, 144, 13);
	public static Bound leftColUpperLblBound = new Bound(42, 52, 144, 13);
	public static Bound rightColLowerLblBound = new Bound(171, 123, 144, 13);
	
	public static Color invalidTextColor = Color.red;
	public static Color generalTextColor = Color.black;

	static boolean addTo = true;
	public static boolean isEngineSizeSafe;
	public static boolean isPriceSafe;
	public static boolean isEditing = false;
	public static boolean isBtnToEdit = false;
	public static double lastCat; // double instead of int, otherwise I will need to code more in one of the methods
	
	// GUI
	
	static JFrame frame;
	static JPanel panel;
	static JPanel editPanel;
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
	
	static JButton btnCheck;
	
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
	static ArrayList<JTextField> editPanelMinTxtFields = new ArrayList<JTextField>(); 
	static ArrayList<JTextField> editPanelMaxTxtFields = new ArrayList<JTextField>(); 
	static ArrayList<Category> categoryList = new ArrayList<Category>();
	
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
	static double minInertia;
	static double maxInertia;
	
	static String[] comboBoxElements;
	private static JLabel lblPriceKoeficient;
	private static JLabel lblPriceRange;
	private static JLabel lblTaxRange;
	private static JLabel lblTopSpeedRange;
	private static JLabel lblAccelerationRange;
	
	private static JLabel lblInertiaRange;
	
	private static JTextField txtMinPrice;
	private static JTextField txtMaxPrice;
	private static JTextField txtMinTax;
	private static JTextField txtMaxTax;
	private static JTextField txtMinTopSpeed;
	private static JTextField txtMaxTopSpeed;
	private static JTextField txtMinAcc;
	private static JTextField txtMaxAcc;
	private static JTextField txtMaxInertia;
	private static JTextField txtMinInertia;
	private static JTextField txtPriceKoef;
	private static JTextField txtWeight;
	private static JLabel lblTo1;
	private static JLabel lblTo2;
	private static JLabel lblTo3;
	private static JLabel lblTo4;
	private static JLabel lblTo6;

	
	public static void main(String[] args) {
		System.out.println(root);
		initializeStart();
		startGUI();	
		System.out.println(categoryList);
		
//		for (String i : categoryValues.keySet()) {
//			  System.out.println("key: " + i + " value: " + categoryValues.get(i));
//			}
		
	}
	
	static void addLabels() {
		int yUpperLblPeriod = 29;
		int yLowerLblPeriod = 23;
		
		lblPrice = createLabel(panel,"Car price:", tahomaBold, leftColUpperLblBound, yUpperLblPeriod, 0);
		lblEngineSize = createLabel(panel, "Engine size (*.*L):", tahomaBold, leftColUpperLblBound, yUpperLblPeriod, 1);
		
		lblOriginalPrice = createLabel(panel, "Original Price", tahomaBold, leftColLowerLblBound, yLowerLblPeriod, 0);
		lblGamePrice =  createLabel(panel, "In-game Price", tahomaBold, leftColLowerLblBound, yLowerLblPeriod, 1);
		lblTax = createLabel(panel, "Tax", tahomaBold, leftColLowerLblBound, yLowerLblPeriod, 2);
		lblTopSpeed = createLabel(panel, "Top Speed", tahomaBold, leftColLowerLblBound, yLowerLblPeriod, 3);
		lblAcceleration = createLabel(panel, "Acceleration", tahomaBold, leftColLowerLblBound, yLowerLblPeriod, 4);
		lblEngineInertia = createLabel(panel, "Engine inertia", tahomaBold, leftColLowerLblBound, yLowerLblPeriod, 5);
		
//		##################################################################
//		You might ask shy the names so weird? In case I decide to make a saving system,
//		where you could see the old values after closing the app.
		
		lblOriginalPriceValue = createLabel(panel, "$ "+String.valueOf(originalPrice), tahomaItalic, rightColLowerLblBound, yLowerLblPeriod, 0);
		lblGamePriceValue =  createLabel(panel, "$ "+String.valueOf(gamePrice), tahomaItalic, rightColLowerLblBound, yLowerLblPeriod, 1);
		lblTaxValue =  createLabel(panel, "$ "+String.valueOf(tax), tahomaItalic, rightColLowerLblBound, yLowerLblPeriod, 2);
		lblTopSpeedValue =  createLabel(panel, String.valueOf(topSpeed)+" KM/h", tahomaItalic, rightColLowerLblBound, yLowerLblPeriod, 3);
		lblAccelerationValue = createLabel(panel, String.valueOf(acceleration), tahomaItalic, rightColLowerLblBound, yLowerLblPeriod, 4);
		lblEngineInertiaValue = createLabel(panel, String.valueOf(acceleration), tahomaItalic, rightColLowerLblBound, yLowerLblPeriod, 5);
	
	}

	static JLabel createLabel (JPanel parentPanel, String lblText, 
							 Font lblFont, Bound bounds, int yPeriod, int position) {
		
		JLabel lblName = new JLabel(lblText);
		lblName.setFont(lblFont);
		lblName.setBounds(bounds.x, bounds.y + yPeriod * position, 
				bounds.width, bounds.height);
		parentPanel.add(lblName);
		
		return lblName;
	}

	static void addComboBox() {
		ComboBoxActionListener listener = new ComboBoxActionListener();
		
		comboBox = new JComboBox(comboBoxElements);
		comboBox.setBounds(42, 18, 273, 21);
		panel.add(comboBox);
		comboBox.addActionListener(listener);
	}
	
	static void addMainPanelFields() {
		MainPanelKeyboardListener listener = new MainPanelKeyboardListener();
		
		engineSize = new JTextField(0);
		engineSize.setBounds(171, 78, 144, 19);
		panel.add(engineSize);
		engineSize.setColumns(10);
		engineSize.addKeyListener(listener);

		priceField = new JTextField(0);
		priceField.setBounds(171, 49, 144, 19);
		panel.add(priceField);
		priceField.setColumns(10);
		priceField.addKeyListener(listener);
	
	}
	
	static void addPanels() {
		
		editPanel = new JPanel();
		editPanel.setBounds(0, 0, 360, 263);
		frame.getContentPane().add(editPanel);
		editPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Weight");
		lblNewLabel.setBounds(20, 49, 117, 13);
		editPanel.add(lblNewLabel);
		
		int h = 19;
		lblPriceKoeficient = new JLabel("Price coeficient");
		lblPriceKoeficient.setBounds(20, 72, 98, h);
		editPanel.add(lblPriceKoeficient);
		
		lblPriceRange = new JLabel("Price range");
		lblPriceRange.setBounds(20, 95, 98, h);
		editPanel.add(lblPriceRange);
		
		lblTaxRange = new JLabel("Tax range");
		lblTaxRange.setBounds(20, 118, 98, h);
		editPanel.add(lblTaxRange);
		
		lblTopSpeedRange = new JLabel("Top Speed range");
		lblTopSpeedRange.setBounds(20, 141, 117, h);
		editPanel.add(lblTopSpeedRange);
		
		lblAccelerationRange = new JLabel("Acceleration range");
		lblAccelerationRange.setBounds(20, 164, 117, h);
		editPanel.add(lblAccelerationRange);
		
		lblInertiaRange = new JLabel("Inertia range");
		lblInertiaRange.setBounds(20, 187, 98, h);
		editPanel.add(lblInertiaRange);
		
		JButton btnRemoveCategory = new JButton("Remove");
		btnRemoveCategory.setBounds(20, 233, 145, 21);
		editPanel.add(btnRemoveCategory);
		
		btnCheck = new JButton("Check");
		btnCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isBtnToEdit) {
					writeTxtFieldValuesToHash();
					writeEditDataToConfig();
				}
				checkTxtFieldValidity();
			}
		});
		
		btnCheck.setBounds(195, 232, 140, 21);
		editPanel.add(btnCheck);
		
		

		txtMinPrice = new JTextField();
		txtMinPrice.setColumns(10);
		txtMinPrice.setBounds(160, 95, 66, h);
		editPanel.add(txtMinPrice);
		editPanelMinTxtFields.add(txtMinPrice);
		
		txtMaxPrice = new JTextField();
		txtMaxPrice.setColumns(10);
		txtMaxPrice.setBounds(269, 95, 66, h);
		editPanel.add(txtMaxPrice);
		editPanelMaxTxtFields.add(txtMaxPrice);
		
		txtMinTax = new JTextField();
		txtMinTax.setColumns(10);
		txtMinTax.setBounds(160, 118, 66, h);
		editPanel.add(txtMinTax);
		editPanelMinTxtFields.add(txtMinTax);
		
		txtMaxTax = new JTextField();
		txtMaxTax.setColumns(10);
		txtMaxTax.setBounds(269, 118, 66, h);
		editPanel.add(txtMaxTax);
		editPanelMaxTxtFields.add(txtMaxTax);
		
		txtMinTopSpeed = new JTextField();
		txtMinTopSpeed.setColumns(10);
		txtMinTopSpeed.setBounds(160, 141, 66, h);
		editPanel.add(txtMinTopSpeed);
		editPanelMinTxtFields.add(txtMinTopSpeed);
		
		txtMaxTopSpeed = new JTextField();
		txtMaxTopSpeed.setColumns(10);
		txtMaxTopSpeed.setBounds(269, 141, 66, h);
		editPanel.add(txtMaxTopSpeed);
		editPanelMaxTxtFields.add(txtMaxTopSpeed);
		
		txtMinAcc = new JTextField();
		txtMinAcc.setColumns(10);
		txtMinAcc.setBounds(160, 164, 66, h);
		editPanel.add(txtMinAcc);
		editPanelMinTxtFields.add(txtMinAcc);
		
		txtMaxAcc = new JTextField();
		txtMaxAcc.setColumns(10);
		txtMaxAcc.setBounds(269, 164, 66, h);
		editPanel.add(txtMaxAcc);
		editPanelMaxTxtFields.add(txtMaxAcc);
		
		txtMaxInertia = new JTextField();
		txtMaxInertia.setColumns(10);
		txtMaxInertia.setBounds(160, 187, 66, h);
		editPanel.add(txtMaxInertia);
		editPanelMinTxtFields.add(txtMaxInertia);
		
		txtMinInertia = new JTextField();
		txtMinInertia.setColumns(10);
		txtMinInertia.setBounds(269, 187, 66, h);
		editPanel.add(txtMinInertia);
		editPanelMaxTxtFields.add(txtMinInertia);
		
		// to
		
		lblTo1 = new JLabel("to");
		lblTo1.setBounds(246, 95, 13, h);
		editPanel.add(lblTo1);
		
		lblTo2 = new JLabel("to");
		lblTo2.setBounds(246, 118, 13, h);
		editPanel.add(lblTo2);
		
		lblTo3 = new JLabel("to");
		lblTo3.setBounds(246, 141, 13, h);
		editPanel.add(lblTo3);
		
		lblTo4 = new JLabel("to");
		lblTo4.setBounds(246, 164, 13, h);
		editPanel.add(lblTo4);
		
		lblTo6 = new JLabel("to");
		lblTo6.setBounds(246, 187, 13, h);
		editPanel.add(lblTo6);
		
		txtPriceKoef = new JTextField();
		txtPriceKoef.setColumns(10);
		txtPriceKoef.setBounds(160, 72, 175, h);
		editPanel.add(txtPriceKoef);
		
		txtWeight = new JTextField();
		txtWeight.setColumns(10);
		txtWeight.setBounds(160, 46, 175, h);
		editPanel.add(txtWeight);
		
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
		addPanels();
		addMainPanelFields();
		addLabels();
		addComboBox();
		frame.setVisible(true);
		editPanel.setVisible(false);
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
			System.out.println();
			System.out.println(e);
			System.out.println(e.getStackTrace()[0]);
			System.out.println();
		}
		
	}
	
	static Double getConfigValue(String key) {
		return categoryValues.get(key);
	}
	
	static void setConfigValuesToIntegers() {
		engineKoef = getConfigValue("engineKoef");
		maxEngineSize  = getConfigValue("maxEngineSize");
		minEngineSize  = getConfigValue("minEngineSize");

//		weight = getConfigValue("weight");
//		priceKoef = getConfigValue("priceKoef");
//		minPrice = getConfigValue("minPrice");
//		maxPrice = getConfigValue("maxPrice");
//		minTax = getConfigValue("minTax");
//		maxTax = getConfigValue("maxTax");
//		minTopSpeed = getConfigValue("minTopSpeed");
//		maxTopSpeed = getConfigValue("maxTopSpeed");
//		minAcc = getConfigValue("minAcc");
//		maxAcc = getConfigValue("maxAcc");
//		minInertia = getConfigValue("minInertia");
//		maxInertia = getConfigValue("maxInertia");

//		for (String i : categoryValues.keySet()) {
//		  System.out.println("key: " + i + " value: " + categoryValues.get(i));
//		}
//		System.out.println();
	}
	
	public static boolean isDoubleRegex(String elem) {
		String regex = ".*\\d.{1}\\..*\\d";
		return Pattern.compile(regex).matcher(elem).find();
	}
	
	public static boolean isIntish(String elem) {
		String regex = ".*\\d";
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
				// why don't I implement another while loop here? Because something is wrong with the way how lines
				// are counted, and I can't figure out what the hell is wrong
				if (line.contains("[Category")) {
					counter++;
					HashMap <String, Double> _objMap = new HashMap<String, Double>();
					for (int i = 0; i < 12; i++) {
						String []_line = scan.nextLine().split("=");
						String key = _line[0].trim();
						Double value = Double.parseDouble(_line[1].trim());
						_objMap.put(key, value);
					}
					categoryList.add(new Category(counter, _objMap));
					categoryNameList.add(line.substring(1, line.length()-1));
					
				}
//				
//				if (line.contains("[Category "+(int) lastCat)) {
//					for (int i = 0; i < 12; i++) {
//						String []_line = scan.nextLine().split("=");
//						String key = _line[0].trim();
//						Double value = Double.parseDouble(_line[1].trim());
//						categoryValues.put(key, value);
//					}
//				}
			}
			comboBoxElements = new String[counter+1];
			categoryNameList.add("Add, edit or remove...");
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
	
	static void changeCategoryVariables(double catNum) {
//		categoryValues = Category.getCategoryValues(categoryList.get((int)catNum));
		Category obj = categoryList.get((int)catNum);
		weight = obj.weight;
		priceKoef = obj.priceKoef;
		
		minPrice = obj.minPrice;
		maxPrice = obj.maxPrice;
		minTax = obj.minTax;
		maxTax = obj.maxTax;
		minTopSpeed = obj.minTopSpeed;
		maxTopSpeed = obj.maxTopSpeed;
		minAcc = obj.minAcc;
		maxAcc = obj.maxAcc;
		minInertia = obj.minInertia;
		maxInertia = obj.maxInertia;
		
		System.out.println(minPrice);
		
//		setConfigValuesToIntegers();
		
//		File file = new File(root+"values.conf");
//		try {
//			Scanner scan = new Scanner(file);
//			while (scan.hasNextLine()) {
//				String line = scan.nextLine();
//				if (line.contains("[Category "+(int)(catNum+1))) {
//					System.out.println("Found line for cat!");
//					for (int i = 0; i < 12; i++) {
//						String []_line = scan.nextLine().split("=");
//						String key = _line[0].trim();
//						Double value = Double.parseDouble(_line[1].trim());
//						categoryValues.replace(key, value);
//					}
//				}
//			}
//			
//			setConfigValuesToIntegers();
//			scan.close();
//		} catch (Exception e) {
//
//		}
	
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
		if (comboBox.getSelectedIndex()+1 == comboBox.getItemCount() && isEditing == false) {
			startCategoryEdit();
		}
		
		changeCategoryVariables(comboBox.getSelectedIndex());
		lastCat = (double)comboBox.getSelectedIndex();
		if (isEditing) {
			writeValuesInTxt();
			return;
		}
		
		checkEngine();
		checkPrice();			
	}
	
	static void startCategoryEdit() {
//		frame.setVisible(false);
		panel.setVisible(false);
		editPanel.setVisible(true);
		isEditing = true;
		comboBox.removeItemAt(comboBox.getItemCount()-1);
		editPanel.add(comboBox);
		
	}
	
	static void writeValuesInTxt() {
		txtMinAcc.setText(String.valueOf(minAcc));
		txtMaxAcc.setText(String.valueOf(maxAcc));
		txtMinInertia.setText(String.valueOf(minInertia));
		txtMaxInertia.setText(String.valueOf(maxInertia));
		txtMaxPrice.setText(String.valueOf(maxPrice));
		txtMinPrice.setText(String.valueOf(minPrice));
		txtMaxTax.setText(String.valueOf(maxTax));
		txtMinTax.setText(String.valueOf(minTax));
		txtMaxTopSpeed.setText(String.valueOf(maxTopSpeed));
		txtMinTopSpeed.setText(String.valueOf(minTopSpeed));
		
		txtPriceKoef.setText(String.valueOf(priceKoef));
		txtWeight.setText(String.valueOf(weight));
		
	}
	
	static void checkTxtFieldValidity() {
		boolean flag = false;
		for (int i = 0; i < editPanelMinTxtFields.size(); i++) {
//			System.out.println("Max field: "+String.valueOf(editPanelMaxTxtFields.get(i).getText()));
//			System.out.println("Min field: "+String.valueOf(editPanelMinTxtFields.get(i).getText()));
			if(isMinAndMaxTxtValid(editPanelMinTxtFields.get(i), 
								   editPanelMaxTxtFields.get(i))) {
				flag = true;
				continue;
			}
			flag = false;
		}
		if (flag) {
			btnCheck.setText("Edit");
			isBtnToEdit = true;
		}
	}
	
	static boolean isMinAndMaxTxtValid(JTextField minValue, JTextField maxValue) {
		String min = minValue.getText();
		String max = maxValue.getText();
		
		if (isDoubleRegex(min) == false && isIntish(min) == false) {
			minValue.setForeground(invalidTextColor);
			return false;
		}
		
		if(isDoubleRegex(max) == false && isIntish(max) == false) {
			maxValue.setForeground(invalidTextColor);
			return false;
		}
		
		if(Double.parseDouble(min) >= Double.parseDouble(max)) {
			maxValue.setForeground(invalidTextColor);
			minValue.setForeground(invalidTextColor);
			return false;
		}
		
		minValue.setForeground(generalTextColor);
		maxValue.setForeground(generalTextColor);
		return true;
	}
	
	static void writeEditDataToConfig() {
		
		File file = new File(root+"values.conf");
		try {
			FileWriter fw = new FileWriter(file.getAbsolutePath(), addTo);
			Scanner scan = new Scanner(file);
			int comboBoxIndex = comboBox.getSelectedIndex()+1;
			System.out.println(comboBoxIndex);
			while (scan.hasNextLine()) {
//				System.out.println("scanning #1");
				String line = scan.nextLine();
//				System.out.println(line);
				if (line.contains("[Category "+comboBoxIndex)) {
					System.out.println("FOUND LINE TO EDIT!");
					for (int i = 0; i < 13; i++) {
						// issue lies, that it writes at the end of the file!
						String []_line = scan.nextLine().split("=");
						String key = _line[0].trim();
//						Double value = Double.parseDouble(_line[1].trim());
						String toReplace = key + " = " + categoryValues.get(key);
						System.out.println(toReplace);
//						fw.append(key + " = " + categoryValues.get(key));
					}
				}
			}
			scan.close();
			fw.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	static void writeTxtFieldValuesToHash() {
		// I could fix this mess by making an extremely crude string operator system and loops
		// Note on the last comment - you cannot, might go with using extend JTextField
		categoryValues.replace("weight", Double.parseDouble(txtWeight.getText()));
		categoryValues.replace("priceKoef", Double.parseDouble(txtPriceKoef.getText()));
		categoryValues.replace("minPrice", Double.parseDouble(txtMinPrice.getText()));
		categoryValues.replace("maxPrice", Double.parseDouble(txtMaxPrice.getText()));
		categoryValues.replace("maxTax", Double.parseDouble(txtMaxTax.getText()));
		categoryValues.replace("minTopSpeed", Double.parseDouble(txtMinTopSpeed.getText()));
		categoryValues.replace("maxTopSpeed", Double.parseDouble(txtMaxTopSpeed.getText()));
		categoryValues.replace("minAcc", Double.parseDouble(txtMinAcc.getText()));
		categoryValues.replace("maxAcc", Double.parseDouble(txtMaxAcc.getText()));
		categoryValues.replace("minInertia", Double.parseDouble(txtMinInertia.getText()));
		categoryValues.replace("maxInertia", Double.parseDouble(txtMaxInertia.getText()));
	}
	
	
	
}
