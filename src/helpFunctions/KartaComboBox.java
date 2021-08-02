package helpFunctions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JComboBox;

import mysqlManagement.Select;

public class KartaComboBox {
	@SuppressWarnings("rawtypes")
	protected JComboBox cb1;
	@SuppressWarnings("rawtypes")
	protected JComboBox cb2;
	@SuppressWarnings("rawtypes")
	protected JComboBox cb3;
	
	public void inputCB1() {
		cb1.removeAllItems();
		cb2.removeAllItems();
		cb3.removeAllItems();

		ArrayList <String> arr = new ArrayList<String>();
		arr.add("idAvion");arr.add("datum_polazak");arr.add("vrijeme_polazak");
		inputCB(cb1, "select idAvion, datum_polazak, vrijeme_polazak from select_redleta_DatumVrijeme", arr);
	}
	
	public void inputCB2(String idAvion, String datum_polazak, String vrijeme_polazak) {
		cb2.removeAllItems();
		cb3.removeAllItems();
		ArrayList <String> arr = new ArrayList<String>();
		arr.add("klasa"); arr.add("cijena"); arr.add("snizenje");
		inputCB(cb2, "call select_cijenaLeta('" + idAvion + "', '" + datum_polazak + "', '" + vrijeme_polazak + "');", arr);
	}
	
	@SuppressWarnings("unchecked")
	public void inputCB3(String idAvion, String klasa) {
		cb3.removeAllItems();
		ArrayList<String> arr = new ArrayList<String>();
		arr.add("brojS"); arr.add("maxBroj");
		inputCB(cb3, "call select_zauzeteKarte('" + idAvion + "', '" + klasa + "');", arr);
		
		Map <String, Boolean> flag = new HashMap<String, Boolean>();
		int maxBroj = -1;
		for (int i=0;i<cb3.getItemCount();i++) {
			String []line = ((String)cb3.getItemAt(i)).split(" ");
			maxBroj = Math.max(maxBroj, Integer.parseInt(line[1]));
			flag.put(line[0], true);
		}
		
		if (maxBroj == -1) {
			maxBroj = Integer.parseInt((String)Select.executeQuery("select broj_sjedista from klaseaviona where idAvion = '" + 
		idAvion + "' and klasa = '" + klasa + "';", "broj_sjedista"));
		}
		
		cb3.removeAllItems();
		for (int i=1;i<=maxBroj;i++) {
			if ( flag.get(String.valueOf(i)) == null ) {
				cb3.addItem(String.valueOf(i));
			}
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void inputCB(JComboBox cb, String query, ArrayList <String> arr) {
		Connection c = null;
		Statement s = null;
		ResultSet rs = null;
		
		//popunjavam sa idAVion, datum, vrijeme
		try {
			c = DriverManager.getConnection(Select.ost, Select.user, Select.dbP);
			s = c.createStatement();
			rs = s.executeQuery(query);

			while (rs.next()) {
				String item = "";
				
				for (int i=0;i<arr.size();i++) {
					item+=rs.getString(arr.get(i));
					
					if ( i < arr.size() - 1 ) {
						item+=" ";
					}
				}
				
				cb.addItem(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (s != null)
				try {
					s.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (c != null)
				try {
					c.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
}
