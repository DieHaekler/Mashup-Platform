package ch.ffhs.inf09.pa.jans_mashup_platform.system.auth;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;
import com.orientechnologies.orient.core.sql.query.OSQLSynchQuery;

import java.sql.*;
import java.util.List;

public class login extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("OrientDB Connect Example.");
		
		Connection conn = null;
		
		//String url = "jdbc:mysql://localhost:3306/";
		//String dbName = "user_register";
		//String driver = "com.mysql.jdbc.Driver";
		
		String userName = "admin";
		String password = "admin";

		String username = "";
		String userpass = "";
		String strQuery = "";
		
		Statement st = null;
		ResultSet rs = null;
		
		// start write login in database
		
		//Lokale Session zur Erstellung der Mashup-Datenbank
		ODatabaseDocumentTx mashupDBLocal = new ODatabaseDocumentTx ("local:C:/orientdb-1.0rc6/databases/mashups");
		if(!mashupDBLocal.exists()){
			mashupDBLocal.create();
		}
				
		//Öffnen der Datenbankverbindung
		ODatabaseDocumentTx mashupDB = new ODatabaseDocumentTx("remote:localhost/mashups").open(userName, password);
		
		ODocument auth1 = new ODocument(mashupDB, "Auth"); 
		auth1.field("login", "jan");
		auth1.field("passwort", "passwort");
		
		auth1.save();
		
		// end write login in database
		
		response.setContentType("text/html");

		HttpSession session = request.getSession(true);

		try {
			
//			Class.forName(driver).newInstance();
//			conn = DriverManager
//					.getConnection(url + dbName, userName, password);
			
			
			
			if (request.getParameter("username") != null
					&& request.getParameter("username") != ""
					&& request.getParameter("password") != null
					&& request.getParameter("password") != "") {
				
				username = request.getParameter("username").toString();
				userpass = request.getParameter("password").toString();
				
				strQuery = "select * from Auth where username='"
						+ username + "' and  password='" + userpass + "'";
				System.out.println(strQuery);
				
//				st = conn.createStatement();
//				rs = st.executeQuery(strQuery);
				
				List<ODocument> auth = mashupDB.query(new OSQLSynchQuery<ODocument>(strQuery));
				
				int count = 0;
				while (rs.next()) {
					session.setAttribute("username", rs.getString(2));
					count++;
				}

				if (count > 0) {
					response.sendRedirect("welcome.jsp");
				} else {
					response.sendRedirect("login.jsp");
				}

			} else {
				response.sendRedirect("login.jsp");
			}

			System.out.println("Connected to the database");

//			conn.close();
			mashupDB.close();
			
			System.out.println("Disconnected from database");
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}