package src.panaderias;

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
