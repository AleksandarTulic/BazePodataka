package forms;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import controller.KartaController;

import java.awt.Font;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class RS_viewOK_one {

	private JFrame frame;

	public RS_viewOK_one(String jmb) {
		initialize(jmb);
	}

	private void initialize(String jmb) {
		frame = new JFrame();
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				RS_view.enableFrame();
			}
		});
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel l1 = new JLabel("JMB:");
		l1.setFont(new Font("Tahoma", Font.BOLD, 14));
		l1.setHorizontalAlignment(SwingConstants.CENTER);
		l1.setBounds(37, 62, 46, 14);
		frame.getContentPane().add(l1);
		
		JLabel l2 = new JLabel("Ime:");
		l2.setHorizontalAlignment(SwingConstants.CENTER);
		l2.setFont(new Font("Tahoma", Font.BOLD, 14));
		l2.setBounds(37, 99, 46, 14);
		frame.getContentPane().add(l2);
		
		JLabel l4 = new JLabel("Prezime:");
		l4.setFont(new Font("Tahoma", Font.BOLD, 14));
		l4.setHorizontalAlignment(SwingConstants.CENTER);
		l4.setBounds(25, 142, 75, 14);
		frame.getContentPane().add(l4);
		
		JLabel l3 = new JLabel("Srednje Ime:");
		l3.setHorizontalAlignment(SwingConstants.CENTER);
		l3.setFont(new Font("Tahoma", Font.BOLD, 14));
		l3.setBounds(10, 180, 100, 14);
		frame.getContentPane().add(l3);
		
		JLabel l5 = new JLabel("Pol:");
		l5.setFont(new Font("Tahoma", Font.BOLD, 14));
		l5.setHorizontalAlignment(SwingConstants.CENTER);
		l5.setBounds(37, 220, 46, 14);
		frame.getContentPane().add(l5);
		
		JLabel ll5 = new JLabel("");
		ll5.setBackground(Color.WHITE);
		ll5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		ll5.setBounds(136, 222, 160, 14);
		frame.getContentPane().add(ll5);
		
		JLabel ll4 = new JLabel("");
		ll4.setBackground(Color.WHITE);
		ll4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		ll4.setBounds(136, 182, 160, 14);
		frame.getContentPane().add(ll4);
		
		JLabel ll3 = new JLabel("");
		ll3.setBackground(Color.WHITE);
		ll3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		ll3.setBounds(136, 144, 160, 14);
		frame.getContentPane().add(ll3);
		
		JLabel ll2 = new JLabel("");
		ll2.setBackground(Color.WHITE);
		ll2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		ll2.setBounds(136, 101, 160, 14);
		frame.getContentPane().add(ll2);
		
		JLabel ll1 = new JLabel("");
		ll1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		ll1.setBackground(Color.WHITE);
		ll1.setBounds(136, 64, 160, 14);
		frame.getContentPane().add(ll1);
		
		JLabel lInfo = new JLabel("Information on Buyer");
		lInfo.setFont(new Font("Sitka Text", Font.BOLD, 17));
		lInfo.setBounds(10, 11, 225, 28);
		frame.getContentPane().add(lInfo);
		
		try {
			ArrayList<String> arr = KartaController.selectOsobaKarta(jmb);
			String res = arr.get(0);
			String []line = res.split(" ");
			
			ll1.setText(jmb);
			ll2.setText(line[0]);
			ll3.setText(line[1]);
			ll4.setText(line[2]);
			ll5.setText(line[3].equals("0") ? "Zenski" : "Muski");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		frame.setVisible(true);
	}

}
