package paquetePOO;

public class Producto {
	private int codigo;
private String nombre;
private double precio;
private int cantidad;

public Producto (int codigo, String nombre, double precio, int cantidad){
	this.codigo = codigo;
	this.nombre = nombre;
	this.precio = precio;
	this.cantidad = cantidad;
}
public int numeroCodigo() {
	return codigo;
}
 public String nombreProducto() {
	 return nombre; 
 }
 public double precioProducto() {
	 return precio;
 }
 public int cantProducto() {
	 return cantidad;
 }
}
