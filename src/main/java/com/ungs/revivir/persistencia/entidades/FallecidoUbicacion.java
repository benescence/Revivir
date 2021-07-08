package com.ungs.revivir.persistencia.entidades;


import java.sql.Date;

import com.ungs.revivir.persistencia.definidos.SubSector;
import com.ungs.revivir.persistencia.definidos.TipoFallecimiento;

public class FallecidoUbicacion {
	private Integer ubicacion,cod_fallecido;
	private String DNI, apellido, nombre, cocheria;
	private Date fechaFallecimiento, fechaIngreso;
	private TipoFallecimiento tipoFallecimiento;
	
	private Integer ID, nicho, fila, macizo, unidad, sepultura, parcela, mueble, inhumacion, circ;
	private Boolean bis, bis_macizo; 
	private String  cementerio, seccion;
	private SubSector subsector;
	private Date vencimiento;
	
	
	public FallecidoUbicacion(Integer ID, Integer ubicacion, TipoFallecimiento tipoFallecimiento, Integer cod_fallecido, String DNI, String apellido, String nombre, String cocheria,
			Date fechaFallecimiento, Date fechaIngreso, SubSector subsector, String cementerio, Integer nicho, Integer fila,
			String seccion, Integer macizo, Integer unidad, Boolean bis, Boolean bis_macizo, Integer sepultura,
			Integer parcela, Integer mueble, Integer inhumacion, Integer circ, Date vencimiento) {
		this.ID = ID;
		this.ubicacion = ubicacion;
		this.tipoFallecimiento = tipoFallecimiento;
		this.cod_fallecido = cod_fallecido;
		this.DNI = DNI;
		this.apellido = apellido;
		this.nombre = nombre;
		this.cocheria = cocheria;
		this.fechaFallecimiento = fechaFallecimiento;
		this.fechaIngreso = fechaIngreso;
		this.subsector = subsector;
		this.cementerio = cementerio;
		this.nicho = nicho;
		this.fila = fila;
		this.seccion = seccion;
		this.macizo = macizo;
		this.unidad = unidad;
		this.bis = bis;
		this.bis_macizo = bis_macizo;
		this.sepultura = sepultura;
		this.parcela = parcela;
		this.mueble = mueble;
		this.inhumacion = inhumacion;
		this.circ = circ;
		this.vencimiento = vencimiento;
	}

	public Integer getCod_fallecido() {
		return cod_fallecido;
	}

	public void setCod_fallecido(Integer cod_fallecido) {
		this.cod_fallecido = cod_fallecido;
	}

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		this.ID = iD;
	}

	public Integer getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(Integer ubicacion) {
		this.ubicacion = ubicacion;
	}

	public String getDNI() {
		return DNI;
	}

	public void setDNI(String DNI) {
		this.DNI = DNI;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCocheria() {
		return cocheria;
	}

	public void setCocheria(String cocheria) {
		this.cocheria = cocheria;
	}

	public Date getFechaFallecimiento() {
		return fechaFallecimiento;
	}

	public void setFechaFallecimiento(Date fechaFallecimiento) {
		this.fechaFallecimiento = fechaFallecimiento;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public TipoFallecimiento getTipoFallecimiento() {
		return tipoFallecimiento;
	}

	public void setTipoFallecimiento(TipoFallecimiento tipoFallecimiento) {
		this.tipoFallecimiento = tipoFallecimiento;
	}
	



	public SubSector getSubsector() {
		return subsector;
	}

	public void setSubsector(SubSector subsector) {
		this.subsector = subsector;
	}

	public String getCementerio() {
		return cementerio;
	}

	public void setCementerio(String cementerio) {
		this.cementerio = cementerio;
	}

	public Integer getNicho() {
		return nicho;
	}

	public void setNicho(Integer nicho) {
		this.nicho = nicho;
	}

	public Integer getFila() {
		return fila;
	}

	public void setFila(Integer fila) {
		this.fila = fila;
	}

	public Integer getMacizo() {
		return macizo;
	}

	public void setMacizo(Integer macizo) {
		this.macizo = macizo;
	}

	public Integer getUnidad() {
		return unidad;
	}

	public void setUnidad(Integer unidad) {
		this.unidad = unidad;
	}

	public Boolean getBis() {
		return bis;
	}

	public void setBis(Boolean bis) {
		this.bis = bis;
	}

	public Boolean getBis_macizo() {
		return bis_macizo;
	}

	public void setBis_macizo(Boolean bis_macizo) {
		this.bis_macizo = bis_macizo;
	}

	public Integer getSepultura() {
		return sepultura;
	}

	public void setSepultura(Integer sepultura) {
		this.sepultura = sepultura;
	}

	public Integer getParcela() {
		return parcela;
	}

	public void setParcela(Integer parcela) {
		this.parcela = parcela;
	}

	public Integer getMueble() {
		return mueble;
	}

	public void setMueble(Integer mueble) {
		this.mueble = mueble;
	}

	public Integer getInhumacion() {
		return inhumacion;
	}

	public void setInhumacion(Integer inhumacion) {
		this.inhumacion = inhumacion;
	}

	public Integer getCirc() {
		return circ;
	}

	public void setCirc(Integer circ) {
		this.circ = circ;
	}

	public String getSeccion() {
		return seccion;
	}

	public void setSeccion(String seccion) {
		this.seccion = seccion;
	}

	public Date getVencimiento() {
		
		
		return vencimiento;}
	public void setVencimiento(Date vencimiento) {
		this.vencimiento = vencimiento;
	}
	
}