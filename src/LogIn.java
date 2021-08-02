import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTextField;

import controller.MainController;
import formsMenu.jobKA;
import formsMenu.jobRS;
import korisnik.Korisnik;

import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.File;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Scanner;
import java.awt.event.ActionEvent;

public class LogIn{

	private JFrame frame;
	private JLabel lblNewLabel;
	private JButton button;
	private JTextField t1;
	private JPasswordField t2;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogIn window = new LogIn();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public LogIn() {
		MainController.readOdakle(frame);
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setTitle("ISA");
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 320, 443);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(System.getProperty("user.dir") + File.separator + "icons" + File.separator + "icon1.png"));
		lblNewLabel.setBounds(97, 25, 106, 92);
		frame.getContentPane().add(lblNewLabel);
		
		button = new JButton("Log in");
		button.addActionListener(new ActionListener() {
			@SuppressWarnings("unused")
			public void actionPerformed(ActionEvent e) {
				if ( checkAccount(t1.getText(), String.valueOf(t2.getPassword())) ) {
					if ( MainController.korisnik.getNazivKompanije().equals("Zaposleni Aerodroma") ) {
						jobRS a = new jobRS(frame);
					}else {
						jobKA a = new jobKA(frame);
					}
					
					frame.setVisible(false);
				}
			}
		});
		
		button.setBackground(Color.WHITE);
		button.setForeground(Color.BLACK);
		button.setFont(new Font("Tahoma", Font.BOLD, 14));
		button.setBounds(97, 323, 106, 35);
		frame.getContentPane().add(button);
		
		t1 = new JTextField();
		t1.setToolTipText("email or username");
		t1.setBounds(97, 176, 140, 20);
		frame.getContentPane().add(t1);
		t1.setColumns(10);
		
		t2 = new JPasswordField();
		t2.setBounds(97, 224, 140, 20);
		frame.getContentPane().add(t2);
		
		JLabel lblNewLabel_1 = new JLabel("username:");
		lblNewLabel_1.setBounds(24, 179, 72, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("password:");
		lblNewLabel_2.setBounds(24, 227, 63, 14);
		frame.getContentPane().add(lblNewLabel_2);
	}
	
	public void setFramevisibility(boolean flag) {
		frame.setVisible(flag);
	}
	
	private boolean checkAccount(String userName, String password) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			
			byte[] messageDigest = md.digest(password.getBytes());
			
			BigInteger no = new BigInteger(1, messageDigest);
			
			String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            
            File f = new File(System.getProperty("user.dir") + File.separator + "users.txt");
            
            Scanner scan = new Scanner(f);
            
            boolean flag = false;
            while (scan.hasNextLine()) {
            	String []line = scan.nextLine().split("-");
            	
            	if ( hashtext.equals(line[2]) && line[0].equals(userName) ) {
            		MainController.korisnik = new Korisnik(line[0], line[1]);
            		t1.setText("");
                    t2.setText("");  
            		flag = true;
            	}
            }
            
            scan.close();  
            
            if ( !flag ) {
            	MainController.showMessage(frame, "Ponovo pokusajte.");
            }else {
            	return true;
            }
		}catch (Exception e) {
			e.printStackTrace();
			frame.dispose();
		}
		
		return false;
	}
}
