import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class SaveButtonListener implements ActionListener {

	private Thermostat thermostat;
	
	SaveButtonListener(Thermostat thermostat) {
		this.thermostat = thermostat;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		BufferedWriter outputWriter = null;
		try {
			outputWriter = new BufferedWriter(new FileWriter("Schedule"));
		for (int i = 0; i < 24; i++) {
			for (int j = 0; j < 8; j++) {
				outputWriter.write(Integer.toString(Main.schedule[i][j]) + " ");
			}
			outputWriter.newLine();
		}
		outputWriter.flush();  
		outputWriter.close(); 
		
		applyChanges();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void applyChanges() throws Exception {
		for (int i = 1; i < 8; i++) {
			
			int k = 0;
			int dayFrom = 0;
			int dayTo = 0;
			int nightFrom = 0;
			int nightTo = 0;
			while (Main.schedule[k][i] != 2 && k < 23) k++; // Get index of the beginning of dayFrom
			System.out.println(k);
			if (k != 0) nightTo = k - 1;
			else nightTo = 0;
			
			dayFrom = k;
			while (Main.schedule[k][i] == 2 && k < 23) k++; // Get index of the end of dayTo
			System.out.println(k);

			if (k != 0) dayTo = k - 1;
			else dayTo = k;
			
			nightFrom = k;

			if (i == 1) { // Monday
				thermostat.setScheduleTime("mon-from1", Integer.toString(dayFrom) + ":00");
				thermostat.setScheduleTime("mon-to1", Integer.toString(dayTo) + ":00");
				thermostat.setScheduleTime("mon-from2", Integer.toString(nightFrom) + ":00");
				thermostat.setScheduleTime("mon-to2", Integer.toString(nightTo) + ":00");
			}
			else if (i == 2) { // Tuesday
				thermostat.setScheduleTime("tue-from1", Integer.toString(dayFrom) + ":00");
				thermostat.setScheduleTime("tue-to1", Integer.toString(dayTo) + ":00");
				thermostat.setScheduleTime("tue-from2", Integer.toString(nightFrom) + ":00");
				thermostat.setScheduleTime("tue-to2", Integer.toString(nightTo) + ":00");
			}
			else if (i == 3) { // Wednesday
				thermostat.setScheduleTime("wed-from1", Integer.toString(dayFrom) + ":00");
				thermostat.setScheduleTime("wed-to1", Integer.toString(dayTo) + ":00");
				thermostat.setScheduleTime("wed-from2", Integer.toString(nightFrom) + ":00");
				thermostat.setScheduleTime("wed-to2", Integer.toString(nightTo) + ":00");
			}
			else if (i == 4) { // Thursday
				thermostat.setScheduleTime("thu-from1", Integer.toString(dayFrom) + ":00");
				thermostat.setScheduleTime("thu-to1", Integer.toString(dayTo) + ":00");
				thermostat.setScheduleTime("thu-from2", Integer.toString(nightFrom) + ":00");
				thermostat.setScheduleTime("thu-to2", Integer.toString(nightTo) + ":00");
			}
			else if (i == 5) { // Friday
				thermostat.setScheduleTime("fri-from1", Integer.toString(dayFrom) + ":00");
				thermostat.setScheduleTime("fri-to1", Integer.toString(dayTo) + ":00");
				thermostat.setScheduleTime("fri-from2", Integer.toString(nightFrom) + ":00");
				thermostat.setScheduleTime("fri-to2", Integer.toString(nightTo) + ":00");
			}
			else if (i == 6) { // Saturday
				thermostat.setScheduleTime("sat-from1", Integer.toString(dayFrom) + ":00");
				thermostat.setScheduleTime("sat-to1", Integer.toString(dayTo) + ":00");
				thermostat.setScheduleTime("sat-from2", Integer.toString(nightFrom) + ":00");
				thermostat.setScheduleTime("sat-to2", Integer.toString(nightTo) + ":00");
			}
			else if (i == 7) { // Sunday
				thermostat.setScheduleTime("sun-from1", Integer.toString(dayFrom) + ":00");
				thermostat.setScheduleTime("sun-to1", Integer.toString(dayTo) + ":00");
				thermostat.setScheduleTime("sun-from2", Integer.toString(nightFrom) + ":00");
				thermostat.setScheduleTime("sun-to2", Integer.toString(nightTo) + ":00");
			}
			
		}
	}

}
