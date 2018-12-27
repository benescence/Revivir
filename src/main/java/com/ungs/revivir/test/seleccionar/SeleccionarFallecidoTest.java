package com.ungs.revivir.test.seleccionar;

import com.ungs.revivir.persistencia.entidades.Fallecido;
import com.ungs.revivir.vista.seleccion.fallecidos.ControladorSeleccionarFallecido;
import com.ungs.revivir.vista.seleccion.fallecidos.FallecidoSeleccionable;

public class SeleccionarFallecidoTest implements FallecidoSeleccionable {

	@Override
	public void seleccionarFallecido(Fallecido fallecido) {
		System.out.println("Se ha seleccionado el cliente:"+fallecido.getNombre()+", "+fallecido.getApellido()+", "+fallecido.getDNI());
	}

	@Override
	public void mostrar() {
	
	}
	
	public static void main(String[] args) {
		new ControladorSeleccionarFallecido(new SeleccionarFallecidoTest());
	}

}