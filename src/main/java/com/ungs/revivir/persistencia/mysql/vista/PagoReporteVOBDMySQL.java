package com.ungs.revivir.persistencia.mysql.vista;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ungs.revivir.persistencia.Definido;
import com.ungs.revivir.persistencia.OBD;
import com.ungs.revivir.persistencia.entidades.Fallecido;
import com.ungs.revivir.persistencia.entidades.vista.VPagoReporte;
import com.ungs.revivir.persistencia.interfaces.vista.PagoReporteVOBD;

public class PagoReporteVOBDMySQL extends OBD implements PagoReporteVOBD {
	private final String campos = "pago_id, pago_importe, pago_observaciones, pago_fecha, "
			+ "cargo_id, cargo_observaciones, cargo_pagado, "
			+ "fallecido_id, fallecido_nombre, fallecido_apellido, fallecido_dni, "
			+ "servicio_id, servicio_nombre, servicio_historico, "
			+ "ubicacion_ID, ubicacion_subsector, ubicacion_circ, ubicacion_seccion, ubicacion_macizo, ubicacion_parcela, "
			+ "ubicacion_fila, ubicacion_unidad, ubicacion_nicho, ubicacion_mueble, ubicacion_sepultura, "
			+ "ubicacion_inhumacion, ubicacion_cementerio, ubicacion_vencimiento, ubicacion_bis_macizo, ubicacion_bis";
	private final String tabla = "rev_v_reporte_pagos";
	
	

	//*********************** METODOS ESPECIFICOS ************************************
	
	@Override
	public List<VPagoReporte> selectByFecha(Date fecha) {
		String condicion = "pago_fecha = '" +fecha+"'";
		return selectByCondicion(condicion);
	}

	@Override
	public List<VPagoReporte> selectByFallecidoDesdeHasta(Fallecido fallecido, Date fechaDesde, Date fechaHasta) {
		String condicion = "fallecido_id = " + fallecido.getID() +" "
				+ "and pago_fecha >= '" + fechaDesde +"' "
				+ "and pago_fecha <= '" + fechaHasta +"' ";
		return selectByCondicion(condicion);
	}
	
	//*********************** METODOS PRIVADOS ************************************
	
	private List<VPagoReporte> selectByCondicion(String condicion) {
		String comandoSQL = "select "+campos+" from "+tabla+" where ("+condicion+") limit "+limite+";";
		return selectBySQL(comandoSQL);
	}
	
	private List<VPagoReporte> selectBySQL(String comandoSQL) {
		List<VPagoReporte> ret = new ArrayList<VPagoReporte>();
		try { 
			Class.forName(driver); 
			Connection conexion = DriverManager.getConnection(cadenaConexion, usuarioBD, passwordBD); 
			Statement sentencia = conexion.createStatement ();
			ResultSet resultados = sentencia.executeQuery(comandoSQL);

			while (resultados.next()) {
				ret.add(new VPagoReporte(
					resultados.getInt("pago_id"),
					resultados.getDouble("pago_importe"),
					resultados.getString("pago_observaciones"),
					resultados.getDate("pago_fecha"),
					resultados.getInt("cargo_id"),
					resultados.getString("cargo_observaciones"),
					resultados.getBoolean("cargo_pagado"),
					resultados.getInt("fallecido_id"),
					resultados.getString("fallecido_nombre"),
					resultados.getString("fallecido_apellido"),
					resultados.getString("fallecido_dni"),
					resultados.getInt("servicio_id"),
					resultados.getString("servicio_nombre"),
					resultados.getBoolean("servicio_historico"),
					resultados.getInt("ubicacion_ID"),
					Definido.subsector(resultados.getInt("ubicacion_subsector")),
					resultados.getInt("ubicacion_circ"),
					resultados.getString("ubicacion_seccion"),
					resultados.getInt("ubicacion_macizo"),
					resultados.getInt("ubicacion_parcela"),
					resultados.getInt("ubicacion_fila"),
					resultados.getInt("ubicacion_unidad"),
					resultados.getInt("ubicacion_nicho"),
					resultados.getInt("ubicacion_mueble"),
					resultados.getInt("ubicacion_sepultura"),
					resultados.getInt("ubicacion_inhumacion"),
					resultados.getString("ubicacion_cementerio"),
					resultados.getDate("ubicacion_vencimiento"),
					resultados.getBoolean("ubicacion_bis_macizo"),
					resultados.getBoolean("ubicacion_bis")
					));
			}
			
			resultados.close();
			sentencia.close();
			conexion.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
			
		return ret;
	}

}