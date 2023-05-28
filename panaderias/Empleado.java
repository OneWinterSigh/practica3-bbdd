package panaderias;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Empleado extends DBTable {

	private int id_empleado;
	private int n_ss;
	private String nombre;
	private String apellido1;
	private String apellido2;

	public Empleado(int id_empleado, DBConnection conn, boolean DBSync) {
		super(conn, DBSync);
		this.id_empleado = id_empleado;
		this.n_ss = DBConnection.NULL_SENTINEL_INT;
		this.apellido1 = DBConnection.NULL_SENTINEL_VARCHAR;
		this.apellido2 = DBConnection.NULL_SENTINEL_VARCHAR;
		this.nombre = DBConnection.NULL_SENTINEL_VARCHAR;
		if ((conn.connect()) && DBSync) {
			if (!(conn.tableExists("Empleado"))) {
				createTable();
			}
			if (!this.insertEntry()) {
				this.id_empleado = DBConnection.NULL_SENTINEL_INT;
				setSync(false);
			}
		}
	}

	public Empleado(int id_empleado, int n_ss, String nombre, String apellido1, String apellido2, DBConnection conn,
			boolean DBSync) {

		super(conn, DBSync);
		this.n_ss = n_ss;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.nombre = nombre;
		this.id_empleado = id_empleado;

		if ((conn.connect()) && DBSync) {
			if (!(conn.tableExists("Empleado"))) {
				createTable();
			}
			if (!insertEntry()) {
				this.id_empleado = DBConnection.NULL_SENTINEL_INT;
				this.nombre = DBConnection.NULL_SENTINEL_VARCHAR;
				this.apellido1 = DBConnection.NULL_SENTINEL_VARCHAR;
				this.apellido2 = DBConnection.NULL_SENTINEL_VARCHAR;
				this.n_ss = DBConnection.NULL_SENTINEL_INT;
				setSync(false);
			}
		}
	}

	// no hace falta actualizar porque este no cambia
	public int getId_empleado() {
		if (DBSync) {
			getEntryChanges();
		}
		return id_empleado;
	}

	public int getN_ss() {
		if (DBSync) {
			getEntryChanges();
		}
		return n_ss;
	}

	public void setN_ss(int n_ss) {
		if (DBSync) {
			getEntryChanges();
		}
		this.n_ss = n_ss;
		if (DBSync) {
			updateEntry();
		}
	}

	public String getNombre() {
		if (DBSync) {
			getEntryChanges();
		}
		return nombre;
	}

	public void setNombre(String nombre) {
		if (DBSync) {
			getEntryChanges();
		}
		this.nombre = nombre;
		if (DBSync) {
			updateEntry();
		}
	}

	public String getApellido1() {
		if (DBSync) {
			getEntryChanges();
		}
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		if (DBSync) {
			System.out.println("Entra aqui");
			getEntryChanges();
		}
		this.apellido1 = apellido1;
		if (DBSync) {
			updateEntry();
		}
	}

	public String getApellido2() {
		if (DBSync) {
			getEntryChanges();
		}
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		if (DBSync) {
			getEntryChanges();
		}
		this.apellido2 = apellido2;
		if (DBSync) {
			updateEntry();
		}
	}

	public void destroy() {
		if (DBSync) {
			deleteEntry();
		}
		n_ss = DBConnection.NULL_SENTINEL_INT;
		apellido1 = DBConnection.NULL_SENTINEL_VARCHAR;
		apellido2 = DBConnection.NULL_SENTINEL_VARCHAR;
		nombre = DBConnection.NULL_SENTINEL_VARCHAR;
		id_empleado = DBConnection.NULL_SENTINEL_INT;
		setSync(false);
	}

	boolean createTable() {
		if (!this.conn.tableExists("Empleado")) {
			String query = "CREATE TABLE IF NOT EXISTS Empleado (" +
					id_empleado + "INT AUTO_INCREMENT NOT NULL," +
					nombre + "varchar(100) NOT NULL," +
					apellido1 + "varchar(100) NOT NULL," +
					apellido2 + "varchar(100) NOT NULL," +
					n_ss + "int NOT NULL," +
					"PRIMARY KEY(" + id_empleado + "));";

			return conn.update(query) > 0;
		} else {
			return false;
		}
	}

	boolean insertEntry() {
		try {
			if (conn.query("SELECT * FROM Empleado WHERE id_empleado = " + id_empleado).next()) {
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (nombre == null) {
			nombre = DBConnection.NULL_SENTINEL_VARCHAR;
		}
		if (apellido1 == null) {
			apellido1 = DBConnection.NULL_SENTINEL_VARCHAR;
		}
		if (apellido2 == null) {
			apellido2 = DBConnection.NULL_SENTINEL_VARCHAR;
		}
		if (this.conn.tableExists("Empleado")) {
			String query = "INSERT INTO Empleado VALUES ("
					+ id_empleado + ",'"
					+ nombre + "','"
					+ apellido1 + "','"
					+ apellido2 + "','"
					+ n_ss + "');";
			return conn.update(query) > 0;
		} else {
			// System.out.println("La tabla no existe");
			return false;
		}
	}

	boolean updateEntry() {
		if (!this.conn.tableExists("Empleado")) {
			String query = "UPDATE empleado SET n_ss = " + n_ss
					+ " nombre=" + nombre
					+ " apellido1=" + apellido1
					+ " apellido2=" + apellido2
					+ " WHERE id_empleado= " + id_empleado + "";
			return conn.update(query) > 0;
		} else {
			return false;
		}
	}

	boolean deleteEntry() {
		boolean deleted = false;
		if (this.conn.tableExists("Empleado")) {
			String query = "DELETE FROM Empleado WHERE id_empleado = " + id_empleado;
			deleted = conn.update(query) > 0;
		}
		return deleted;
	}

	void getEntryChanges() {
		ResultSet rs = null;
		if (this.conn.tableExists("Empleado")) {
			try {
				String query = "SELECT * FROM Empleado WHERE id_empleado = " + id_empleado;
				rs = conn.query(query);

				if (rs != null && rs.next()) { // tengo q mirar si es null? while?
					n_ss = rs.getInt("n_ss");
					nombre = rs.getString("nombre");
					apellido1 = rs.getString("apellido1");
					apellido2 = rs.getString("apellido2");
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

}
