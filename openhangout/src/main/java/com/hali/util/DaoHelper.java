package com.hali.util;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoHelper {
	
		private static String url = "jdbc:mysql://localhost:3306/openhangout";
		private static String user = "mehdi";
		private static String password = "1234";
		
	public static Connection getDbConn() {
		Connection conn = null;
		try {
            Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url,user,password);
		}
		catch(Exception e) {
			System.out.println("DB DOWN");
		}
		return conn;
	}
	
	public static Connection getDbConn_remote(){
		URI jdbUri = null;
		Connection conn = null;
		try {
			System.out.println("JWSDB_URL"+System.getenv("JAWSDB_URL"));
			jdbUri = new URI(System.getenv("JAWSDB_URL"));
		}catch(URISyntaxException e) {
			e.printStackTrace();
		}

			String username = jdbUri.getUserInfo().split(":")[0];
			String password = jdbUri.getUserInfo().split(":")[1];
			String port = String.valueOf(jdbUri.getPort());
			String jdbUrl = "jdbc:mysql://" + jdbUri.getHost() + ":" + port + jdbUri.getPath();
			System.out.println("URL INFO");
			System.out.println("username "+username);
			System.out.println("password "+password);
			System.out.println("jdburl "+jdbUrl);

		try {
            Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(jdbUrl, username, password);
		}catch(Exception e) {
			e.printStackTrace();
		}
	    return conn;
	}


}
