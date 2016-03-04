import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;

public class AddButtonListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		for (int i = 0; i < 24; i++) {
			for (int j = 0; j < 8; j++) {
				//System.out.println(Main.time);
				if (Main.selected[i][j] == 1 && Main.time.equals("Day")) {
					if (Main.schedule[i][j] == 0) Main.schedule[i][j] = 2;
				}
				else if (Main.selected[i][j] == 1 && Main.time.equals("Night")) {
					if (Main.schedule[i][j] == 0) Main.schedule[i][j] = 3;
				}
				System.out.print(Main.schedule[i][j] + " ");
			}
			System.out.println();
			
		}		
	}

}
