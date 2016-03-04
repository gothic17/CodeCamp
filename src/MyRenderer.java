import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class MyRenderer extends DefaultTableCellRenderer {
	
	
    public Component getTableCellRendererComponent(JTable table, Object value, boolean   isSelected, boolean hasFocus, int row, int column) { 
    
    	Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); 

    	
    	if (!table.isRowSelected(row)) {
    	    if (Main.schedule[row][column] == 2) {
    	    	c.setBackground(Color.ORANGE);
    		}
    	    else if (Main.schedule[row][column] == 3) {
    	    	c.setBackground(Color.BLUE);
    	    }
    	    else {
    	        c.setBackground(table.getBackground());
    	    }
    	}
    	else {
    		c.setBackground(Color.CYAN);
    	}  	
    	
    	return c;
    } 
} 