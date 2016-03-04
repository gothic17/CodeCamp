import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;

public class SelectionListener implements ListSelectionListener, MouseListener {
	  JTable table;
	 // private int disabled_col = 0;
	  
	  //private int[][] schedule;
	  
	  private int firstRow = 0;
	  private int lastRow = 0;
	  private int column = 0;

	  SelectionListener(JTable table) {
	    this.table = table;
	    Main.selected = new int[24][8];
	  }
	  
	  @Override
	  public void valueChanged(ListSelectionEvent event) {}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {
		 firstRow = table.rowAtPoint(e.getPoint());//get mouse-selected row
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		lastRow = table.rowAtPoint(e.getPoint()); //get mouse-selected row
        column = table.columnAtPoint(e.getPoint()); //get mouse-selected column
        
        if (firstRow == lastRow) {
        	// If cell wasn't selected, mark it as selected
        	if (Main.selected[lastRow][column] == 0) Main.schedule[lastRow][column] = 1;
        	// If cell was already selected, deselect it
        	else Main.selected[lastRow][column] = 1;
        }
        else {
        	if (lastRow > firstRow) {
        		for (int i = firstRow; i <= lastRow; i++) {
        			Main.selected[i][column] = 1;
        		}
        	}
        	else {
        		for (int i = lastRow; i >= firstRow; i--) {
        			Main.selected[i][column] = 1;
        		}
        	}
        }        
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}  
	  
}


