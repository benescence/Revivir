package com.ungs.revivir.persistencia.entidades;

import com.ungs.revivir.persistencia.definidos.SubSector;

public class Ubicacion {
	private Integer ID, nicho, fila, macizo,
	unidad, sepultura, parcela, mueble, inhumacion, circ;
	private Boolean bis, bis_macizo; 
	private String  otroCementerio, seccion;
	private SubSector subsector;
	
	public Ubicacion(Integer ID, SubSector subsector, String otroCementerio, Integer nicho, Integer fila,
			String seccion, Integer macizo, Integer unidad, Boolean bis, Boolean bis_macizo, Integer sepultura,
			Integer parcela, Integer mueble, Integer inhumacion, Integer circ) {
		this.ID = ID;
		this.subsector = subsector;
		this.otroCementerio = otroCementerio;
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
	}

	public Integer getID() {
		return ID;
	}

	public void setID(Integer ID) {
		this.ID = ID;
	}

	public SubSector getSubsector() {
		return subsector;
	}

	public void setSubsector(SubSector subsector) {
		this.subsector = subsector;
	}

	public String getOtroCementerio() {
		return otroCementerio;
	}

	public void setOtroCementerio(String otroCementerio) {
		this.otroCementerio = otroCementerio;
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

}