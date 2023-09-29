package paquetePOO;

	public class DescuentoPorcentaje extends Descuento {
	public double precioFinal(double precioInicial) {
		return precioInicial - (precioInicial * this.precioConDescuento());
	}
}
