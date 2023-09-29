//package paquetePOO;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//
//
//
//public class App  {
//	public static void main(String[] args) throws SQLException {
//
//	// 1para hacer la conexción declaramos un obj de tipo conecction
//	 //2 le asignamos lo que nos devuelve a través del método get
//     Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/supermercado","root", "Matilda39455327");
//  //3Creo el tipo de obj statement para ejecutar la consulta
//     Statement statement = connection.createStatement();
//     //Creo la consulta para listar los clientes 
//   
//        String query = "SELECT * FROM productos";
//        //Paso la consulta a un obj de tipo ResultSet
//        ResultSet resultsetConsulta = statement.executeQuery(query);
// 		    
//	}
//}
package paquetePOO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class App {
    public static void main(String[] args) {
        // Establecer la conexión con la base de datos
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/supermercado", "root", "Matilda39455327")) {
            // Crear el objeto Statement para ejecutar la consulta
            try (Statement statement = connection.createStatement()) {
                // Crear la consulta para listar los productos
                String query = "SELECT * FROM productos";
                // Ejecutar la consulta y obtener el objeto ResultSet
                try (ResultSet resultSet = statement.executeQuery(query)) {
                    // Procesar los resultados de la consulta
                    while (resultSet.next()) {
                        // Aquí puedes realizar alguna acción con los datos obtenidos
                        // Por ejemplo, imprimir los valores de algunas columnas
                        String nombreProducto = resultSet.getString("nombre");
                        double precioProducto = resultSet.getDouble("precio");
                        System.out.println("Nombre: " + nombreProducto + ", Precio: " + precioProducto);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Manejo básico de la excepción: imprimir el rastreo de la excepción
            // Aquí puedes agregar tu lógica de manejo de excepciones personalizada
        }
    }
}