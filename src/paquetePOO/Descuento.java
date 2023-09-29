package paquetePOO;

public abstract class Descuento {
private double precioDescuento;

public double precioConDescuento() {
	return precioDescuento; 
}
public void montoDescuento(double montoFijo) {
	precioDescuento = montoFijo;
}

public abstract double precioFinal(double precioInicial);
}
