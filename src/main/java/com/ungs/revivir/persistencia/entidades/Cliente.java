package com.ungs.revivir.persistencia.entidades;

public class Cliente {
	private Integer ID;
	private String nombre, apellido, DNI, domicilio, telefono, email;

	public Cliente(Integer iD, String nombre, String apellido, String DNI, String domicilio, String telefono,
			String email) {
		this.ID = iD;
		this.nombre = nombre;
		this.apellido = apellido;
		this.DNI = DNI;
		this.domicilio = domicilio;
		this.telefono = telefono;
		this.email = email;
	}

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		this.ID = iD;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDNI() {
		return DNI;
	}

	public void setDNI(String DNI) {
		this.DNI =DNI;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}