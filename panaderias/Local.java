package panaderias;

import java.sql.SQLException;

public class Local extends DBTable {

	private int id_local;
	private boolean tiene_cafeteria;
	private String direccion;
	private String descripcion;

	public Local(int id_local, DBConnection conn, boolean DBSync) {
		super(conn, DBSync);
	}

	public Local(int id_local, boolean tiene_cafeteria, String direccion, String descripcion, DBConnection conn,
			boolean DBSync) {
		super(conn, DBSync);
	}

	public int getId_local() {
		return id_local;
	}

	public boolean getTiene_cafeteria() {
		return tiene_cafeteria;
	}

	public void setTiene_cafeteria(boolean tiene_cafeteria) {
		this.tiene_cafeteria = tiene_cafeteria;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void destroy() {
	}

	boolean createTable() {
		String query = "CREATE TABLE IF NOT EXISTS local ("
				+ "id_local INT NOT NULL AUTO_INCREMENT,"
				+ "tiene_cafeteria BOOLEAN NOT NULL,"
				+ "direccion VARCHAR(100) NOT NULL,"
				+ "descripcion VARCHAR(100) NOT NULL,"
				+ "PRIMARY KEY (id_local)"
				+ ");";
		return conn.update(query) > 0;
	}

	boolean insertEntry() {
		if (direccion == null) {
			direccion = DBConnection.NULL_SENTINEL_VARCHAR;
		}
		/*
		 * if(tiene_cafeteria == null) {
		 * tiene_cafeteria = DBConnection.NULL_SENTINEL_BINARY;
		 * }
		 */
		if (descripcion == null) {
			descripcion = DBConnection.NULL_SENTINEL_VARCHAR;
		}

		// Write a query to insert a new entry into the table
		String query = "INSERT INTO local (id_local, tiene_cafeteria, direccion, descripcion) VALUES ("
				+ id_local + ", "
				+ tiene_cafeteria + ", "
				+ direccion + ", "
				+ descripcion + ");";

		return conn.update(query) > 0;
	}

	boolean updateEntry() {
		// Write a query to update an entry in the table
		String query = "UPDATE local SET "
				+ "tiene_cafeteria = " + tiene_cafeteria + ", "
				+ "direccion = " + direccion + ", "
				+ "descripcion = " + descripcion + " "
				+ "WHERE id_local = " + id_local + ";";
		return conn.update(query) > 0;
	}

	boolean deleteEntry() {
		// Create a query to delete the current entry from the table
		String query = "DELETE FROM local WHERE id_local = " + id_local + ";";

		return conn.update(query) > 0;
	}

	void getEntryChanges() {
	}

}
