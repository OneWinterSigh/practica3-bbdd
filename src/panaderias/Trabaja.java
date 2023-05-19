package src.panaderias;

public class Trabaja extends DBTable {

	private int id_empleado;
	private int id_local;
	private java.sql.Date fecha_inicio;
	private java.sql.Date fecha_fin;

	public Trabaja(int id_empleado, int id_local, java.sql.Date fecha_inicio, DBConnection conn, boolean DBSync) {
		super(conn, DBSync);
	}

	public Trabaja(int id_empleado, int id_local, java.sql.Date fecha_inicio, java.sql.Date fecha_fin,
			DBConnection conn, boolean DBSync) {
		super(conn, DBSync);
	}

	public int getId_empleado() {
		return id_empleado;
	}

	public int getId_local() {
		return id_local;
	}

	public java.sql.Date getFecha_inicio() {
		return fecha_inicio;
	}

	public java.sql.Date getFecha_fin() {
		return fecha_fin;
	}

	public void setFecha_fin(java.sql.Date fecha_fin) {
		this.fecha_fin = fecha_fin;
	}

	public void destroy() {
	}

	boolean createTable() {
		return false;
	}

	boolean insertEntry() {
		return false;
	}

	boolean updateEntry() {
		return false;
	}

	boolean deleteEntry() {
		return false;
	}

	void getEntryChanges() {
	}

}
