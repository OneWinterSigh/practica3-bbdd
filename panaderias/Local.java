package panaderias;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Local extends DBTable {

	private int id_local;
	private boolean tiene_cafeteria;
	private String direccion;
	private String descripcion;

	public Local(int id_local, DBConnection conn, boolean DBSync) {
		super(conn, DBSync);
		this.id_local = id_local;
		this.tiene_cafeteria = false;
		this.direccion = DBConnection.NULL_SENTINEL_VARCHAR;
		this.descripcion = DBConnection.NULL_SENTINEL_VARCHAR;
		if ((conn.connect()) && DBSync) {
			if (!(conn.tableExists("EMPLEADO"))) {
				createTable();
			}
			if (!this.insertEntry()) {
				this.id_local = DBConnection.NULL_SENTINEL_INT;
				setSync(false);
			}
		}
	}

	public Local(int id_local, boolean tiene_cafeteria, String direccion, String descripcion, DBConnection conn,
			boolean DBSync) {
		super(conn, DBSync);
		this.id_local = id_local;
		this.tiene_cafeteria = tiene_cafeteria;
		this.direccion = direccion;
		this.descripcion = descripcion;

		if ((conn.connect()) && DBSync) {
			if (!(conn.tableExists("EMPLEADO"))) {
				createTable();
			}
			if (!insertEntry()) {
				this.id_local = DBConnection.NULL_SENTINEL_INT;
				this.direccion = DBConnection.NULL_SENTINEL_VARCHAR;
				this.descripcion = DBConnection.NULL_SENTINEL_VARCHAR;
				this.tiene_cafeteria = false;
				setSync(false);
			}
		}
	}

	public int getId_local() {
		if (DBSync) {
			getEntryChanges();
		}
		return id_local;
	}

	public boolean getTiene_cafeteria() {
		if (DBSync) {
			getEntryChanges();
		}
		return tiene_cafeteria;
	}

	public void setTiene_cafeteria(boolean tiene_cafeteria) {
		if (DBSync) {
			getEntryChanges();
		}
		this.tiene_cafeteria = tiene_cafeteria;
		if (DBSync) {
			updateEntry();
		}
	}

	public String getDireccion() {
		if (DBSync) {
			getEntryChanges();
		}
		return direccion;
	}

	public void setDireccion(String direccion) {
		if (DBSync) {
			getEntryChanges();
		}
		this.direccion = direccion;
		if (DBSync) {
			updateEntry();
		}

	}

	public String getDescripcion() {
		if (DBSync) {
			getEntryChanges();
		}
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		if (DBSync) {
			getEntryChanges();
		}
		this.descripcion = descripcion;
		if (DBSync) {
			updateEntry();
		}
	}

	public void destroy() {
		if (DBSync) {
			deleteEntry();
		}
		id_local = DBConnection.NULL_SENTINEL_INT;
		descripcion = DBConnection.NULL_SENTINEL_VARCHAR;
		direccion = DBConnection.NULL_SENTINEL_VARCHAR;
		tiene_cafeteria = false;
		setSync(false);
	}

	boolean createTable() {
		if (!this.conn.tableExists("LOCAL")) {
			String query = "CREATE TABLE local ( id_local int not null, tiene_cafeteria int, direccion varchar(100), descripcion varchar(100), PRIMARY KEY ( id_local ))";
			return conn.update(query) > 0;
		} else {
			return false;
		}
	}

	boolean insertEntry() {
		try {
			if (conn.query("SELECT * FROM Empleado WHERE id_empleado = " + id_local).next()) {
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (direccion == null) {
			direccion = DBConnection.NULL_SENTINEL_VARCHAR;
		}
		if (descripcion == null) {
			descripcion = DBConnection.NULL_SENTINEL_VARCHAR;
		}
		// Write a query to insert a new entry into the table
		if (this.conn.tableExists("LOCAL")) {
			String query = "INSERT INTO local (id_local, tiene_cafeteria, direccion, descripcion) VALUES ("
					+ id_local + ", "
					+ tiene_cafeteria + ", "
					+ direccion + ", "
					+ descripcion + ")";
			return conn.update(query) > 0;
		} else {
			return false;
		}
	}

	boolean updateEntry() {
		if (!this.conn.tableExists("LOCAL")) {
			// Write a query to update an entry in the table
			String query = "UPDATE local SET "
					+ "tiene_cafeteria = " + tiene_cafeteria + ", "
					+ "direccion = " + direccion + ", "
					+ "descripcion = " + descripcion + " "
					+ "WHERE id_local = " + id_local + "";
			return conn.update(query) > 0;
		} else {
			return false;
		}
	}

	boolean deleteEntry() {
		boolean deleted = false;
		if (this.conn.tableExists("LOCAL")) {
			String query = "DELETE FROM local WHERE id_local = " + id_local;
			deleted = conn.update(query) > 0;
		}
		return deleted;
	}

	void getEntryChanges() {
		ResultSet rs = null;
		if (this.conn.tableExists("LOCAL")) {
			try {
				String query = "SELECT * FROM LOCAL WHERE id_local = " + id_local;
				rs = conn.query(query);

				if (rs != null && rs.next()) { // tengo q mirar si es null? while?
					descripcion = rs.getString("descripcion");
					direccion = rs.getString("direccion");
					tiene_cafeteria = rs.getBoolean("tiene_cafeteria");
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

}
