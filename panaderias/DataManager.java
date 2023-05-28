package panaderias;

import java.io.BufferedReader;
import java.io.FileReader;

import java.util.ArrayList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DataManager {

	public static ArrayList<Empleado> getEmpleadosFromDB(DBConnection conn, boolean sync) {
		ArrayList<Empleado> result = null;
		try {
			ResultSet rsq = conn.query("SELECT * FROM EMPLEADO");
			result = new ArrayList<Empleado>();
			int i = 0;
			while (rsq != null && rsq.next()) {
				result.add(new Empleado(rsq.getInt(1), rsq.getString(5), rsq.getString(2), rsq.getString(3),
						rsq.getString(4), conn, false));
				result.get(i).setSync(true);
				i++;
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static ArrayList<Empleado> getEmpleadosFromCSV(String filename, DBConnection conn, boolean sync) {
		ArrayList<Empleado> empleados = new ArrayList<>();
		String l = "";
		BufferedReader bufferEmpleado = null;

		try {
			bufferEmpleado = new BufferedReader(new FileReader(filename));
			l = bufferEmpleado.readLine();
			while ((l = bufferEmpleado.readLine()) != null) {
				String[] data = l.split(";");
				Empleado empleadoLeido = new Empleado(Integer.parseInt(data[0]), data[1], data[2],
						data[3], data[4],
						conn,
						sync);
				if (sync) {
					empleadoLeido.insertEntry();
				}
				empleados.add(empleadoLeido);
			}
			return empleados;
		} catch (Exception e1) {
			e1.printStackTrace();
			return null;
		} finally {
			if (bufferEmpleado != null) {
				try {
					bufferEmpleado.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
	}

	public static ArrayList<Local> getLocalesFromDB(DBConnection conn, boolean sync) {
		ArrayList<Local> result = null;
		try {
			ResultSet rsq = conn.query("SELECT * FROM LOCAL");
			result = new ArrayList<Local>();
			int i = 0;
			while (rsq != null && rsq.next()) {
				result.add(
						new Local(rsq.getInt(1), rsq.getBoolean(2), rsq.getString(3), rsq.getString(4), conn, false));
				result.get(i).setSync(true);
				i++;
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static ArrayList<Local> getLocalesFromCSV(String filename, DBConnection conn, boolean sync) {
		ArrayList<Local> locales = new ArrayList<>();
		String linea = "";
		BufferedReader bufferLocales = null;

		try {
			bufferLocales = new BufferedReader(new FileReader(filename));
			linea = bufferLocales.readLine();
			while ((linea = bufferLocales.readLine()) != null) {
				String[] data = linea.split(";");
				Local localLeido = new Local(Integer.parseInt(data[0]), (data[1].equals("1")) ? true : false, data[2],
						data[3], conn, sync);
				if (sync) {
					localLeido.insertEntry();
				}
				locales.add(localLeido);
			}
			return locales;
		} catch (Exception e1) {
			e1.printStackTrace();
			return null;
		} finally {
			if (bufferLocales != null) {
				try {
					bufferLocales.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
	}

}
