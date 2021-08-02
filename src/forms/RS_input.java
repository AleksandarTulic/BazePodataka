package forms;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import controller.KartaController;
import controller.MainController;
import formsMenu.jobRS;
import helpFunctions.KartaComboBox;

import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class RS_input extends KartaComboBox{

	private JFrame frameRS;
	private JTextField t2;
	private JTextField t3;
	private JTextField t4;
	private JTextField t1;
	private ButtonGroup bg;
	private JRadioButton radio1;
	private JRadioButton radio2;
	private JLabel l1;
	private JLabel l2;
	private JLabel l3;
	private JLabel l4;
	private JLabel l5;
	
	public RS_input() {
		initialize();
		
		inputCB1();
	}

	@SuppressWarnings("rawtypes")
	private void initialize() {
		frameRS = new JFrame();
		frameRS.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				jobRS.setAllEnabled();
			}
		});
		frameRS.setTitle("ISA");
		frameRS.setBounds(100, 100, 603, 314);
		frameRS.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameRS.getContentPane().setLayout(null);
		
		t2 = new JTextField();
		t2.setBounds(77, 50, 146, 20);
		frameRS.getContentPane().add(t2);
		t2.setColumns(10);
		
		cb2 = new JComboBox();
		cb2.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if ( cb2.getItemAt(cb2.getSelectedIndex()) != null ) {
					String []line = ((String)cb2.getItemAt(cb2.getSelectedIndex())).split(" ");
					String idAvion = (String)cb1.getItemAt(cb1.getSelectedIndex());
					inputCB3(idAvion.split(" ")[0], line[0]);
				}
			}
		});
		cb2.setBounds(251, 63, 125, 22);
		frameRS.getContentPane().add(cb2);
		
		cb1 = new JComboBox();
		cb1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String redLeta = (String)cb1.getItemAt(cb1.getSelectedIndex());
				if ( redLeta != null ) {
					String []line = redLeta.split(" ");
					inputCB2(line[0], line[1], line[2]);
				}
			}
		});
		cb1.setBounds(251, 7, 260, 22);
		frameRS.getContentPane().add(cb1);
		
		cb3 = new JComboBox();
		cb3.setBounds(386, 63, 125, 22);
		frameRS.getContentPane().add(cb3);
		
		l2 = new JLabel("Ime:");
		l2.setHorizontalAlignment(SwingConstants.CENTER);
		l2.setBounds(21, 53, 46, 14);
		frameRS.getContentPane().add(l2);
		
		l3 = new JLabel("Srednje ime:");
		l3.setHorizontalAlignment(SwingConstants.CENTER);
		l3.setBounds(10, 89, 66, 14);
		frameRS.getContentPane().add(l3);
		
		l4 = new JLabel("Prezime:");
		l4.setHorizontalAlignment(SwingConstants.CENTER);
		l4.setBounds(21, 127, 46, 14);
		frameRS.getContentPane().add(l4);
		
		bg = new ButtonGroup();
		
		radio1 = new JRadioButton("Muski");
		radio1.setSelected(true);
		radio1.setBounds(53, 200, 109, 23);
		frameRS.getContentPane().add(radio1);
		
		radio2 = new JRadioButton("Zenski");
		radio2.setBounds(53, 226, 109, 23);
		frameRS.getContentPane().add(radio2);
		
		bg.add(radio1);
		bg.add(radio2);
		
		t3 = new JTextField();
		t3.setBounds(77, 86, 146, 20);
		frameRS.getContentPane().add(t3);
		t3.setColumns(10);
		
		t4 = new JTextField();
		t4.setBounds(76, 124, 146, 20);
		frameRS.getContentPane().add(t4);
		t4.setColumns(10);
		
		l5 = new JLabel("Pol:");
		l5.setHorizontalAlignment(SwingConstants.CENTER);
		l5.setBounds(10, 179, 46, 14);
		frameRS.getContentPane().add(l5);
		
		l1 = new JLabel("Jmb:");
		l1.setHorizontalAlignment(SwingConstants.CENTER);
		l1.setBounds(21, 11, 46, 14);
		frameRS.getContentPane().add(l1);
		
		t1 = new JTextField();
		t1.setBounds(77, 8, 146, 20);
		frameRS.getContentPane().add(t1);
		t1.setColumns(10);
		
		JButton button1 = new JButton("Insert");
		button1.setFont(new Font("Tahoma", Font.BOLD, 14));
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//moramo implementirati uzimanje podataka i proslijediti na input neki controller funkciju
				boolean flag = KartaController.input(t1.getText(), t2.getText(), t3.getText(), t4.getText(), radio1.isSelected() ? (byte)1 : (byte)0, (String)cb1.getItemAt(cb1.getSelectedIndex()), 
						(String)cb2.getItemAt(cb2.getSelectedIndex()), (String)cb3.getItemAt(cb3.getSelectedIndex()));
				
				if ( flag ) {
					MainController.showMessage(frameRS, "Operacija je uspjesna.");
				}else {
					MainController.showMessage(frameRS, "Operacija nije uspjesna.");
				}
				
				t1.setText("");
				t2.setText("");
				t3.setText("");
				t4.setText("");
				
				inputCB1();
			}
		});
		
		button1.setBackground(Color.WHITE);
		button1.setBounds(452, 215, 125, 49);
		frameRS.getContentPane().add(button1);
		
		JButton button2 = new JButton("Refresh Info");
		button2.setBackground(Color.WHITE);
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inputCB1();
			}
		});
		button2.setFont(new Font("Tahoma", Font.BOLD, 14));
		button2.setBounds(323, 215, 125, 49);
		frameRS.getContentPane().add(button2);
		
		frameRS.setVisible(true);
	}

	public JFrame getFrame() {
		return frameRS;
	}
}
