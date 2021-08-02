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

import controller.KartaController;
import controller.MainController;
import formsMenu.jobRS;
import helpFunctions.RadioButtonEditor;
import helpFunctions.RadioButtonRenderer;
import helpFunctions.interfaces.ViewInterface;
import javax.swing.SwingConstants;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class RS_viewOK implements ViewInterface{

	private static JFrame frameRS;
	private JTable table;
	private JScrollPane sc;
	private JTextField t1;
	private JTextField t2;
	private JTextField t3;
	private JButton button1;
	private JButton button2;
	private DefaultTableModel dtm;
	private ButtonGroup bg;
	private ArrayList<JRadioButton> rb = new ArrayList<JRadioButton>();

	public RS_viewOK() {
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
		  dtm.setColumnIdentifiers(new Object[] {"Check", "jmb", "Ime", "Srednje Ime", "Prezime", "Pol"});
		  
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
	      sc.setBounds(0, 0, 470, 450);
	      frameRS.getContentPane().setLayout(null);
	      frameRS.getContentPane().add(sc);
	      
	      button1 = new JButton("Update");
	      button1.addActionListener(new ActionListener() {
	      	@SuppressWarnings("unused")
			public void actionPerformed(ActionEvent e) {
	      		if ( rb.size() != 0 ) {
	      			frameRS.setEnabled(false);
	      			RS_updateOK a = new RS_updateOK((String)table.getValueAt(RadioButtonEditor.selectedRow, 1));
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
	      
	      JLabel l1 = new JLabel("JMB:");
	      l1.setHorizontalAlignment(SwingConstants.CENTER);
	      l1.setFont(new Font("Tahoma", Font.BOLD, 12));
	      l1.setBounds(558, 25, 78, 14);
	      frameRS.getContentPane().add(l1);
	      
	      JLabel l2 = new JLabel("Ime:");
	      l2.setHorizontalAlignment(SwingConstants.CENTER);
	      l2.setFont(new Font("Tahoma", Font.BOLD, 12));
	      l2.setBounds(550, 87, 86, 14);
	      frameRS.getContentPane().add(l2);
	      
	      JLabel l3 = new JLabel("Prezime:");
	      l3.setHorizontalAlignment(SwingConstants.CENTER);
	      l3.setFont(new Font("Tahoma", Font.BOLD, 12));
	      l3.setBounds(536, 149, 100, 17);
	      frameRS.getContentPane().add(l3);
	      
	      button2 = new JButton("Delete");
	      button2.addActionListener(new ActionListener() {
	      	public void actionPerformed(ActionEvent e) {
	      		if ( rb.size() != 0 ) {
	      			if ( KartaController.deleteOsobaKarta((String)table.getValueAt(RadioButtonEditor.selectedRow, 1)) ){
	      				MainController.showMessage(frameRS, "Operacija je uspjesna.");
	      			}else {
	      				MainController.showMessage(frameRS, "Operacija nije uspjesna.");
	      			}
	      		}
	      	}
	      });
	      button2.setBackground(Color.WHITE);
	      button2.setFont(new Font("Tahoma", Font.BOLD, 14));
	      button2.setBounds(590, 341, 145, 44);
	      frameRS.getContentPane().add(button2);
	      
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
		
		fillTable(KartaController.selectALLOsobaKarta(t1 == null ? "" : t1.getText(), t2 == null ? "" : t2.getText(), t3 == null ? "" : t3.getText()));
	}
	
	public void fillTable(ArrayList <String> arr) {
		if ( arr.size() != 0 ) {
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
