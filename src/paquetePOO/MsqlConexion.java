package paquetePOO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.text.SimpleDateFormat;

public class MsqlConexion {
    private static List<Producto> itemcarrito; // Lista para almacenar los productos del carrito
    private static boolean clienteIngresado = false;
    private static int dniCliente; // Variable para almacenar el DNI del cliente

    public static void main(String[] args) {
        itemcarrito = new ArrayList<>(); // Inicializar la lista de productos del carrito

        try {
            // Establecer la conexión con la base de datos
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/supermercado", "root", "Matilda39455327");

            // Mostrar el menú principal
            mostrarMenu(connection);

            // Cerrar la conexión con la base de datos
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error en la conexión con la base de datos");
            System.out.println(e.getMessage());
        }
    }

    private static void mostrarMenu(Connection connection) throws SQLException {
        int opcion;
        Scanner scanner = new Scanner(System.in);

        boolean continuarCompra = true;
        do {
            System.out.println("=== Carrito de Compras ===");
            System.out.println("1. " + (clienteIngresado ? "Ingresar como cliente (Ya ingresado)" : "Ingresar como cliente"));
            System.out.println("2. Registrar cliente");
            System.out.println("3. Agregar producto");
            System.out.println("4. Ver carrito");
            System.out.println("5. Mostrar productos disponibles");
            System.out.println("6. Nuevo Producto");
            System.out.println("7. Calcular compra");
            System.out.println("8. Finalizar compra");
            System.out.println("9. Salir");
            System.out.print("Selecciona una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    if (clienteIngresado) {
                        System.out.println("Ya has ingresado como cliente.");
                    } else {
                        ingresarCliente(connection, scanner);
                        clienteIngresado = true;
                    }
                    break;
                case 2:
                    registrarCliente(scanner, connection);
                    break;
                case 3:
                    agregarProducto(scanner, connection);
                    break;
                case 4:
                    verCarrito();
                    break;
                case 5:
                    mostrarProductosDisponibles(connection);
                    break;
                case 6:
                    nuevoProducto(scanner, connection);
                    break;
                case 7:
                    calcularCompra();
                    break;
                case 8:
                    finalizarCompra(connection);
                    break;
                case 9:
                    System.out.println("Gracias por usar el carrito de compras. ¡Hasta luego!");
                    continuarCompra = false;
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, selecciona una opción válida.");
                    break;
            }
        } while (continuarCompra);
    }

    private static void registrarCliente(Scanner scanner, Connection connection) throws SQLException {
        System.out.println("=== Agregar Cliente ===");
        System.out.print("Ingrese el DNI del cliente: ");
        dniCliente = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea pendiente

        try {
            dniCliente = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea pendiente
        } catch (InputMismatchException e) {
            System.out.println("El valor ingresado para el DNI no es un número válido.");
            return;
        }

        scanner.nextLine(); // Consumir el salto de línea pendiente
        System.out.print("Ingrese el nombre del cliente: ");
        String nomCliente = scanner.nextLine();

        System.out.print("Ingrese el teléfono del cliente: ");
        long celuCliente = scanner.nextLong();
        scanner.nextLine(); // Consumir el salto de línea pendiente

        System.out.print("Ingrese la dirección del cliente: ");
        String direCliente = scanner.nextLine();

        System.out.print("Ingrese el email del cliente: ");
        String emailCliente = scanner.nextLine();

        // Verificar si el cliente ya existe en la base de datos
        String sqlVerificar = "SELECT * FROM cliente WHERE dniCliente = ?";

        try (PreparedStatement statementVerificar = connection.prepareStatement(sqlVerificar)) {
            statementVerificar.setInt(1, dniCliente);

            ResultSet resultSet = statementVerificar.executeQuery();

            if (resultSet.next()) {
                System.out.println("El cliente ya existe en la base de datos.");
            } else {
                // Agregar el cliente a la base de datos
                String sqlAgregarCliente = "INSERT INTO cliente (dniCliente, nomCliente, celuCliente, direCliente, emailCliente) VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement statementAgregar = connection.prepareStatement(sqlAgregarCliente)) {
                    statementAgregar.setInt(1, dniCliente);
                    statementAgregar.setString(2, nomCliente);
                    statementAgregar.setLong(3, celuCliente);
                    statementAgregar.setString(4, direCliente);
                    statementAgregar.setString(5, emailCliente);
                    statementAgregar.executeUpdate();

                    System.out.println("Cliente agregado correctamente.");
                }
            }
        }
    }

    private static void agregarProducto(Scanner scanner, Connection connection) throws SQLException {
        System.out.println("=== Agregar Producto ===");
        System.out.println("Productos disponibles:");

        mostrarProductosDisponibles(connection); // Mostrar la lista de productos disponibles

        System.out.print("Ingrese el ID del producto: ");
        int idProducto = scanner.nextInt();

        // Verificar si el producto existe en la base de datos
        String sqlVerificar = "SELECT * FROM productos WHERE idProducto = ?";
        try (PreparedStatement statementVerificar = connection.prepareStatement(sqlVerificar)) {
            statementVerificar.setInt(1, idProducto);
            ResultSet resultSet = statementVerificar.executeQuery();

            if (resultSet.next()) {
                // El producto existe en la base de datos
                String nombreProducto = resultSet.getString("nomProducto");
                double precioProducto = resultSet.getDouble("precioProducto");
                int cantidadDisponible = resultSet.getInt("cantidad");
                System.out.print("Ingrese la cantidad: ");
                int cantidad = scanner.nextInt();
                if (cantidad > cantidadDisponible) {
                    System.out.println("Lo sentimos, el producto se ha agotado.");
                } else {
                    itemcarrito.add(new Producto(idProducto, nombreProducto, precioProducto, cantidad)); // Agregar el producto a la lista del carrito
                    System.out.println("Producto agregado al carrito.");

                    // Actualizar la cantidad en la base de datos
                    int nuevaCantidad = cantidadDisponible - cantidad;

                    String sqlActualizarCantidad = "UPDATE productos SET cantidad = ? WHERE idProducto = ?";
                    try (PreparedStatement statementActualizar = connection.prepareStatement(sqlActualizarCantidad)) {
                        statementActualizar.setInt(1, nuevaCantidad);
                        statementActualizar.setInt(2, idProducto);
                        statementActualizar.executeUpdate();
                    }
                }
            } else {
                System.out.println("El producto no existe en la base de datos.");
            }
        }
    }

    private static void nuevoProducto(Scanner scanner, Connection connection) throws SQLException {
        System.out.println("=== Agregar Producto ===");
        System.out.print("Ingrese el ID del producto: ");
        int idProducto = scanner.nextInt();
        System.out.print("Ingrese el nombre del producto: ");
        String nombreProducto = scanner.next();
        System.out.print("Ingrese el precio del producto: ");
        double precioProducto = scanner.nextDouble();
        System.out.print("Ingrese la cantidad: ");
        int cantidad = scanner.nextInt();

        itemcarrito.add(new Producto(idProducto, nombreProducto, precioProducto, cantidad)); // Agregar el producto a la lista del carrito
        System.out.println("Producto agregado al carrito.");

        // Guardar el producto en la base de datos
        String sql = "INSERT INTO productos (idProducto, nomProducto, precioProducto, cantidad) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idProducto);
            statement.setString(2, nombreProducto);
            statement.setDouble(3, precioProducto);
            statement.setInt(4, cantidad);
            statement.executeUpdate();
        }
    }

    private static void mostrarProductosDisponibles(Connection connection) throws SQLException {
        String sql = "SELECT * FROM productos";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            System.out.println("ID\tPRECIO\tNOMBRE PRODUCTO:");
            while (resultSet.next()) {
                int idProducto = resultSet.getInt("idProducto");
                double precioProducto = resultSet.getDouble("precioProducto");
                String nomProducto = resultSet.getString("nomProducto");
                System.out.println(idProducto + "\t" + precioProducto + "\t" + nomProducto);
            }
        } catch (SQLException e) {
            System.out.println("Error al mostrar los productos disponibles: " + e.getMessage());
        }
    }

    private static void verCarrito() {
        System.out.println("=== Carrito de Compras ===");
        if (itemcarrito.isEmpty()) {
            System.out.println("El carrito está vacío.");
        } else {
            System.out.println("Productos en el carrito:");
            System.out.println("------------------------");
            for (Producto producto : itemcarrito) {
                System.out.println("ID: " + producto.numeroCodigo() + ", Producto: " + producto.nombreProducto() +
                        ", Precio: " + producto.precioProducto() + ", Cantidad: " + producto.cantProducto());
            }
        }
    }

    private static void calcularCompra() {
        System.out.println("=== Carrito de Compras ===");
        if (itemcarrito.isEmpty()) {
            System.out.println("El carrito está vacío.");
        } else {
            double total = 0.0;
            double descuento = 0.0;

            System.out.println("Productos en el carrito:");
            System.out.println("------------------------");

            for (Producto producto : itemcarrito) {
                String nombreProducto = producto.nombreProducto();
                int cantidad = producto.cantProducto();
                double precio = producto.precioProducto();

                System.out.println("Producto: " + nombreProducto);
                System.out.println("Cantidad: " + cantidad);
                System.out.println("Precio: " + precio);
                System.out.println("------------------------");

                double subtotal = precio * cantidad;
                total += subtotal;
            }

            // Calcular descuento si es miércoles
            Calendar calendar = Calendar.getInstance();
            int diaSemana = calendar.get(Calendar.DAY_OF_WEEK);
            boolean esMiercoles = (diaSemana == Calendar.WEDNESDAY);

            if (esMiercoles) {
                descuento = 700.0;
                total -= descuento;
            } else {
                descuento = total * 0.1;
                total -= descuento;
            }

            // Obtener la fecha y la hora actual
            LocalDate fechaActual = LocalDate.now();
            Date horaActual = new Date();

            // Formatear la fecha y la hora en HH:mm:ss
            DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String fechaFormateada = fechaActual.format(formatoFecha);
            SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
            String horaFormateada = formatoHora.format(horaActual);

            System.out.println("Fecha: " + fechaFormateada + "  Hora: " + horaFormateada);
            System.out.println("Total de la compra: " + total);
            System.out.println("Descuento aplicado: " + descuento);
        }
    }

    private static void finalizarCompra(Connection connection) throws SQLException {
        if (itemcarrito.isEmpty()) {
            System.out.println("El carrito está vacío. No se puede finalizar la compra.");
            return;
        }

        double totalCompra = 0;
        for (Producto producto : itemcarrito) {
            totalCompra += producto.precioProducto() * producto.cantProducto();
        }

        // Verificar si el cliente existe en la tabla cliente
        if (!verificarClienteExistente(connection, dniCliente)) {
            System.out.println("El cliente con DNI " + dniCliente + " no existe en la base de datos. No se puede finalizar la compra.");
            return;
        }

        // Obtener la fecha actual
        LocalDate fechaActual = LocalDate.now();

        // Insertar la compra en la tabla compras
        String sqlInsertarCompra = "INSERT INTO compras (dniCliente, total, fechaCompra) VALUES (?, ?, ?)";
        try (PreparedStatement statementInsertarCompra = connection.prepareStatement(sqlInsertarCompra, Statement.RETURN_GENERATED_KEYS)) {
            statementInsertarCompra.setInt(1, dniCliente);
            statementInsertarCompra.setDouble(2, totalCompra);
            statementInsertarCompra.setDate(3, java.sql.Date.valueOf(fechaActual)); // Asignar la fecha de compra
            statementInsertarCompra.executeUpdate();

            // Obtener el ID de la compra generada
            int idCompra = 0;
            try (ResultSet generatedKeys = statementInsertarCompra.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    idCompra = generatedKeys.getInt(1);
                    System.out.println("La compra ha sido registrada con éxito. ID de compra: " + idCompra);
                } else {
                    System.out.println("No se pudo obtener el ID de la compra generada.");
                    return;
                }
            }

            // Insertar los detalles de la compra en la tabla detalle_compra
            String sqlInsertarDetalles = "INSERT INTO detalle_compra (idCompra, idProducto, cantidad) VALUES (?, ?, ?)";
            try (PreparedStatement statementInsertarDetalles = connection.prepareStatement(sqlInsertarDetalles)) {
                for (Producto producto : itemcarrito) {
                    statementInsertarDetalles.setInt(1, idCompra);
                    statementInsertarDetalles.setInt(2, producto.numeroCodigo());
                    statementInsertarDetalles.setInt(3, producto.cantProducto());
                    statementInsertarDetalles.executeUpdate();
                }
            } catch (SQLException e) {
                System.out.println("Error al insertar los detalles de la compra: " + e.getMessage());
            }
            // Limpiar el carrito de compras después de finalizar la compra
            itemcarrito.clear();
        } catch (SQLException e) {
            System.out.println("Error al insertar la compra: " + e.getMessage());
        }
    }

    private static void ingresarCliente(Connection connection, Scanner scanner) throws SQLException {
        System.out.println("=== Ingresar Cliente ===");
        System.out.print("Ingrese el DNI del cliente: ");
        dniCliente = scanner.nextInt();

        if (!verificarClienteExistente(connection, dniCliente)) {
            System.out.println("El cliente con DNI " + dniCliente + " no existe en la base de datos.");
            return;
        }

        String sql = "SELECT dniCliente, nomCliente FROM cliente WHERE dniCliente = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, dniCliente);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String nomCliente = resultSet.getString("nomCliente");
                System.out.println("¡Bienvenido/a, " + nomCliente + "!");
            } else {
                System.out.println("Cliente no encontrado");
            }
        }
    }

    private static boolean verificarClienteExistente(Connection connection, int dniCliente) throws SQLException {
        String sql = "SELECT dniCliente FROM cliente WHERE dniCliente = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, dniCliente);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next(); // Si hay algún resultado, significa que el cliente existe
        }
    }
}

           
