package panaderias;
import java.util.ArrayList;
import java.io.StringBufferInputStream;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Iterator;

import javax.naming.spi.DirStateFactory.Result;

import com.mysql.cj.x.protobuf.MysqlxPrepare.Prepare;

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
				Class.forName("com.mysql.cj.jdbc.Driver");
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

		int nFilasAfectadas = 0;
		int contadorPos = 0;

		Iterator iter = a.iterator();
		Object par;

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);

			while (iter.hasNext()) {
				par = iter.next();

				if (par.getClass().getName() == "java.lang.Integer") {
					pstmt.setInt(contadorPos, (int) par);
					nFilasAfectadas++;
					contadorPos++;
				} else if (par.getClass().getName() == "java.lang.Float") {
					pstmt.setFloat(contadorPos, (float) par);
					nFilasAfectadas++;
					contadorPos++;
				} else if (par.getClass().getName() == "java.lang.String") {
					pstmt.setString(contadorPos, (String) par);
					nFilasAfectadas++;
					contadorPos++;
				} else if (par.getClass().getName() == "java.lang.Double") {
					pstmt.setDouble(contadorPos, (double) par);
					nFilasAfectadas++;
					contadorPos++;
				} else if (par.getClass().getName() == "java.sql.Date") {
					pstmt.setDate(contadorPos, (java.sql.Date) par);
					nFilasAfectadas++;
					contadorPos++;
				} else if (par.getClass().getName() == "java.sql.Time") {
					pstmt.setTime(contadorPos, (java.sql.Time) par);
					nFilasAfectadas++;
					contadorPos++;
				}
				// else?
			}

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}

		return nFilasAfectadas;
	}

	// Comprobar que devuelve null
	// No se pueden cerrar ni el stmt ni el result -> ¿Cuándo los cierro?
	public ResultSet query(String sql) {
		Statement stmt = null;
		ResultSet result = null;
		try {
			stmt = conn.createStatement();
			result = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public ResultSet query(String sql, ArrayList<Object> a) {
	
		int nFilasAfectadas = 0;
		ResultSet result;

		int contadorPos = 0;

		Iterator iter = a.iterator();
		Object par;

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);

			while (iter.hasNext()) {
				par = iter.next();

				if (par.getClass().getName() == "java.lang.Integer") {
					pstmt.setInt(contadorPos, (int) par);
					nFilasAfectadas++;
					contadorPos++;
				} else if (par.getClass().getName() == "java.lang.Float") {
					pstmt.setFloat(contadorPos, (float) par);
					nFilasAfectadas++;
					contadorPos++;
				} else if (par.getClass().getName() == "java.lang.String") {
					pstmt.setString(contadorPos, (String) par);
					nFilasAfectadas++;
					contadorPos++;
				} else if (par.getClass().getName() == "java.lang.Double") {
					pstmt.setDouble(contadorPos, (double) par);
					nFilasAfectadas++;
					contadorPos++;
				} else if (par.getClass().getName() == "java.sql.Date") {
					pstmt.setDate(contadorPos, (java.sql.Date) par);
					nFilasAfectadas++;
					contadorPos++;
				} else if (par.getClass().getName() == "java.sql.Time") {
					pstmt.setTime(contadorPos, (java.sql.Time) par);
					nFilasAfectadas++;
					contadorPos++;
				}
				// else?
			}

			result = pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return result;
	}

	public boolean tableExists(String tableName) {
		boolean res = false;
		try {
			ResultSet rs = query("SHOW TABLES");

			while (rs.next() && !res) {
				if (rs.getString(1).equals(tableName))
					res = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			res = false;
		}

		return res;
	}
}


