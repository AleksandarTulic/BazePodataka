package mysqlManagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Select {
	public static Connection c = null;
	public static Statement s = null;
	public static ResultSet rs = null;
	public static final String user = "bazeP";
	public static final String dbP = "baze123baze";
	public static final String ost = "jdbc:mysql://localhost:3306/isa";

	public static Object executeQuery(String query, String index) {
		Object ret = null;
		
		try {
			c = DriverManager.getConnection(ost, user, dbP);
			s = c.createStatement();
			rs = s.executeQuery(query);
			
			while (rs.next()) {
				ret = rs.getString(index);
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
		
		return ret;
	}
	
	public static boolean executeQueryBool(String query, String index) {
		boolean flag = false;
		try {
			c = DriverManager.getConnection(ost, user, dbP);
			s = c.createStatement();
			rs = s.executeQuery(query);
			
			rs = s.executeQuery("select " + index);
			
			while (rs.next()) {
				if ( rs.getString(index) == null ) {
					flag = false;
				}else {
					flag = rs.getString(index).equals("1") ? true : false;
				}
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
		
		return flag;
	}
	
	public static ArrayList<String> executeSQLArray(String query, ArrayList <String> index){
		ArrayList <String> res = new ArrayList<String>();
		
		try {
			c = DriverManager.getConnection(ost, user, dbP);
			s = c.createStatement();

			rs = s.executeQuery(query);
			
			while (rs.next()) {
				String buf = "";
				
				for (int i=0;i<index.size();i++) {
					String inp = rs.getString(index.get(i));
					if ( inp == null ) {
						inp = "null";
					}
					
					inp = inp.replace(' ', '*');
					buf+=inp;
						
					if ( i != index.size() - 1 ) {
						buf+=" ";
					}
				}
				
				res.add(buf);
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
		
		return res;
	}
}
