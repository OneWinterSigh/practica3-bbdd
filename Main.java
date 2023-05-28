
import java.util.Scanner;

import panaderias.DBConnection;
import panaderias.Empleado;
import panaderias.Local;
import panaderias.Trabaja;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

	public static void main(String[] args) {
		// Hacer pruebas aquí para comprobar la funcionalidad
		System.out.println("-- INICIO --");
		DBConnection conexion = new DBConnection("localhost", 3306, "root", "1234", "panaderias");
		conexion.connect();
		System.out.println("-- CONECTADO A LA BASE DE DATOS --");

		// Metodos de Empleado
		// // Crear un nuevo empleado
		Empleado empleado1 = new Empleado(8326, "97329", "carmen", "Martinez",
				"Cobo", conexion, true);
		//
		System.out.println(empleado1);
		//
		/*
		 * System.out.println("Presiona Enter para continuar...");
		 * Scanner scanner = new Scanner(System.in);
		 * scanner.nextLine(); // Pausa la ejecución hasta que se presione Enter
		 * System.out.println("Continuando la ejecución...");
		 * scanner.close();
		 * System.out.println(empleado1);
		 */
		Empleado empleado2 = new Empleado(8326, conexion, true);

		System.out.println("ID: " + empleado2.getId_empleado());

		System.out.println("N_ss: " + empleado2.getN_ss()); // Obtener el valor de
		// n_ss
		empleado2.setN_ss("1789"); // Actualizar el valor de n_ss en la base de datos
		//
		System.out.println("Nombre: " + empleado2.getNombre()); // Obtener el valor
		// de nombre
		empleado2.setNombre("Juan"); // Actualizar el valor de nombre en la base de
		// datos
		//
		System.out.println("Apellido1: " + empleado2.getApellido1()); // Obtener el
		// valor de apellido1
		empleado2.setApellido1("Perez"); // Actualizar el valor de apellido1 en la
		// base de datos
		//
		System.out.println("Apellido2: " + empleado2.getApellido2()); // Obtener el
		// valor de apellido2
		empleado2.setApellido2("Gomez"); // Actualizar el valor de apellido2 en la
		// base de datos

		System.out.println(empleado2);

		System.out.println(empleado1);
		// System.out.println(empleado2.createTable());
		// System.out.println(conexion.tableExists("Empleado"));
		boolean inser2 = empleado2.insertEntry();

		System.out.println(inser2);

		// // Ejemplo de actualización (update)
		String updateSql = "UPDATE Empleado SET nombre = 'Juan' WHERE id_empleado = 1";
		int rowsAffected = conexion.update(updateSql);
		System.out.println("Filas afectadas: " + rowsAffected);

		String selectSql = "SELECT * FROM Empleado";
		ResultSet resultSet = conexion.query(selectSql);
		try {
			while (resultSet.next()) {
				int id_empleado = resultSet.getInt("id_empleado");
				String nombre = resultSet.getString("nombre");
				System.out.println("ID Empleado: " + id_empleado + ", Nombre: " + nombre);
			}
		} catch (SQLException e) {
			e.printStackTrace(); // O maneja la excepción de alguna otra manera
		}

		// Metodos de Local

		// Crear un nuevo objeto Local
		Local local = new Local(2, true, "Calle Principal", "Local amplio y luminoso", conexion, true);
		//
		//
		Local local3 = new Local(3, true, "Calle 3", "Local 3 y luminoso", conexion,
				true);
		//
		//
		// // Probar los métodos de la clase Local
		System.out.println("ID Local: " + local.getId_local());
		System.out.println("Tiene Cafetería: " + local.getTiene_cafeteria());
		System.out.println("Dirección: " + local.getDireccion());
		System.out.println("Descripción: " + local.getDescripcion());
		//
		//// // Actualizar los atributos del objeto Local
		local.setTiene_cafeteria(false);
		local.setDireccion("Calle secundaria");
		local.setDescripcion("Local pequeño y feo");
		////
		// // Actualizar la entrada en la base de datos
		boolean updated = local.updateEntry();
		System.out.println(updated ? "Entrada actualizada con éxito" : "Error al actualizar la entrada");
		////
		//// // Obtener los cambios de la entrada desde la base de datos
		local.getEntryChanges();
		////
		//// // Mostrar los atributos actualizados
		System.out.println("ID Local: " + local.getId_local());
		System.out.println("Tiene Cafetería: " + local.getTiene_cafeteria());
		System.out.println("Dirección: " + local.getDireccion());
		System.out.println("Descripción: " + local.getDescripcion());
		//
		//// Eliminar la entrada de la base de datos
		boolean deleted = local.deleteEntry();
		System.out.println(deleted ? "Entrada eliminada con éxito" : "Error al eliminar la entrada");

		// Metodos de Trabaja

		// Crear un nuevo registro de Trabaja
		int id_empleado = 1;
		int id_local = 1;
		java.sql.Date fecha_inicio = java.sql.Date.valueOf("2023-01-01");
		java.sql.Date fecha_fin = java.sql.Date.valueOf("2023-05-31");
		//
		Trabaja trabaja = new Trabaja(id_empleado, id_local, fecha_inicio, fecha_fin,
				conexion, true);
		//
		//// // Obtener los datos de un registro existente de Trabaja
		Trabaja trabajaExistente = new Trabaja(2, 2, fecha_inicio, fecha_fin,
				conexion, true);
		////
		//// // Modificar un registro de Trabaja
		trabajaExistente.setFecha_fin(java.sql.Date.valueOf("2023-04-30"));
		trabajaExistente.updateEntry();
		////
		//// // Eliminar un registro de Trabaja
		Trabaja trabajaAEliminar = new Trabaja(3, 3, fecha_inicio, fecha_fin,
				conexion, true);
		trabajaAEliminar.deleteEntry();
		//
		//

		// // Prueba de getEmpleadosFromDB
		ArrayList<Empleado> empleadosFromDB = DataManager.getEmpleadosFromDB(conexion, false);
		if (empleadosFromDB != null) {
			System.out.println("Empleados obtenidos de la base de datos:");
			for (Empleado empleado : empleadosFromDB) {
				System.out.println(empleado);
			}
		}

		String csvFile = "src/data/empleados.csv";
		Path filePath = Paths.get(csvFile);
		String absolutePath = filePath.toAbsolutePath().toString();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(absolutePath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		System.out.println(absolutePath);
		ArrayList<Empleado> empleadosFromCSV = DataManager.getEmpleadosFromCSV(absolutePath, conexion, true);
		if (empleadosFromCSV != null) {
			System.out.println("Empleados obtenidos desde el archivo CSV:");
			for (Empleado empleado : empleadosFromCSV) {
				System.out.println(empleado);
			}
		}
		//
		String csvFile_locales = "src/data/locales.csv";
		Path filePath_locales = Paths.get(csvFile_locales);
		String absolutePath_locales = filePath_locales.toAbsolutePath().toString();
		System.out.println(absolutePath_locales);
		ArrayList<Local> localesFromCSV = DataManager.getLocalesFromCSV(absolutePath_locales, conexion, true);
		if (localesFromCSV != null) {
			System.out.println("Locales obtenidos desde el archivo CSV:");
			for (Local l : localesFromCSV) {
				System.out.println(l);
			}
		}

		//

		if (empleadosFromCSV != null) {
			System.out.println("Empleados obtenidos desde el archivo CSV:");
			for (Empleado empleado : empleadosFromCSV) {
				System.out.println(empleado);
			}
		}
		//

		if (localesFromCSV != null) {
			System.out.println("Empleados obtenidos desde el archivo CSV:");
			for (Local l : localesFromCSV) {
				System.out.println(l);
			}
		}

		ArrayList<Empleado> empleados = DataManager.getEmpleadosFromDB(conexion, false);
		if (empleados != null) {
			System.out.println("Empleados en la base de datos:");
			for (Empleado empleado : empleados) {
				System.out.println("ID: " + empleado.getId_empleado());
				System.out.println("N_SS: " + empleado.getN_ss());
				System.out.println("Nombre: " + empleado.getNombre());
				System.out.println("Apellido1: " + empleado.getApellido1());
				System.out.println("Apellido2: " + empleado.getApellido2());
				System.out.println("-------------");
			}
		} else {
			System.out.println("Error al obtener los empleados de la base de datos");
		}

		ArrayList<Local> locales = DataManager.getLocalesFromDB(conexion, true);

		if (locales != null) {
			System.out.println("Locales en la base de datos:");
			for (Local l : locales) {
				System.out.println("ID: " + l.getId_local());
				System.out.println("Tiene cafetería: " + l.getTiene_cafeteria());
				System.out.println("Dirección: " + l.getDireccion());
				System.out.println("Descripción: " + l.getDescripcion());
				System.out.println("-------------");
			}
		} else {
			System.out.println("Error al obtener los locales de la base de datos.");
		}

		// Cerrar la conexión a la base de datos
		// scanner.close();
		conexion.close();
		System.out.println("Conexión cerrada correctamente");
		//

		// Insertar el empleado en la base de datos
		// boolean insertado = empleado1.insertEntry();

	}

}
