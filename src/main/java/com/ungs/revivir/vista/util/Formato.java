package com.ungs.revivir.vista.util;


import java.sql.Date;
import java.text.SimpleDateFormat;

import java.util.List;

import com.ungs.revivir.negocios.Vinculador;
import com.ungs.revivir.negocios.manager.CargoManager;
import com.ungs.revivir.negocios.manager.FallecidoManager;
import com.ungs.revivir.negocios.manager.ServicioManager;
import com.ungs.revivir.negocios.manager.UbicacionManager;
import com.ungs.revivir.persistencia.entidades.Cargo;
import com.ungs.revivir.persistencia.entidades.Cliente;
import com.ungs.revivir.persistencia.entidades.Fallecido;
import com.ungs.revivir.persistencia.entidades.Movimiento;
import com.ungs.revivir.persistencia.entidades.Pago;
import com.ungs.revivir.persistencia.entidades.Servicio;
import com.ungs.revivir.persistencia.entidades.Ubicacion;

public class Formato {

	// BASICOS
	public static String cliente(Cliente cliente) {
		return cliente.getApellido()+", "+cliente.getNombre();
	}

	public static String fallecido(Fallecido fallecido) {
		return fallecido.getApellido()+", "+fallecido.getNombre();
	}

	public static String BooleanoACadena(boolean valor) {
		if (valor)
			return "Sí";
		return "No";
	}

	// COMPUESTOS

	public static String DNIfallecido(Pago pago) {
		Cargo cargo = CargoManager.traerPorID(pago.getCargo());
		Fallecido fallecido = FallecidoManager.traerPorID(cargo.getFallecido());
		return fallecido.getDNI();
	}

	public static String codigoServicio(Pago pago) {
		Cargo cargo = CargoManager.traerPorID(pago.getCargo());
		Servicio servicio = ServicioManager.traerPorID(cargo.getServicio());
		return servicio.getCodigo();
	}

	public static String fallecido(Cargo cargo) {
		Fallecido fallecido = FallecidoManager.traerPorID(cargo.getFallecido());
		return fallecido(fallecido);
	}
	
	public static String fallecido(Pago pago) {
		Cargo cargo = CargoManager.traerPorID(pago.getCargo());
		return fallecido(cargo);
	}
	
	public static String fallecido(Movimiento movimiento) {
		Fallecido fallecido = FallecidoManager.traerPorID(movimiento.getFallecido());
		return fallecido(fallecido);
	}
	
	public static String dinero(Double importe) {
		String ret = String.format( "%.2f", importe);
		return "$ "+ret;
	}

	public static String formatoFecha(Date fecha) {
		SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
		if (fecha == null)
			return "Sin fecha";
		return formateador.format(fecha);
	}
	
	public static String ubicacion(Ubicacion ubicacion) {
		String ret = "Sector "+ubicacion.getSubsector();
		ret += (ubicacion.getCirc() != null) ? ", circ " + ubicacion.getCirc() : ""; 
		ret += (ubicacion.getSeccion() != null) ? ", seccion " + ubicacion.getSeccion() : ""; 
		ret += (ubicacion.getMacizo() != null) ? ", macizo " + ubicacion.getMacizo() : ""; 
		ret += (ubicacion.getParcela() != null) ? ", parcela " + ubicacion.getParcela() : ""; 
		ret += (ubicacion.getUnidad() != null) ? ", unidad " + ubicacion.getUnidad() : ""; 
		ret += (ubicacion.getNicho() != null) ? ", nicho " + ubicacion.getNicho() : ""; 
		ret += (ubicacion.getFila() != null) ? ", fila " + ubicacion.getFila() : ""; 
		ret += (ubicacion.getMueble() != null) ? ", mueble " + ubicacion.getMueble() : ""; 
		ret += (ubicacion.getSepultura() != null) ? ", sepultura " + ubicacion.getSepultura() : ""; 
		ret += (ubicacion.getInhumacion() != null) ? ", inhumacion " + ubicacion.getInhumacion() : ""; 
		ret += (ubicacion.getBis() != null) ? ", bis " + Formato.BooleanoACadena(ubicacion.getBis()) : ""; 
		ret += (ubicacion.getBis_macizo() != null) ? ", bis macizo " + Formato.BooleanoACadena(ubicacion.getBis_macizo())  : ""; 
		ret += (ubicacion.getCementerio() != null) ? ", cementerio " + ubicacion.getCementerio(): ""; 
		return ret;
	}
	
	public static String ubicacion(Fallecido fallecido) {
		Ubicacion ubicacion = UbicacionManager.traerPorFallecido(fallecido);
		return ubicacion(ubicacion);
	}
	
	public static String fallecido(Ubicacion ubicacion) {
		List<Fallecido> fallecido =FallecidoManager.traerPorUbicacion(ubicacion);
		return fallecido.get(0).getNombre() + fallecido.get(0).getApellido() ;
	}
	
	public static Integer fallecidoCod(Ubicacion ubicacion) {
		List<Fallecido> fallecido =FallecidoManager.traerPorUbicacion(ubicacion);
		return fallecido.get(0).getCod_fallecido() ;
	}
	
	public static String Vencimientoubicacion(Fallecido fallecido) {
		Ubicacion ubicacion = UbicacionManager.traerPorFallecido(fallecido);
		return formatoFecha(ubicacion.getVencimiento());
	}
	
	public static String ubicaciondesdePago(Pago pago) {
		Cargo cargo = CargoManager.traerPorID(pago.getCargo());
		Fallecido fallecido = FallecidoManager.traerPorID(cargo.getFallecido());
		Ubicacion ubicacion = UbicacionManager.traerPorFallecido(fallecido);
		return ubicacion(ubicacion);
	}

	public static String cargo(Pago pago) {
		Cargo cargo = CargoManager.traerPorID(pago.getCargo());
		return servicio(cargo);
	}

	public static String servicioDesdeCargo(Pago pago) {
		Cargo cargo = CargoManager.traerPorID(pago.getCargo());
		Servicio servicio = ServicioManager.traerPorID(cargo.getServicio());
		return servicio.getNombre();
	}

	public static String servicio(Cargo cargo) {
		Servicio servicio = ServicioManager.traerPorID(cargo.getServicio());
		return servicio.getNombre();
	}
	
	public static String fallecidos(Cliente cliente) {
		List<Fallecido> fallecidos = Vinculador.traerFallecidosDeCliente(cliente);
		String ret = "<html>";

		for (Fallecido fallecido : fallecidos) {
			String nombre = fallecido.getApellido()+", "+fallecido.getNombre();
			if (fallecido != fallecidos.get(0))
				nombre = "<br>" + nombre;
			ret += nombre;
		}
		
		return ret += "</html>";
	}

	public static Integer contarRenglones(String texto) {
		Integer cantidad = 1;
		if (texto == null)
			return 0;
		
		for (int i = 0; i < texto.length()-3; i++)
			if (texto.charAt(i) == '<' && texto.charAt(i+1) == 'b' && texto.charAt(i+2) == 'r' && texto.charAt(i+3) == '>')
				cantidad++;
		
		return cantidad;
	}
	
	public static Integer calcularAlturaDeCelda(Object[] fila) {
		int renglonesMaximo = 0;
		for (Object objecto : fila) {
			int renglones = contarRenglones((String)objecto);
			if (renglones > renglonesMaximo)
				renglonesMaximo = renglones;
		}
		
		return renglonesMaximo*20;
	}

	// REPORTES

}