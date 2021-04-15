package com.ungs.revivir.negocios.manager;

import java.util.List;

import com.ungs.revivir.negocios.Validador;
import com.ungs.revivir.negocios.verificador.Verificador;
import com.ungs.revivir.persistencia.FactoryOBD;
import com.ungs.revivir.persistencia.entidades.Fallecido;
import com.ungs.revivir.persistencia.entidades.Ubicacion;
import com.ungs.revivir.persistencia.interfaces.FallecidoOBD;
import com.ungs.revivir.persistencia.interfaces.UbicacionOBD;

public class FallecidoManager {
	
	
	
	public static void guardar(Fallecido nuevo) throws Exception {
		Fallecido fallecido = Verificador.fallecido(nuevo);
		FallecidoOBD obd = FactoryOBD.crearFallecidoOBD();
		obd.insert(fallecido);
	}

	public static void modificar(Fallecido fallecido) {
		Fallecido fallecido1 = null;
		try {
			 fallecido1 = Verificador.fallecido(fallecido);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FallecidoOBD obd = FactoryOBD.crearFallecidoOBD();
		UbicacionOBD obd1 = FactoryOBD.crearUbicacionOBD();
		fallecido1.setUbicacion(obd1.selectByFallecido(fallecido).getID());
		obd.updateSinUbicacion(fallecido1);
	}
	public static void modificarUbicacion(Fallecido fallecido) {
		Fallecido fallecido1 = null;
		try {
			 fallecido1 = Verificador.fallecido(fallecido);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FallecidoOBD obd = FactoryOBD.crearFallecidoOBD();
		obd.update(fallecido1);
	}
	
	public static void eliminar(Fallecido fallecido) {
		FallecidoOBD obd = FactoryOBD.crearFallecidoOBD();
		UbicacionOBD obdubi = FactoryOBD.crearUbicacionOBD();
		obd.delete(fallecido);
		Ubicacion eliminar = obdubi.selectByFallecido(fallecido);
		obdubi.delete(eliminar);
	}
	
	public static List<Fallecido> traerTodo() {
		FallecidoOBD obd = FactoryOBD.crearFallecidoOBD();
		return obd.select();
	}

	/*public static Fallecido traerPorDNI(String DNI) {
		FallecidoOBD obd = FactoryOBD.crearFallecidoOBD();
		return obd.selectByDNI(DNI);
	}*/
	public static Fallecido traerPorCOD(Integer cod_fallecido) {
		FallecidoOBD obd = FactoryOBD.crearFallecidoOBD();
		return obd.selectByCOD(cod_fallecido);
	}

	public static Fallecido traerPorID(Integer ID) {
		FallecidoOBD obd = FactoryOBD.crearFallecidoOBD();
		return obd.selectByID(ID);
	}
	public static List<Fallecido> traerPorUbicacion(Ubicacion ubicacion) {
		FallecidoOBD obd = FactoryOBD.crearFallecidoOBD();
		return obd.selectByUbicacion(ubicacion);
	}
public Integer selectLastCod_fallecido() {
		//FallecidoOBD obd = FactoryOBD.crearFallecidoOBD();
		//Integer id_fallecido = obd.selectLastID( "rev_fallecidos");
		//Fallecido fallecido = obd.selectByID(id_fallecido);
		//return fallecido.getCod_fallecido();
		return null;
	} 
	
	public static Fallecido traerMasReciente() {
		FallecidoOBD obd = FactoryOBD.crearFallecidoOBD();
		return obd.ultimoInsertado();
	}

	public static List<Fallecido> traer(String nombres, String apellido, /*String DNI*/Integer cod_fallecido) throws Exception {
		nombres = Verificador.anular(nombres);
		apellido = Verificador.anular(apellido);
		//DNI = Verificador.anular(DNI);
		cod_fallecido = Verificador.anularInt(cod_fallecido);
		String mensaje = "";

		if (nombres != null && !Validador.nombrePersona(nombres))
			mensaje += "\n    -El NOMBRE solo puede estar compuesto de letras y espacios.";

		if (apellido != null && !Validador.apellido(apellido))
			mensaje += "\n    -El APELLIDO solo puede estar compuesto de letras y espacios.";
		
		/*if (DNI != null && !Validador.DNI(DNI))
			mensaje += "\n    -El DNI solo puede estar compuesto de n�meros.";*/
		if (cod_fallecido != null && !Validador.cod_fallecido(Integer.toString((cod_fallecido))))
			mensaje += "\n    -El codigo solo puede estar compuesto de n�meros.";
		
		if (nombres == null && apellido == null && /*DNI == null*/ cod_fallecido == null)
			mensaje += "\n    -Debe llenar al menos uno de los campos para realizar la busqueda.";
		
		if (!mensaje.equals(""))
			throw new Exception("Se encontraron los siguientes errores:"+mensaje);
		FallecidoOBD obd = FactoryOBD.crearFallecidoOBD();
		
		return obd.selectByNombreApellidoCOD(nombres, apellido, cod_fallecido);
		/*FallecidoOBD obd = FactoryOBD.crearFallecidoOBD();
		return obd.selectByNombreApellidoDNI(nombres, apellido, DNI);*/
	}
	
}