package paquetePOO;

public class DescuentoFijo extends Descuento {
	
	public double precioFinal(double precioInicial) {
		return precioInicial - this.precioConDescuento();
	}
}
