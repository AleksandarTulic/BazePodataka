package controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import korisnik.Korisnik;

public class MainController {
	public static String odakle = "";
	public static Korisnik korisnik = null;
	public static ArrayList<String> openWindow = new ArrayList<String>();
	public static Map <String, Boolean> flagWindow = new HashMap<String, Boolean>();
	
	public static void showMessage(JFrame frame, String message) {
		JOptionPane.showMessageDialog(frame, message);
	}
	
	public static void readOdakle(JFrame frame) {
		try {
			File f = new File(System.getProperty("user.dir") + File.separator + "odakle.txt");
			
			Scanner scan = new Scanner(f);
			
			if (scan.hasNextLine()) {
				odakle = scan.nextLine();
			}
			
			scan.close();
		}catch (Exception e) {
			e.printStackTrace();
			frame.dispose();
		}
	}
}
