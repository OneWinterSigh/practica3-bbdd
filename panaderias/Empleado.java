package panaderias;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

	//no hace falta actualizar porque este no cambia
	public int getId_empleado() {
		return id_empleado;
	}

	public String getN_ss() {
		if(DBSync){
			getEntryChanges();
		}
		return n_ss;
	}

	public void setN_ss(String n_ss) {
		if(DBSync){
			getEntryChanges();
		}
		this.n_ss = n_ss;
		if(DBSync){
			updateEntry();
		}
	}

	public String getNombre() {
		if (DBSync){
			getEntryChanges();
		}
		return nombre;
	}

	public void setNombre(String nombre) {
		if(DBSync){
			getEntryChanges();
		}
		this.nombre = nombre;
		if(DBSync){
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
			getEntryChanges();
		}
		this.apellido1 = apellido1;
		if(DBSync){
			updateEntry();
		}
	}

	public String getApellido2() {
		if (DBSync){
			getEntryChanges();
		}
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		if (DBSync){
			getEntryChanges();
		}
		this.apellido2 = apellido2;
		if(DBSync){
			updateEntry();
		}
	}

	public void destroy() {
		if(DBSync){
			deleteEntry();
			n_ss = DBConnection.NULL_SENTINEL_VARCHAR;
			apellido1 = DBConnection.NULL_SENTINEL_VARCHAR;
			apellido2 = DBConnection.NULL_SENTINEL_VARCHAR;
			nombre = DBConnection.NULL_SENTINEL_VARCHAR;
			id_empleado = DBConnection.NULL_SENTINEL_INT;
			setSync(false);
		}
	}

	boolean createTable() {
		if(this.conn.connect() && !this.conn.tableExists("EMPLEADO")){
			String query = "CREATE TABLE IF NOT EXISTS Empleado (" +
					id_empleado + " INT PRIMARY KEY," +
					n_ss + " VARCHAR(50)," +
					nombre+ " VARCHAR(50),"+
					apellido1 +" VARCHAR(50),"+
					apellido2 +" VARCHAR(50);";
            
            return conn.update(query) >0;
		}else{
			return false;
		}
	}
 
	boolean insertEntry() {
		if(n_ss == null){
			n_ss = DBConnection.NULL_SENTINEL_VARCHAR;
		}
		if(nombre == null){
			nombre = DBConnection.NULL_SENTINEL_VARCHAR;
		}
		if(apellido1 == null){
			apellido1 = DBConnection.NULL_SENTINEL_VARCHAR;
		}
		if (apellido2 == null){
			apellido2 = DBConnection.NULL_SENTINEL_VARCHAR;
		}
		if(this.conn.connect() && !this.conn.tableExists("EMPLEADO")){
			String query = "INSERT INTO empleado VALUES ("+ id_empleado + ", " 
															+ n_ss + ", " 
															+ apellido1 + "," 
															+ apellido2 + ")";
			return conn.update(query)> 0 ;
		}else{
			return false;
		}
	}

	boolean updateEntry() {
		if(this.conn.connect() && !this.conn.tableExists("EMPLEADO")){
			String query = "UPDATE empleado SET n_ss = "+ n_ss 
												+ " nombre=" + nombre 
												+ " apellido1=" + apellido1 
												+ " apellido2=" + apellido2
												+ " WHERE id_empleado= " + id_empleado + "";
			return conn.update(query) >0;
		}else{
			return false;
		}
	}

	boolean deleteEntry() {
		boolean deleted = false;
		if (this.conn.connect() && this.conn.tableExists("EMPLEADO")){
			String query = "DELETE FROM EMPLEADO WHERE id_empleado = " + getId_empleado();;
			deleted = conn.update(query)>0;	
		}
		return deleted;
	}
	
	void getEntryChanges() {
		ResultSet rs = null;
		if (conn.connect() && conn.tableExists("EMPLEADO")) {
			try {
				String query = "SELECT * FROM EMPLEADO WHERE id_empleado = " + getId_empleado();
				rs = conn.query(query);
				
				if (rs != null && rs.next()) { //tengo q mirar si es null? while?
					n_ss = rs.getString("n_ss");
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
