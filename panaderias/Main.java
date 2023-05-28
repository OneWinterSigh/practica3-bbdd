package panaderias;

public class Main {
    public static void main(String[] args) {
        System.out.println("-- INICIO --");
        DBConnection conexion = new DBConnection("localhost", 3306, "root", "1234", "panaderias");
        conexion.connect();
        System.out.println("-- CONECTADO A LA BASE DE DATOS --");

        Empleado empleado1 = new Empleado(8326, 97329, "carmen", "Martinez",
                "Cobo", conexion, true);

        System.out.println(conexion.tableExists("Empleado"));
        System.out.println(empleado1.insertEntry() == true);

        conexion.close();
        System.out.println("Conexión cerrada correctamente");
    }
}
