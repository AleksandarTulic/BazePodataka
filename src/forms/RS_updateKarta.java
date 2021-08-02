package forms;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;

import helpFunctions.KartaComboBox;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;

import controller.KartaController;
import controller.MainController;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class RS_updateKarta extends KartaComboBox{

	private JFrame frameRS;
	private JTextField t1;
	
	public RS_updateKarta(String idKarta) {
		initialize(idKarta);
		
		inputCB1();
	}

	@SuppressWarnings({ "rawtypes"})
	private void initialize(String idKarta) {
		frameRS = new JFrame();
		frameRS.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				RS_view.enableFrame();
			}
		});
		frameRS.setBounds(100, 100, 561, 207);
		frameRS.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
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
		cb2.setBounds(251, 80, 125, 22);
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
		cb1.setBounds(251, 40, 260, 22);
		frameRS.getContentPane().add(cb1);
		
		cb3 = new JComboBox();
		cb3.setBounds(386, 80, 125, 22);
		frameRS.getContentPane().add(cb3);
		frameRS.getContentPane().setLayout(null);
		
		t1 = new JTextField();
		t1.setBounds(86, 45, 126, 20);
		frameRS.getContentPane().add(t1);
		t1.setColumns(10);
		
		JLabel l1 = new JLabel("JMB:");
		l1.setHorizontalAlignment(SwingConstants.CENTER);
		l1.setFont(new Font("Tahoma", Font.BOLD, 14));
		l1.setBounds(10, 48, 46, 14);
		frameRS.getContentPane().add(l1);
		
		JButton button1 = new JButton("Update");
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean flag = KartaController.updateKarta(idKarta, (String)cb1.getItemAt(cb1.getSelectedIndex()), (String)cb2.getItemAt(cb2.getSelectedIndex()), (String)cb3.getItemAt(cb3.getSelectedIndex()),
						t1.getText());
				
				if ( flag ) {
					MainController.showMessage(frameRS, "Operacija je uspjesna.");
				}else {
					MainController.showMessage(frameRS, "Operacija nije uspjesna.");
				}
				
				t1.setText("");
				inputCB1();
			}
		});
		button1.setBackground(Color.WHITE);
		button1.setFont(new Font("Tahoma", Font.BOLD, 18));
		button1.setBounds(10, 103, 202, 51);
		frameRS.getContentPane().add(button1);
		frameRS.setVisible(true);
	}
}
