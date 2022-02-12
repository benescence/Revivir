package com.ungs.revivir.persistencia;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuracion {
	private String direccion;
	
	public Configuracion(String direccion) {
		this.direccion = direccion;
	}

	public String recuperar(String clave) {
		Properties propiedades = new Properties();
		InputStream entrada = null;
		String ret = "";
		
		try {	
			entrada = new FileInputStream(direccion);
			propiedades.load(entrada);
			ret = propiedades.getProperty(clave);
	
		} catch (IOException ex) {
			ex.printStackTrace();
			
		} finally {
			if (entrada != null) {
				try {
					entrada.close();
				
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	
		return ret;
	}
	
	public void guardar(String clave, String valor) {
		
		Properties propiedades = new Properties();
		
		// Cargo los datos existentes en propiedades
		FileInputStream entrada = null;
		try {
			entrada = new FileInputStream(direccion);
			propiedades.load(entrada);

		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		
		} catch (IOException e1) {
			e1.printStackTrace();
		
		} finally {
			if (entrada != null) {
				try {
					entrada.close();
		
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		// Coloco el nuevo valor y lo guardo
		FileOutputStream salida = null;
		
		try {
			salida = new FileOutputStream(direccion);
			propiedades.setProperty(clave, valor);
			propiedades.store(salida, null);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		
		} finally {
			if (salida != null) {
				try {
					salida.close();
				
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}


	}
	
	public static void main(String[] args) {
		
		Configuracion configuracion = new Configuracion("config.properties");
		
		// Ejecutar para guardar los valores por primera vez o cambiarlos
		configuracion.guardar("cadenaConexion", "TuCadena");
		configuracion.guardar("usuarioBD", "TuUsuario");
		configuracion.guardar("passwordBD", "TuPass");
		configuracion.guardar("driver", "org.postgresql.Driver");
		
		System.out.println("Cadena conexion: " + configuracion.recuperar("cadenaConexion"));
		System.out.println("Usuario BD: " + configuracion.recuperar("usuarioBD"));
		System.out.println("Password BD: " + configuracion.recuperar("passwordBD"));
		System.out.println("Driver : " + configuracion.recuperar("driver"));
	}

}