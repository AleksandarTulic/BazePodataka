package forms;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;

import controller.RedLetaController;
import formsMenu.jobKA;
import formsMenu.jobRS;
import helpFunctions.RadioButtonEditor;
import helpFunctions.RadioButtonRenderer;
import helpFunctions.interfaces.ViewInterface;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class view_redLeta implements ViewInterface{

	protected static JFrame frameRS;
	protected JTable table;
	protected JScrollPane sc;
	protected JTextField t1;
	protected JTextField t2;
	protected JTextField t3;
	protected JTextField t4;
	protected JButton button1;
	protected DefaultTableModel dtm;
	protected ButtonGroup bg;
	protected ArrayList<JRadioButton> rb = new ArrayList<JRadioButton>();

	public view_redLeta(int broj) {
		initialize(broj);
	}

	@SuppressWarnings("serial")
	private void initialize(int broj) {
		frameRS = new JFrame();
		frameRS.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if ( broj == 1 ) {
					jobRS.setAllEnabled();
				}else {
					jobKA.setAllEnabled();
				}
			}
		});
		frameRS.setTitle("ISA");
		frameRS.setBounds(100, 100, 770, 508);
		frameRS.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		dtm = new DefaultTableModel();
		dtm.setColumnIdentifiers(new Object[] {"Check", "idAvion", "Datum polaska", "Vrijeme polaska", "Ocekivano trajanje leta", "Mjesto polaska", "Mjesto Dolaska", "Aerodrom dolaska",
				"Vrijeme ukrcavanja"});
		
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
	      
	      button1 = new JButton("More info");
	      button1.addActionListener(new ActionListener() {
	      	@SuppressWarnings("unused")
			public void actionPerformed(ActionEvent e) {
	      		if ( rb.size() != 0 ) {
	      			frameRS.setEnabled(false);
	      			view_UcestvujeRedLeta vurl = new view_UcestvujeRedLeta((String)table.getValueAt(RadioButtonEditor.selectedRow, 1), (String)table.getValueAt(RadioButtonEditor.selectedRow, 2), 
	      					(String)table.getValueAt(RadioButtonEditor.selectedRow, 3));
	      		}
	      	}
	      });
	      button1.setBackground(Color.WHITE);
	      button1.setFont(new Font("Tahoma", Font.BOLD, 14));
	      button1.setBounds(536, 246, 196, 62);
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
	      
	      JLabel l4 = new JLabel("Mjesto dolaska:");
	      l4.setFont(new Font("Tahoma", Font.BOLD, 12));
	      l4.setBounds(536, 206, 100, 14);
	      frameRS.getContentPane().add(l4);
	      
	      frameRS.setVisible(true);
		  
		  table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	}

	@Override
	public void fillTable(ArrayList<String> arr) {
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
						String inp = line[j];
						inp = inp.replace('*', ' ');
						obj[j+1] = inp;
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
		
		fillTable(RedLetaController.selectALLRedLeta(t1 == null ? "" : idAvion, t2 == null ? "" : t2.getText(), 
				t3 == null ? "" : t3.getText(), t4 == null ? "" : t4.getText()));
	}

	public static void enableFrame() {
		frameRS.setEnabled(true);
	}
}
