package src.panaderias;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

import javax.naming.spi.DirStateFactory.Result;

//import apple.laf.JRSUIConstants.State;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection {

	final static String NULL_SENTINEL_VARCHAR = "NULL";
	final static int NULL_SENTINEL_INT = Integer.MIN_VALUE;
	final static java.sql.Date NULL_SENTINEL_DATE = java.sql.Date.valueOf("1900-01-01");

	private Connection conn = null;
	private String user;
	private String pass;
	private String url;

	public DBConnection(String server, int port, String user, String pass, String database) {
		this.user = user;
		this.pass = pass;
		this.url = "jdbc:mysql://" + server + ":" + port + "/" + database;
	}

	public boolean connect() {
		try {
			if (conn == null) {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection(url, user, pass);
			} else {
				return false;
			}

		} catch (ClassNotFoundException e) {
			System.out.println("Error: unable to load driver class!");
			return false;
		} catch (SQLException e) {
			System.out.println("Error: unable to connect to database!");
			return false;
		}

		return true;
	}

	public boolean close() {
		try {
			conn.close();
			return true;
		} catch (SQLException e) {
			System.out.println("Error: unable to close connection!");
			return false;
		}
	}

	// CHECK --->
	// Que se cierran las conexiones de manera adecuada y que da el resultado
	// esperado
	// -- mirar las diapositivas
	public int update(String sql) {
		try {
			Statement stmt = conn.createStatement();
			int filasAfectadas = stmt.executeUpdate(sql);
			stmt.close();
			return filasAfectadas;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
	}

	// TODO ----->
	public int update(String sql, ArrayList<Object> a) {
		return -1;
	}

	// Comprobar que devuelve null
	public ResultSet query(String sql) {
		Statement stmt = null;
		ResultSet result = null;
		try {
			stmt = conn.createStatement();
			result = stmt.executeQuery(sql);
		} catch (Exception e) {
			// Error Handling
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (Exception e) {
			}
			;
			try {
				if (result != null)
					result.close();
			} catch (Exception e) {
			}
			;
		}

		return result;
	}

	public ResultSet query(String sql, ArrayList<Object> a) {
		return null;
	}

	public boolean tableExists(String tableName) {
		return false;
	}

}
