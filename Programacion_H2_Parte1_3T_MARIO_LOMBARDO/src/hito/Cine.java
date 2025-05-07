package hito;

import java.sql.*;

public class Cine {
    public static void mostrarPeliculas() {
        // Obtenemos la conexión a la base de datos
        Connection conexion = Conexion.conectar();

        // Si la conexión es nula, no se puede continuar
        if (conexion == null) {
            return;
        }

        try {
            // Sentencia SQL para obtener las películas
            String query = "SELECT p.id_pelicula, p.titulo, p.director, p.duracion, g.nombre as genero "
                         + "FROM pelicula p "
                         + "JOIN genero g ON p.id_genero = g.id_genero";

            // Crear el Statement y ejecutar la consulta
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            // Mostramos los resultados
            while (rs.next()) {
                int idPelicula = rs.getInt("id_pelicula");
                String titulo = rs.getString("titulo");
                String director = rs.getString("director");
                int duracion = rs.getInt("duracion");
                String genero = rs.getString("genero");
                System.out.println("ID: " + idPelicula + ", Título: " + titulo + ", Director: " + director + ", Duración: " + duracion + " minutos, Género: " + genero);
            }
            
            // Cerramos la sentencia
            stmt.close();
        } catch (SQLException e) {
            // Mostramos un mensaje de error
            System.out.println("Error al obtener las películas: " + e.getMessage());
        } finally {
            try {
            	// Cerramos la conexión
            	conexion.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }

    public static void agregarPelicula(int idPelicula, String titulo, String director, int duracion, int idGenero) {
        // Obtenemos la conexión a la base de datos
        Connection conexion = Conexion.conectar();
        
        if (conexion == null) {
            return;
        }
        
        try {
        	// Creamos la sentencia preparada
        	String query = "INSERT INTO pelicula (id_pelicula, titulo, director, duracion, id_genero) VALUES (?, ?, ?, ?, ?)";
        	PreparedStatement pstmt = conexion.prepareStatement(query);
            
        	// Reemplazamos los parámetros de la consulta
        	pstmt.setInt(1, idPelicula);
        	pstmt.setString(2, titulo);
            pstmt.setString(3, director);
            pstmt.setInt(4, duracion);
            pstmt.setInt(5, idGenero);
            
            // Ejecutamos la sentencia preparada
            pstmt.executeUpdate();
            
            // Cerramos la sentencia
            pstmt.close();
            
            System.out.println("Película agregada con éxito.");            
        } catch (SQLException e) {
        	// Comprobamos si la excepción es por duplicidad de ID
        	if (e.getErrorCode() == 1062) {
        		System.out.println("Error: Ya existe una película con ese ID.");
        	} else {        		
        		System.out.println("Error al agregar película: " + e.getMessage());
        	}
        } finally {
            try {
            	// Cerramos la conexión
            	conexion.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
    
    public static void eliminarPelicula(int idPelicula) {
    	Connection conexion = Conexion.conectar();
    	
    	if (conexion == null) {
    		return;
    	}
    	
    	try {
    		String query = "DELETE FROM pelicula WHERE id_pelicula = ?";
    		PreparedStatement pstmt = conexion.prepareStatement(query);
    		
    		pstmt.setInt(1, idPelicula);
    		
            // Ejecutamos la sentencia preparada
            int filasAfectadas = pstmt.executeUpdate();            
            if (filasAfectadas > 0) {
            	System.out.println("Película eliminada con éxito.");            	
            } else {
            	System.out.println("No se ha encontrado una película con ese ID.");
            }
            
            // Cerramos la sentencia
            pstmt.close();
    	} catch (SQLException e) {
        	System.out.println("Error al agregar película: " + e.getMessage());
        } finally {
            try {
            	// Cerramos la conexión
            	conexion.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
    
    public static void modificarPelicula(int idPelicula, String titulo, String director) {
    	Connection conexion = Conexion.conectar();
    	
    	if (conexion == null) {
    		return;
    	}
    	
    	try {
    		String query = "UPDATE pelicula SET titulo = ?, director = ? WHERE id_pelicula = ?";
    		PreparedStatement pstmt = conexion.prepareStatement(query);
    		
    		pstmt.setString(1, titulo);
    		pstmt.setString(2, director);
    		pstmt.setInt(3, idPelicula);
    		
            // Ejecutamos la sentencia preparada
            int filasAfectadas = pstmt.executeUpdate();            
            if (filasAfectadas > 0) {
            	System.out.println("Película modificada con éxito.");            	
            } else {
            	System.out.println("No se ha encontrado una película con ese ID.");
            }
            
            // Cerramos la sentencia
            pstmt.close();
    	} catch (SQLException e) {
        	System.out.println("Error al agregar película: " + e.getMessage());
        } finally {
            try {
            	// Cerramos la conexión
            	conexion.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
}
