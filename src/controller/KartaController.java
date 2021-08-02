package controller;

import java.util.ArrayList;

import mysqlManagement.Select;

public class KartaController {
	public static boolean input(String jmb_ok, String ime, String srednjeIme, String prezime, byte pol, String redLeta, String cijenaLeta, String broj) {
		String input = "";
		
		try {
			String []line1 = redLeta.split(" ");
			String []line2 = cijenaLeta.split(" ");
			double cijena = Double.parseDouble(line2[1]);
			double snizenje = line2[2].equals("null") ? 0 : Double.parseDouble(line2[2]);
			cijena -= ((snizenje / 100) * cijena);
			if ( srednjeIme.equals("") ) {
				srednjeIme = null;
			}else {
				srednjeIme+="'";
				String buffer = srednjeIme;
				srednjeIme = "'";
				srednjeIme+=buffer;
			}
			input = "call insert_karta(" + broj + ", '" + line2[0] + "', 1, " 
			+ line2[1] + ", '" + MainController.korisnik.getJmb() + "', '" + jmb_ok + "', '" + line1[0] + "', '" + line1[1] + "', '" +
					line1[2]  + "', '" + ime + "', " + srednjeIme + ", '" + prezime + "', " + pol + ", @res);";
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return Select.executeQueryBool(input, "@res");
	}
	
	public static boolean updateKarta(String idKarta, String redLeta, String cijenaLeta, String brojS, String jmb_ok) {
		String query = "";
		
		try{
			String []line1 = redLeta.split(" ");
			String []line2 = cijenaLeta.split(" ");
			String idAvion = line1[0];
			String datumP = line1[1];
			String vrijemeP = line1[2];
			String klasa = line2[0];
			double cijena = Double.parseDouble(line2[1]);
			double snizenje = line2[2].equals("null") ? 0 : Double.parseDouble(line2[2]);
			cijena -= ((snizenje / 100) * cijena);
			String jmb_rs = MainController.korisnik.getJmb();

			query = "call update_karta(" + idKarta + ", " + brojS + ", '" + klasa + "', " + cijena + ", '" + jmb_rs + "', '" + jmb_ok + "', '" + idAvion + "', '" + datumP + "', '" + vrijemeP + "', @res);";
			System.out.println(query);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return Select.executeQueryBool(query, "@res");
	}
	
	public static ArrayList<String> selectAllKarta(String idAvion, String datum, String vrijeme, String ime, String prezime){
		idAvion = addApostrof(idAvion);
		datum = addApostrof(datum);
		vrijeme = addApostrof(vrijeme);
		ime = addApostrof(ime);
		prezime = addApostrof(prezime);
		
		String query = "call select_kartaUslov(" + idAvion + ", " + datum + ", " + vrijeme + ", " + ime + ", " + prezime + ");";
		ArrayList <String> arr = new ArrayList<String>();
		arr.add("idKarta"); arr.add("vrijeme_kupovine"); arr.add("datum_kupovine");
		arr.add("broj_sjedista"); arr.add("klasa"); arr.add("izlaz");
		arr.add("cijena"); arr.add("jmb_rs"); arr.add("jmb_ok");
		arr.add("idAvion");arr.add("datum_polazak");arr.add("vrijeme_polazak");
		
		return Select.executeSQLArray(query, arr);
	}
	
	public static ArrayList<String> selectALLOsobaKarta(String jmb, String ime, String prezime){
		jmb = addApostrof(jmb);
		ime = addApostrof(ime);
		prezime = addApostrof(prezime);
		
		String query = "call select_OK(" + jmb + ", " + ime + ", " + prezime + ");";
		ArrayList<String> arr = new ArrayList <String>();
		arr.add("jmb");arr.add("ime");arr.add("srednje_ime");arr.add("prezime");arr.add("pol");
		
		return Select.executeSQLArray(query, arr);
	}
	
	public static ArrayList<String> selectOsobaKarta(String jmb) {
		String query = "call select_osobaKarta('" + jmb + "');";
		ArrayList <String> arr = new ArrayList<String>();
		arr.add("ime");
		arr.add("srednje_ime");
		arr.add("prezime");
		arr.add("pol");
		
		return Select.executeSQLArray(query, arr);
	}
	
	public static String addApostrof(String a) {
		String buf = "'%";
		buf+=a;
		buf+="%'";
		
		return buf;
	}
	
	public static String addApostrofWithoutProcent(String a) {
		String buf = "'";
		buf+=a;
		buf+="'";
		
		return buf;
	}
	
	public static boolean updateOsobaKarta(String jmb1, String jmb2, String ime, String srednje_ime, String prezime, String pol) {
		if ( !srednje_ime.equals("null") ) {
			String buf = "'";
			buf += srednje_ime;
			buf += "'";
			srednje_ime = buf;
		}
		
		String query = "call update_osobaKarta('" + jmb1 + "', '" + jmb2 + "', '" + ime + "', " + srednje_ime + ", '" + prezime + "', " + pol + ", @res);";
	
		return Select.executeQueryBool(query, "@res");
	}
	
	public static boolean deleteOsobaKarta(String jmb) {
		String query = "call delete_osobaKarta('" + jmb + "', @res);";
		
		return Select.executeQueryBool(query, "@res");
	}
	
	public static boolean deleteKarta(String idKarta) {
		String query = "call delete_Karta('" + idKarta + "', @res);";
		
		return Select.executeQueryBool(query, "@res");
	}
}
