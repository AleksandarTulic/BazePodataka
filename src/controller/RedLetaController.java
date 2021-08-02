package controller;

import java.util.ArrayList;

import mysqlManagement.Select;

public class RedLetaController {
	public static ArrayList<String> selectALLRedLeta(String idAvion, String datumP, String vrijemeP, String dokle) {
		idAvion = KartaController.addApostrof(idAvion);
		datumP = KartaController.addApostrof(datumP);
		vrijemeP = KartaController.addApostrof(vrijemeP);
		dokle = KartaController.addApostrof(dokle);
		
		String query = "call select_ALLRedLeta(" + idAvion + ", " + datumP + ", " + vrijemeP + ", " + dokle + ");";
		ArrayList <String> arr = new ArrayList<String>();
		arr.add("idAvion");arr.add("datum_polazak");
		arr.add("vrijeme_polazak");arr.add("vrijeme_dolazak");
		arr.add("odakle");arr.add("dokle");
		arr.add("dolazak_aerodrom");arr.add("vrijeme_ukrcavanja");
		
		return Select.executeSQLArray(query, arr);
	}
	
	public static ArrayList<String> selectALLUcestvuje(String idAvion, String datumP, String vrijemeP){
		ArrayList <String> arr = new ArrayList<String>();
		arr.add("jmb");arr.add("ime");arr.add("srednje_ime");arr.add("prezime");arr.add("pol");arr.add("pozicija");
		
		ArrayList <String> res = new ArrayList<String>();
		
		//prvo pilote
		String query = "call select_ALLUcestvuje('" + idAvion + "', '" + datumP + "', '" + vrijemeP + "', 'pilot');";
		res.addAll(Select.executeSQLArray(query, arr));
		//sjuardese
		query = "call select_ALLUcestvuje('" + idAvion + "', '" + datumP + "', '" + vrijemeP + "', 'stjuardesa');";
		res.addAll(Select.executeSQLArray(query, arr));
		//r_prtljaga
		query = "call select_ALLUcestvuje('" + idAvion + "', '" + datumP + "', '" + vrijemeP + "', 'r_prtljag');";
		res.addAll(Select.executeSQLArray(query, arr));
		
		return res;
	}
	
	public static ArrayList<String> selectALLAvion(String naziv_a){
		ArrayList<String> arr = new ArrayList<String>();
		arr.add("idAvion"); arr.add("model"); arr.add("datum_proizvodnje"); arr.add("maksimalna_nosivost");
		arr.add("broj_mjesta"); arr.add("naziv_P");
		
		String query = "select idAvion, model, datum_proizvodnje, maksimalna_nosivost, broj_mjesta, naziv_P from avion where avion.naziv_A = '" + naziv_a + "';";
		
		return Select.executeSQLArray(query, arr);
	}
	
	public static ArrayList<String> selectALLZaposleniKompanije(String naziv, String tip){
		ArrayList<String> arr = new ArrayList<String>();
		arr.add("jmb"); arr.add("ime"); arr.add("srednje_ime"); arr.add("prezime"); arr.add("pol");
		
		String query = "";
		
		if ( tip.equals("pilot") ) {
			query = "call selectALLPilotKompanija('" + naziv + "');";
		}else if ( tip.equals("stjuardesa" ) ){
			query = "call selectALLStjuardesaKompanija('" + naziv + "');";
		}
		
		return Select.executeSQLArray(query, arr);
	}

	public static boolean inputRedLeta(String[] idAvion, String[] datumP, String[] vrijemeP, String[] vrijemeD,
			String[] vrijemeU, ArrayList<String> pilotJmb, ArrayList<String> stjuJmb, String dokle, String aerodromD, ArrayList<String> klasa, ArrayList<String> cijena, ArrayList<String> snizenje) {
		
		if ( aerodromD.equals("") ) {
			aerodromD = null;
		}else {
			aerodromD = KartaController.addApostrofWithoutProcent(aerodromD);
		}
		
		if ( vrijemeD[0].equals("") ) {
			vrijemeD[0] = null;
		}else {
			vrijemeD[0] = KartaController.addApostrofWithoutProcent(vrijemeD[0]);
		}
		
		if ( vrijemeU[0].equals("") ) {
			vrijemeU[0] = null;
		}else {
			vrijemeU[0] = KartaController.addApostrofWithoutProcent(vrijemeU[0]);
		}
		
		String input = "call insert_redLeta('" + idAvion[0] + "', '" + datumP[0] + "', '" + vrijemeP[0] + "', " + vrijemeD[0] + ", '" + MainController.odakle + "', '"
				+ dokle + "', " + aerodromD + ", " + vrijemeU[0] + ", @res);";
		
		System.out.println(input);
		
		ArrayList<Boolean> flagRes = new ArrayList<Boolean>();
		flagRes.add(Select.executeQueryBool(input, "@res"));
		
		for (int i=0;i<klasa.size();i++) {
			if ( !snizenje.get(i).equals("null") ) {
				snizenje.set(i, KartaController.addApostrofWithoutProcent(snizenje.get(i)));
			}
			
			input = "call insert_cijenaLeta('" + idAvion[0] + "', '" + datumP[0] + "', '" + vrijemeP[0] + "', '" + klasa.get(i) + "', '" + cijena.get(i) + "', " + snizenje.get(i) + ", @res);";
		
			flagRes.add(Select.executeQueryBool(input, "@res"));
		}
		
		for (int i=0;i<pilotJmb.size();i++) {
			input = "call insert_p_ucestvuje('" + pilotJmb.get(i) + "', '" + idAvion[0] + "', '" + datumP[0] + "', '" + vrijemeP[0] + "', @res);";
			
			flagRes.add(Select.executeQueryBool(input, "@res"));
		}
		
		for (int i=0;i<stjuJmb.size();i++) {
			input = "call insert_s_ucestvuje('" + stjuJmb.get(i) + "', '" + idAvion[0] + "', '" + datumP[0] + "', '" + vrijemeP[0] + "', @res);";
			
			flagRes.add(Select.executeQueryBool(input, "@res"));
		}
		
		if ( !flagRes.get(0) ) {
			return false;
		}
		
		int br = 0;
		for (int i=1;i<klasa.size()+1;i++) {
			if ( flagRes.get(i) ) br++;
		}
		
		if ( br == 0 ) {
			deleteRedLeta(idAvion[0], datumP[0], vrijemeP[0]);
			return false;
		}
		
		br = 0;
		
		for (int i=klasa.size()+1;i<klasa.size()+1+pilotJmb.size();i++) {
			if ( flagRes.get(i) ) br++;
		}
		
		if ( br == 0 ) {
			deleteRedLeta(idAvion[0], datumP[0], vrijemeP[0]);
			return false;
		}
		
		return true;
	}
	
	public static boolean deleteRedLeta(String idAvion, String datumP, String vrijemeP) {
		String query = "call delete_redLeta('" + idAvion + "', '" + datumP + "', '" + vrijemeP + "', @res);";

		return Select.executeQueryBool(query, "@res");
	}
}
