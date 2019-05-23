package com.ungs.revivir.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class OBD {
	protected final String driver = "com.mysql.jdbc.Driver";
	protected final Integer limite = 20;
	
	// DESARROLLO
	protected final String cadenaConexion = "jdbc:mysql://localhost:3306/revivir"; 
	protected String usuarioBD = "root"; 
	protected String passwordBD = "root";
	//protected String passwordBD = "pass";
	
	// PRODUCCION
	//protected final String cadenaConexion = "jdbc:mysql://sql143.main-hosting.eu:3306/u147800277_cemen"; 
	//protected String usuarioBD = "u147800277_ben";
	//protected String passwordBD = "Tiburones";
			
	// Ejecutar sentencias que no traigan resultados
	public void ejecutarSQL(String sql) {
		try { 
			Class.forName(driver); 
			Connection conexion = DriverManager.getConnection(cadenaConexion, usuarioBD, passwordBD);
			Statement sentencia = conexion.createStatement();
			sentencia.execute(sql);
			sentencia.close();
			conexion.close();
			
		}catch(Exception e) {
			System.out.println("       ERROR: "+sql);
			e.printStackTrace();
		}
	}

	public Integer selectLastID(String tabla) {
		String sql = "select ID from "+tabla+" order by ID desc limit 1";
		Integer ret = null;
		try { 
			Class.forName(driver); 
			Connection conexion = DriverManager.getConnection(cadenaConexion, usuarioBD, passwordBD); 
			Statement sentencia = conexion.createStatement ();
			ResultSet resultados = sentencia.executeQuery(sql);			
	
			if (resultados.next())
				ret = resultados.getInt("ID");
				
			resultados.close();
			sentencia.close();
			conexion.close();
			
		}catch(Exception e) {
			System.out.println(sql);
			e.printStackTrace();
		}
			
		return ret;
	}

}
	
