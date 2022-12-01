package testing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileWriter;

import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main {
//	static String root = System.getProperty("user.dir") + "\\src\\testing\\"; // for widnows

	static String root = System.getProperty("user.dir") + "/src/testing/";
	
	static ImageIcon img = new ImageIcon(root+"icon.png");

	public static Font tahomaBold = new Font("Tahoma", Font.BOLD, 10);
	public static Font tahomaItalic = new Font("Tahoma", Font.ITALIC, 10);
	
	public static Bound leftColLowerLblBound = new Bound(42, 123, 144, 13);
	public static Bound leftColUpperLblBound = new Bound(42, 52, 144, 13);
	public static Bound rightColLowerLblBound = new Bound(171, 123, 144, 13);
	 
	public static Bound editPanelMainLblBound = new Bound(20, 49, 117, 13);
	public static Bound editPanelTxtLeftColBound = new Bound(160, 69+23, 66, 19);
	public static Bound editPanelToLblBound = new Bound(240, 95, 13, 19);
	public static Bound editPanelTxtRightColBound = new Bound(269, 69+23, 66, 19);
	
	static Rectangle mainComboBoxRec = new Rectangle (42, 18, 273, 21);
	static Rectangle editComboBoxRec = new Rectangle (61, 18, 229, 21);
	
	public static Color invalidTextColor = Color.red;
	public static Color generalTextColor = Color.black;

	static boolean addTo = true;
	static boolean overwrite = false;
	public static boolean isEngineSizeSafe;
	public static boolean isPriceSafe;
	public static boolean isEditing = false;
	public static boolean isBtnToEdit = false;
	public static boolean isInitialized = false;
	public static boolean isAdding = false;
	public static boolean isRemovable = true;
	public static double lastCat; // double instead of int, otherwise I will need to code more in one of the methods
	
	// GUI
	
	static JFrame frame;
	static JPanel mainPanel;
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
	
	private static JLabel lblWeight;
	
	private static JLabel lblPrice;
	private static JLabel lblEngineSize;
	
	static JButton btnEdit;
	static JButton btnRemoveCategory;
	
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
	
	// why would I add these next 2?
	// now I rembember - is for when they are looking if min > max, because alphabetical order
	static ArrayList<JTextField> minTxtList = new ArrayList<JTextField>(); 
	static ArrayList<JTextField> maxTxtList = new ArrayList<JTextField>(); 
	
	
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
	private static JLabel lblTo5;
	private static JButton btnGlobalSettings;

	
	public static void main(String[] args) {
		System.out.println(root);
		initializeStart();
		startGUI();	
		System.out.println(categoryList);
	}
	
	static void addLabels() {
		int lowerPeriod = 23;
		int upperPeriod = 29;
		
		lblPrice = createLabel(mainPanel,"Car price:", tahomaBold, leftColUpperLblBound, upperPeriod, 0);
		lblEngineSize = createLabel(mainPanel, "Engine size (*.*L):", tahomaBold, leftColUpperLblBound, upperPeriod, 1);
		
		lblOriginalPrice = createLabel(mainPanel, "Original Price", tahomaBold, leftColLowerLblBound, lowerPeriod, 0);
		lblGamePrice =  createLabel(mainPanel, "In-game Price", tahomaBold, leftColLowerLblBound, lowerPeriod, 1);
		lblTax = createLabel(mainPanel, "Tax", tahomaBold, leftColLowerLblBound, lowerPeriod, 2);
		lblTopSpeed = createLabel(mainPanel, "Top Speed", tahomaBold, leftColLowerLblBound, lowerPeriod, 3);
		lblAcceleration = createLabel(mainPanel, "Acceleration", tahomaBold, leftColLowerLblBound, lowerPeriod, 4);
		lblEngineInertia = createLabel(mainPanel, "Engine inertia", tahomaBold, leftColLowerLblBound, lowerPeriod, 5);

//		You might ask why the names so weird? In case I decide to make a saving system,
//		where you could see the old values after closing the app.
		
		lblOriginalPriceValue = createLabel(mainPanel, "$ "+String.valueOf(originalPrice), tahomaItalic, rightColLowerLblBound, lowerPeriod, 0);
		lblGamePriceValue =  createLabel(mainPanel, "$ "+String.valueOf(gamePrice), tahomaItalic, rightColLowerLblBound, lowerPeriod, 1);
		lblTaxValue =  createLabel(mainPanel, "$ "+String.valueOf(tax), tahomaItalic, rightColLowerLblBound, lowerPeriod, 2);
		lblTopSpeedValue =  createLabel(mainPanel, String.valueOf(topSpeed)+" KM/h", tahomaItalic, rightColLowerLblBound, lowerPeriod, 3);
		lblAccelerationValue = createLabel(mainPanel, String.valueOf(acceleration), tahomaItalic, rightColLowerLblBound, lowerPeriod, 4);
		lblEngineInertiaValue = createLabel(mainPanel, String.valueOf(acceleration), tahomaItalic, rightColLowerLblBound, lowerPeriod, 5);
	
// 		###########################################################################################	
//		EditPanel labels - for now they will be initialized with main ones #######
		// temporarily placed here, until I sort methods out
		
		int editPanelPeriod = 26;
		lblWeight = createLabel(editPanel, "Weight", tahomaBold, editPanelMainLblBound, editPanelPeriod, 0);
		lblPriceKoeficient = createLabel(editPanel, "Price Coeficient", tahomaBold, editPanelMainLblBound, editPanelPeriod, 1);
		lblPriceRange = createLabel(editPanel, "Price range", tahomaBold, editPanelMainLblBound, editPanelPeriod, 2);
		lblTaxRange = createLabel(editPanel, "Tax range", tahomaBold, editPanelMainLblBound, editPanelPeriod, 3);
		lblTopSpeedRange = createLabel(editPanel, "Top Speed range", tahomaBold, editPanelMainLblBound, editPanelPeriod, 4);
		lblAccelerationRange = createLabel(editPanel, "Acceleration range", tahomaBold, editPanelMainLblBound, editPanelPeriod, 5);
		lblInertiaRange = createLabel(editPanel, "Inertia range", tahomaBold, editPanelMainLblBound, editPanelPeriod, 6);
		
		EditTxtFieldPanelListener listener = new EditTxtFieldPanelListener();
		
		txtMinPrice = createTxtField(editPanel, minTxtList, 1, listener, editPanelTxtLeftColBound, editPanelPeriod, 0);
		txtMinTax = createTxtField(editPanel, minTxtList, 1, listener, editPanelTxtLeftColBound, editPanelPeriod,1);
		txtMinTopSpeed = createTxtField(editPanel, minTxtList, 1, listener, editPanelTxtLeftColBound, editPanelPeriod, 2);
		txtMinAcc = createTxtField(editPanel, minTxtList, 1, listener, editPanelTxtLeftColBound, editPanelPeriod, 3);
		txtMinInertia = createTxtField(editPanel, minTxtList, 1, listener, editPanelTxtLeftColBound, editPanelPeriod, 4);
		
		txtMaxPrice = createTxtField(editPanel, maxTxtList, 1, listener, editPanelTxtRightColBound, editPanelPeriod, 0);
		txtMaxTax = createTxtField(editPanel, maxTxtList, 1, listener, editPanelTxtRightColBound, editPanelPeriod, 1);
		txtMaxTopSpeed	= createTxtField(editPanel, maxTxtList, 1, listener, editPanelTxtRightColBound, editPanelPeriod, 2);	
		txtMaxAcc = createTxtField(editPanel, maxTxtList, 1, listener, editPanelTxtRightColBound, editPanelPeriod, 3);
		txtMaxInertia = createTxtField(editPanel, maxTxtList, 1, listener, editPanelTxtRightColBound, editPanelPeriod, 4);
		
		lblTo1 = createLabel(editPanel, "to", tahomaBold, editPanelToLblBound, editPanelPeriod, 0);
		lblTo2 = createLabel(editPanel, "to", tahomaBold, editPanelToLblBound, editPanelPeriod, 1);
		lblTo3 = createLabel(editPanel, "to", tahomaBold, editPanelToLblBound, editPanelPeriod, 2);
		lblTo4 = createLabel(editPanel, "to", tahomaBold, editPanelToLblBound, editPanelPeriod, 3);
		lblTo5 = createLabel(editPanel, "to", tahomaBold, editPanelToLblBound, editPanelPeriod, 4);
		
		txtPriceKoef = new JTextField();
		txtPriceKoef.setColumns(10);
		txtPriceKoef.setBounds(160, 69, 175, 19);
		editPanel.add(txtPriceKoef);
		
		txtWeight = new JTextField();
		txtWeight.setColumns(10);
		txtWeight.setBounds(160, 46, 175, 19);
		editPanel.add(txtWeight);

	}

	static JLabel createLabel (JPanel parentPanel, String lblText, 
							 Font lblFont, Bound bounds, int yPeriod, int position) {
		
		JLabel lblName = new JLabel(lblText);
		lblName.setFont(lblFont);
		lblName.setBounds(bounds.x,
						  bounds.y + yPeriod * position,
						  bounds.width, 
						  bounds.height);
		parentPanel.add(lblName);
		
		return lblName;
	}
	
	static JTextField createTxtField(JPanel parentPanel, ArrayList<JTextField> list, int columns,
									  EditTxtFieldPanelListener listener, Bound bounds, 
									  int yPeriod, int pos) {
		JTextField txtField  = new JTextField();
		txtField.setColumns(columns);
		txtField.setBounds(bounds.x,
						   bounds.y + yPeriod * pos,
						   bounds.width,
						   bounds.height);
		parentPanel.add(txtField);
		list.add(txtField);
		txtField.addKeyListener(listener);
		return txtField;
		
	}
	static void addButtons() {

		btnRemoveCategory = new JButton("Remove");
		btnRemoveCategory.setBounds(20, 233, 145, 21);
		btnRemoveCategory.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				removeButtonAction();
			}
		});
		editPanel.add(btnRemoveCategory);
		
		btnEdit = new JButton("Edit");
		btnEdit.setEnabled(false);
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editButtonAction();
			}
		});
		
		btnEdit.setBounds(195, 232, 140, 21);
		editPanel.add(btnEdit);
		
		JButton btnBack = new JButton("<");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				backButtonAction();	
			}
		});
		btnBack.setBounds(20, 18, 41, 21);
		editPanel.add(btnBack);
		btnGlobalSettings = new JButton("G");
		btnGlobalSettings.setBounds(289, 18, 45, 21);
		editPanel.add(btnGlobalSettings);
	}
	
	static void editButtonAction() {
		if (isBtnToEdit) {
			writeTxtToObj();
			writeEditDataToConfig();
		}
		checkTxtFieldValidity();
	}
	
	static void backButtonAction() {
		isEditing = false;
		editPanel.setVisible(false);
		mainPanel.setVisible(true);
		removeComboBox();
		fillComboBox();
		addComboBox();
		comboBox.setSelectedIndex(0);
		
		/*
		isEditing = true;
		mainPanel.setVisible(false);
		editPanel.setVisible(true);
		addButtons();
		removeComboBox();
		fillComboBox();
		addComboBox();
		comboBox.setBounds(editComboBoxRec); // COMBOBOX BOUND CHANGE!!
//		comboBox.removeItemAt(comboBox.getItemCount()-1);
		comboBox.setSelectedIndex(0);
		System.out.println(comboBoxElements[comboBoxElements.length-1]);
		*/
	}
	
	static void removeButtonAction() {
		int index = comboBox.getSelectedIndex();
		JOptionPane.showConfirmDialog(null,
	             "Do you really want to remove"+Category.all.get(index).name, "Promt of removal!",  
	             JOptionPane.YES_NO_OPTION);
	}

	static void addComboBox() {
		ComboBoxActionListener listener = new ComboBoxActionListener();
		comboBox = new JComboBox<String>(comboBoxElements);
		comboBox.addActionListener(listener); 
		if (isEditing) {
			comboBox.setBounds(editComboBoxRec);
			editPanel.add(comboBox);
			return;
		}
		mainPanel.add(comboBox);
		comboBox.setBounds(mainComboBoxRec);
	}
	
	static void removeComboBox() {
		editPanel.remove(comboBox);
		mainPanel.remove(comboBox);
	}
	
	static void addMainPanelFields() {
		MainPanelKeyboardListener listener = new MainPanelKeyboardListener();
		
		engineSize = new JTextField(0);
		engineSize.setBounds(171, 78, 144, 19);
		mainPanel.add(engineSize);
		engineSize.setColumns(10);
		engineSize.addKeyListener(listener);

		priceField = new JTextField(0);
		priceField.setBounds(171, 49, 144, 19);
		mainPanel.add(priceField);
		priceField.setColumns(10);
		priceField.addKeyListener(listener);
	
	}
	
	static void addPanels() {
		
		editPanel = new JPanel();
		editPanel.setBounds(0, 0, 360, 263);
		frame.getContentPane().add(editPanel);
		editPanel.setLayout(null);
		
		mainPanel = new JPanel();
		mainPanel.setBounds(0, 0, 360, 263);
		frame.getContentPane().add(mainPanel);
		mainPanel.setLayout(null);
		
	}
	
	static void makeFrame() {
		frame = new JFrame("VehLib Calculator");
        frame.setBounds(0,0, 374, 300);
        frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setIconImage(img.getImage());
		
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
			engineInertia = (maxInertia + ((minInertia - maxInertia) * internalPercent / 2));
			
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
				double lastCat = categoryValues.get("lastCat"); // this could have use

				if (line.contains("[Category")) {
					counter++;
					HashMap <String, Double> _objMap = new HashMap<String, Double>();
					for (int i = 0; i < 12; i++) {
						String []_line = scan.nextLine().split("=");
						String key = _line[0].trim();
						Double value = Double.parseDouble(_line[1].trim());
						_objMap.put(key, value);
					}
					new Category(counter, _objMap);
					categoryNameList.add(line.substring(1, line.length()-1));
					
				}
			}
			fillComboBox();
			
			scan.close();
			setConfigValuesToIntegers();
			changeCategoryVariables(lastCat);
			} catch (Exception e) {
			System.out.println("ERROR: INITIALIZATION!");
			System.out.println(e);
			System.out.println(e.getStackTrace()[0]);
			System.out.println();
		}	
		Category.printAll();
		isInitialized = true;
	}
	
	static void fillComboBox() {
		int size = Category.all.size();
		comboBoxElements = new String[size+1];
		System.out.println("Length: "+comboBoxElements.length);
		for (int i = 0; i < comboBoxElements.length-1; i++) {
			comboBoxElements[i] = Category.all.get(i).name;
		}
		if (isEditing) {
			comboBoxElements[comboBoxElements.length-1] = ("Add new");
			return;
		}
		comboBoxElements[comboBoxElements.length-1] = ("Add, edit or remove...");
	}
	
	static void changeCategoryVariables(double catNum) {
		Category obj = Category.all.get((int)catNum);
		System.out.println("     Changing variables to "+obj.name);
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
	static boolean isLastElementAndIsEditing(boolean editing) {
		return comboBox.getSelectedIndex()+1 == comboBox.getItemCount() && isEditing == editing;
	}
	
	static boolean isNotLastElementAndIsEditing(boolean editing) {
		return comboBox.getSelectedIndex()+1 != comboBox.getItemCount() && isEditing == editing;
	}
	

	
	public static void changeCategories() {
		if (isLastElementAndIsEditing(false)) {
			isRemovable = true;	
			System.out.println("Changing panel to edit.");
			changePanelToEdit();
			changeCategoryVariables(comboBox.getSelectedIndex());
		}
		if (isLastElementAndIsEditing(true)) {
			System.out.println("Starting new cat input.");
			isRemovable = false;
			startNewCategoryInput();
		}
		
		System.out.println("# selected + 1 = "+(comboBox.getSelectedIndex()+1));
		System.out.println("# item counts = "+comboBox.getItemCount());
		System.out.println("# is editing = "+isEditing);
		
		if (isNotLastElementAndIsEditing(true)) {
			changeCategoryVariables(comboBox.getSelectedIndex());
			writeValuesInTxt();
		}
		
		if (isNotLastElementAndIsEditing(false)) {
			changeCategoryVariables(comboBox.getSelectedIndex());
		}
		
		lastCat = (double)comboBox.getSelectedIndex();
		checkEngine();
		checkPrice();			
	}
	
	static void changePanelToEdit() {
		isEditing = true;
		mainPanel.setVisible(false);
		editPanel.setVisible(true);
		addButtons();
		removeComboBox();
		fillComboBox();
		addComboBox();
		comboBox.setSelectedIndex(0);
		System.out.println(comboBoxElements[comboBoxElements.length-1]);
		
	}
	
	static void startNewCategoryInput() {
		clearTxtFields();
		editBtnToAddBtn();
		btnRemoveCategory.setEnabled(isRemovable);
	}
	
	static void clearTxtFields() {
		txtMinAcc.setText(null);
		txtMaxAcc.setText(null);
		txtMinInertia.setText(null);
		txtMaxInertia.setText(null);
		txtMaxPrice.setText(null);
		txtMinPrice.setText(null);
		txtMaxTax.setText(null);
		txtMinTax.setText(null);
		txtMaxTopSpeed.setText(null);
		txtMinTopSpeed.setText(null);
		txtPriceKoef.setText(null);
		txtWeight.setText(null);
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
	
	static void editBtnToAddBtn() {
		btnEdit.setText("Add");
		isAdding = true;
	}
	
	static void checkTxtFieldValidity() {
		boolean flag = false;
		for (int i = 0; i < minTxtList.size(); i++) {
			if(isMinAndMaxTxtValid(minTxtList.get(i), 
								   maxTxtList.get(i))) {
				flag = true;
				continue;
			}
			flag = false;
		}
		if (flag) {
			isBtnToEdit = true;
		}
		btnEdit.setEnabled(flag);
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
			FileWriter fw = new FileWriter(file.getAbsoluteFile(), overwrite);
			Scanner scan = new Scanner(file);

			System.out.println("startin to write");
			fw.write("[Global settings]"+"\n");
			fw.write("lastCat  = "+categoryValues.get("lastCat")+"\n");
			fw.write("engineKoef = "+categoryValues.get("engineKoef")+"\n");
			fw.write("minEngineSize = "+categoryValues.get("minEngineSize")+"\n");
			fw.write("maxEngineSize = "+categoryValues.get("maxEngineSize")+"\n");
			fw.write("\n");
				
			for (int i = 0;i+1 < comboBox.getItemCount()+1; i++) {
				Category cat = Category.all.get(i);
				fw.write("["+cat.name+"]"+"\n");
				fw.write("weight = "+cat.weight+"\n");
				fw.write("priceKoef = "+cat.priceKoef+"\n");
				fw.write("minPrice = "+cat.minPrice+"\n");
				fw.write("maxPrice = "+cat.maxPrice+"\n");
				fw.write("minTax = "+cat.minTax+"\n");
				fw.write("maxTax = "+cat.maxTax+"\n");
				fw.write("minTopSpeed = "+cat.minTopSpeed+"\n");
				fw.write("maxTopSpeed = "+cat.maxTopSpeed+"\n");
				fw.write("minAcc = "+cat.minAcc+"\n");
				fw.write("maxAcc = "+cat.maxAcc+"\n");
				fw.write("minInertia = "+cat.minInertia+"\n");
				fw.write("maxInertia = "+cat.maxInertia+"\n");
				fw.write("\n");				
			}
			fw.close();
			scan.close();
		} catch (Exception e) {
			System.out.println("Bad!");
			System.out.println();
			System.out.println(e);
			System.out.println(e.getStackTrace()[0]);
			System.out.println();
		}

	}
	
	static void writeTxtToObj() {
		// this is bad, I know
		int index = comboBox.getSelectedIndex();
		Category.all.get(index).weight = Double.parseDouble(txtWeight.getText());
		Category.all.get(index).priceKoef = Double.parseDouble(txtPriceKoef.getText());
		Category.all.get(index).minPrice = Double.parseDouble(txtMinPrice.getText());
		Category.all.get(index).maxPrice = Double.parseDouble(txtMaxPrice.getText());
		Category.all.get(index).minTax = Double.parseDouble(txtMinTax.getText());
		Category.all.get(index).maxTax = Double.parseDouble(txtMaxTax.getText());
		Category.all.get(index).minTopSpeed = Double.parseDouble(txtMinTopSpeed.getText());
		Category.all.get(index).maxTopSpeed = Double.parseDouble(txtMaxTopSpeed.getText());
		Category.all.get(index).minAcc = Double.parseDouble(txtMinAcc.getText());
		Category.all.get(index).maxAcc = Double.parseDouble(txtMaxAcc.getText());
		Category.all.get(index).minInertia = Double.parseDouble(txtMinInertia.getText());
		Category.all.get(index).maxInertia = Double.parseDouble(txtMaxInertia.getText());
		Category.all.get(index).name = "Category "+(index+1)
				+" ("+txtMinPrice.getText()+"-"+txtMaxPrice.getText()+")";

	}
}
