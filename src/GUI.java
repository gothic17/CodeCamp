import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Date;
import java.awt.image.*;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.table.TableColumnModel;

import org.openide.awt.DropDownButtonFactory;

public class GUI extends JFrame {
	
	private		JTabbedPane tabbedPane;
	private		JPanel		panel1;
	private		JPanel		panel2;
	private		JPanel		panel3;
	private 	Object[][] 	data;
	
	private		Thermostat	thermostat;
	private		MovementDetector	movementDetector;
	private		WindowDetector	windowDetector;
	
	public GUI(Thermostat thermostat, MovementDetector movementDetector, WindowDetector windowDetector) throws IOException {
		this.thermostat = thermostat;
		this.movementDetector = movementDetector;
		this.windowDetector = windowDetector;
		
		
		setTitle( "Smart-House Application" );
		setSize( 600, 500 );
		setBackground( Color.GREEN );
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel topPanel = new JPanel();
		topPanel.setLayout( new BorderLayout() );
		getContentPane().add( topPanel );

		// Create the tab pages
		createPage1();
		createPage2();
		//createPage3();

		// Create a tabbed pane
		tabbedPane = new JTabbedPane();
		tabbedPane.addTab( "Basic", panel1 );
		tabbedPane.addTab( "Schedule", panel2 );
		//tabbedPane.addTab( "Statistics", panel3 );
		topPanel.add( tabbedPane, BorderLayout.CENTER );
	}

	public void createPage1() throws IOException
	{
		panel1 = new JPanel();
		panel1.setLayout( null );
		
		JLabel currentTemperature1 = new JLabel("Current temperature:");
		currentTemperature1.setBounds(25, 15, 160, 25);
		
		JLabel currentTemperature2 = new JLabel(thermostat.getCurrentTemperature());
		currentTemperature2.setBounds(190, 15, 50, 25);
		
		JLabel desiredTemperature1 = new JLabel("Desired temperature:");
		desiredTemperature1.setBounds(25, 45, 160, 25);
		
		JLabel desiredTemperature2 = new JLabel(thermostat.getDesiredTemperature());
		desiredTemperature2.setBounds(190, 45, 50, 25);
		
		JLabel selectTemperature1 = new JLabel("Select desired temperature:");
		selectTemperature1.setBounds(25, 75, 230, 25);
		
		
		// temperatures Combo-box
		String[] temperaturesString = new String[21];
		for (int i = 10; i <= 30; i++) {
			temperaturesString[i - 10] = Integer.toString(i) + ".0";
		}

		// Create the combo box
		JComboBox<?> temperaturesComboBox = new JComboBox<Object>(temperaturesString);
		temperaturesComboBox.setSelectedIndex(0);
		temperaturesComboBox.addActionListener(temperaturesComboBox);
		temperaturesComboBox.setBounds(250, 75, 200, 25);
		
		// Add ActionListener to ComboBox
		temperaturesComboBox.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	String selectedTemperature = (String)temperaturesComboBox.getSelectedItem();
		    	try {
					thermostat.setTemperature(Double.parseDouble(selectedTemperature));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		    }
		});
		
		//String selectedTemperature = (String)cb.getSelectedItem();!!!!!!!!!
		
		JLabel windowDetector1  = new JLabel("Window is open:");
		windowDetector1.setBounds(25, 105, 160, 25);
		
		JLabel windowDetector2 = new JLabel();
		windowDetector2.setBounds(190, 105, 50, 25);
		
		WindowSwingWorker windowSwingWorker = new WindowSwingWorker(windowDetector2, windowDetector);
		windowSwingWorker.execute();
		
		JLabel movementDetector1  = new JLabel("There was movement:");
		movementDetector1.setBounds(25, 135, 160, 25);
		
		JLabel movementDetector2 = new JLabel();
		movementDetector2.setBounds(190, 135, 50, 25);
		
		MovementSwingWorker movementSwingWorker = new MovementSwingWorker(movementDetector2, movementDetector, thermostat);
		movementSwingWorker.execute();
		
		panel1.add(currentTemperature1);
		panel1.add(currentTemperature2);
		panel1.add(desiredTemperature1);
		panel1.add(desiredTemperature2);
		panel1.add(selectTemperature1);
		panel1.add(temperaturesComboBox);
		panel1.add(windowDetector1);
		panel1.add(windowDetector2);
		panel1.add(movementDetector1);
		panel1.add(movementDetector2);		
	}

	public void createPage2()
	{
		panel2 = new JPanel();
		panel2.setLayout(null);
		
		JLabel temperatureLabel = new JLabel("Select desired temperature:");
		temperatureLabel.setBounds(25, 30, 230, 25);
		
		
		// temperatures Combo-box
		String[] temperaturesString = new String[21];
		for (int i = 10; i <= 30; i++) {
			temperaturesString[i - 10] = Integer.toString(i) + ".0";
		}

		// Create the combo box
		JComboBox<?> temperaturesComboBox = new JComboBox<Object>(temperaturesString);
		temperaturesComboBox.setSelectedIndex(0);
		temperaturesComboBox.addActionListener(temperaturesComboBox);
		temperaturesComboBox.setBounds(250, 30, 200, 25);
		
		JLabel dayTimeLabel = new JLabel("Select desired type of action:");
		dayTimeLabel.setBounds(25, 60, 230, 25);

		String[] dayTime = new String[2];
		dayTime[0] = "Day";
		dayTime[1] = "Night";
		// Create the combo box
		JComboBox<?> dayTimeComboBox = new JComboBox<Object>(dayTime);
		dayTimeComboBox.setBounds(250, 60, 200, 25);
		dayTimeComboBox.setSelectedIndex(0);
		
		// Add ActionListener to ComboBox
		dayTimeComboBox.addActionListener (new ActionListener () {
				    public void actionPerformed(ActionEvent e) {
				    	Main.time= (String)dayTimeComboBox.getSelectedItem();
				    	//System.out.println(time);
				    }
				});
		
		//String selectedTemperature = (String)cb.getSelectedItem();!!!!!!!!!
		
		// ***********Create a table with 24 rows and 7 columns************
		data = new Object[24][8];
		for (int i = 0; i < 24; i++) {
			String str = Integer.toString(i) + ":00";
			data[i][0] = str;
		}
		String[] columnNames = {"Hours", "Monday", "Tueasday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
	    JTable table = new JTable(data, columnNames);
	    
	    MyRenderer myRenderer = new MyRenderer();
	    table.setDefaultRenderer(Object.class, myRenderer);
	    
	    SelectionListener listener = new SelectionListener(table);
	    table.addMouseListener(listener);
	    table.setCellSelectionEnabled(true); // Disables selecting rows in each column at one time
	    table.getSelectionModel().addListSelectionListener(listener);
	    table.getColumnModel().getSelectionModel().addListSelectionListener(listener);
	    
	    // Disable multi-column selection
	    TableColumnModel columnModel = table.getColumnModel();

	    ListSelectionModel selectionModel = columnModel.getSelectionModel();

	    selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    

	    // Make the table vertically scrollable
	    JScrollPane scrollPane = new JScrollPane(table);	    
	    scrollPane.setVisible(true);
	    scrollPane.setBounds(25, 90, 550, 300);
	    
	    JButton addButton = new JButton("Add new action");
	    AddButtonListener addButtonListener = new AddButtonListener();
	    addButton.addActionListener(addButtonListener);
	    addButton.setBounds(150, 410, 150, 25);
	    
	    JButton deleteButton = new JButton("Delete");
	    DeleteButtonListener deleteButtonListener = new DeleteButtonListener();
	    deleteButton.addActionListener(deleteButtonListener);
	    deleteButton.setBounds(325, 410, 100, 25);
	    
	    JButton saveButton = new JButton("Save");
	    SaveButtonListener saveButtonListener = new SaveButtonListener(thermostat);
	    saveButton.addActionListener(saveButtonListener);
	    saveButton.setBounds(450, 410, 100, 25);
	    
	    
	   // panel2.add(temperatureLabel);
	    //panel2.add(temperaturesComboBox);
	    panel2.add(dayTimeLabel);
	    panel2.add(dayTimeComboBox);
	    panel2.add(scrollPane);		
	    panel2.add(addButton);
	    panel2.add(deleteButton);
	    panel2.add(saveButton);
	}

	public void createPage3() throws IOException {
		panel3 = new JPanel();
		panel3.setLayout(null);
		
		URL url = new URL("http://157.24.191.6:8083/fhem/SVG_showLog?dev=weblink_thermo_Group3&logdev=FileLog_thermo_Group3&gplotfile=fht&logfile=CURRENT&pos");
		//URL url = new URL("http://i.dailymail.co.uk/i/pix/2014/06/13/article-2657035-1EB9365300000578-176_634x505.jpg");

		Image image = ImageIO.read(url);
		ImagePanel imagePanel = new ImagePanel(image);
		imagePanel.setBounds(0, 0, 600, 500);
		
		//panel3.add(imagePanel);
		JButton button = new JButton("See statistics");
		button.setBounds(25, 25, 150, 25);
		button.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
					openWebpage("www.google.com");
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} // just what is the 'open' method?
		    }
		});
		
		panel3.add(button);
	}

	public void openWebpage(String page) throws URISyntaxException {
		URI uri = new URI(page);
	    Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
	    if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
	        try {
	            desktop.browse(uri);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}
}

class WindowSwingWorker extends SwingWorker<Integer, Integer> {
	
	private JLabel label;
	WindowDetector	windowDetector;
	
	WindowSwingWorker(JLabel label, WindowDetector windowDetector) {
		this.label = label;		
		this.windowDetector = windowDetector;
	}
	
    protected Integer doInBackground() throws Exception
    {
    	while (true) {
        	label.setText(windowDetector.checkIfWindowIsOpened());
        	Thread.sleep(5000);
    	}
    }

    protected void done() {}
}

class MovementSwingWorker extends SwingWorker<Integer, Integer> {
	
	private Thermostat thermostat;
	private JLabel label;
	MovementDetector movementDetector;
	Date lastMovementDate;
	String desiredTemperature;
	
	MovementSwingWorker(JLabel label, MovementDetector movementDetector, Thermostat thermostat) throws IOException {
		this.label = label;		
		this.movementDetector = movementDetector;
		this.thermostat = thermostat;
		
		desiredTemperature = thermostat.getDesiredTemperature();

		lastMovementDate = new Date( );
	}
	
    protected Integer doInBackground() throws Exception
    {
    	while (true) {
    		Date newDate = new Date();
    		long result = ((newDate.getTime()/60000) - (lastMovementDate.getTime()/60000));
    		if (result >= 30 && movementDetector.checkIfThereWasMovement().equals("NO")) { // Lower the temperature
    			thermostat.setTemperature(10);
    		}
    		else if (movementDetector.checkIfThereWasMovement().equals("YES")){
    			lastMovementDate = newDate;
    			thermostat.setTemperature(Double.parseDouble(desiredTemperature));
    		}
        	label.setText(movementDetector.checkIfThereWasMovement());
        	Thread.sleep(10000);
    	}
    }

    protected void done() {}
}

class ImagePanel extends JComponent {
    private Image image;
    public ImagePanel(Image image) {
        this.image = image;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }
}