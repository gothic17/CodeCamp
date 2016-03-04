import java.awt.Component;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;

import javax.swing.SwingUtilities;

import org.apache.commons.logging.LogFactory;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlOption;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
 

public class Main {	
	
	public static int[][] schedule;
	public static int[][] selected;
	public static String time;
	
	public static String getBody(String address) throws IOException {
		
		URL url = new URL(address);
		URLConnection con = url.openConnection();
		InputStream in = con.getInputStream();
		String encoding = con.getContentEncoding();
		encoding = encoding == null ? "UTF-8" : encoding;
	
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buf = new byte[8192];
		int len = 0;
		while ((len = in.read(buf)) != -1) {
		    baos.write(buf, 0, len);
		}
		String body = new String(baos.toByteArray(), encoding);
		
		return body;
	}

	public static void main(String[] args) throws Exception {
		
		schedule = new int[24][8];
		selected = new int[24][8];
		
		// Disable HtmlUnit warnings
		LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.NoOpLog");

	    java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(Level.OFF); 
	    java.util.logging.Logger.getLogger("org.apache.commons.httpclient").setLevel(Level.OFF);
	    
	    Thermostat thermostat = new Thermostat();
	    WindowDetector windowDetector = new WindowDetector();
	    //windowDetector.start();
	    
	    MovementDetector movementDetector = new MovementDetector("MovementDetectorThread");
		// movementDetector.start();
		
		// Set the temperature
		//thermostat.setTemperature(30.0);
		
		// Get all so far measured temperatures
		//thermostat.getMeasuredTemperatures();		
		
	    SwingUtilities.invokeLater(new Runnable() {
	        @Override
	    	public void run() {
	        	GUI mainFrame;
				try {
					mainFrame = new GUI(thermostat, movementDetector, windowDetector);
					mainFrame.setVisible( true );
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	      });
	}
}
