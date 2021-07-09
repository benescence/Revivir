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
		Fallecido fallecidoVerificado = null;
		try {
			 fallecidoVerificado = Verificador.fallecido(fallecido);
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		FallecidoOBD fallecidoOBD = FactoryOBD.crearFallecidoOBD();
		UbicacionOBD ubicacionOBD = FactoryOBD.crearUbicacionOBD();
		
		Fallecido fallecidoOriginal = fallecidoOBD.selectByID(fallecido.getID());
		Ubicacion ubicacion = ubicacionOBD.selectByID(fallecidoOriginal.getUbicacion());
		fallecidoVerificado.setUbicacion(ubicacion.getID());
		
		fallecidoOBD.updateSinUbicacion(fallecidoVerificado);
	}
	
	public static void modificarUbicacion(Fallecido fallecido) {
		FallecidoOBD obd = FactoryOBD.crearFallecidoOBD();
		obd.update(fallecido);
	}
	
	public static void eliminar(Fallecido fallecido) {
		// Elimino al fallecido
		FallecidoOBD fallecidoOBD = FactoryOBD.crearFallecidoOBD();
		fallecidoOBD.delete(fallecido);
		
		// Elimino su ubicacion
		UbicacionOBD ubicacionOBD = FactoryOBD.crearUbicacionOBD();
		Ubicacion ubicacion = ubicacionOBD.selectByFallecido(fallecido);
		ubicacionOBD.delete(ubicacion);
	}
	
	public static List<Fallecido> traerTodo() {
		FallecidoOBD obd = FactoryOBD.crearFallecidoOBD();
		return obd.select();
	}

	public static Fallecido traerPorCOD(Integer codFallecido) {
		FallecidoOBD obd = FactoryOBD.crearFallecidoOBD();
		return obd.selectByCOD(codFallecido);
	}

	public static Fallecido traerPorID(Integer ID) {
		FallecidoOBD obd = FactoryOBD.crearFallecidoOBD();
		return obd.selectByID(ID);
	}
	
	public static List<Fallecido> traerPorUbicacion(Ubicacion ubicacion) {
		FallecidoOBD obd = FactoryOBD.crearFallecidoOBD();
		return obd.selectByUbicacion(ubicacion);
	}
	
	public static Integer traerUltimoCodFallecido() {
		FallecidoOBD obd = FactoryOBD.crearFallecidoOBD();
		Integer codFallecido = obd.traerUltimoCodFallecido();
		if (codFallecido != null)
			return codFallecido;
		return 0;
	} 
	
	public static Fallecido traerMasReciente() {
		FallecidoOBD obd = FactoryOBD.crearFallecidoOBD();
		return obd.ultimoInsertado();
	}

	public static List<Fallecido> traer(String nombres, String apellido, Integer codFallecido) throws Exception {
		nombres = Verificador.anular(nombres);
		apellido = Verificador.anular(apellido);
		String mensaje = "";

		if (nombres != null && !Validador.nombrePersona(nombres))
			mensaje += "\n    -El NOMBRE solo puede estar compuesto de letras y espacios.";

		if (apellido != null && !Validador.apellido(apellido))
			mensaje += "\n    -El APELLIDO solo puede estar compuesto de letras y espacios.";
		
		if (codFallecido != null && !Validador.cod_fallecido(Integer.toString((codFallecido))))
			mensaje += "\n    -El codigo solo puede estar compuesto de n�meros.";
		
		if (nombres == null && apellido == null && codFallecido == null)
			mensaje += "\n    -Debe llenar al menos uno de los campos para realizar la busqueda.";
		
		if (!mensaje.equals(""))
			throw new Exception("Se encontraron los siguientes errores:"+mensaje);
		FallecidoOBD obd = FactoryOBD.crearFallecidoOBD();
		
		return obd.selectByNombreApellidoCOD(nombres, apellido, codFallecido);
	}
	
}