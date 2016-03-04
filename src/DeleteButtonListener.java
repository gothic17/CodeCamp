import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteButtonListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		for (int i = 0; i < 24; i++) {
			for (int j = 0; j < 8; j++) {
				//System.out.println(Main.time);
				if (Main.selected[i][j] == 1) {
					Main.schedule[i][j] = 0;
					Main.selected[i][j] = 0;
				}
			}
		}	
	}
}
