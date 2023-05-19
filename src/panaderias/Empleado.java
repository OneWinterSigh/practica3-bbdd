package src.panaderias;

public class Empleado extends DBTable {

	private int id_empleado;
	private String n_ss;
	private String nombre;
	private String apellido1;
	private String apellido2;

	public Empleado(int id_empleado, DBConnection conn, boolean DBSync) {
		super(conn, DBSync);
	}

	public Empleado(int id_empleado, String n_ss, String nombre, String apellido1, String apellido2, DBConnection conn,
			boolean DBSync) {
		super(conn, DBSync);
	}

	public int getId_empleado() {
		return id_empleado;
	}

	public String getN_ss() {
		return n_ss;
	}

	public void setN_ss(String n_ss) {
		this.n_ss = n_ss;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
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
