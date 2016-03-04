import java.io.IOException;
import java.util.Scanner;

public class MovementDetector {

	String lastValue;
	
	MovementDetector(String name) throws IOException {
		/*lastValue = "";
		
		String body = Main.getBody("http://157.24.191.6:8083/fhem/FileLog_logWrapper&dev=FileLog_PIRI_Group3&type=text&file=PIRI_Group3-2016.log");
		Scanner scanner = new Scanner(body);
		while (scanner.hasNextLine()) {
			String str = scanner.nextLine();
			if (str.contains("PIRI_Group3")) {
				lastValue = str;
			}
		}
		scanner.close();*/
	}

	public String checkIfThereWasMovement() throws IOException {

		String body = Main.getBody("http://157.24.191.6:8083/fhem/FileLog_logWrapper&dev=FileLog_2PIRI_Group3&type=text&file=2PIRI_Group3-2016.log");		
		Scanner scanner = new Scanner(body);

		String lastLine = "";
		String movement = "";
		
		while (scanner.hasNextLine()) {
			String str = scanner.nextLine();
			if (str.contains("2PIRI_Group3")) {
				lastLine = str;
			}
		}
		scanner.close();
		
		if (lastLine.equals(lastValue)) {
			movement = "NO";
			//System.out.println("There was NO MOVEMENT");
		}
		else {
			lastValue = lastLine;
			movement = "YES";
			//System.out.println("There was MOVEMENT");
		}
		
		return movement;
	}
}
