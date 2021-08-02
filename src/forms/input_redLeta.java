package forms;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import controller.MainController;
import controller.RedLetaController;
import formsMenu.jobKA;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class input_redLeta {

	private static JFrame frame;
	private JTextField t1;
	private JTextField t2;
	private JTextField t3;
	private JTextField t4;
	private JTextField t5;
	private String []idAvion = new String[1];
	private String []datumP = new String[1];
	private String []vrijemeP = new String[1];
	private String []vrijemeD = new String[1];
	private String []vrijemeU = new String[1];
	private JCheckBox[] cc1 = new JCheckBox[1];
	private JCheckBox[] cc2 = new JCheckBox[1];
	private JCheckBox[] cc3 = new JCheckBox[1];
	private JCheckBox[] cc4 = new JCheckBox[1];
	private JCheckBox[] cc5 = new JCheckBox[1];
	private JCheckBox[] cc6 = new JCheckBox[1];
	private JCheckBox[] cc7 = new JCheckBox[1];
	private JCheckBox[] cc8 = new JCheckBox[1];
	private JCheckBox[] cc9 = new JCheckBox[1];
	private JCheckBox[] cc10 = new JCheckBox[1];
	private ArrayList<String> pilotJmb = new ArrayList<String>();
	private ArrayList<String> stjuJmb = new ArrayList<String>();
	private JTextField t6;
	private JTextField t7;
	private JTextField t8;

	public input_redLeta() {
		vrijemeP[0] = "";
		vrijemeU[0] = "";
		datumP[0] = "";
		vrijemeD[0] = "";
		idAvion[0] = "";
		
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				jobKA.setAllEnabled();
			}
		});
		frame.setBounds(100, 100, 651, 347);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel l1 = new JLabel("idAvion");
		l1.setToolTipText("Identifikator aviona");
		l1.setHorizontalAlignment(SwingConstants.CENTER);
		l1.setFont(new Font("Tahoma", Font.BOLD, 12));
		l1.setBounds(28, 19, 53, 14);
		frame.getContentPane().add(l1);
		
		JButton b1 = new JButton("...");
		b1.addActionListener(new ActionListener() {
			@SuppressWarnings("unused")
			public void actionPerformed(ActionEvent e) {
				frame.setEnabled(false);
				viewAvion a = new viewAvion(idAvion, cc1);
			}
		});
		b1.setBackground(Color.WHITE);
		b1.setFont(new Font("Tahoma", Font.BOLD, 12));
		b1.setBounds(91, 11, 45, 30);
		frame.getContentPane().add(b1);
		
		cc1[0] = new JCheckBox("");
		cc1[0].setEnabled(false);
		cc1[0].setBounds(152, 10, 21, 31);
		frame.getContentPane().add(cc1[0]);
		
		JLabel l2 = new JLabel("DatumP");
		l2.setToolTipText("Datum polaska");
		l2.setHorizontalAlignment(SwingConstants.CENTER);
		l2.setFont(new Font("Tahoma", Font.BOLD, 12));
		l2.setBounds(28, 80, 53, 14);
		frame.getContentPane().add(l2);
		
		JLabel l3 = new JLabel("VrijemeP");
		l3.setToolTipText("Vrijeme kada se polazi");
		l3.setHorizontalAlignment(SwingConstants.CENTER);
		l3.setFont(new Font("Tahoma", Font.BOLD, 12));
		l3.setBounds(28, 144, 53, 14);
		frame.getContentPane().add(l3);
		
		JLabel l5 = new JLabel("VrijemeD");
		l5.setToolTipText("Za koliko sati se dolazi");
		l5.setHorizontalAlignment(SwingConstants.CENTER);
		l5.setFont(new Font("Tahoma", Font.BOLD, 12));
		l5.setBounds(20, 265, 71, 14);
		frame.getContentPane().add(l5);
		
		JLabel l4 = new JLabel("VrijemeU");
		l4.setToolTipText("Vrijeme kada se ukrcava u avion");
		l4.setHorizontalAlignment(SwingConstants.CENTER);
		l4.setFont(new Font("Tahoma", Font.BOLD, 12));
		l4.setBounds(20, 200, 71, 14);
		frame.getContentPane().add(l4);
		
		t1 = new JTextField();
		t1.setBounds(304, 17, 266, 20);
		frame.getContentPane().add(t1);
		t1.setColumns(10);
		
		t2 = new JTextField();
		t2.setBounds(304, 78, 266, 20);
		frame.getContentPane().add(t2);
		t2.setColumns(10);
		
		JLabel l7 = new JLabel("Odrediste:");
		l7.setToolTipText("Koji grad je odrediste");
		l7.setHorizontalAlignment(SwingConstants.CENTER);
		l7.setFont(new Font("Tahoma", Font.BOLD, 12));
		l7.setBounds(218, 19, 71, 14);
		frame.getContentPane().add(l7);
		
		JLabel l8 = new JLabel("Dolazak:");
		l8.setToolTipText("Aerodrom dolaska");
		l8.setHorizontalAlignment(SwingConstants.CENTER);
		l8.setFont(new Font("Tahoma", Font.BOLD, 12));
		l8.setBounds(218, 80, 71, 14);
		frame.getContentPane().add(l8);
		
		JButton b2 = new JButton("...");
		b2.addActionListener(new ActionListener() {
			@SuppressWarnings("unused")
			public void actionPerformed(ActionEvent e) {
				frame.setEnabled(false);
				pickDate a = new pickDate(datumP, cc2);
			}
		});
		b2.setBackground(Color.WHITE);
		b2.setFont(new Font("Tahoma", Font.BOLD, 12));
		b2.setBounds(91, 72, 45, 30);
		frame.getContentPane().add(b2);
		
		cc2[0] = new JCheckBox("");
		cc2[0].setEnabled(false);
		cc2[0].setBounds(152, 72, 21, 31);
		frame.getContentPane().add(cc2[0]);
		
		JButton b3 = new JButton("...");
		b3.addActionListener(new ActionListener() {
			@SuppressWarnings("unused")
			public void actionPerformed(ActionEvent e) {
				frame.setEnabled(false);
				pickTime a = new pickTime(vrijemeP, cc3, 24);
			}
		});
		b3.setBackground(Color.WHITE);
		b3.setFont(new Font("Tahoma", Font.BOLD, 12));
		b3.setBounds(91, 136, 45, 30);
		frame.getContentPane().add(b3);
		
		cc3[0] = new JCheckBox("");
		cc3[0].setEnabled(false);
		cc3[0].setBounds(152, 136, 21, 31);
		frame.getContentPane().add(cc3[0]);
		
		JButton b4 = new JButton("...");
		b4.addActionListener(new ActionListener() {
			@SuppressWarnings("unused")
			public void actionPerformed(ActionEvent e) {
				frame.setEnabled(false);
				pickTime a = new pickTime(vrijemeU, cc4, 24);
			}
		});
		b4.setBackground(Color.WHITE);
		b4.setFont(new Font("Tahoma", Font.BOLD, 12));
		b4.setBounds(91, 197, 45, 30);
		frame.getContentPane().add(b4);
		
		cc4[0] = new JCheckBox("");
		cc4[0].setEnabled(false);
		cc4[0].setBounds(152, 197, 21, 31);
		frame.getContentPane().add(cc4[0]);
		
		JButton b5 = new JButton("...");
		b5.addActionListener(new ActionListener() {
			@SuppressWarnings("unused")
			public void actionPerformed(ActionEvent e) {
				frame.setEnabled(false);
				pickTime a = new pickTime(vrijemeD, cc5, 100);
			}
		});
		b5.setBackground(Color.WHITE);
		b5.setFont(new Font("Tahoma", Font.BOLD, 12));
		b5.setBounds(91, 257, 45, 30);
		frame.getContentPane().add(b5);
		
		cc5[0] = new JCheckBox("");
		cc5[0].setEnabled(false);
		cc5[0].setBounds(152, 257, 21, 31);
		frame.getContentPane().add(cc5[0]);
		
		JButton btnNewButton = new JButton("Input");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean flag = false;
				if ( !cc1[0].isSelected() || !cc2[0].isSelected() || !cc3[0].isSelected() || pilotJmb.size() == 0 || t1.getText().equals("")) {
					flag = true;
				}
				
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-M-d");  
				LocalDateTime now = LocalDateTime.now();
				String datecur = dtf.format(now);
				
				if ( datumP[0].equals(datecur) ) {
					dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
					String timecur = dtf.format(now);
					if ( compareTime(timecur, vrijemeP[0]) == 1 ) {
						flag = true;
					}
					
					if ( compareTime(timecur, vrijemeU[0]) == 1 ) {
						flag = true;
					}
				}
				
				if ( cc4[0].isSelected() ) {
					if ( compareTime(vrijemeP[0], vrijemeU[0]) == 2  ) {
						flag = true;
					}
				}
				
				int br = 0;
				br+= cc6[0].isSelected() ? 1 : 0;
				br+= cc7[0].isSelected() ? 1 : 0;
				br+= cc8[0].isSelected() ? 1 : 0;
				
				if ( br == 3 ) {
					flag = true;
				}
				
				ArrayList<String> klasa = new ArrayList<String>();
				ArrayList<String> cijena = new ArrayList<String>();
				ArrayList<String> snizenje = new ArrayList<String>();
				
				try {
					if ( !t3.getText().equals("") ) {
						Double c1 = Double.parseDouble(t3.getText());
						Double s1 = null;
						
						if ( !t6.getText().equals("") ) {
							s1 = Double.parseDouble(t6.getText());
						}
						
						klasa.add("economy");
						cijena.add(String.valueOf(c1));
						snizenje.add(s1 == null ? "null" : String.valueOf(s1));
					}
					
					if ( !t4.getText().equals("") ) {
						Double c2 = Double.parseDouble(t4.getText());
						Double s2 = null;
						
						if ( !t7.getText().equals("") ) {
							s2 = Double.parseDouble(t7.getText());
						}
						
						klasa.add("business");
						cijena.add(String.valueOf(c2));
						snizenje.add(s2 == null ? "null" : String.valueOf(s2));
					}
					
					if ( !t5.getText().equals("") ) {
						Double c3 = Double.parseDouble(t5.getText());
						Double s3 = null;
						
						if ( !t8.getText().equals("") ) {
							s3 = Double.parseDouble(t8.getText());
						}
						
						klasa.add("premium");
						cijena.add(String.valueOf(c3));
						snizenje.add(s3 == null ? "null" : String.valueOf(s3));
					}
				}catch (Exception ee) {
					ee.printStackTrace();
					flag = true;
				}
				
				if ( flag ) {
					boolean flagRes = RedLetaController.inputRedLeta(idAvion, datumP, vrijemeP, vrijemeD, vrijemeU, pilotJmb, stjuJmb, t1.getText(), t2.getText(), klasa, cijena, snizenje);
					
					if ( flagRes ) {
						MainController.showMessage(frame, "Operacija je uspjesna.");
					}else {
						MainController.showMessage(frame, "Operacija nije uspjesna.");
					}
				}else {
					MainController.showMessage(frame, "Operacija nije uspjesna.");
				}
				
				cc1[0].setSelected(false);
				cc2[0].setSelected(false);
				cc3[0].setSelected(false);
				cc4[0].setSelected(false);
				cc5[0].setSelected(false);
				cc6[0].setSelected(false); cc6[0].setEnabled(true);
				cc7[0].setSelected(false); cc7[0].setEnabled(true);
				cc8[0].setSelected(false); cc8[0].setEnabled(true);
				cc9[0].setSelected(false);
				cc10[0].setSelected(false);
				
				idAvion[0] = "";
				vrijemeP[0] = "";
				datumP[0] = "";
				vrijemeU[0] = "";
				vrijemeD[0] = "";
				stjuJmb.clear();
				pilotJmb.clear();
				
				t1.setText("");
				t2.setText("");
				t3.setText("");
				t4.setText("");
				t5.setText("");
				t6.setText("");
				t7.setText("");
				t8.setText("");
			}
		});
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton.setBounds(472, 232, 153, 65);
		frame.getContentPane().add(btnNewButton);
		
		JLabel l9 = new JLabel("EconomyClass");
		l9.setToolTipText("");
		l9.setHorizontalAlignment(SwingConstants.CENTER);
		l9.setFont(new Font("Tahoma", Font.BOLD, 12));
		l9.setBounds(199, 144, 106, 14);
		frame.getContentPane().add(l9);
		
		JLabel l10 = new JLabel("BusinessClass");
		l10.setToolTipText("");
		l10.setHorizontalAlignment(SwingConstants.CENTER);
		l10.setFont(new Font("Tahoma", Font.BOLD, 12));
		l10.setBounds(199, 200, 99, 14);
		frame.getContentPane().add(l10);
		
		JLabel l11 = new JLabel("PremiumClass");
		l11.setToolTipText("");
		l11.setHorizontalAlignment(SwingConstants.CENTER);
		l11.setFont(new Font("Tahoma", Font.BOLD, 12));
		l11.setBounds(199, 246, 106, 14);
		frame.getContentPane().add(l11);
		
		t3 = new JTextField();
		t3.setToolTipText("cijena");
		t3.setBounds(304, 142, 36, 20);
		frame.getContentPane().add(t3);
		t3.setColumns(10);
		
		t4 = new JTextField();
		t4.setToolTipText("cijena");
		t4.setColumns(10);
		t4.setBounds(304, 198, 36, 20);
		frame.getContentPane().add(t4);
		
		t5 = new JTextField();
		t5.setToolTipText("cijena");
		t5.setColumns(10);
		t5.setBounds(304, 244, 36, 20);
		frame.getContentPane().add(t5);
		
		cc6[0] = new JCheckBox("");
		cc6[0].setToolTipText("Klikom nema ove klase");
		cc6[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ( cc6[0].isSelected() ) {
					t3.setEnabled(false);
					t6.setEnabled(false);
					
					t3.setText("");
					t6.setText("");
				}else {
					t3.setEnabled(true);
					t6.setEnabled(true);
				}
			}
		});
		cc6[0].setBounds(411, 144, 21, 20);
		frame.getContentPane().add(cc6[0]);
		
		cc7[0] = new JCheckBox("");
		cc7[0].setToolTipText("Klikom nema ove klase");
		cc7[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ( cc7[0].isSelected() ) {
					t4.setEnabled(false);
					t7.setEnabled(false);
					
					t4.setText("");
					t7.setText("");
				}else {
					t4.setEnabled(true);
					t7.setEnabled(true);
				}
			}
		});
		cc7[0].setBounds(411, 197, 21, 17);
		frame.getContentPane().add(cc7[0]);
		
		cc8[0] = new JCheckBox("");
		cc8[0].setToolTipText("Klikom nema ove klase");
		cc8[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ( cc8[0].isSelected() ) {
					t5.setEnabled(false);
					t8.setEnabled(false);
					
					t5.setText("");
					t8.setText("");
				}else {
					t5.setEnabled(true);
					t8.setEnabled(true);
				}
			}
		});
		cc8[0].setBounds(411, 240, 21, 20);
		frame.getContentPane().add(cc8[0]);
		
		JLabel l12 = new JLabel("Piloti");
		l12.setToolTipText("");
		l12.setHorizontalAlignment(SwingConstants.CENTER);
		l12.setFont(new Font("Tahoma", Font.BOLD, 12));
		l12.setBounds(415, 145, 106, 14);
		frame.getContentPane().add(l12);
		
		JLabel l13 = new JLabel("Stjuardese");
		l13.setToolTipText("");
		l13.setHorizontalAlignment(SwingConstants.CENTER);
		l13.setFont(new Font("Tahoma", Font.BOLD, 12));
		l13.setBounds(415, 201, 106, 14);
		frame.getContentPane().add(l13);
		
		JButton b6 = new JButton("...");
		b6.addActionListener(new ActionListener() {
			@SuppressWarnings("unused")
			public void actionPerformed(ActionEvent e) {
				frame.setEnabled(false);
				viewPilot a = new viewPilot(pilotJmb, cc9);
			}
		});
		b6.setFont(new Font("Tahoma", Font.BOLD, 12));
		b6.setBackground(Color.WHITE);
		b6.setBounds(517, 136, 45, 30);
		frame.getContentPane().add(b6);
		
		JButton b7 = new JButton("...");
		b7.addActionListener(new ActionListener() {
			@SuppressWarnings("unused")
			public void actionPerformed(ActionEvent e) {
				frame.setEnabled(false);
				viewStjuardesa a = new viewStjuardesa(stjuJmb, cc10);
			}
		});
		b7.setFont(new Font("Tahoma", Font.BOLD, 12));
		b7.setBackground(Color.WHITE);
		b7.setBounds(517, 194, 45, 30);
		frame.getContentPane().add(b7);
		
		cc9[0] = new JCheckBox("");
		cc9[0].setEnabled(false);
		cc9[0].setBounds(573, 141, 21, 23);
		frame.getContentPane().add(cc9[0]);
		
		cc10[0] = new JCheckBox("");
		cc10[0].setEnabled(false);
		cc10[0].setBounds(573, 197, 21, 23);
		frame.getContentPane().add(cc10[0]);
		
		t6 = new JTextField();
		t6.setToolTipText("snizenje");
		t6.setColumns(10);
		t6.setBounds(350, 142, 36, 20);
		frame.getContentPane().add(t6);
		
		t7 = new JTextField();
		t7.setToolTipText("snizenje");
		t7.setColumns(10);
		t7.setBounds(350, 198, 36, 20);
		frame.getContentPane().add(t7);
		
		t8 = new JTextField();
		t8.setToolTipText("snizenje");
		t8.setColumns(10);
		t8.setBounds(350, 244, 36, 20);
		frame.getContentPane().add(t8);
	}
	
	private int compareTime(String time1, String time2) {
		for (int i=0;i<time1.length();i++) {
			if ( time1.charAt(i) > time2.charAt(i) ) {
				return 1;
			}else if (time1.charAt(i) < time2.charAt(i)) {
				return 2;
			}
		}
		
		return 0;
	}
	
	public static void enableFrame() {
		frame.setEnabled(true);
	}
}
