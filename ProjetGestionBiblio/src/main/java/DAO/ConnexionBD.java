package DAO;

import java.sql.*;

public class ConnexionBD {
	public static String url = "jdbc:mysql://localhost:3306/biblio1" , user = "root" , password = "" ;
	public static Connection cnx = null;
	
	public static Connection seConnecter() {
		if (cnx == null) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				cnx = DriverManager.getConnection(url,user,password);
			} catch (Exception e) {
				System.out.println("erreur connextion BD");
			}
		}
		return cnx;
	}
	
	public static void deconnect () {
		if (cnx != null) {
			try {
				cnx.close();
				cnx = null;
			} catch (Exception e) {
				System.out.println("erreur deconnexion BD");
			}
		}
	}
	
}
