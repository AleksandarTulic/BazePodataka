package forms;

import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;

import controller.MainController;
import controller.RedLetaController;
import helpFunctions.RadioButtonEditor;
import helpFunctions.RadioButtonRenderer;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class viewAvion {

	private JFrame frameRS;
	private JScrollPane sc;
	private JTable table;
	private ButtonGroup bg;
	private DefaultTableModel dtm;
	private ArrayList<JRadioButton> rb = new ArrayList<JRadioButton>();
	private JButton btnNewButton;

	public viewAvion(String []idAvion, JCheckBox[] cc) {
		initialize(idAvion, cc);
	}

	@SuppressWarnings("serial")
	private void initialize(String []idAvion, JCheckBox[] cc) {
		frameRS = new JFrame();
		frameRS.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				input_redLeta.enableFrame();
			}
		});
		frameRS.setTitle("ISA");
		frameRS.setBounds(100, 100, 701, 508);
		frameRS.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		dtm = new DefaultTableModel();
		dtm.setColumnIdentifiers(new Object[] {"Check", "idAvion", "model", "datum_proizvodnje", "maksimalna_nosivost", "broj_mjesta", "naziv_P"});
		
		input();
		
		table = new JTable(dtm) {
	         public void tableChanged(TableModelEvent tme) {
	            super.tableChanged(tme);
	            repaint();
	         }
	    };
	      
	    bg = new ButtonGroup();
	      
	    table.getColumn("Check").setCellRenderer(new RadioButtonRenderer());
	    table.getColumn("Check").setCellEditor(new RadioButtonEditor(new JCheckBox()));
	      
	    sc = new JScrollPane(table);
	    sc.setBounds(0, 0, 500, 450);
	    frameRS.getContentPane().setLayout(null);
	    frameRS.getContentPane().add(sc);
		
		frameRS.setVisible(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		btnNewButton = new JButton("OK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ( rb.size() != 0 ) {
					idAvion[0] = (String)table.getValueAt(RadioButtonEditor.selectedRow, 1);
					cc[0].setSelected(true);
					frameRS.dispose();
				}
			}
		});
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton.setBounds(522, 381, 138, 69);
		frameRS.getContentPane().add(btnNewButton);
	}
	
	private void input() {
		bg = new ButtonGroup();
		
		ArrayList <String> arr = RedLetaController.selectALLAvion(MainController.korisnik.getNazivKompanije());
		
		for (String i : arr) {
			String []line = i.split(" ");
			
			Object []obj = new Object[line.length + 1];
			JRadioButton a = new JRadioButton();
			rb.add(a);
			bg.add(a);
			obj[0] = rb.get(rb.size() - 1);
			for (int j=0;j<line.length;j++) {
				obj[j+1] = line[j];
			}
			
			dtm.addRow(obj);
		}
		
		rb.get(0).setSelected(true);
	}

}
