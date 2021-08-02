package forms;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;

import controller.MainController;
import controller.RedLetaController;
import helpFunctions.CheckBoxEditor;
import helpFunctions.RadioButtonRenderer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class viewStjuardesa {

	private JFrame frame;
	private JTable table;
	private JScrollPane sc;
	private DefaultTableModel dtm;
	private JButton btnNewButton;
	private ArrayList<JCheckBox> rb = new ArrayList<JCheckBox>();

	public viewStjuardesa(ArrayList <String> stjuJmb, JCheckBox []cc) {
		initialize(stjuJmb, cc);
	}

	@SuppressWarnings("serial")
	private void initialize(ArrayList <String> stjuJmb, JCheckBox []cc) {
		frame = new JFrame();
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				input_redLeta.enableFrame();
			}
		});
		frame.setBounds(100, 100, 659, 500);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		dtm = new DefaultTableModel();
		dtm.setColumnIdentifiers(new Object[] {"Check", "jmb", "ime", "srednjeIme", "prezime", "pol", "naziv"});
		
		input();
		
		table = new JTable(dtm) {
	         public void tableChanged(TableModelEvent tme) {
	            super.tableChanged(tme);
	            repaint();
	         }
	    };
	    
	    table.getColumn("Check").setCellRenderer(new RadioButtonRenderer());
	    table.getColumn("Check").setCellEditor(new CheckBoxEditor(new JCheckBox()));
	    
	    sc = new JScrollPane(table);
	    sc.setBounds(0, 0, 500, 450);
		
	    frame.getContentPane().setLayout(null);
	    frame.getContentPane().add(sc);
	    
		frame.setVisible(true);
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		btnNewButton = new JButton("OK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stjuJmb.clear();
				for (int i=0;i<rb.size();i++) {
					if ( rb.get(i).isSelected() ) {
						stjuJmb.add((String)table.getValueAt(i, 1));
					}
				}
				cc[0].setSelected(true);
				frame.dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setBounds(520, 384, 113, 66);
		frame.getContentPane().add(btnNewButton);
		
		frame.setVisible(true);
	}
	
	private void input() {
		ArrayList <String> arr = RedLetaController.selectALLZaposleniKompanije(MainController.korisnik.getNazivKompanije(), "stjuardesa");
		
		for (int i=0;i<arr.size();i++) {
			String []line = arr.get(i).split(" ");
			
			Object []obj = new Object[line.length + 1];
			JCheckBox cc = new JCheckBox("");
			rb.add(cc);
			obj[0] = cc;
			for (int j=0;j<line.length;j++) {
				obj[j+1] = line[j];
			}
			
			dtm.addRow(obj);
		}
	}
}
