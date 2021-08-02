package forms;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import controller.RedLetaController;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class view_UcestvujeRedLeta {

	private JFrame frameRS;
	private JTable table1;
	private JTable table2;
	private DefaultTableModel dtm1;
	private DefaultTableModel dtm2;

	public view_UcestvujeRedLeta(String idAvion, String datumP, String vrijemeP) {
		initialize(idAvion, datumP, vrijemeP);
	}

	private void initialize(String idAvion, String datumP, String vrijemeP) {
		frameRS = new JFrame();
		frameRS.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				view_redLeta.enableFrame();
			}
		});
		frameRS.setBounds(100, 100, 489, 409);
		frameRS.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameRS.getContentPane().setLayout(null);
		
		JLabel l1 = new JLabel("Piloti:");
		l1.setHorizontalAlignment(SwingConstants.CENTER);
		l1.setFont(new Font("Tahoma", Font.BOLD, 14));
		l1.setBounds(23, 22, 72, 14);
		frameRS.getContentPane().add(l1);
		
		JLabel l2 = new JLabel("Stjuardese:");
		l2.setHorizontalAlignment(SwingConstants.CENTER);
		l2.setFont(new Font("Tahoma", Font.BOLD, 14));
		l2.setBounds(23, 191, 123, 14);
		frameRS.getContentPane().add(l2);
		
		dtm1 = new DefaultTableModel();
		dtm1.setColumnIdentifiers(new Object[] {"JMB", "Ime", "SrednjeIme", "Prezime", "Pol"});
		
		dtm2 = new DefaultTableModel();
		dtm2.setColumnIdentifiers(new Object[] {"JMB", "Ime", "SrednjeIme", "Prezime", "Pol"});
		
		input(idAvion, datumP, vrijemeP);
		
		table1 = new JTable(dtm1);
		table2 = new JTable(dtm2);
		
		JScrollPane sc1 = new JScrollPane(table1);
		sc1.setBounds(70, 60, 378, 100);
		
		JScrollPane sc2 = new JScrollPane(table2);
		sc2.setBounds(70, 233, 378, 105);
		
		frameRS.getContentPane().add(sc1);
		frameRS.getContentPane().add(sc2);
		
		frameRS.setVisible(true);
		table1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	}
	
	public void input(String idAvion, String datumP, String vrijemeP) {
		ArrayList <String> arr = RedLetaController.selectALLUcestvuje(idAvion, datumP, vrijemeP);
		
		for (int i=0;i<arr.size();i++) {
			String []line = arr.get(i).split(" ");
			
			if ( line[line.length-1].equals("pilot") ) {
				dtm1.addRow(line);
			}else if (line[line.length-1].equals("stjuardesa")){
				dtm2.addRow(line);
			}
		}
	}
}
