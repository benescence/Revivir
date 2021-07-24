package com.ungs.revivir.persistencia.entidades.vista;

import java.sql.Date;

public class ReportePago {
	
	// Pago
	private Integer pagoID;
	private Double pagoImporte;
	private String pagoObservaciones;
	private Date pagoFecha;
	
	// Cargo
	private Integer cargoID;
	private String cargoObservaciones;
	private Boolean cargoPagado;

	// Fallecido
	private Integer fallecidoID;
	private String fallecidoNombre;
	private String fallecidoApellido;
	private String fallecidoDNI;

	// Servicio
	private Integer servicioID;
	private String servicioNombre;
	private Boolean servicioHistorico;
	
	public ReportePago(Integer pagoID, Double pagoImporte, String pagoObservaciones, Date pagoFecha, Integer cargoID,
			String cargoObservaciones, Boolean cargoPagado, Integer fallecidoID, String fallecidoNombre,
			String fallecidoApellido, String fallecidoDNI, Integer servicioID, String servicioNombre,
			Boolean servicioHistorico) {
		this.pagoID = pagoID;
		this.pagoImporte = pagoImporte;
		this.pagoObservaciones = pagoObservaciones;
		this.pagoFecha = pagoFecha;
		this.cargoID = cargoID;
		this.cargoObservaciones = cargoObservaciones;
		this.cargoPagado = cargoPagado;
		this.fallecidoID = fallecidoID;
		this.fallecidoNombre = fallecidoNombre;
		this.fallecidoApellido = fallecidoApellido;
		this.fallecidoDNI = fallecidoDNI;
		this.servicioID = servicioID;
		this.servicioNombre = servicioNombre;
		this.servicioHistorico = servicioHistorico;
	}

	public Integer getPagoID() {
		return pagoID;
	}

	public void setPagoID(Integer pagoID) {
		this.pagoID = pagoID;
	}

	public Double getPagoImporte() {
		return pagoImporte;
	}

	public void setPagoImporte(Double pagoImporte) {
		this.pagoImporte = pagoImporte;
	}

	public String getPagoObservaciones() {
		return pagoObservaciones;
	}

	public void setPagoObservaciones(String pagoObservaciones) {
		this.pagoObservaciones = pagoObservaciones;
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

	public Boolean getCargoPagado() {
		return cargoPagado;
	}

	public void setCargoPagado(Boolean cargoPagado) {
		this.cargoPagado = cargoPagado;
	}

	public Integer getFallecidoID() {
		return fallecidoID;
	}

	public void setFallecidoID(Integer fallecidoID) {
		this.fallecidoID = fallecidoID;
	}

	public String getFallecidoNombre() {
		return fallecidoNombre;
	}

	public void setFallecidoNombre(String fallecidoNombre) {
		this.fallecidoNombre = fallecidoNombre;
	}

	public String getFallecidoApellido() {
		return fallecidoApellido;
	}

	public void setFallecidoApellido(String fallecidoApellido) {
		this.fallecidoApellido = fallecidoApellido;
	}

	public String getFallecidoDNI() {
		return fallecidoDNI;
	}

	public void setFallecidoDNI(String fallecidoDNI) {
		this.fallecidoDNI = fallecidoDNI;
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

	public Boolean getServicioHistorico() {
		return servicioHistorico;
	}

	public void setServicioHistorico(Boolean servicioHistorico) {
		this.servicioHistorico = servicioHistorico;
	}
		
}