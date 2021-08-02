package forms;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.awt.Color;
import java.awt.event.ItemListener;
import java.time.LocalDate;
import java.util.Calendar;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class pickDate {

	private JFrame frame;
	@SuppressWarnings("rawtypes")
	private JComboBox cb1;
	@SuppressWarnings("rawtypes")
	private JComboBox cb2;
	@SuppressWarnings("rawtypes")
	private JComboBox cb3;
	
	public pickDate(String []date, JCheckBox[] cc) {
		initialize(date, cc);
		
		initYears();
	}

	@SuppressWarnings("rawtypes")
	private void initialize(String []date, JCheckBox[] cc) {
		frame = new JFrame();
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				input_redLeta.enableFrame();
			}
		});
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel l1 = new JLabel("Godina");
		l1.setHorizontalAlignment(SwingConstants.CENTER);
		l1.setFont(new Font("Tahoma", Font.BOLD, 12));
		l1.setBounds(10, 27, 46, 14);
		frame.getContentPane().add(l1);
		
		cb1 = new JComboBox();
		cb1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String year = (String)cb1.getItemAt(cb1.getSelectedIndex());
				
				if ( year != null ) {
					initMonths(year);
				}
			}
		});
		cb1.setBounds(83, 24, 58, 22);
		frame.getContentPane().add(cb1);
		
		JLabel l2 = new JLabel("Mjesec");
		l2.setHorizontalAlignment(SwingConstants.CENTER);
		l2.setFont(new Font("Tahoma", Font.BOLD, 12));
		l2.setBounds(10, 93, 46, 14);
		frame.getContentPane().add(l2);
		
		cb2 = new JComboBox();
		cb2.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String year = (String)cb1.getItemAt(cb1.getSelectedIndex());
				String month = (String)cb2.getItemAt(cb2.getSelectedIndex());
				
				if ( year != null && month != null ) {
					initDays(year, month);
				}
			}
		});
		cb2.setBounds(83, 90, 58, 22);
		frame.getContentPane().add(cb2);
		
		JLabel l3 = new JLabel("Dan");
		l3.setHorizontalAlignment(SwingConstants.CENTER);
		l3.setFont(new Font("Tahoma", Font.BOLD, 12));
		l3.setBounds(10, 168, 46, 14);
		frame.getContentPane().add(l3);
		
		cb3 = new JComboBox();
		cb3.setBounds(83, 165, 58, 22);
		frame.getContentPane().add(cb3);
		
		JButton btnNewButton = new JButton("OK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				date[0] = cb1.getItemAt(cb1.getSelectedIndex()) + "-" + cb2.getItemAt(cb2.getSelectedIndex()) + "-" + cb3.getItemAt(cb3.getSelectedIndex());
				cc[0].setSelected(true);
				frame.dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setBounds(279, 182, 145, 68);
		frame.getContentPane().add(btnNewButton);
		
		frame.setVisible(true);
	}

	@SuppressWarnings({ "unchecked"})
	private void initYears() {
		cb1.removeAllItems();
		cb2.removeAllItems();
		cb3.removeAllItems();
		
		LocalDate current_date = LocalDate.now();
        int year=current_date.getYear();
        //dodajem samo maksimalno 10 godina unaprijed planiranja leta i to je previse
		for (int i=year;i<year+10;i++) {
			cb1.addItem(String.valueOf(i));
		}
	}
	
	@SuppressWarnings("unchecked")
	private void initMonths(String year) {
		cb2.removeAllItems();
		cb3.removeAllItems();
		
		LocalDate current_date = LocalDate.now();
		
		int month = 1;
		if ( Integer.parseInt(year) == current_date.getYear() ) {
			month = current_date.getMonthValue();
		}
		
		for (int i=month;i<=12;i++) {
			cb2.addItem(String.valueOf(i));
		}
	}
	
	@SuppressWarnings("unchecked")
	private void initDays(String year, String month) {
		cb3.removeAllItems();
		
		int dayStart = 1;
		int dayMax = 30;
		LocalDate current_date = LocalDate.now();
		
		if ( current_date.getMonthValue() == Integer.parseInt(month) ) {
			dayStart = current_date.getDayOfMonth();
		}
		
		Calendar calendar = Calendar.getInstance();
		
		calendar.set(Integer.parseInt(year), Integer.parseInt(month), 1);
		dayMax = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		for (int i=dayStart;i<=dayMax;i++) {
			cb3.addItem(String.valueOf(i));
		}
	}
}
