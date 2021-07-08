package com.ungs.revivir.vista.menu.fallecidos.fallecidoAM;

import com.ungs.revivir.persistencia.entidades.Fallecido;

public interface FallecidoInvocable {
	
	public void mostrar();

	public void actualizarFallecidos();
	
	public void actualizarFallecidos(Fallecido nuevo);

}