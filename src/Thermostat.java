import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlOption;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

public class Thermostat {

	public String[][] measuredTemperatures;
	public int measuredTemperaturesCounter = 0;
	public String measuredTemperaturesString = "";
	
	// Get "current" temperature
	public String getDesiredTemperature() throws IOException {
		    
		String body = Main.getBody("http://157.24.191.6:8083/fhem?detail=thermo_Group3");
		String desiredTemperature = "";
		
		Scanner scanner = new Scanner(body);
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			if (line.contains("thermo_Group3-desired-temp\"")) {
				desiredTemperature = line;
			}
		}
		scanner.close();
				
		desiredTemperature = desiredTemperature.substring(desiredTemperature.indexOf("\">")+2);
		desiredTemperature = desiredTemperature.substring(0, 4);
			
		return desiredTemperature;
	}
	
	public void fillTable(int i) {
		
		int k = 0;
		measuredTemperatures = new String[i][3];
		measuredTemperaturesString = measuredTemperaturesString.replace(" thermo_Group3 temperature: ","_");
		//System.out.println(measuredTemperaturesString);
		Scanner scanner = new Scanner(measuredTemperaturesString);
		while (scanner.hasNextLine()) {
		  String line = scanner.nextLine();
		  String[] parts = line.split("_");
		  measuredTemperatures[k][0] = parts[0];
		  measuredTemperatures[k][1] = parts[1];
		  measuredTemperatures[k][2] = parts[2];
		  k++;
		}
		scanner.close();
	}

	public void setTemperature(double temperature) throws Exception {
		
	    @SuppressWarnings("resource")
		WebClient webClient = new WebClient(BrowserVersion.FIREFOX_38);

	    // Get the page
	    HtmlPage page = webClient.getPage("http://157.24.191.6:8083/fhem?detail=thermo_Group3");

	    HtmlSelect select = page.getElementByName("arg.setthermo_Group3");
	    List<HtmlOption> options = select.getOptions();
	    select.setSelectedAttribute(options.get(2), true);
	    
	    HtmlSelect temperatureSelect = page.getElementByName("val.setthermo_Group3");
	    temperatureSelect.setSelectedAttribute(Double.toString(temperature), true);
	    
	    HtmlSubmitInput button = page.getElementByName("cmd.setthermo_Group3");

	    button.click();
	}
	
	public void setScheduleTime(String dayTime, String time) throws Exception {
		
	    @SuppressWarnings("resource")
		WebClient webClient = new WebClient(BrowserVersion.FIREFOX_38);

	    // Get the page
	    HtmlPage page = webClient.getPage("http://157.24.191.6:8083/fhem?detail=thermo_Group3");

	    HtmlSelect select = page.getElementByName("arg.setthermo_Group3");
	    List<HtmlOption> options = select.getOptions();
	    select.setSelectedAttribute(dayTime, true);
	    
	    HtmlTextInput temperatureSelect = page.getElementByName("val.setthermo_Group3");
	    temperatureSelect.setText(time);
	    
	    HtmlSubmitInput button = page.getElementByName("cmd.setthermo_Group3");

	    button.click();
	}
	
	public void getMeasuredTemperatures() throws IOException {
		
		String body = Main.getBody("http://157.24.191.6:8083/fhem?cmd=logwrapper%20FileLog_thermo_Group3%20text%20thermo_Group3-2016.log");
		
		measuredTemperaturesString = "";
		
		Scanner scanner = new Scanner(body);
		while (scanner.hasNextLine()) {
		  String line = scanner.nextLine();
		  if (line.toLowerCase().contains("temperature:")) {
			  measuredTemperaturesString += line + "\n";
			  measuredTemperaturesCounter++;
		  }
		}
		scanner.close();
		
		fillTable(measuredTemperaturesCounter);
		/*for (int i = 0; i < measuredTemperaturesCounter; i++) {
			System.out.println(measuredTemperatures[i][0] + " " + measuredTemperatures[i][1] + " " + measuredTemperatures[i][2]);
		}*/
	}
	
	public String getCurrentTemperature() throws IOException {
		String body = Main.getBody("http://157.24.191.6:8083/fhem?detail=thermo_Group3");
		
		String measuredTemperature = "";
		Scanner scanner = new Scanner(body);
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			if (line.contains("thermo_Group3-measured-temp\"")) {
				measuredTemperature = line;				
			}
		}
		scanner.close();
		
	    measuredTemperature = measuredTemperature.substring(measuredTemperature.indexOf("\">")+2);
	    measuredTemperature = measuredTemperature.substring(0, 4);
		
		return measuredTemperature;
	}


}
