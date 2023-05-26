package panaderias;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
public class Trabaja extends DBTable {

	private int id_empleado;
	private int id_local;
	private java.sql.Date fecha_inicio;
	private java.sql.Date fecha_fin;

	public Trabaja(int id_empleado, int id_local, java.sql.Date fecha_inicio, DBConnection conn, boolean DBSync) {
		super(conn, DBSync);
		this.id_empleado = id_empleado;
		this.id_local = id_local;
		this.fecha_inicio = fecha_inicio;
		this.fecha_fin = DBConnection.NULL_SENTINEL_DATE;
		if ((conn.connect()) && DBSync) {
			if (!(conn.tableExists("EMPLEADO"))) {
				createTable();
			}
			if (!this.insertEntry()) {
				this.id_empleado = DBConnection.NULL_SENTINEL_INT;
				this.id_local = DBConnection.NULL_SENTINEL_INT;
				this.fecha_inicio = DBConnection.NULL_SENTINEL_DATE;
				setSync(false);
			}
		}
	}

	public Trabaja(int id_empleado, int id_local, java.sql.Date fecha_inicio, java.sql.Date fecha_fin,
			DBConnection conn, boolean DBSync) {
		super(conn, DBSync);
		this.id_local = id_local;
		this.id_empleado = id_empleado;
		this.fecha_inicio = fecha_inicio;
		this.fecha_fin = fecha_fin;

		if ((conn.connect()) && DBSync) {
			if (!(conn.tableExists("EMPLEADO"))) {
				createTable();
			}
			if (!insertEntry()) {
				this.id_empleado = DBConnection.NULL_SENTINEL_INT;
				this.id_local = DBConnection.NULL_SENTINEL_INT;
				this.fecha_inicio = DBConnection.NULL_SENTINEL_DATE;
				this.fecha_fin =  DBConnection.NULL_SENTINEL_DATE;
				setSync(false);
			}
		}
	}

	public int getId_empleado() {
		if(DBSync){
			getEntryChanges();
		}
		return id_empleado;
	}

	public int getId_local() {
		if(DBSync){
			getEntryChanges();
		}
		return id_local;
	}

	public java.sql.Date getFecha_inicio() {
		if(DBSync){
			getEntryChanges();
		}
		return fecha_inicio;
	}

	public java.sql.Date getFecha_fin() {
		if(DBSync){
			getEntryChanges();
		}
		return fecha_fin;
	}

	public void setFecha_fin(java.sql.Date fecha_fin) {
		if(DBSync){
			getEntryChanges();
		}
		this.fecha_fin = fecha_fin;
		if(DBSync){
			updateEntry();
		}
	}

	public void destroy() {
		if(DBSync){
			deleteEntry();
		}
			id_local = DBConnection.NULL_SENTINEL_INT;
			id_empleado = DBConnection.NULL_SENTINEL_INT;
			fecha_inicio = DBConnection.NULL_SENTINEL_DATE;
			fecha_fin = DBConnection.NULL_SENTINEL_DATE;
			setSync(false);
	}

	boolean createTable() {
		if(!this.conn.tableExists("LOCAL")){
			String query = "CREATE TABLE trabaja(id_empleado int not null, id_local int not null, "
			+ "fecha_inicio date not null, fecha_fin date, PRIMARY KEY (id_empleado, "
			+ "id_local, fecha_inicio),FOREIGN KEY (id_empleado) REFERENCES empleado(id_empleado), "
			+ "FOREIGN KEY (id_local) REFERENCES local(id_local))";
			return conn.update(query) > 0;
		}else{
			return false;
		}
	}

	boolean insertEntry() {
		if (fecha_fin == null){
			fecha_fin = DBConnection.NULL_SENTINEL_DATE;
		}
		if(this.conn.tableExists("TRABAJA")){
			String query = "INSERT INTO trabaja (id_empleado, id_local, fecha_inicio, fecha_fin) VALUES ("
						+ id_empleado + ", "
						+ id_local + ", "
						+ fecha_inicio + ", "
						+ fecha_fin + ")";
			return conn.update(query)>0;
		}else{
			return false;
		}
	}

	boolean updateEntry() {
		if(!this.conn.tableExists("TRABAJA")){
			String query = "UPDATE local SET id_empleado= "+ id_empleado 
												+ " id_local=" + id_local 
												+ " fecha_inicio=" + fecha_inicio 
												+ " fecha_fin=" + fecha_fin
												+ " WHERE id_empleado= " + id_empleado 
												+ " AND id_local= " + id_local
												+ " AND fecha_inicio= " + fecha_inicio + "";
			return conn.update(query) >0;
		}else{
			return false;
		}
	}

	boolean deleteEntry() {
		boolean deleted = false;
		if (this.conn.tableExists("TRABAJA")){
			String query = "DELETE FROM TRABAJA WHERE id_empleado = " + id_empleado + " AND id_local=" + id_local + "AND fecha_inicio=" + fecha_inicio;
			deleted = conn.update(query)>0;	
		}
		return deleted;
	}

	void getEntryChanges() {
		ResultSet rs = null;
		if (this.conn.tableExists("TRABAJA")) {
			try {
				String query = "SELECT * FROM TRABAJA WHERE id_empleado = " + id_empleado + " AND id_local=" + id_local + "AND fecha_inicio=" + fecha_inicio;
				rs = conn.query(query);
				
				if (rs != null && rs.next()) { //tengo q mirar si es null? while?
					fecha_fin = rs.getDate("fecha_fin");
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} 
			
		}
	}

}
