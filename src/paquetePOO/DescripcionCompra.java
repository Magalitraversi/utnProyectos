package paquetePOO;

import java.io.IOException;

public class DescripcionCompra {

	public static void main(String[] args) throws IOException {
		Producto producto1 = new Producto(0, "Yerba", 0001, 500);
		Producto producto2 = new Producto(0, "Jabón", 0002, 250);
		Producto producto3 = new Producto(0, "Shampoo", 0003, 620);

		Cliente cliente = new Cliente("Magali Aylen", 39555666, 341522211, "Avenida San Martín 1000");
		Carrito carrito1 = new Carrito(12, cliente);

		ItemCarrito item[] = new ItemCarrito[3];
		item[0] = new ItemCarrito(carrito1, producto1, 5);
		item[1] = new ItemCarrito(carrito1, producto2, 2);
		item[2] = new ItemCarrito(carrito1, producto3, 8);

		mostrarCompra(item, carrito1, cliente);
	}

	public static void mostrarCompra(ItemCarrito cargarItems[], Carrito carrito1, Cliente cliente) {
		System.out.println("Carro N°: " + carrito1.carroNumero() + " - " + "Cliente: " + cliente.nombreCliente());
		System.out.printf("%-10s %-10s %-10s %-10s\n", "Producto", "Cantidad", "Precio", "Subtotal");
		for (ItemCarrito items : cargarItems) {
			items.mostrarItem();
			carrito1.sumarItems(items);
		 }
		System.out.printf("%-32s $%-9.2f\n","Total: " , carrito1.totalCompra());
		Descuento dfijo = new DescuentoFijo();
		dfijo.montoDescuento(750);
		System.out.printf("%-32s $%-9.2f\n","Precio final: " , dfijo.precioFinal(carrito1.totalCompra()));
	} 
}
