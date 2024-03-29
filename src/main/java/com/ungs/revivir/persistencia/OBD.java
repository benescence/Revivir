package com.ungs.revivir.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OBD {
	public String tabla;
	public String campos;
	protected static String driver;
	protected static String cadenaConexion;
	protected static String usuarioBD; 
	protected static String passwordBD;

	static {
		Configuracion configuracion = new Configuracion("config.properties");
		driver = configuracion.recuperar("driver");
		cadenaConexion = configuracion.recuperar("cadenaConexion");
		usuarioBD = configuracion.recuperar("usuarioBD");
		passwordBD = configuracion.recuperar("passwordBD");
	}

	
	protected final Integer limite = 100;
	protected static Connection conexion = null;
	

	// DESARROLLO
	//protected final String cadenaConexion = "jdbc:mysql://localhost:3306/revivir_escobar?serverTimezone=UTC"; 
	//protected String usuarioBD = "root"; 
	//protected String passwordBD = "root";
	
	 

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
	
	public Connection getConexion() {
		if (conexion == null) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conexion = DriverManager.getConnection(cadenaConexion, usuarioBD, passwordBD);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return conexion;
	}

	public void desconectar() {
		try {
			conexion.close();
			conexion = null;
		} catch (Exception ex) {
			ex.printStackTrace();
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
	
	public void ejecutarTimeZone() {
		try {
			
			ejecutarSQL("SET @@global.time_zone = '+00:00' ;");
			ejecutarSQL("SET @@session.time_zone = '+00:00';");
			
		}catch(Exception e) {
			e.printStackTrace();
		}	
	}

}