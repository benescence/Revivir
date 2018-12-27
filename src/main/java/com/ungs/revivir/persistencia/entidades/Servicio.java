package com.ungs.revivir.persistencia.entidades;

public class Servicio {
	private Integer ID;
	private String codigo, nombre, descripcion;
	private Double importe;
	private Boolean historico;
	
	public Servicio(Integer iD, String codigo, String nombre, String descripcion, Double importe, Boolean historico) {
		this.ID = iD;
		this.codigo = codigo;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.importe = importe;
		this.historico = historico;
	}

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		this.ID = iD;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Double getImporte() {
		return importe;
	}

	public void setImporte(Double importe) {
		this.importe = importe;
	}

	public Boolean getHistorico() {
		return historico;
	}

	public void setHistorico(Boolean historico) {
		this.historico = historico;
	}
	
}