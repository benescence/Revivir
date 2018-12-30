package com.ungs.revivir.persistencia.interfaces;

public interface ConfiguracionOBD {
	
	public void insert(String clave, String valor);
	
	public void update(String clave, String valor);
	
	public String selectByClave(String clave);

}