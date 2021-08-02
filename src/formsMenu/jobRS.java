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

import controller.MainController;
import forms.RS_input;
import forms.RS_view;
import forms.RS_viewOK;
import forms.view_redLeta;

public class jobRS {

	private JFrame frame;
	private JFrame LogIn;
	private JMenuItem mi1;
	private JMenuItem mi2;
	private JMenuItem mi3;
	private JMenuItem mi4;
	private JMenuItem mi5;
	private static JMenu mc1;
	private static JMenu mc2;
	private static JMenu mc3;
	private static JMenu mc4;

	public jobRS(JFrame LogIn) {
		MainController.flagWindow.put("jobRS", false);
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
		
		mc1 = new JMenu("Karta");
		menuBar.add(mc1);
		
		mi1 = new JMenuItem("view");
		mi1.addActionListener(new ActionListener() {
			@SuppressWarnings("unused")
			public void actionPerformed(ActionEvent e) {
				setAllDisabled();
				RS_view a = new RS_view();
			}
		});
		mc1.add(mi1);
		
		mi2 = new JMenuItem("input");
		mi2.addActionListener(new ActionListener() {
			@SuppressWarnings("unused")
			public void actionPerformed(ActionEvent e) {
				setAllDisabled();
				RS_input a = new RS_input();
			}
		});
		mc1.add(mi2);
		
		mc2 = new JMenu("OsobaKarta");
		menuBar.add(mc2);
		
		mi3 = new JMenuItem("view");
		mi3.addActionListener(new ActionListener() {
			@SuppressWarnings("unused")
			public void actionPerformed(ActionEvent e) {
				setAllDisabled();
				RS_viewOK a = new RS_viewOK();
			}
		});
		mc2.add(mi3);
		
		mc3 = new JMenu("RedLeta");
		menuBar.add(mc3);
		
		mi4 = new JMenuItem("view");
		mi4.addActionListener(new ActionListener() {
			@SuppressWarnings("unused")
			public void actionPerformed(ActionEvent e) {
				setAllDisabled();
				view_redLeta a = new view_redLeta(1);
			}
		});
		mc3.add(mi4);
		
		mc4 = new JMenu("Options");
		menuBar.add(mc4);
		
		mi5 = new JMenuItem("Log out");
		mi5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				LogIn.setVisible(true);
				frame.dispose();
			}
		});
		mc4.add(mi5);
		frame.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		frame.setVisible(true);
	}
	
	public static void setAllDisabled() {
		mc1.setEnabled(false);
		mc2.setEnabled(false);
		mc3.setEnabled(false);
		mc4.setEnabled(false);
	}
	
	public static void setAllEnabled() {
		mc1.setEnabled(true);
		mc2.setEnabled(true);
		mc3.setEnabled(true);
		mc4.setEnabled(true);
	}
}
