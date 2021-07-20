package com.ungs.revivir.persistencia.mysql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ungs.revivir.persistencia.OBD;
import com.ungs.revivir.persistencia.entidades.ReportePago;
import com.ungs.revivir.persistencia.interfaces.ReportePagoOBD;

public class ReportePagoOBDMySQL extends OBD implements ReportePagoOBD {
	private final String campos = "id_pago, importe, observaciones_pago, fecha_pago, id_cargo, observaciones_cargo, pagado, "
			+ "id_fallecido, nombre, apellido, id_servicio, servicio_nombre, historico";
	private final String tabla = "rev_v_reporte_pagos";
	

	//*********************** METODOS ESPECIFICOS ************************************
	
	@Override
	public List<ReportePago> selectByFecha(Date fecha) {
		String condicion = "fecha_pago = '" +fecha+"'";
		return selectByCondicion(condicion);
	}


	//*********************** METODOS PRIVADOS ************************************
	
	private List<ReportePago> selectByCondicion(String condicion) {
		List<ReportePago> ret = new ArrayList<ReportePago>();
		String comandoSQL = "select "+campos+" from "+tabla+" where ("+condicion+") limit "+limite+";";
		try { 
			Class.forName(driver); 
			Connection conexion = DriverManager.getConnection(cadenaConexion, usuarioBD, passwordBD); 
			Statement sentencia = conexion.createStatement ();
			ResultSet resultados = sentencia.executeQuery(comandoSQL);			

			while (resultados.next()) {
				ret.add(new ReportePago(
					resultados.getInt("id_pago"),
					resultados.getFloat("importe"),
					resultados.getString("observaciones_pago"),
					resultados.getDate("fecha_pago"),
					resultados.getInt("id_cargo"),
					resultados.getString("observaciones_cargo"),
					resultados.getBoolean("pagado"),
					resultados.getInt("id_fallecido"),
					resultados.getString("nombre"),
					resultados.getString("apellido"),
					resultados.getInt("id_servicio"),
					resultados.getString("servicio_nombre"),
					resultados.getBoolean("historico")
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