package com.ungs.revivir.persistencia.entidades.vista;

import java.sql.Date;
import com.ungs.revivir.persistencia.definidos.SubSector;

public class VPagoReporte {
	
	// Representa un pago extendido con todos los datos para msotrar en un reporte
	
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
	private Integer fallecidoCodigo;
	private String fallecidoNombre;
	private String fallecidoApellido;
	private String fallecidoDNI;

	// Servicio
	private Integer servicioID;
	private String servicioNombre;
	private Boolean servicioHistorico;

	// Ubicacion
	private Integer ubicacionID;
	private SubSector ubicacionSubsector;
	private Integer ubicacionCirc;
	private String ubicacionSeccion;
	private Integer ubicacionMacizo;
	private Integer ubicacionParcela;
	private Integer ubicacionFila;
	private Integer ubicacionUnidad;
	private Integer ubicacionNicho;
	private Integer ubicacionMueble;
	private Integer ubicacionSepultura;
	private Integer ubicacionInhumacion;
	private String ubicacionCementerio;
	private Date ubicacionVencimiento;
	private Boolean ubicacionBisMacizo;
	private Boolean ubicacionBis;
	
	public VPagoReporte(
			
			// Parametros pago
			Integer pagoID, Double pagoImporte, String pagoObservaciones, Date pagoFecha,

			// Parametros cargo			
			Integer cargoID, String cargoObservaciones, Boolean cargoPagado,
			
			// Parametros fallecido
			Integer fallecidoID, Integer fallecidoCodigo, String fallecidoNombre, String fallecidoApellido, String fallecidoDNI,
			
			// Parametros servicio
			Integer servicioID, String servicioNombre, Boolean servicioHistorico,

			// Parametros ubicacion
			Integer ubicacionID, SubSector ubicacionSubsector, Integer ubicacionCirc,
			String ubicacionSeccion, Integer ubicacionMacizo, Integer ubicacionParcela, Integer ubicacionFila,
			Integer ubicacionUnidad, Integer ubicacionNicho, Integer ubicacionMueble, Integer ubicacionSepultura,
			Integer ubicacionInhumacion, String ubicacionCementerio, Date ubicacionVencimiento,
			Boolean ubicacionBisMacizo, Boolean ubicacionBis) {
		
		this.pagoID = pagoID;
		this.pagoImporte = pagoImporte;
		this.pagoObservaciones = pagoObservaciones;
		this.pagoFecha = pagoFecha;
		
		this.cargoID = cargoID;
		this.cargoObservaciones = cargoObservaciones;
		this.cargoPagado = cargoPagado;
		
		this.fallecidoID = fallecidoID;
		this.fallecidoCodigo= fallecidoCodigo;
		this.fallecidoNombre = fallecidoNombre;
		this.fallecidoApellido = fallecidoApellido;
		this.fallecidoDNI = fallecidoDNI;
		
		this.servicioID = servicioID;
		this.servicioNombre = servicioNombre;
		this.servicioHistorico = servicioHistorico;
		
		this.ubicacionID = ubicacionID;
		this.ubicacionSubsector = ubicacionSubsector;
		this.ubicacionCirc = ubicacionCirc;
		this.ubicacionSeccion = ubicacionSeccion;
		this.ubicacionMacizo = ubicacionMacizo;
		this.ubicacionParcela = ubicacionParcela;
		this.ubicacionFila = ubicacionFila;
		this.ubicacionUnidad = ubicacionUnidad;
		this.ubicacionNicho = ubicacionNicho;
		this.ubicacionMueble = ubicacionMueble;
		this.ubicacionSepultura = ubicacionSepultura;
		this.ubicacionInhumacion = ubicacionInhumacion;
		this.ubicacionCementerio = ubicacionCementerio;
		this.ubicacionVencimiento = ubicacionVencimiento;
		this.ubicacionBisMacizo = ubicacionBisMacizo;
		this.ubicacionBis = ubicacionBis;
	}

	public Integer getFallecidoCodigo() {
		return fallecidoCodigo;
	}

	public void setFallecidoCodigo(Integer fallecidoCodigo) {
		this.fallecidoCodigo = fallecidoCodigo;
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

	public Integer getUbicacionID() {
		return ubicacionID;
	}

	public void setUbicacionID(Integer ubicacionID) {
		this.ubicacionID = ubicacionID;
	}

	public SubSector getUbicacionSubsector() {
		return ubicacionSubsector;
	}

	public void setUbicacionSubsector(SubSector ubicacionSubsector) {
		this.ubicacionSubsector = ubicacionSubsector;
	}

	public Integer getUbicacionCirc() {
		return ubicacionCirc;
	}

	public void setUbicacionCirc(Integer ubicacionCirc) {
		this.ubicacionCirc = ubicacionCirc;
	}

	public String getUbicacionSeccion() {
		return ubicacionSeccion;
	}

	public void setUbicacionSeccion(String ubicacionSeccion) {
		this.ubicacionSeccion = ubicacionSeccion;
	}

	public Integer getUbicacionMacizo() {
		return ubicacionMacizo;
	}

	public void setUbicacionMacizo(Integer ubicacionMacizo) {
		this.ubicacionMacizo = ubicacionMacizo;
	}

	public Integer getUbicacionParcela() {
		return ubicacionParcela;
	}

	public void setUbicacionParcela(Integer ubicacionParcela) {
		this.ubicacionParcela = ubicacionParcela;
	}

	public Integer getUbicacionFila() {
		return ubicacionFila;
	}

	public void setUbicacionFila(Integer ubicacionFila) {
		this.ubicacionFila = ubicacionFila;
	}

	public Integer getUbicacionUnidad() {
		return ubicacionUnidad;
	}

	public void setUbicacionUnidad(Integer ubicacionUnidad) {
		this.ubicacionUnidad = ubicacionUnidad;
	}

	public Integer getUbicacionNicho() {
		return ubicacionNicho;
	}

	public void setUbicacionNicho(Integer ubicacionNicho) {
		this.ubicacionNicho = ubicacionNicho;
	}

	public Integer getUbicacionMueble() {
		return ubicacionMueble;
	}

	public void setUbicacionMueble(Integer ubicacionMueble) {
		this.ubicacionMueble = ubicacionMueble;
	}

	public Integer getUbicacionSepultura() {
		return ubicacionSepultura;
	}

	public void setUbicacionSepultura(Integer ubicacionSepultura) {
		this.ubicacionSepultura = ubicacionSepultura;
	}

	public Integer getUbicacionInhumacion() {
		return ubicacionInhumacion;
	}

	public void setUbicacionInhumacion(Integer ubicacionInhumacion) {
		this.ubicacionInhumacion = ubicacionInhumacion;
	}

	public String getUbicacionCementerio() {
		return ubicacionCementerio;
	}

	public void setUbicacionCementerio(String ubicacionCementerio) {
		this.ubicacionCementerio = ubicacionCementerio;
	}

	public Date getUbicacionVencimiento() {
		return ubicacionVencimiento;
	}

	public void setUbicacionVencimiento(Date ubicacionVencimiento) {
		this.ubicacionVencimiento = ubicacionVencimiento;
	}

	public Boolean getUbicacionBisMacizo() {
		return ubicacionBisMacizo;
	}

	public void setUbicacionBisMacizo(Boolean ubicacionBisMacizo) {
		this.ubicacionBisMacizo = ubicacionBisMacizo;
	}

	public Boolean getUbicacionBis() {
		return ubicacionBis;
	}

	public void setUbicacionBis(Boolean ubicacionBis) {
		this.ubicacionBis = ubicacionBis;
	}

}