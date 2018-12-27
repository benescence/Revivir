package com.ungs.revivir.test.seleccionar;

import com.ungs.revivir.persistencia.entidades.Servicio;
import com.ungs.revivir.vista.seleccion.servicio.ControladorSeleccionarServicio;
import com.ungs.revivir.vista.seleccion.servicio.ServicioSeleccionable;

public class SeleccionarServicioTest implements ServicioSeleccionable {

	@Override
	public void seleccionarServicio(Servicio fallecido) {
		System.out.println("Se ha seleccionado el servicio: "+fallecido.getNombre()+", "+fallecido.getCodigo());
	}

	@Override
	public void mostrar() {
	
	}
	
	public static void main(String[] args) {
		new ControladorSeleccionarServicio(new SeleccionarServicioTest());
	}

}