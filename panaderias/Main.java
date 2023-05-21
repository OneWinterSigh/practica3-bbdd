package panaderias;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import panaderias.DBConnection.*;

public class Main {

	public static void main(String[] args) {

		// *******Prueba de query*******
		String query = "SELECT * FROM Empleado";
		DBConnection conn = new DBConnection("localhost", 3306, "root", "1234", "panaderias");
		conn.connect();
		try {
			ResultSet empleados = conn.query(query);

			while (empleados.next()) {
				System.out.println(empleados.getString("nombre"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		conn.close();
		// **************** */
	}
}
