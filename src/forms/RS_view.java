package forms;

import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import controller.KartaController;
import controller.MainController;
import formsMenu.jobRS;

import javax.swing.JScrollPane;
import javax.swing.ButtonGroup;
import java.util.ArrayList;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import helpFunctions.*;
import helpFunctions.interfaces.ViewInterface;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class RS_view implements ViewInterface{

	private static JFrame frameRS;
	private JTable table;
	private JScrollPane sc;
	private JTextField t1;
	private JTextField t2;
	private JTextField t3;
	private JTextField t4;
	private JTextField t5;
	private JButton button2;
	private JButton button3;
	private DefaultTableModel dtm;
	private ButtonGroup bg;
	private ArrayList<JRadioButton> rb = new ArrayList<JRadioButton>();

	public RS_view() {
		initialize();
	}

	@SuppressWarnings("serial")
	private void initialize() {
		frameRS = new JFrame();
		frameRS.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				jobRS.setAllEnabled();
			}
		});
		frameRS.setTitle("ISA");
		frameRS.setBounds(100, 100, 770, 508);
		frameRS.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		dtm = new DefaultTableModel();
		dtm.setColumnIdentifiers(new Object[] {"Check", "idKarta", "Vrijeme Kupovine", "Datum Kupovine", "Broj Sjedista" , "Klasa", "Izlaz", "Cijena", "JMB RS", "JMB OK", "ID Avion",
				"Datum polazak", "Vrijeme polazak"});
		
		input(false);
	
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
	      
	      JButton button1 = new JButton("More info");
	      button1.addActionListener(new ActionListener() {
	      	@SuppressWarnings("unused")
			public void actionPerformed(ActionEvent e) {
	      		if ( rb.size() != 0 ) {
	      			frameRS.setEnabled(false);
	      			String jmb = (String)table.getValueAt(RadioButtonEditor.selectedRow, 9);
	      			RS_viewOK_one a = new RS_viewOK_one(jmb);
	      		}
	      	}
	      });
	      button1.setBackground(Color.WHITE);
	      button1.setFont(new Font("Tahoma", Font.BOLD, 14));
	      button1.setBounds(590, 286, 145, 44);
	      frameRS.getContentPane().add(button1);
	      
	      t1 = new JTextField();
	      t1.addKeyListener(new KeyAdapter() {
	      	@Override
	      	public void keyReleased(KeyEvent e) {
	      		input(true);
	      	}
	      });
	      t1.setBounds(646, 23, 86, 20);
	      frameRS.getContentPane().add(t1);
	      t1.setColumns(10);
	      
	      t2 = new JTextField();
	      t2.addKeyListener(new KeyAdapter() {
	      	@Override
	      	public void keyReleased(KeyEvent e) {
	      		input(true);
	      	}
	      });
	      t2.setBounds(646, 85, 86, 20);
	      frameRS.getContentPane().add(t2);
	      t2.setColumns(10);
	      
	      t3 = new JTextField();
	      t3.addKeyListener(new KeyAdapter() {
	      	@Override
	      	public void keyReleased(KeyEvent e) {
	      		input(true);
	      	}
	      });
	      t3.setBounds(646, 148, 86, 20);
	      frameRS.getContentPane().add(t3);
	      t3.setColumns(10);
	      
	      t4 = new JTextField();
	      t4.addKeyListener(new KeyAdapter() {
	      	@Override
	      	public void keyReleased(KeyEvent e) {
	      		input(true);
	      	}
	      });
	      t4.setBounds(646, 204, 86, 20);
	      frameRS.getContentPane().add(t4);
	      t4.setColumns(10);
	      
	      t5 = new JTextField();
	      t5.addKeyListener(new KeyAdapter() {
	      	@Override
	      	public void keyReleased(KeyEvent e) {
	      		input(true);
	      	}
	      });
	      t5.setBounds(646, 255, 86, 20);
	      frameRS.getContentPane().add(t5);
	      t5.setColumns(10);
	      
	      JLabel l1 = new JLabel("ID Aviona:");
	      l1.setFont(new Font("Tahoma", Font.BOLD, 12));
	      l1.setBounds(558, 25, 78, 14);
	      frameRS.getContentPane().add(l1);
	      
	      JLabel l2 = new JLabel("Datum polaska:");
	      l2.setFont(new Font("Tahoma", Font.BOLD, 12));
	      l2.setBounds(536, 87, 100, 14);
	      frameRS.getContentPane().add(l2);
	      
	      JLabel l3 = new JLabel("Vrijeme polaska:");
	      l3.setFont(new Font("Tahoma", Font.BOLD, 12));
	      l3.setBounds(528, 149, 108, 17);
	      frameRS.getContentPane().add(l3);
	      
	      JLabel l4 = new JLabel("Ime:");
	      l4.setFont(new Font("Tahoma", Font.BOLD, 12));
	      l4.setBounds(590, 206, 46, 14);
	      frameRS.getContentPane().add(l4);
	      
	      JLabel l5 = new JLabel("Prezime:");
	      l5.setFont(new Font("Tahoma", Font.BOLD, 12));
	      l5.setBounds(579, 257, 57, 14);
	      frameRS.getContentPane().add(l5);
	      
	      button2 = new JButton("Update");
	      button2.addActionListener(new ActionListener() {
	      	@SuppressWarnings("unused")
			public void actionPerformed(ActionEvent e) {
	      		if ( rb.size() != 0 ) {
	      			frameRS.setEnabled(false);
	      			RS_updateKarta a = new RS_updateKarta((String)table.getValueAt(RadioButtonEditor.selectedRow, 1));
	      		}
	      	}
	      });
	      button2.setBackground(Color.WHITE);
	      button2.setFont(new Font("Tahoma", Font.BOLD, 14));
	      button2.setBounds(590, 341, 145, 44);
	      frameRS.getContentPane().add(button2);
	      
	      button3 = new JButton("Delete");
	      button3.addActionListener(new ActionListener() {
	      	public void actionPerformed(ActionEvent e) {
	      		if ( rb.size() != 0 ) {
		      		if ( KartaController.deleteKarta((String)table.getValueAt(RadioButtonEditor.selectedRow, 1)) ) {
		      			MainController.showMessage(frameRS, "Operacija je uspjesna.");
		      			input(true);
		      		}else {
		      			MainController.showMessage(frameRS, "Operacija nije uspjesna.");
		      		}
		      	}
	      	}
	      });
	      button3.setBackground(Color.WHITE);
	      button3.setFont(new Font("Tahoma", Font.BOLD, 14));
	      button3.setBounds(590, 396, 142, 44);
	      frameRS.getContentPane().add(button3);
		  frameRS.setVisible(true);
		  
		  table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	}
	
	@Override
	public void input(boolean flag) {
		if ( flag ) {
			clearTable();

			for (JRadioButton i : rb) {
				bg.remove(i);
			}
			
			rb.clear();
		}else {
			bg = new ButtonGroup();
		}
		
		String idAvion = "";
		if ( t1 != null ) {
			if ( t1.getText().length() >= 100 ) {
				idAvion = t1.getText().substring(0, 99);
			}else {
				idAvion = t1.getText();
			}
		}
		
		String datumP = "";
		if ( t2 != null ) {
			if ( t2.getText().length() >= 100 ) {
				datumP = t2.getText().substring(0, 99);
			}else {
				datumP = t2.getText();
			}
		}
		
		String vrijemeP = "";
		if ( t3 != null ) {
			if ( t3.getText().length() >= 100 ) {
				vrijemeP = t3.getText().substring(0, 99);
			}else {
				vrijemeP = t3.getText();
			}
		}
		
		String ime = "";
		if ( t4 != null ) {
			if ( t4.getText().length() >= 100 ) {
				ime = t4.getText().substring(0, 99);
			}else {
				ime = t4.getText();
			}
		}
		
		String prezime = "";
		if ( t5 != null ) {
			if ( t5.getText().length() >= 100 ) {
				prezime = t5.getText().substring(0, 99);
			}else {
				prezime = t5.getText();
			}
		}
		
		fillTable(KartaController.selectAllKarta(t1 == null ? "" : idAvion, t2 == null ? "" : datumP, 
				t3 == null ? "" : vrijemeP, t4 == null ? "" : ime, t5 == null ? "" : prezime));
	}
	
	public void fillTable(ArrayList <String> arr) {
		if (arr.size() != 0 ) {
			try {
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
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void clearTable() {
		try {
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.setRowCount(0);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void enableFrame() {
		frameRS.setEnabled(true);
	}
}

