package com.ungs.revivir.persistencia.entidades;

import java.sql.Date;

public class ReportePago {
	
	// Pago
	public Integer pagoID;
	public Float importe;
	public String observaciones;
	public Date pagoFecha;
	
	// Cargo
	public Integer cargoID;
	public String cargoObservaciones;
	public Boolean pagado;

	// Fallecido
	public Integer fallecidoID;
	public String nombre;
	public String apellido;

	// Servicio
	public Integer servicioID;
	public String servicioNombre;
	public Boolean historico;
	
	
	public ReportePago(Integer pagoID, Float importe, String observaciones, Date pagoFecha, Integer cargoID,
			String cargoObservaciones, Boolean pagado, Integer fallecidoID, String nombre, String apellido,
			Integer servicioID, String servicioNombre, Boolean historico) {
		super();
		this.pagoID = pagoID;
		this.importe = importe;
		this.observaciones = observaciones;
		this.pagoFecha = pagoFecha;
		this.cargoID = cargoID;
		this.cargoObservaciones = cargoObservaciones;
		this.pagado = pagado;
		this.fallecidoID = fallecidoID;
		this.nombre = nombre;
		this.apellido = apellido;
		this.servicioID = servicioID;
		this.servicioNombre = servicioNombre;
		this.historico = historico;
	}


	public Integer getPagoID() {
		return pagoID;
	}


	public void setPagoID(Integer pagoID) {
		this.pagoID = pagoID;
	}


	public Float getImporte() {
		return importe;
	}


	public void setImporte(Float importe) {
		this.importe = importe;
	}


	public String getObservaciones() {
		return observaciones;
	}


	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}


	public Date getPagoFecha() {
		return pagoFecha;
	}


	public void setPagoFecha(Date pagoFecha) {
		this.pagoFecha = pagoFecha;
	}


	public Integer getCargoID() {
		return cargoID;
	}


	public void setCargoID(Integer cargoID) {
		this.cargoID = cargoID;
	}


	public String getCargoObservaciones() {
		return cargoObservaciones;
	}


	public void setCargoObservaciones(String cargoObservaciones) {
		this.cargoObservaciones = cargoObservaciones;
	}


	public Boolean getPagado() {
		return pagado;
	}


	public void setPagado(Boolean pagado) {
		this.pagado = pagado;
	}


	public Integer getFallecidoID() {
		return fallecidoID;
	}


	public void setFallecidoID(Integer fallecidoID) {
		this.fallecidoID = fallecidoID;
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


	public Integer getServicioID() {
		return servicioID;
	}


	public void setServicioID(Integer servicioID) {
		this.servicioID = servicioID;
	}


	public String getServicioNombre() {
		return servicioNombre;
	}


	public void setServicioNombre(String servicioNombre) {
		this.servicioNombre = servicioNombre;
	}


	public Boolean getHistorico() {
		return historico;
	}


	public void setHistorico(Boolean historico) {
		this.historico = historico;
	}

	
	
	
}
