package com.ungs.revivir.persistencia.entidades;

import java.sql.Date;

public class Expensas {
	private Integer ID, responsable, periodo, ubicacion, importe;
	private String observaciones;
	private Date fechavencimiento;

	public Expensas(Integer iD, Integer responsable, Integer periodo ,  Integer ubicacion ,Date fechaVencimiento, Integer importe ,String observacones) {
		this.ID = iD;
		this.responsable = responsable;
		this.periodo = periodo;
		this.ubicacion = ubicacion;
		this.fechavencimiento = fechaVencimiento;
		this.importe = importe;
		this.observaciones = observacones;
	}

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public Integer getResponsable() {
		return responsable;
	}

	public void setResponsable(Integer responsable) {
		this.responsable = responsable;
	}

	public Integer getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Integer periodo) {
		this.periodo = periodo;
	}

	public Integer getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(Integer ubicacion) {
		this.ubicacion = ubicacion;
	}

	public Integer getImporte() {
		return importe;
	}

	public void setImporte(Integer importe) {
		this.importe = importe;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Date getFechavencimiento() {
		return fechavencimiento;
	}

	public void setFechavencimiento(Date fechavencimiento) {
		this.fechavencimiento = fechavencimiento;
	}
}
