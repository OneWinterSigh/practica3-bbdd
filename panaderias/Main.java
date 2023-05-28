package panaderias;

import java.util.Scanner;
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
		DBConnection conn = new DBConnection("localhost", 3306, "root", "1234", "panaderias");
		System.out.println("-- CONECTADO A LA BASE DE DATOS --");

		// Metodos de Empleado
		// // Crear un nuevo empleado
		Empleado e1 = new Empleado(8326, "97329", "carmen", "Martinez",
				"Cobo", conn, true);
		//
		System.out.println(e1);

		System.out.println(conn.tableExists("Local"));
		//
		/*
		 * System.out.println("Presiona Enter para continuar...");
		 * Scanner scanner = new Scanner(System.in);
		 * scanner.nextLine(); // Pausa la ejecución hasta que se presione Enter
		 * System.out.println("Continuando la ejecución...");
		 * scanner.close();
		 * System.out.println(e1);
		 */
		Empleado e2 = new Empleado(23423, conn, true);

		System.out.println("ID: " + e2.getId_empleado());

		System.out.println("N_ss: " + e2.getN_ss()); // Obtener el valor de
		// n_ss
		e2.setN_ss("1789"); // Actualizar el valor de n_ss en la base de datos
		//
		System.out.println("Nombre: " + e2.getNombre()); // Obtener el valor
		// de nombre
		e2.setNombre("Juan"); // Actualizar el valor de nombre en la base de
		// datos
		//
		System.out.println("Apellido1: " + e2.getApellido1()); // Obtener el
		// valor de apellido1
		e2.setApellido1("Perez"); // Actualizar el valor de apellido1 en la
		// base de datos
		//
		System.out.println("Apellido2: " + e2.getApellido2()); // Obtener el
		// valor de apellido2
		e2.setApellido2("Gomez"); // Actualizar el valor de apellido2 en la
		// base de datos

		System.out.println(e2);

		e2.insertEntry();
		System.out.println(e1);
		// System.out.println(e2.createTable());
		// System.out.println(conn.tableExists("Empleado"));
		boolean inser2 = e2.insertEntry();
		boolean inser1 = e1.insertEntry();
		System.out.println(inser1);
		System.out.println(inser2);

		// Metodos de Local

		// Crear un nuevo objeto Local
		Local l1 = new Local(2, true, "Calle Principal", "Local amplio y luminoso", conn, true);
		//
		//
		Local l3 = new Local(3, true, "Calle 3", "Local 3 y luminoso", conn,
				true);
		//
		//
		// // Probar los métodos de la clase Local
		System.out.println("ID Local: " + l1.getId_local());
		System.out.println("Tiene Cafetería: " + l1.getTiene_cafeteria());
		System.out.println("Dirección: " + l1.getDireccion());
		System.out.println("Descripción: " + l1.getDescripcion());
		//
		//// // Actualizar los atributos del objeto Local
		l1.setTiene_cafeteria(false);
		l1.setDireccion("Calle secundaria");
		l1.setDescripcion("Local pequeño y feo");

		////
		//// // Obtener los cambios de la entrada desde la base de datos
		l1.getEntryChanges();
		////
		//// // Mostrar los atributos actualizados
		System.out.println("ID Local: " + l1.getId_local());
		System.out.println("Tiene Cafetería: " + l1.getTiene_cafeteria());
		System.out.println("Dirección: " + l1.getDireccion());
		System.out.println("Descripción: " + l1.getDescripcion());
		//
		//// Eliminar la entrada de la base de datos
		boolean deleted = l1.deleteEntry();
		System.out.println(deleted ? "Entrada eliminada con éxito" : "Error al eliminar la entrada");

		// Metodos de Trabaja

		// Crear un nuevo registro de Trabaja
		int id_empleado = 1;
		int codigo = 1;
		java.sql.Date fecha_inicio = java.sql.Date.valueOf("2023-01-01");
		java.sql.Date fecha_fin = java.sql.Date.valueOf("2023-05-31");
		//
		Trabaja trabaja = new Trabaja(id_empleado, codigo, fecha_inicio, fecha_fin,
				conn, true);
		//
		//// // Obtener los datos de un registro existente de Trabaja
		Trabaja trabajaExistente = new Trabaja(2, 2, fecha_inicio, fecha_fin,
				conn, true);
		////
		//// // Modificar un registro de Trabaja
		trabajaExistente.setFecha_fin(java.sql.Date.valueOf("2023-04-30"));
		trabajaExistente.updateEntry();
		////
		//// // Eliminar un registro de Trabaja
		Trabaja trabajaAEliminar = new Trabaja(3, 3, fecha_inicio, fecha_fin,
				conn, true);
		trabajaAEliminar.deleteEntry();
		//
		//

		// // Prueba de getEmpleadosFromDB
		ArrayList<Empleado> empleadosFromDB = DataManager.getEmpleadosFromDB(conn, false);
		if (empleadosFromDB != null) {
			System.out.println("Empleados obtenidos de la base de datos:");
			for (Empleado empleado : empleadosFromDB) {
				System.out.println(empleado);
			}
		}

		String csvFile = "./panaderias/empleados.csv";
		Path filePath = Paths.get(csvFile);
		String absolutePath = filePath.toAbsolutePath().toString();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(absolutePath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		System.out.println(absolutePath);
		ArrayList<Empleado> empleadosFromCSV = DataManager.getEmpleadosFromCSV(absolutePath, conn, true);
		if (empleadosFromCSV != null) {
			System.out.println("Empleados obtenidos desde el archivo CSV:");
			for (Empleado empleado : empleadosFromCSV) {
				System.out.println(empleado);
			}
		}
		//
		String csvFile_locales = "./panaderias/locales.csv";
		Path filePath_locales = Paths.get(csvFile_locales);
		String absolutePath_locales = filePath_locales.toAbsolutePath().toString();
		System.out.println(absolutePath_locales);
		ArrayList<Local> localesFromCSV = DataManager.getLocalesFromCSV(absolutePath_locales, conn, true);
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

		ArrayList<Empleado> empleados = DataManager.getEmpleadosFromDB(conn, false);
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

		ArrayList<Local> locales = DataManager.getLocalesFromDB(conn, true);

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
		conn.close();
		System.out.println("Conexión cerrada correctamente");
		//

		// Insertar el empleado en la base de datos
		// boolean insertado = e1.insertEntry();

	}

}
