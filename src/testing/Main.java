package testing;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Main implements KeyListener, ActionListener  {
	static String root = System.getProperty("user.dir") + "/src/testing/";
	static ImageIcon img = new ImageIcon(root+"icon.png");
	static Main listener = new Main();
	
	static File configFile = new File(root+"values.conf");
	
	// Fonts
	public static Font tahomaBold = new Font("Tahoma", Font.BOLD, 10);
	public static Font tahomaItalic = new Font("Tahoma", Font.ITALIC, 10);

	// Rectangles

	
	// Colors
	public static Color invalidTextColor = Color.red;
	public static Color generalTextColor = Color.black;

	// Flags
	static boolean addTo = true;
	static boolean overwrite = false;
	static boolean printDebugs = false;
	static boolean isCreatingNewConfig = false;
	public static boolean isEngineSizeSafe;
	public static boolean isPriceSafe;
	public static boolean isEditing = false;
	public static boolean isEditingGlobal = false;
	public static boolean isEditBtnEditing = true;
	public static boolean isInitialized = false;
	public static boolean isRemovable = true;
	public static boolean isEditInitialized = false;
	
	// double instead of int, otherwise I will need to code more in one of the methods
	public static double lastCat; 
	
	// GUI
	static JFrame frame;
	public static JComboBox comboBox;
	static String[] comboBoxElements;
	static Rectangle mainComboBoxRec = new Rectangle (42, 18, 273, 21);
	static Rectangle editComboBoxRec = new Rectangle (61, 18, 229, 21);
	static Rectangle frameRec = new Rectangle (0,0, 374, 300);
	static Rectangle panelRec = new Rectangle (0, 0, 360, 263);
	
	// Main Panel
	public static Bound leftColLowerLblBound = new Bound(42, 123, 144, 13);
	public static Bound leftColUpperLblBound = new Bound(42, 52, 144, 13);
	public static Bound rightColLowerLblBound = new Bound(171, 123, 144, 13);
	public static Bound mainTxtFieldBound = new Bound(171, 49, 144, 19);
	
	public static double inputPrice;
	public static double inputEngineSize;
	 
	static JPanel mainPanel;

	static JTextField txtEngineSize;
	static JTextField txtPriceField;
	
	private static JLabel lblPrice;
	private static JLabel lblEngineSize;
	
	private static JLabel lblOriginalPriceHeader;
	private static JLabel lblGamePriceHeader;
	private static JLabel lblTaxHeader;
	private static JLabel lblTopSpeedHeader;
	private static JLabel lblAccelerationHeader;
	private static JLabel lblEngineInertiaHeader;
	
	private static JLabel lblOriginalPriceValue;
	private static JLabel lblGamePriceValue;
	private static JLabel lblTaxValue;
	private static JLabel lblTopSpeedValue;
	private static JLabel lblAccelerationValue;
	private static JLabel lblEngineInertiaValue;
	
	// Edit Panel
	public static Bound editPanelMainLblBound = new Bound(20, 49, 117, 13);
	public static Bound editPanelToLblBound = new Bound(240, 95, 13, 19);
	public static Bound editPanelTxtLeftColBound = new Bound(160, 69+26, 66, 19);
	public static Bound editPanelTxtRightColBound = new Bound(269, 69+26, 66, 19);
	public static Bound editPanelTxtUpperBound = new Bound(160, 46, 175, 19);
	static int editPanelPeriod = 26;
	
	static JPanel editPanel;
	
	private static JLabel lblWeight;
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
	 
	static JButton btnEdit;
	static JButton btnEditRemoveCategory;
	static JButton btnEditBack; 
	
	// Global Settings Panel
	public static Bound globalSettingPanelMainLblBound = new Bound (20, 60, 140, 20);
	public static Bound globalSettingPanelTxtBound = new Bound (195, 60, 140, 20);
	
	static JPanel globalSettingsPanel;
	
	static JLabel lblEngineKoef;
	static JLabel lblMinEngineSize;
	static JLabel lblMaxEngineSize;
	
	static JTextField txtEngineKoef;
	static JTextField txtMinEngineSize;
	static JTextField txtMaxEngineSize;
	
	static JButton btnGlobalBack;
	static JButton btnGlobalEdit;
	static JButton btnGlobalSettings;
	
	// Mathematical variables
	private static double originalPrice = 0;
	private static double gamePrice = 0;
	private static double tax = 0;
	private static double topSpeed = 0;
	private static double acceleration = 0;
	private static double engineInertia = 0;
	
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
	
	// Lists & maps
	static HashMap<String, Double> globalSettingValues = new HashMap<String, Double>();
	
	static ArrayList<String> categoryNameList = new ArrayList<String>(); // is this redundant?

	static ArrayList<JTextField> minEditTxtList = new ArrayList<JTextField>(); 
	static ArrayList<JTextField> maxEditTxtList = new ArrayList<JTextField>(); 
	
	static ArrayList<JTextField> mainTxtList = new ArrayList<JTextField>(); // check if sizes are good
	static ArrayList<JTextField> editTxtMainList = new ArrayList<JTextField>(); // check if numeral
	static ArrayList<JTextField> editTxtRangesList = new ArrayList<JTextField>(); // check if numeral and if min < max
	static ArrayList<JTextField> globalTxtList = new ArrayList<JTextField>(); // check if numeral


	public static void main(String[] args) {
		System.out.println(root); // DEBUG
		readConfig();
	}
	
	// Methods
	static void startGUI() {
		createFrame();
		addMainPanel();
		addMainPanelElements();
		addEditPanel();
		addEditPanelElements();
		editPanel.setVisible(false);
		fillComboBox();
		addComboBox();
		comboBox.setSelectedIndex((int) lastCat);
	}
	
	// Events
	public void keyReleased(KeyEvent e) {
		JTextField source = (JTextField) e.getSource();
		if (mainTxtList.contains(source)) {
			checkMainTxtFields();
		}
		if(editTxtMainList.contains(source) ||
		   editTxtRangesList.contains(source)) {
			btnEdit.setEnabled(areEditTxtFieldsValid());
			checkEditTxtFieldValidity();
		}
		if(globalTxtList.contains(source)) {
			btnGlobalEdit.setEnabled(areTxtListNumerics(globalTxtList));
		}
	}
	
	
	// Events
	public void actionPerformed(ActionEvent e) { //
		try { // the use of try/catch here is due to different sources
			if (e.getSource() instanceof JButton) {
				JButton btnSource = (JButton) e.getSource();
				if (btnSource == btnEditBack) 			editBackButtonAction();
				if (btnSource == btnEdit) 				editButtonAction();
				if (btnSource == btnEditRemoveCategory) editRemoveButtonAction();
				if (btnSource == btnGlobalSettings)		editOpenGlobalSettingsButtonAction();
				if (btnSource == btnGlobalBack) 		globalSettingBackButtonAction();
				if (btnSource == btnGlobalEdit)			globalSettingEditButtonAction();
				
			}
			if (e.getSource() instanceof JComboBox) {
				JComboBox comboBoxSource = (JComboBox) e.getSource();
				if (comboBoxSource == comboBox) 		tryCategoryChange();
			}
			
		} catch (Exception e2) {
		}
	}
	
	static void checkMainTxtFields() {
		checkPriceField();
		checkEngineSizeField();
	}
	
	static void checkPriceField() {
		checkTxtLimits(txtPriceField, inputPrice, minPrice, maxPrice, isPriceSafe);
		tryMath();
	}
	
	static void checkEngineSizeField() {
		checkTxtLimits(txtEngineSize, inputEngineSize, minEngineSize, maxEngineSize, isEngineSizeSafe);
		tryMath();
	}
	
	static void tryCategoryChange() {
		if ((int)lastCat != comboBox.getSelectedIndex()) 
			changeCategories();
	}
	
	public static void changeCategories() {
		updateLastCat();
		System.out.println("lastCat: "+lastCat);
		if (isLastElementAndIsEditing(false)) {
			System.out.println("Changing panel to edit.");
			changePanelToEdit();
			changeCategoryVariables(comboBox.getSelectedIndex());
		}
		
		if (isNotLastElementAndIsEditing(false)) {
			changeCategoryVariables(comboBox.getSelectedIndex());
		}
		
		if (isLastElementAndIsEditing(true)) {
			System.out.println("Starting new cat input.");
			startNewCategoryInput();
		}
		
		if (isNotLastElementAndIsEditing(true)) {
			btnEditRemoveCategory.setEnabled(isRemovable);
			changeCategoryVariables(comboBox.getSelectedIndex());
			setValuesToTxt();
		}
		lastCat = (double)comboBox.getSelectedIndex();
		checkPriceField();
		checkEngineSizeField();
	}
	
	static void changePanelToEdit() {
		isEditing = true;
		isRemovable = true;
		if (comboBox.getItemCount() == 2) isRemovable = false;
		swapToPanel(editPanel);
		refreshComboBox();
		comboBox.setSelectedIndex(0);
		System.out.println(comboBoxElements[comboBoxElements.length-1]);
	}
	
	// Creator functions
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
		
	static JTextField createTxtField(JPanel parentPanel, ArrayList<JTextField> groupList, int columns,
							  Main listener, Bound bounds, 
							  int yPeriod, int pos) {
		JTextField txtField  = new JTextField();
		txtField.setColumns(columns);
		txtField.setBounds(bounds.x,
						   bounds.y + yPeriod * pos,
						   bounds.width,
						   bounds.height);
		parentPanel.add(txtField);
		if (groupList != null) groupList.add(txtField);
		txtField.addKeyListener(listener);
		return txtField;
	}
	

	// GUI element adding methods
	
	static void createFrame() {
		frame = new JFrame("VehLib Calculator");
        frame.setBounds(frameRec);
        frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setIconImage(img.getImage());
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
	}
	
	static void addMainPanel() {
		mainPanel = new JPanel();
		mainPanel.setBounds(panelRec);
		frame.getContentPane().add(mainPanel);
		mainPanel.setLayout(null);
		
	}
	
	static void addMainPanelElements() {
		addMainPanelLabels();
		addMainPanelTxtFields();
	}
	
	static void addMainPanelLabels() {
		int lowerPeriod = 23;
		int upperPeriod = 29;
		
		lblPrice = createLabel(mainPanel,"Car price:", tahomaBold, leftColUpperLblBound, upperPeriod, 0);
		lblEngineSize = createLabel(mainPanel, "Engine size (*.*L):", tahomaBold, leftColUpperLblBound, upperPeriod, 1);
			
		lblOriginalPriceHeader = createLabel(mainPanel, "Original Price", tahomaBold, leftColLowerLblBound, lowerPeriod, 0);
		lblGamePriceHeader =  createLabel(mainPanel, "In-game Price", tahomaBold, leftColLowerLblBound, lowerPeriod, 1);
		lblTaxHeader = createLabel(mainPanel, "Tax", tahomaBold, leftColLowerLblBound, lowerPeriod, 2);
		lblTopSpeedHeader = createLabel(mainPanel, "Top Speed", tahomaBold, leftColLowerLblBound, lowerPeriod, 3);
		lblAccelerationHeader = createLabel(mainPanel, "Acceleration", tahomaBold, leftColLowerLblBound, lowerPeriod, 4);
		lblEngineInertiaHeader = createLabel(mainPanel, "Engine inertia", tahomaBold, leftColLowerLblBound, lowerPeriod, 5);
		
		lblOriginalPriceValue = createLabel(mainPanel, "$ "+String.valueOf(originalPrice), tahomaItalic, rightColLowerLblBound, lowerPeriod, 0);
		lblGamePriceValue =  createLabel(mainPanel, "$ "+String.valueOf(gamePrice), tahomaItalic, rightColLowerLblBound, lowerPeriod, 1);
		lblTaxValue =  createLabel(mainPanel, "$ "+String.valueOf(tax), tahomaItalic, rightColLowerLblBound, lowerPeriod, 2);
		lblTopSpeedValue =  createLabel(mainPanel, String.valueOf(topSpeed)+" KM/h", tahomaItalic, rightColLowerLblBound, lowerPeriod, 3);
		lblAccelerationValue = createLabel(mainPanel, String.valueOf(acceleration), tahomaItalic, rightColLowerLblBound, lowerPeriod, 4);
		lblEngineInertiaValue = createLabel(mainPanel, String.valueOf(acceleration), tahomaItalic, rightColLowerLblBound, lowerPeriod, 5);
	}
	
	static void addMainPanelTxtFields() {
		int upperPeriod = 29;
		txtPriceField = createTxtField(mainPanel, mainTxtList, 10, listener, mainTxtFieldBound, upperPeriod, 0);
		txtEngineSize = createTxtField(mainPanel, mainTxtList, 10, listener, mainTxtFieldBound, upperPeriod, 1);
	}
	
	static void addEditPanel() {
		editPanel = new JPanel();
		editPanel.setBounds(panelRec);
		frame.getContentPane().add(editPanel);
		editPanel.setLayout(null);
	}
	
	static void addEditPanelElements() {
		addEditPanelButtons();
		addEditPanelLabels();
		addEditPanelTxtFields();
	}

	static void addEditPanelLabels() {
		
		lblWeight = 			createLabel(editPanel, "Weight", tahomaBold, editPanelMainLblBound, editPanelPeriod, 0);
		lblPriceKoeficient = 	createLabel(editPanel, "Price Coeficient", tahomaBold, editPanelMainLblBound, editPanelPeriod, 1);
		lblPriceRange = 		createLabel(editPanel, "Price range", tahomaBold, editPanelMainLblBound, editPanelPeriod, 2);
		lblTaxRange = 			createLabel(editPanel, "Tax range", tahomaBold, editPanelMainLblBound, editPanelPeriod, 3);
		lblTopSpeedRange = 		createLabel(editPanel, "Top Speed range", tahomaBold, editPanelMainLblBound, editPanelPeriod, 4);
		lblAccelerationRange = 	createLabel(editPanel, "Acceleration range", tahomaBold, editPanelMainLblBound, editPanelPeriod, 5);
		lblInertiaRange = 		createLabel(editPanel, "Inertia range", tahomaBold, editPanelMainLblBound, editPanelPeriod, 6);
		
		lblTo1 = createLabel(editPanel, "to", tahomaBold, editPanelToLblBound, editPanelPeriod, 0);
		lblTo2 = createLabel(editPanel, "to", tahomaBold, editPanelToLblBound, editPanelPeriod, 1);
		lblTo3 = createLabel(editPanel, "to", tahomaBold, editPanelToLblBound, editPanelPeriod, 2);
		lblTo4 = createLabel(editPanel, "to", tahomaBold, editPanelToLblBound, editPanelPeriod, 3);
		lblTo5 = createLabel(editPanel, "to", tahomaBold, editPanelToLblBound, editPanelPeriod, 4);

		
	}
	
	static void addEditPanelTxtFields() {
		
		txtMinPrice = 		createTxtField(editPanel, minEditTxtList, 1, listener, editPanelTxtLeftColBound, editPanelPeriod, 0);
		txtMinTax = 		createTxtField(editPanel, minEditTxtList, 1, listener, editPanelTxtLeftColBound, editPanelPeriod, 1);
		txtMinTopSpeed = 	createTxtField(editPanel, minEditTxtList, 1, listener, editPanelTxtLeftColBound, editPanelPeriod, 2);
		txtMinAcc = 		createTxtField(editPanel, minEditTxtList, 1, listener, editPanelTxtLeftColBound, editPanelPeriod, 3);
		txtMinInertia = 	createTxtField(editPanel, minEditTxtList, 1, listener, editPanelTxtLeftColBound, editPanelPeriod, 4);
		
		txtMaxPrice = 		createTxtField(editPanel, maxEditTxtList, 1, listener, editPanelTxtRightColBound, editPanelPeriod, 0);
		txtMaxTax = 		createTxtField(editPanel, maxEditTxtList, 1, listener, editPanelTxtRightColBound, editPanelPeriod, 1);
		txtMaxTopSpeed	= 	createTxtField(editPanel, maxEditTxtList, 1, listener, editPanelTxtRightColBound, editPanelPeriod, 2);	
		txtMaxAcc = 		createTxtField(editPanel, maxEditTxtList, 1, listener, editPanelTxtRightColBound, editPanelPeriod, 3);
		txtMaxInertia = 	createTxtField(editPanel, maxEditTxtList, 1, listener, editPanelTxtRightColBound, editPanelPeriod, 4);
		
		txtWeight = 		createTxtField(editPanel, editTxtMainList, 1, listener, editPanelTxtUpperBound, 23, 0);
		txtPriceKoef = 		createTxtField(editPanel, editTxtMainList, 1, listener, editPanelTxtUpperBound, 23, 1);
		
		editTxtRangesList.addAll(minEditTxtList);
		editTxtRangesList.addAll(maxEditTxtList);
	}

	static void addEditPanelButtons() {
		
		btnEdit = new JButton("Edit");
		btnEdit.addActionListener(listener);
		btnEdit.setBounds(195, 233, 145, 21);
		editPanel.add(btnEdit);
		btnEdit.setEnabled(false);
		
		if (configFile.exists()) {
			btnEditRemoveCategory = new JButton("Remove");
			btnEditRemoveCategory.setBounds(20, 233, 145, 21);
			btnEditRemoveCategory.addActionListener(listener);
			editPanel.add(btnEditRemoveCategory);
			
			btnEditBack = new JButton("<");
			btnEditBack.addActionListener(listener);
			btnEditBack.setBounds(20, 18, 41, 21);
			editPanel.add(btnEditBack);
			
			btnGlobalSettings = new JButton("G");
			btnGlobalSettings.setBounds(289, 18, 45, 21);
			btnGlobalSettings.addActionListener(listener);
			editPanel.add(btnGlobalSettings);
		}
	}
	
	static void addGlobalSettingElements() {
		addGlobalSettingsButtons();
		addGlobalSettingsLabels();
		addGlobalSettingsTxtFields();
	}
	
	static void addGlobalSettingPanel() {
		globalSettingsPanel = new JPanel();
		globalSettingsPanel.setBounds(panelRec);
		frame.getContentPane().add(globalSettingsPanel);
		globalSettingsPanel.setLayout(null);
	}
	
	static void addGlobalSettingsLabels() {
		int upperPeriod = 40;
		lblEngineKoef = 	createLabel(globalSettingsPanel, "Engine coefficient", tahomaBold, globalSettingPanelMainLblBound, upperPeriod, 0);
		lblMinEngineSize = 	createLabel(globalSettingsPanel, "Min engine volume", tahomaBold, globalSettingPanelMainLblBound, upperPeriod, 1);
		lblMaxEngineSize = 	createLabel(globalSettingsPanel, "Max engine volume", tahomaBold, globalSettingPanelMainLblBound, upperPeriod, 2);
	}
	
	static void addGlobalSettingsTxtFields() {
		int upperPeriod = 40;
		txtEngineKoef = 	createTxtField(globalSettingsPanel, globalTxtList, 10, listener, globalSettingPanelTxtBound, upperPeriod, 0);
		txtMinEngineSize = 	createTxtField(globalSettingsPanel, globalTxtList, 10, listener, globalSettingPanelTxtBound, upperPeriod, 1);
		txtMaxEngineSize = 	createTxtField(globalSettingsPanel, globalTxtList, 10, listener, globalSettingPanelTxtBound, upperPeriod, 2);
	}
	
	static void addGlobalSettingsButtons() {
		btnGlobalBack = new JButton("Cancel");
		btnGlobalBack.setEnabled(true);
		btnGlobalBack.addActionListener(listener);
		btnGlobalBack.setBounds(20, 233, 145, 21);
		globalSettingsPanel.add(btnGlobalBack);
		
		btnGlobalEdit = new JButton("Edit");
		btnGlobalEdit.setEnabled(false);
		btnGlobalEdit.addActionListener(listener);
		btnGlobalEdit.setBounds(195, 233, 145, 21);
		globalSettingsPanel.add(btnGlobalEdit);
		
	}
	
	static void editBtnSwap() {
		System.out.println("isBtnToEdit = "+isEditBtnEditing);
		if (isEditBtnEditing) {
			System.out.println("Swapped from Edit to Add");
			btnEdit.setText("Add");
			isEditBtnEditing = false;
			return;
		}
		if (!isEditBtnEditing) {
			System.out.println("Swapped from Add to Edit");
			btnEdit.setText("Edit");
			isEditBtnEditing = true;
			return;
		}
	}
	
	// Button actions
	static void editButtonAction() {
		checkEditTxtFieldValidity();
		if (isEditBtnEditing) {
			setTxtValuesToObject();
			writeEditedDataToConfig();
		}
		if (!isEditBtnEditing) {
			addNewCategory();
			if (isCreatingNewConfig) createNewConfigPart2();;
		}
		refreshComboBox();
	}
	
	static void editBackButtonAction() {
		isEditing = false;
		swapToPanel(mainPanel);
		refreshComboBox();
		comboBox.setSelectedIndex((int)lastCat);
		checkMainTxtFields();
	}
	
	static void editRemoveButtonAction() {
		int index = comboBox.getSelectedIndex();
		int reply = JOptionPane.showConfirmDialog(null,
	             "Do you really want to remove "+Category.all.get(index).name+" ?", "Prompt of removal",  
	             JOptionPane.YES_NO_OPTION);
		if (reply == JOptionPane.YES_OPTION) {
			removeCategory(index);
		}
	}
	
	static void editOpenGlobalSettingsButtonAction() {
		addGlobalSettingPanel();
		swapToPanel(globalSettingsPanel);
		addGlobalSettingsLabels();
		addGlobalSettingsTxtFields();
		addGlobalSettingsButtons();
		setGlobalSettingsTxtFields();
	}
	
	static void globalSettingBackButtonAction() {
		swapToPanel(editPanel);
	}
	
	static void globalSettingEditButtonAction() {
		writeGlobalEditedToMap();
		writeEditedDataToConfig();
		if (btnGlobalEdit.getText().equals("Add")) createNewConfigPart3();
	}

	// ComboBox Functionality Methods
	static void refreshComboBox() {
		removeComboBox();
		fillComboBox();
		addComboBox();
	}
	
	static void addComboBox() {
		comboBox = new JComboBox<String>(comboBoxElements);
		comboBox.addActionListener(listener); 
		setProperComboBoxBounds();
	}
	
	static void removeComboBox() {
		editPanel.remove(comboBox);
		mainPanel.remove(comboBox);
	}
	
	static void fillComboBox() {
		int size = Category.all.size();
		comboBoxElements = new String[size+1];
		for (int i = 0; i < comboBoxElements.length-1; i++) {
			comboBoxElements[i] = Category.all.get(i).name;
		}
		setCorrectLastElement();
	}
	
	static void setProperComboBoxBounds() {
		if (isEditing) {
			comboBox.setBounds(editComboBoxRec);
			editPanel.add(comboBox);
			return;
		}
		mainPanel.add(comboBox);
		comboBox.setBounds(mainComboBoxRec);
	}
	
	static void setCorrectLastElement() {
		if (isEditing) {
			comboBoxElements[comboBoxElements.length-1] = ("Add new");
			return;
		}
		comboBoxElements[comboBoxElements.length-1] = ("Add, edit or remove...");		
	}
	
	static void removeCategory(int index) {
		Category.all.remove(index);
		refreshComboBox();
		comboBox.setSelectedIndex(0);
		writeEditedDataToConfig();
	}
	
	// Panel Switching Methods
	static void goDark() {
		// don't ask, for now I really cannot think of a better way. Might do with arrays and iterating.
		try {
			try {
				mainPanel.setVisible(false);
			} catch (Exception e) {
			}
			try {
				editPanel.setVisible(false);
			} catch (Exception e) {
			}
			try {
				globalSettingsPanel.setVisible(false);			
			} catch (Exception e) {
			}
		} catch (Exception e) {
		}
	}
	
	static void swapToPanel(JPanel panel) {
		goDark();
		panel.setVisible(true);
	}

	// Mathematical methods
	public static void doMath() {
		double methodLocalPrice = 0;
		double methodLocalEki = 0;
		double methodLocalPercent = 0;
		try {
			System.out.println("Input price = " +inputPrice);
			methodLocalPrice = inputPrice - minPrice;
			methodLocalEki = inputEngineSize * (inputPrice * engineKoef / weight);
			methodLocalPercent = methodLocalPrice / (maxPrice - minPrice) * (methodLocalEki);
			
			tax = (minTax + (maxTax - minTax) * methodLocalPercent);
			topSpeed = (minTopSpeed + ((maxTopSpeed - minTopSpeed) * methodLocalPercent));
			acceleration = (minAcc + ((maxAcc - minAcc) * methodLocalPercent));
			engineInertia = (maxInertia + ((minInertia - maxInertia) * methodLocalPercent / 2));
			
			gamePrice = inputPrice + (inputPrice / 100 * priceKoef);
			printMathDebug(printDebugs); // DEBUG TO DELETE PLS
			setResultsToLabels();

		} catch (Exception e) {
			System.out.println("Error in mathematics!");
			System.out.println(e);
			System.out.println(e.getStackTrace()[0]);
			System.out.println();
		}
	}

	static void tryMath() {
		if (isEngineSizeSafe && isPriceSafe) {
			doMath();
		}
	}
	
	// Value operation methods
	static void setResultsToLabels() {
		lblOriginalPriceValue.setText("$ "+String.valueOf(inputPrice));
		lblGamePriceValue.setText("$ "+String.valueOf(gamePrice));
		lblTaxValue.setText("$ "+String.valueOf(tax));
		lblTopSpeedValue.setText(String.valueOf(topSpeed)+" KM/h");
		lblAccelerationValue.setText(String.valueOf(acceleration));
		lblEngineInertiaValue.setText(String.valueOf(engineInertia));
	}
	
	static void setGlobalValuesToVariables() {
		engineKoef = getConfigValue("engineKoef");
		maxEngineSize  = getConfigValue("maxEngineSize");
		minEngineSize  = getConfigValue("minEngineSize");
	}
	
	static void writeGlobalEditedToMap() {
		globalSettingValues.put("engineKoef", getTxtValueAsDouble(txtEngineKoef));
		globalSettingValues.put("maxEngineSize", getTxtValueAsDouble(txtMaxEngineSize));
		globalSettingValues.put("minEngineSize", getTxtValueAsDouble(txtMinEngineSize));
	}
	
	
	static void setValuesToTxt() {
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
	
	static void setTxtValuesToObject() {
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

	static void setGlobalSettingsTxtFields() {
		txtEngineKoef.setText(String.valueOf(getConfigValue("engineKoef")));
		txtMaxEngineSize.setText(String.valueOf(getConfigValue("maxEngineSize")));
		txtMinEngineSize.setText(String.valueOf(getConfigValue("minEngineSize")));
	}
	
	static void clearTxtFields() {
		for (JTextField f : editTxtRangesList) {
			f.setText(null);
		}
		txtPriceKoef.setText(null);
		txtWeight.setText(null);
	}
	
	
	// File I/O
	static void readConfig () {	
		int counter = 0;
		if (!configFile.exists()) {
			System.out.println("CONFIG NONEXISTANT!");
			createNewConfig();
			return;
		}
		try {
			Scanner scan = new Scanner(configFile);
			while(scan.hasNextLine()) {
				String line = scan.nextLine();
				if (line.equals("!")) {
					popupIncompleteConfig();
					return;
				}
				if (line.equals("[Global settings]")) {
					for (int i = 0; i < 3; i++) {
						String[] _line = scan.nextLine().split("=");
						String key = _line[0].trim();
						if (_line[0].equals("null")) _line[0] = "0";
						Double value = Double.parseDouble(_line[1].trim());
						globalSettingValues.put(key, value);		
					}
				}
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
			scan.close();
			setGlobalValuesToVariables();
			changeCategoryVariables(lastCat);
			} catch (Exception e) {
			System.out.println("ERROR: INITIALIZATION!");
			System.out.println(e);
			System.out.println(e.getStackTrace()[0]);
			System.out.println();
		}	
		isInitialized = true;
		startGUI();
	}
	static void writeGlobalSettings(FileWriter fw) {
		try {
		if (isCreatingNewConfig) fw.write("!\n");
		fw.write("[Global settings]"+"\n");
		fw.write("engineKoef = "+globalSettingValues.get("engineKoef")+"\n");
		fw.write("minEngineSize = "+globalSettingValues.get("minEngineSize")+"\n");
		fw.write("maxEngineSize = "+globalSettingValues.get("maxEngineSize")+"\n");
		fw.write("\n");
			
		} catch (Exception e) {
			// TODO: handle exception
	}
	}
	
	static void writeCategoryValuesToFile(FileWriter fw, Category cat) {
		try {
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
		} catch (Exception e) {
		}
	}

	static void writeEditedDataToConfig() {
		System.out.println("usCretingNew Conf? = "+isCreatingNewConfig);
		try {
			FileWriter fw = new FileWriter(configFile.getAbsoluteFile(), overwrite);
			writeGlobalSettings(fw);
			for (int i = 0;i < Category.all.size(); i++) {
				Category cat = Category.all.get(i);
				writeCategoryValuesToFile(fw, cat);
			}
			fw.close();
		} catch (Exception e) {
			System.out.println("Bad!");
			System.err.println();
		}
	}
	
	static void updateLastCat( ) {
		if (isLastElement()) return;
		lastCat = comboBox.getSelectedIndex();
		globalSettingValues.put("lastCat", (double) lastCat);
	}
	
	static void addNewCategory() {
		// yes, this is terrible, I know
		HashMap<String, Double> _objMap = new HashMap<String, Double>();	
		_objMap.put("weight", Double.parseDouble(txtWeight.getText()));
		_objMap.put("priceKoef", Double.parseDouble(txtPriceKoef.getText()));
		_objMap.put("minPrice", Double.parseDouble(txtMinPrice.getText()));
		_objMap.put("maxPrice", Double.parseDouble(txtMaxPrice.getText()));
		_objMap.put("minTax", Double.parseDouble(txtMinTax.getText()));
		_objMap.put("maxTax", Double.parseDouble(txtMaxTax.getText()));
		_objMap.put("minTopSpeed", Double.parseDouble(txtMinTopSpeed.getText()));
		_objMap.put("maxTopSpeed", Double.parseDouble(txtMaxTopSpeed.getText()));
		_objMap.put("minAcc", Double.parseDouble(txtMinAcc.getText()));
		_objMap.put("maxAcc", Double.parseDouble(txtMaxAcc.getText()));
		_objMap.put("minInertia", Double.parseDouble(txtMinInertia.getText()));
		_objMap.put("maxInertia", Double.parseDouble(txtMaxInertia.getText()));
		new Category(Category.getEmptyPlaceInAll(), _objMap);
		writeEditedDataToConfig();
		editBtnSwap();
	}
	
	static void createNewConfig() {
		JOptionPane.showMessageDialog(frame, "Config file is missing. Insert new values!\n"
				+ "(Input fields might be invisble!)");
		try {
			isCreatingNewConfig = true;
			createFrame();
			addEditPanel();
			addEditPanelElements();
			editBtnSwap();
			editPanel.remove(btnEditBack);
			editPanel.remove(btnGlobalSettings);
			editPanel.remove(btnEditRemoveCategory);
			editPanel.repaint();
			
		} catch (Exception e) {
			System.out.println("uh ohhh");
			System.out.println(e);
			System.out.println(e.getStackTrace()[0]);
		}
		
	}
	
	static void createNewConfigPart2() {
		System.out.println("Part 2 incoming!");
		addGlobalSettingPanel();
		addGlobalSettingElements();
		globalSettingsPanel.remove(btnGlobalBack);
		swapToPanel(globalSettingsPanel);
		btnGlobalEdit.setText("Add");
	}
	
	static void createNewConfigPart3() {
		System.out.println("Part 3 incoming!");
		frame.setVisible(false);
		isCreatingNewConfig = false;
		writeEditedDataToConfig();
		startGUI();
	}
	
	static void startNewCategoryInput() {
		isRemovable = false;
		clearTxtFields();
		editBtnSwap();
		btnEditRemoveCategory.setEnabled(isRemovable);
	}
	
	static void popupIncompleteConfig() {
		JOptionPane.showMessageDialog(frame, "Config file is corrupted. Try again!");
		createNewConfig();
	}
	
	// Validating methods
	public static boolean isDoubleRegex(String elem) {
		try {
			Double.parseDouble(elem);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static boolean isIntish(String elem) {
		try {
			Integer.parseInt(elem);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static boolean isNumeric(String elem) {
		return isDoubleRegex(elem) || isIntish(elem);
	}

	static Double getConfigValue(String key) {
		return globalSettingValues.get(key);
	}
	
	static double getTxtValueAsDouble(JTextField txt) {
		return Double.parseDouble(txt.getText());
	}
	
	static boolean areEditTxtFieldsValid() {
		return areTxtListNumerics(editTxtMainList) && areTxtListNumerics(editTxtRangesList);	
	}

	static boolean areTxtListNumerics(ArrayList<JTextField> list) {
		boolean fl = true;
		for (JTextField v : list) {
			if (!isTxtNumeric(v)) fl = false;
		}
		return fl;
	}
	
	static boolean isTxtNumeric(JTextField txtField) {
		String text = txtField.getText();
		if (!isNumeric(text)) {
			txtField.setForeground(invalidTextColor);
			return false;
		}
		txtField.setForeground(generalTextColor);
		return true;
	}
	
	static void checkTxtLimits(JTextField txtField, Double internalVar, Double min, 
							  Double max, boolean flag) {
		try {
			internalVar = Double.parseDouble(txtField.getText());
			if (internalVar < min ||
				internalVar > max ||
				!isDoubleRegex(txtField.getText())) {
				txtField.setForeground(invalidTextColor);
				if (flag == isPriceSafe) isPriceSafe = false;
				if (flag == isEngineSizeSafe) isEngineSizeSafe = false;
				return;
			}
			
			if (internalVar >= min &&
				internalVar <= max) {
				txtField.setForeground(generalTextColor);
				if (txtField == txtPriceField) {
					System.out.println("PRICE IS SAFE");
					isPriceSafe = true;
					inputPrice = internalVar;
				}
				if (txtField == txtEngineSize) {
					System.out.println("ENGINE IS SAFE");
					isEngineSizeSafe = true;
					inputEngineSize = internalVar;
				}
				return;
			}
			
		} catch (Exception e2) {
			txtField.setForeground(invalidTextColor);		
		}
		
	}
	
	
	static boolean isLastElementAndIsEditing(boolean editing) {
		return isLastElement() && isEditing == editing;
	}
	
	static boolean isNotLastElementAndIsEditing(boolean editing) {
		return !isLastElement() && isEditing == editing;
	}
	
	static boolean isLastElement() {
		return comboBox.getSelectedIndex() == comboBox.getItemCount()-1;
	}
	
	
	static void checkEditTxtFieldValidity() {
		boolean flag = false;
		for (int i = 0; i < minEditTxtList.size(); i++) {
			if(isMinAndMaxTxtValid(minEditTxtList.get(i), 
								   maxEditTxtList.get(i))) {
				flag = true;
				continue;
			}
			flag = false;
		}
		if (flag && !btnEdit.getText().equals("Add")) {
			isEditBtnEditing = true;
		}
	}
	
	static boolean isMinAndMaxTxtValid(JTextField minValue, JTextField maxValue) {
		String min = minValue.getText();
		String max = maxValue.getText();
		
		if (!isNumeric(min)) {
			minValue.setForeground(invalidTextColor);
			return false;
		}
		
		if(!isNumeric(max)) {
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
	

	//DEBUG METHODS
	static void printMathDebug(boolean print) {
		if (!print) return;
		System.out.println("Game Price = "+gamePrice);
		System.out.println("Tax = " +tax);
		System.out.println("Speed = " +topSpeed);
		System.out.println("Acceleration = " +acceleration);
		System.out.println("Engine Inertia = " +engineInertia);
	}
	
	
	// Garbage so eclipse does not cry
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
