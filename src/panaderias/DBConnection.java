package src.panaderias;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
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
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, pass);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean connect() {
		return false;
	}

	public boolean close() {
		return false;
	}

	public int update(String sql) {
		return -1;
	}

	public int update(String sql, ArrayList<Object> a) {
		return -1;
	}

	public ResultSet query(String sql) {
		return null;
	}

	public ResultSet query(String sql, ArrayList<Object> a) {
		return null;
	}

	public boolean tableExists(String tableName) {
		return false;
	}

}
