package com.ungs.revivir.test.seleccionar;

import com.ungs.revivir.persistencia.entidades.Cargo;
import com.ungs.revivir.vista.seleccion.cargos.CargoSeleccionable;
import com.ungs.revivir.vista.seleccion.cargos.ControladorSeleccionCargo;

public class SeleccionarCargoTest implements CargoSeleccionable {

	@Override
	public void seleccionarCargo(Cargo cargo) {
		System.out.println("Se ha seleccionado el cargo:"+cargo.getObservaciones());
	}

	@Override
	public void mostrar() {
	
	}
	
	public static void main(String[] args) {
		new ControladorSeleccionCargo(new SeleccionarCargoTest());
	}

}