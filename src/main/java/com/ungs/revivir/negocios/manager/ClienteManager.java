package com.ungs.revivir.negocios.manager;

import java.util.ArrayList;
import java.util.List;

import com.ungs.revivir.negocios.Validador;
import com.ungs.revivir.negocios.verificador.Verificador;
import com.ungs.revivir.persistencia.FactoryOBD;
import com.ungs.revivir.persistencia.entidades.Cliente;
import com.ungs.revivir.persistencia.entidades.Fallecido;
import com.ungs.revivir.persistencia.entidades.Responsable;
import com.ungs.revivir.persistencia.interfaces.ClienteOBD;

public class ClienteManager {
	
	public static void guardar(Cliente nuevo) throws Exception {
		Cliente cliente = Verificador.cliente(nuevo, null);
		ClienteOBD obd = FactoryOBD.crearClienteOBD();
		obd.insert(cliente);
	}

	public static void modificar(Cliente nuevo, Cliente anterior) throws Exception {
		Cliente cliente = Verificador.cliente(nuevo, anterior);
		ClienteOBD obd = FactoryOBD.crearClienteOBD();
		obd.update(cliente);
	}
		
	public static void eliminar(Cliente cliente) {
		ClienteOBD obd = FactoryOBD.crearClienteOBD();
		obd.delete(cliente);
	}
	
	public static List<Cliente> traerTodo() {
		ClienteOBD obd = FactoryOBD.crearClienteOBD();
		return obd.select();
	}
	
	public static Cliente traerPorID(Integer ID) {
		ClienteOBD obd = FactoryOBD.crearClienteOBD();
		return obd.selectByID(ID);
	}

	public static List<Cliente> traerPorFallecido(Fallecido fallecido) {
		List<Responsable> responsables = ResponsableManager.traerPorFallecido(fallecido);
		List<Cliente> clientes = new ArrayList<Cliente>();
		for (Responsable responsable : responsables)
			clientes.add(ClienteManager.traerPorID(responsable.getCliente()));
		return clientes;
	}
	
	public static Cliente traerPorDNI(String DNI) {
		ClienteOBD obd = FactoryOBD.crearClienteOBD();		
		return obd.selectByDNI(DNI);
	}
	
	public static Cliente traerMasReciente() {
		ClienteOBD obd = FactoryOBD.crearClienteOBD();		
		return obd.ultimoInsertado();
	}

	public static List<Cliente> traer(String nombres, String apellido, String DNI) throws Exception {
		nombres = Verificador.anular(nombres);
		apellido = Verificador.anular(apellido);
		DNI = Verificador.anular(DNI);
		String mensaje = "";

		if (nombres != null && !Validador.nombrePersona(nombres))
			mensaje += "\n    -El NOMBRE solo puede estar compuesto de letras y espacios.";

		if (apellido != null && !Validador.apellido(apellido))
			mensaje += "\n    -El APELLIDO solo puede estar compuesto de letras y espacios.";
		
		if (DNI != null && !Validador.DNI(DNI))
			mensaje += "\n    -El DNI solo puede estar compuesto de n�meros.";
		
		if (nombres == null && apellido == null && DNI == null)
			mensaje += "\n    -Debe llenar al menos uno de los campos para realizar la busqueda.";
		
		if (!mensaje.equals(""))
			throw new Exception("Se encontraron los siguientes errores:"+mensaje);
		
		ClienteOBD obd = FactoryOBD.crearClienteOBD();
		return obd.selectByNombreApellidoDNI(nombres, apellido, DNI);
	}

}