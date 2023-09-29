package paquetePOO;

public class ItemCarrito {
    private Carrito carro;
    private Producto prod;
    private int cantidad;
    private double precioItem;

    public ItemCarrito(Carrito carro, Producto producto, int cantidadProducto) {
        this.setCarro(carro);
        prod = producto;
        cantidad = cantidadProducto;
        precioItem = prod.precioProducto() * cantidadProducto;
    }

    public int cantidadProducto() {
        return cantidad;
    }

    public double precioItem() {
        return precioItem;
    }

    public void mostrarItem() {
        System.out.printf("%-10s %-10d $%-9.2f $%-9.2f\n", prod.nombreProducto(), cantidad, prod.precioProducto(), precioItem);
    }

	public Carrito getCarro() {
		return carro;
	}

	public void setCarro(Carrito carro) {
		this.carro = carro;
	}
}
