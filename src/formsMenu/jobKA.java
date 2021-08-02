package formsMenu;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import forms.delete_redLeta;
import forms.input_redLeta;

public class jobKA {

	private JFrame frame;
	private JFrame LogIn;
	private static JMenu mc1;
	private static JMenu mc2;


	public jobKA(JFrame LogIn) {
		initialize();
		this.LogIn = LogIn;
	}

	private void initialize() {
		frame = new JFrame();
		frame.setTitle("ISA");
		frame.setBounds(100, 100, 687, 515);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.WHITE);
		menuBar.setFont(new Font("Segoe UI", Font.BOLD, 12));
		frame.setJMenuBar(menuBar);
		
		mc1 = new JMenu("RedLeta");
		menuBar.add(mc1);
		
		JMenuItem mi1 = new JMenuItem("view");
		mi1.addActionListener(new ActionListener() {
			@SuppressWarnings("unused")
			public void actionPerformed(ActionEvent e) {
				setAllDisabled();
				delete_redLeta a = new delete_redLeta(2);
			}
		});
		mc1.add(mi1);
		
		JMenuItem mi2 = new JMenuItem("input");
		mi2.addActionListener(new ActionListener() {
			@SuppressWarnings("unused")
			public void actionPerformed(ActionEvent e) {
				setAllDisabled();
				input_redLeta a = new input_redLeta();
			}
		});
		mc1.add(mi2);
		
		mc2 = new JMenu("Options");
		menuBar.add(mc2);
		
		JMenuItem mi3 = new JMenuItem("Log out");
		mi3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LogIn.setVisible(true);
				
				frame.dispose();
			}
		});
		mc2.add(mi3);
		frame.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		frame.setVisible(true);
	}
	
	public static void setAllDisabled() {
		mc1.setEnabled(false);
		mc2.setEnabled(false);
	}
	
	public static void setAllEnabled() {
		mc1.setEnabled(true);
		mc2.setEnabled(true);
	}

}
