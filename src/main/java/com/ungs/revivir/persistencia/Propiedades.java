package com.ungs.revivir.persistencia;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class Propiedades {
	private static final String direccion = "config.properties";
	private static List<String> campos = crearCampos();
	
	public static String recuperar1(String clave) {	
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
	
	public static void guardar(String clave, String valor) {
		/*if (!campos.contains(clave)) {
			System.out.println("La palabra "+clave+" no es una clave valida.");
			return;
		}

		// Obtengo el mapa en su estado actual
		//String IP = recuperar("IP"); 
		String puerto = recuperar("puerto"); 
		String usuario_BD = recuperar("usuario_BD");
		String password_BD = recuperar("password_BD");
		String direccion_email = recuperar("direccion_email");
		String password_email = recuperar("password_email");
		
		Map<String, String> mapa = new HashMap<String, String>();
		mapa.put("IP", IP == null ? "" : IP);
		mapa.put("puerto", puerto == null ? "" : puerto);
		mapa.put("usuario_BD", usuario_BD == null ? "" : usuario_BD);
		mapa.put("password_BD", password_BD == null ? "" : password_BD);
		mapa.put("direccion_email", direccion_email == null ? "" : direccion_email);
		mapa.put("password_email", password_email == null ? "" : password_email);
		
		// agrego el nuevo valor y lo guardo todo
		mapa.put(clave, valor);
		guardarTodo(mapa);*/
	}
	
	private static List<String> crearCampos(){
		List<String> lista = new ArrayList<String>();
		lista.add("IP");
		lista.add("puerto");
		lista.add("usuario_BD");
		lista.add("password_BD");
		lista.add("direccion_email");
		lista.add("password_email");
		return lista;
	}
	
	private static void guardarTodo(Map<String, String> valores) {
		Properties parametros = new Properties();
		OutputStream salida = null;
		
		try {
			salida = new FileOutputStream(direccion);
			for (Map.Entry<String, String> entrada: valores.entrySet()) {
				parametros.setProperty(entrada.getKey(), entrada.getValue());
			}
			
			parametros.store(salida, null);
			} catch (Exception io) {
				System.out.println("Error 1"+io.getMessage());
				io.printStackTrace();
			} finally {
				if (salida != null) {
					try {
						salida.close();
							} catch (IOException e) {
								System.out.println("Error 2"+e.getMessage());
								e.printStackTrace();
							}
						}
				
			}
	
	}

	public static void main(String[] args) {
	/*	try {
			guardar("IP", "localhost");
			guardar("puerto", "3306");
			guardar("usuario_BD", "root");
			guardar("password_BD", "root");
			guardar("direccion_email", "root");
			guardar("password_email", "root");
		} catch (Exception e) {
			System.out.println("___ERROR: "+e.getMessage());
		}
		
		System.out.println("IP: "+recuperar("IP"));
		System.out.println("Puerto: "+recuperar("puerto"));
		System.out.println("Usuario BD: "+recuperar("usuario_BD"));
		System.out.println("Password BD: "+recuperar("password_BD"));
		System.out.println("Direccion E-Mail: "+recuperar("direccion_email"));
		System.out.println("Password E-Mail: "+recuperar("password_email"));
		*/
	}

}