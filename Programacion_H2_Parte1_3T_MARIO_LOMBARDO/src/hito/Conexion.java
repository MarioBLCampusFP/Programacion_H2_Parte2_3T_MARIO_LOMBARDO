package hito;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    public static Connection conectar() {
        String url = "jdbc:mysql://localhost:3306/cine_mariolombardo";
        String usuario = "root";
        String password = "";

        try {
        	// Creamos la conexión
            Connection conexion = DriverManager.getConnection(url, usuario, password);
            return conexion;
        } catch (SQLException e) {
        	// Devolvemos null en caso de fallar la conexión
            System.out.println("Error de conexión: " + e.getMessage());
            return null;
        }
    }
}
