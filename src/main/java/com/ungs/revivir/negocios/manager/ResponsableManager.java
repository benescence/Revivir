package com.ungs.revivir.negocios.manager;

import java.util.List;

import com.ungs.revivir.persistencia.FactoryOBD;
import com.ungs.revivir.persistencia.entidades.Cliente;
import com.ungs.revivir.persistencia.entidades.Fallecido;
import com.ungs.revivir.persistencia.entidades.Responsable;
import com.ungs.revivir.persistencia.interfaces.ResponsableOBD;

public class ResponsableManager {
	
	public static void guardar(Responsable nuevo) {
		ResponsableOBD obd = FactoryOBD.crearResponsableOBD();
		obd.insert(nuevo);
	}

	public static void modificar(Responsable modificar) {
		ResponsableOBD obd = FactoryOBD.crearResponsableOBD();
		obd.update(modificar);
	}
		
	public static void eliminar(Responsable eliminar) {
		ResponsableOBD obd = FactoryOBD.crearResponsableOBD();
		obd.delete(eliminar);
	}
	
	public static List<Responsable> traerTodo() {
		ResponsableOBD obd = FactoryOBD.crearResponsableOBD();
		return obd.select();
	}
	
	public static Responsable traerPorID(Integer ID) {
		ResponsableOBD obd = FactoryOBD.crearResponsableOBD();
		return obd.selectByID(ID);
	}

	public static List<Responsable> traerPorCliente(Cliente cliente) {
		ResponsableOBD obd = FactoryOBD.crearResponsableOBD();
		return obd.selectByCliente(cliente);
	}

	public static Responsable traerPorClienteFallecido(Cliente cliente, Fallecido fallecido) {
		ResponsableOBD obd = FactoryOBD.crearResponsableOBD();
		return obd.selectByClienteFallecido(cliente, fallecido);
	}
	
}