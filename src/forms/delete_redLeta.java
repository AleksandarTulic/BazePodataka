package forms;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import controller.MainController;
import controller.RedLetaController;
import helpFunctions.RadioButtonEditor;

public class delete_redLeta extends view_redLeta{
	private JButton button2;
	
	public delete_redLeta(int broj) {
		super(broj);
		
		button2 = new JButton("DELETE");
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	      		if ( rb.size() != 0 ) {
	      			boolean flag = RedLetaController.deleteRedLeta((String)table.getValueAt(RadioButtonEditor.selectedRow, 1), (String)table.getValueAt(RadioButtonEditor.selectedRow, 2), 
	      					(String)table.getValueAt(RadioButtonEditor.selectedRow, 3));
	      		
	      			if ( flag ) {
	      				MainController.showMessage(frameRS, "Operacija je uspjesna.");
	      			}else {
	      				MainController.showMessage(frameRS, "Operacija je uspjesna.");
	      			}
	      		}
	      	}
	      });
		button2.setFont(new Font("Tahoma", Font.BOLD, 14));
		button2.setBackground(Color.WHITE);
	    button2.setBounds(536, 319, 196, 62);
		frameRS.getContentPane().add(button2);
	}
}
