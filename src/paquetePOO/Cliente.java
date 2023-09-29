package paquetePOO;

public class Cliente {
private int id;	
private int dni;
private String nombreCliente;
private int celular;
private String direccion;
private String email;

public Cliente( int idCli, int dni, String nombreCliente, int celu, String dire, String email) {
	id = idCli;
	this.dni = dni;
	this.nombreCliente = nombreCliente;
	celular = celu;
	direccion = dire; 
	this.email = email;
}
public Cliente(String string, int i, int j, String string2) {
	// TODO Auto-generated constructor stub
}
public int id() {
	return id;
}

public int dni() {
	return dni;
}
public int celular() {
	return celular;
}
public String nombreCliente() {
	return nombreCliente;
}
public String direccion() {
	return direccion;
}
public String email() {
	return email;
}
}
