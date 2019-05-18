package com.ungs.revivir.negocios;

public class Validador {
	
	public static boolean nombrePersona(String nombre) {
		return formatoLetraEspacio(nombre);
	}

	public static boolean apellido(String apellido) {
		return formatoLetraEspacio(apellido);
	}

	public static boolean DNI(String DNI) {
		return formatoNumero(DNI);
	}
	
	public static boolean telefono(String telefono) {
		return formatoNumero(telefono);
	}
	public static boolean cod_fallecido(String codigo) {
		return formatoNumero(codigo);
	}
	public static boolean email(String email) {
		return email.matches("^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@" + "[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})$");
	}
	
	public static boolean codigo(String codigo) {
		return (formatoNumeroLetra(codigo));
	}
	
	public static boolean nombreServicio(String nombre) {
		return !nombre.equals("");
	}

	public static boolean usuario(String texto) {
		return (formatoNumeroLetra(texto));
	}
	
	public static boolean password(String texto) {
		return (formatoNumeroLetra(texto));
	}
	
	// METODOS INTERNOS DEL VALIDADOR
	private static boolean formatoNumero(String texto) {
		return texto.matches("[0-9]+");
	}

	private static boolean formatoLetra(String texto) {
		return texto.matches("[a-zA-Z�-�\\u00f1\\u00d1]+");
	}

	private static boolean formatoLetraEspacio(String texto) {
		return texto.matches("[a-zA-Z�-�\\u00f1\\u00d1\\s]+");
	}
	
	private static boolean formatoNumeroLetraEspacio(String texto) {
		return texto.matches("[a-zA-Z�-�0-9\\u00f1\\u00d1\\s]+");
	}
	
	private static boolean formatoNumeroLetra(String texto) {
		return texto.matches("[a-zA-Z�-�0-9\\u00f1\\u00d1]+");
	}
	
	public static void main(String[] args) {
		formatoNumeroLetraEspacio("");
		formatoLetra("");
	}
			
}