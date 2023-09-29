package paquetePOO;

public class Carrito {
private int carroNumero;
private Cliente cliente;
private double total;

public Carrito(int carroNumero, Cliente cliente) {
	this.carroNumero = carroNumero;
	this.setCliente(cliente);
	total=0.0;	
}
public int carroNumero() {
	return carroNumero; 
}
public double totalCompra() {
	return total;
}
public void sumarItems(ItemCarrito precioItem) {
	total = total + precioItem.precioItem();
}
public Cliente getCliente() {
	return cliente;
}
public void setCliente(Cliente cliente) {
	this.cliente = cliente;
}
}
 