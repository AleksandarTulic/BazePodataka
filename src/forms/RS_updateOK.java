package forms;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import controller.KartaController;
import controller.MainController;

import javax.swing.ButtonGroup;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class RS_updateOK {

	private JFrame frameRS;
	private JTextField t1;
	private JTextField t2;
	private JTextField t3;
	private JTextField t4;
	private ButtonGroup bg;
	private String jmb;
	
	public RS_updateOK(String jmb) {
		this.jmb = jmb;
		initialize();
	}

	private void initialize() {
		frameRS = new JFrame();
		frameRS.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				RS_viewOK.enableFrame();
			}
		});
		frameRS.setTitle("ISA");
		frameRS.setBounds(100, 100, 581, 330);
		frameRS.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameRS.getContentPane().setLayout(null);
		
		JLabel l1 = new JLabel("JMB:");
		l1.setFont(new Font("Tahoma", Font.BOLD, 12));
		l1.setBounds(32, 71, 46, 14);
		frameRS.getContentPane().add(l1);
		
		JLabel l2 = new JLabel("Ime:");
		l2.setFont(new Font("Tahoma", Font.BOLD, 12));
		l2.setBounds(32, 110, 46, 14);
		frameRS.getContentPane().add(l2);
		
		JLabel l3 = new JLabel("Srednje Ime:");
		l3.setFont(new Font("Tahoma", Font.BOLD, 12));
		l3.setBounds(10, 150, 90, 14);
		frameRS.getContentPane().add(l3);
		
		JLabel l4 = new JLabel("Prezime");
		l4.setFont(new Font("Tahoma", Font.BOLD, 12));
		l4.setBounds(32, 192, 68, 14);
		frameRS.getContentPane().add(l4);
		
		JLabel l5 = new JLabel("Pol:");
		l5.setFont(new Font("Tahoma", Font.BOLD, 12));
		l5.setBounds(310, 72, 46, 14);
		frameRS.getContentPane().add(l5);
		
		JRadioButton radio1 = new JRadioButton("Muski");
		radio1.setBounds(334, 107, 109, 23);
		frameRS.getContentPane().add(radio1);
		
		JRadioButton radio2 = new JRadioButton("Zenski");
		radio2.setBounds(334, 147, 109, 23);
		frameRS.getContentPane().add(radio2);
		
		t1 = new JTextField();
		t1.setBounds(110, 68, 86, 20);
		frameRS.getContentPane().add(t1);
		t1.setColumns(10);
		
		t2 = new JTextField();
		t2.setBounds(110, 107, 86, 20);
		frameRS.getContentPane().add(t2);
		t2.setColumns(10);
		
		t3 = new JTextField();
		t3.setBounds(110, 147, 86, 20);
		frameRS.getContentPane().add(t3);
		t3.setColumns(10);
		
		t4 = new JTextField();
		t4.setBounds(110, 189, 86, 20);
		frameRS.getContentPane().add(t4);
		t4.setColumns(10);
		
		JButton button = new JButton("Update");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList <String> arr = KartaController.selectOsobaKarta(jmb);
				String res = arr.get(0);
				String []line = res.split(" ");
				
				String JMB_changed = t1.getText().length() == 0 ? jmb : t1.getText();
				String ime = t2.getText().length() == 0 ? line[0] : t2.getText();
				String srednje_ime = t3.getText().length() == 0 ? line[1] : t3.getText();
				String prezime = t4.getText().length() == 0 ? line[2] : t4.getText();
				String pol = radio1.isSelected() ? "1" : "0";
				
				if ( KartaController.updateOsobaKarta(jmb, JMB_changed, ime, srednje_ime, prezime, pol) ) {
					MainController.showMessage(frameRS, "Operacija je uspjesna.");
				}else {
					MainController.showMessage(frameRS, "Operacija nije uspjesna.");
				}
			}
		});
		button.setBackground(Color.WHITE);
		button.setFont(new Font("Tahoma", Font.BOLD, 14));
		button.setBounds(408, 210, 147, 70);
		frameRS.getContentPane().add(button);
		
		JLabel lblNewLabel = new JLabel("Update OK");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel.setBounds(10, 11, 186, 33);
		frameRS.getContentPane().add(lblNewLabel);
		
		bg = new ButtonGroup();
		bg.add(radio1);
		bg.add(radio2);
		
		radio1.setSelected(true);
		
		frameRS.setVisible(true);
	}
}
