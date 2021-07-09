package com.ungs.revivir.persistencia.entidades;

import java.sql.Date;

public class Pago {
	private Integer ID, cargo;
	private Double importe;
	private String observaciones;
	private Date fecha;
	
	public Pago(Integer ID, Integer cargo, Double importe, String observaciones, Date fecha) {
		this.ID = ID;
		this.cargo = cargo;
		this.importe = importe;
		this.fecha = fecha;
		this.observaciones = observaciones;
	}

	public Integer getID() {
		return ID;
	}

	public void setID(Integer ID) {
		this.ID = ID;
	}
	
	public Integer getCargo() {
		return cargo;
	}

	public void setCargo(Integer cargo) {
		this.cargo = cargo;
	}

	public Double getImporte() {
		return importe;
	}

	public void setImporte(Double importe) {
		this.importe = importe;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
}