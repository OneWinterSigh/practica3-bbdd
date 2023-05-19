import java.sql.*;

public class prueba {

    public void createConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "1234");
            System.out.println("Conexion exitosa");
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        prueba p = new prueba();
        p.createConnection();
    }
}