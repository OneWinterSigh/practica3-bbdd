package src.panaderias;

import src.panaderias.DBConnection.*;

public class Main {

	public static void main(String[] args) {
		// Hacer pruebas aquí para comprobar la funcionalidad
		DBConnection conn = new DBConnection("localhost", 3306, "root", "1234", "panaderias");

		conn.connect();

		conn.close();
	}

}
