package panaderias;

import java.sql.ResultSet;
import java.sql.SQLException;

import panaderias.DBConnection.*;

public class Main {

	public static void main(String[] args) {
		String query = "SELECT * FROM Empleado";
		// Hacer pruebas aquí para comprobar la funcionalidad
		DBConnection conn = new DBConnection("localhost", 3306, "root", "1234", "panaderias");
		conn.connect();
		ResultSet empleados = conn.query(query);
		try {
			while (empleados.next()) {
				System.out.println(empleados.getString("nombre"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		conn.close();
	}

}
