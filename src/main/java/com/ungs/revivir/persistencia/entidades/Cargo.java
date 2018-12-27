package com.ungs.revivir.persistencia.entidades;

public class Cargo {
	private Integer ID, fallecido, servicio;
	private String observaciones;
	private Boolean pagado;
	
	public Cargo(Integer ID, Integer fallecido, Integer servicio, String observaciones, Boolean pagado) {
		this.ID = ID;
		this.fallecido = fallecido;
		this.servicio = servicio;
		this.observaciones = observaciones;
		this.pagado = pagado;
	}
	
	public Integer getID() {
		return ID;
	}
	
	public void setID(Integer id) {
		this.ID = id;
	}
	
	public Integer getFallecido() {
		return fallecido;
	}
	
	public void setFallecido(Integer fallecido) {
		this.fallecido = fallecido;
	}
	
	public Integer getServicio() {
		return servicio;
	}
	
	public void setServicio(Integer servicio) {
		this.servicio = servicio;
	}
	
	public String getObservaciones() {
		return observaciones;
	}
	
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	
	public Boolean getPagado() {
		return pagado;
	}

	public void setPagado(Boolean pagado) {
		this.pagado = pagado;
	}

}