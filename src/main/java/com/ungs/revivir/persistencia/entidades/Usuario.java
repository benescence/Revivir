package com.ungs.revivir.persistencia.entidades;

import com.ungs.revivir.persistencia.definidos.Rol;

public class Usuario {
	private Integer ID;
	private String  usuario, password;
	private Rol rol;

	public Usuario(Integer iD, String usuario, String password, Rol rol) {
		this.ID = iD;
		this.usuario = usuario;
		this.password = password;
		this.rol = rol;
	}

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		this.ID = iD;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

}