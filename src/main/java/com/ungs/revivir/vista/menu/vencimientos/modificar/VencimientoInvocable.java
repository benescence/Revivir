package com.ungs.revivir.vista.menu.vencimientos.modificar;

import com.ungs.revivir.persistencia.entidades.vista.VFallecidoUbicacion;

public interface VencimientoInvocable {

	public void mostrar();
	
	public void actualizarVencimientos();

	public void actualizarVencimientos(VFallecidoUbicacion nuevo);
	
}