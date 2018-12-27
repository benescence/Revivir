package com.ungs.revivir.persistencia.entidades;

public class Responsable {
	private Integer ID,  cliente, fallecido;
	private String observaciones;
	
	public Responsable(Integer ID, Integer cliente, Integer fallecido, String observaciones) {
		this.ID = ID;
		this.cliente = cliente;
		this.fallecido = fallecido;
		this.observaciones = observaciones;
	}

	public Integer getCliente() {
		return cliente;
	}

	public void setCliente(Integer cliente) {
		this.cliente = cliente;
	}

	public Integer getFallecido() {
		return fallecido;
	}

	public void setFallecido(Integer fallecido) {
		this.fallecido = fallecido;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Integer getID() {
		return ID;
	}

	public void setID(Integer ID) {
		this.ID = ID;
	}
	
}