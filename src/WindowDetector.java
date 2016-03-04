import java.io.IOException;
import java.util.Scanner;

public class WindowDetector  {

	public String checkIfWindowIsOpened() throws IOException {

		String body = Main.getBody("http://157.24.191.6:8083/fhem?detail=TFKCHA1_Group3");
		
		String windowOpened = "";
		
		Scanner scanner = new Scanner(body);
		while (scanner.hasNextLine()) {
		  String line = scanner.nextLine();
		  if (line.contains("STATE")) {
			  line = scanner.nextLine();
			  if (line.contains("on")) {
				  //System.out.println("It is ON");
				  windowOpened = "YES";
			  }
			  else if (line.contains("off")) {
				  //System.out.println("It is OFF");
				  windowOpened = "NO";
			  }
		  }
		}
		scanner.close();
		
		return windowOpened;
	}
}
