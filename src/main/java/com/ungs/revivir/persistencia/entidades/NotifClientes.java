package com.ungs.revivir.persistencia.entidades;

import java.sql.Date;

import com.ungs.revivir.persistencia.definidos.SubSector;
import com.ungs.revivir.persistencia.definidos.TipoFallecimiento;

public class NotifClientes {
	// Representa un fallecido con todos los datos de su ubicacion
	
	//  Datos cliente
	private Integer cli_iD;
	private String cli_nombre, cli_apellido, cli_DNI, domicilio, telefono, email;	
	
	
	// Datos del fallecido
	private Integer ID, ubicacion, codFallecido;
	private String DNI, apellido, nombre, cocheria;	
	private Date fechaFallecimiento, fechaIngreso;
	private TipoFallecimiento tipoFallecimiento;
	
	
	// Datos de la ubicacion vinculada a ese fallecido
	private Integer nicho, fila, macizo, unidad, sepultura, parcela, mueble, inhumacion, circ;
	private Boolean bis, bisMacizo;
	private String  cementerio, seccion;
	private SubSector subsector;
	private Date vencimiento;
		
	public NotifClientes(
			// Parametros del cliente
			Integer cli_iD, String cli_nombre, String cli_apellido, String cli_DNI, String domicilio, String telefono,
			String email,
			
			// Parametros del fallecido
			 Integer ubicacion, TipoFallecimiento tipoFallecimiento, Integer codFallecido, String DNI,
			String apellido, String nombre, String cocheria, Date fechaFallecimiento, Date fechaIngreso,
			
			// Parametros de la ubicacion
			SubSector subsector, String cementerio, Integer nicho, Integer fila, String seccion, Integer macizo,
			Integer unidad, Boolean bis, Boolean bisMacizo, Integer sepultura, Integer parcela, Integer mueble,
			Integer inhumacion, Integer circ, Date vencimiento) {
		
		//Datos del cliente
		this.cli_iD = cli_iD;
		this.nombre = cli_nombre;
		this.apellido = cli_apellido;
		this.DNI = cli_DNI;
		this.domicilio = domicilio;
		this.telefono = telefono;
		this.email = email;
		
		// Datos del fallecido
		this.ubicacion = ubicacion;
		this.tipoFallecimiento = tipoFallecimiento;
		this.codFallecido = codFallecido;
		this.DNI = DNI;
		this.apellido = apellido;
		this.nombre = nombre;
		this.cocheria = cocheria;
		this.fechaFallecimiento = fechaFallecimiento;
		this.fechaIngreso = fechaIngreso;
		
		// Datos de la ubicacion
		this.subsector = subsector;
		this.cementerio = cementerio;
		this.nicho = nicho;
		this.fila = fila;
		this.seccion = seccion;
		this.macizo = macizo;
		this.unidad = unidad;
		this.bis = bis;
		this.bisMacizo = bisMacizo;
		this.sepultura = sepultura;
		this.parcela = parcela;
		this.mueble = mueble;
		this.inhumacion = inhumacion;
		this.circ = circ;
		this.vencimiento = vencimiento;
	}
	public Integer getcli_ID() {
		return cli_iD;
	}

	public void setcli_D(Integer iD) {
		this.cli_iD = iD;
	}

	public String getCli_Nombre() {
		return cli_nombre;
	}

	public void setCli_Nombre(String cli_nombre) {
		this.cli_nombre = cli_nombre;
	}

	public String getCli_Apellido() {
		return cli_apellido;
	}

	public void setCli_Apellido(String cli_apellido) {
		this.cli_apellido = cli_apellido;
	}

	public String getCli_DNI() {
		return cli_DNI;
	}

	public void setcli_DNI(String cli_DNI) {
		this.cli_DNI =cli_DNI;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public Integer getCodFallecido() {
		return codFallecido;
	}

	public void setCodFallecido(Integer codFallecido) {
		this.codFallecido = codFallecido;
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
		return bisMacizo;
	}

	public void setBisMacizo(Boolean bisMacizo) {
		this.bisMacizo = bisMacizo;
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
		return vencimiento;
	}
	
	public void setVencimiento(Date vencimiento) {
		this.vencimiento = vencimiento;
	}
	
}