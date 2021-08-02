package forms;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class pickTime {

	private JFrame frame;
	@SuppressWarnings("rawtypes")
	private JComboBox cb1;
	@SuppressWarnings("rawtypes")
	private JComboBox cb2;

	public pickTime(String []time, JCheckBox[] cc, int maksHour) {
		initialize(time, cc, maksHour);
		
		initHours(maksHour);
	}

	@SuppressWarnings("rawtypes")
	private void initialize(String []time, JCheckBox[] cc, int maksHour) {
		frame = new JFrame();
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				input_redLeta.enableFrame();
			}
		});
		frame.setBounds(100, 100, 450, 175);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel l1 = new JLabel("Sat");
		l1.setHorizontalAlignment(SwingConstants.CENTER);
		l1.setFont(new Font("Tahoma", Font.BOLD, 12));
		l1.setBounds(10, 27, 46, 14);
		frame.getContentPane().add(l1);
		
		cb1 = new JComboBox();
		cb1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String hour = (String)cb1.getItemAt(cb1.getSelectedIndex());
				
				if ( hour != null ) {
					initMinutes(maksHour, hour);
				}
			}
		});
		cb1.setBounds(83, 24, 58, 22);
		frame.getContentPane().add(cb1);
		
		JLabel l2 = new JLabel("Minut");
		l2.setHorizontalAlignment(SwingConstants.CENTER);
		l2.setFont(new Font("Tahoma", Font.BOLD, 12));
		l2.setBounds(10, 93, 46, 14);
		frame.getContentPane().add(l2);
		
		cb2 = new JComboBox();
		cb2.setBounds(83, 90, 58, 22);
		frame.getContentPane().add(cb2);
		
		JButton btnNewButton = new JButton("OK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				time[0] = cb1.getItemAt(cb1.getSelectedIndex()) + ":" + cb2.getItemAt(cb2.getSelectedIndex()) + ":00";
				cc[0].setSelected(true);
				frame.dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setBounds(279, 57, 145, 68);
		frame.getContentPane().add(btnNewButton);
		
		frame.setVisible(true);
	}
	
	@SuppressWarnings("unchecked")
	private void initHours(int maksHour) {
		cb1.removeAllItems();
		cb2.removeAllItems();
		
		for (int i=0;i<=maksHour;i++) {
			cb1.addItem(String.valueOf(i));
		}
	}
	
	@SuppressWarnings("unchecked")
	private void initMinutes(int maksHour, String hour) {
		cb2.removeAllItems();
		
		int end = 0;
		if ( maksHour == 24 && hour.equals("24") ) {
			end  = 0;
		}else {
			end = 59;
		}
		
		for (int i=0;i<=end;i++) {
			cb2.addItem(String.valueOf(i));
		}
	}

}
