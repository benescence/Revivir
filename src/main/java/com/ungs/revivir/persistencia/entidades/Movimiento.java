package com.ungs.revivir.persistencia.entidades;

import java.sql.Date;

public class Movimiento {
	private Integer ID, fallecido;
	private String antiguaUbicacion, causaTraslado, observaciones;
	private Date fecha;
	
	public Movimiento(Integer ID, Integer fallecido, String antiguaUbicacion, String causaTraslado,
			String observaciones, Date fecha) {
		this.ID = ID;
		this.fallecido = fallecido;
		this.antiguaUbicacion = antiguaUbicacion;
		this.causaTraslado = causaTraslado;
		this.observaciones = observaciones;
		this.fecha = fecha;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Integer getID() {
		return ID;
	}

	public void setID(Integer ID) {
		this.ID = ID;
	}

	public Integer getFallecido() {
		return fallecido;
	}

	public void setFallecido(Integer fallecido) {
		this.fallecido = fallecido;
	}

	public String getAntiguaUbicacion() {
		return antiguaUbicacion;
	}

	public void setAntiguaUbicacion(String antiguaUbicacion) {
		this.antiguaUbicacion = antiguaUbicacion;
	}

	public String getCausaTraslado() {
		return causaTraslado;
	}

	public void setCausaTraslado(String causaTraslado) {
		this.causaTraslado = causaTraslado;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	
}