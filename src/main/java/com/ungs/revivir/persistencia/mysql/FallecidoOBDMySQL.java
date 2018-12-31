package com.ungs.revivir.persistencia.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ungs.revivir.persistencia.Definido;
import com.ungs.revivir.persistencia.OBD;
import com.ungs.revivir.persistencia.entidades.Fallecido;
import com.ungs.revivir.persistencia.entidades.Ubicacion;
import com.ungs.revivir.persistencia.interfaces.FallecidoOBD;

public class FallecidoOBDMySQL extends OBD implements FallecidoOBD{
	private final String campos = " ubicacion, DNI, apellido, nombre, fecha_fallecimiento, tipo_fallecimiento, cocheria, fecha_ingreso";
	private final String tabla = "rev_fallecidos";
	
	@Override
	public void insert(Fallecido obciso) {
		String valores = obciso.getUbicacion()
				+", '"+obciso.getDNI()+"'"
				+", '"+obciso.getApellido()+"'"
				+", '"+obciso.getNombre()+"'"
				+", '"+obciso.getFechaFallecimiento()+"'"+", "+Definido.tipoFallecimiento(obciso.getTipoFallecimiento())+", '"+obciso.getCocheria()+"'"+", '"+obciso.getFechaIngreso()+"'";
		String sql = "insert into "+tabla+"("+campos+") values("+valores+");";
		ejecutarSQL(sql);		
	}

	@Override
	public void update(Fallecido fallecido) {
		String condicion = "ID = "+fallecido.getID();
		String valores = " DNI = '"+fallecido.getDNI()+"'"
				+", apellido = '"+fallecido.getApellido()+"'"
				+", ubicacion = "+fallecido.getUbicacion()
				+", nombre = '"+fallecido.getNombre()+"'"
				+", fecha_fallecimiento = '"+fallecido.getFechaFallecimiento()+"'"
				+", tipo_fallecimiento = "+Definido.tipoFallecimiento(fallecido.getTipoFallecimiento())
				+", cocheria = '"+fallecido.getCocheria()+"'"
				+", fecha_ingreso = '"+fallecido.getFechaIngreso()+"'";
		String consulta = "update "+tabla+" set "+valores+"  where ("+condicion+");";
		ejecutarSQL(consulta);
	}

	@Override
	public void delete(Fallecido obciso) {
		String condicion = "ID = "+obciso.getID();
		String consulta = "delete from "+tabla+" where ("+condicion+");";
		ejecutarSQL(consulta);
	}

	
	
	@Override
	public List<Fallecido> select() {
		return selectByCondicion("true");
	}

	@Override
	public List<Fallecido> selectByNombreApellidoDNI(String nombre, String apellido, String DNI) {
		String condicion = "";
		if (nombre != null)
			condicion += "upper(nombre) like '"+nombre.toUpperCase()+"%'";
		
		if (apellido != null) {
			if (!condicion.equals(""))
				condicion += " and "; 
			condicion += "upper(apellido) like '"+apellido.toUpperCase()+"%'";
		}
		
		if (DNI != null) {
			if (!condicion.equals(""))
				condicion += " and "; 
			condicion += "upper(DNI) like '"+DNI.toUpperCase()+"%'";
		}
		
		return selectByCondicion(condicion);
	}

	
	@Override
	public List<Fallecido> selectByNombreApellido(String nombre, String apellido) {
		String condicion = "";
		if (nombre != null)
			condicion += "upper(nombre) like '"+nombre.toUpperCase()+"%'";
		
		if (apellido != null) {
			if (!condicion.equals(""))
				condicion += " and "; 
			condicion += "upper(apellido) like '"+apellido.toUpperCase()+"%'";
		}
		
		
		return selectByCondicion(condicion);
	}
	
	private List<Fallecido> selectByCondicion(String condicion) {
		List<Fallecido> ret = new ArrayList<Fallecido>();
		String comandoSQL = "select ID, "+campos+" from "+tabla+" where ("+condicion+");";  
		
		try { 
			Class.forName(driver); 
			Connection conexion = DriverManager.getConnection(cadenaConexion, usuarioBD, passwordBD); 
			Statement sentencia = conexion.createStatement ();
			ResultSet resultados = sentencia.executeQuery(comandoSQL);			

			while (resultados.next()) {
				ret.add(new Fallecido(
						resultados.getInt("ID"),
						resultados.getInt("ubicacion"),
						Definido.tipoFallecimiento(resultados.getInt("tipo_fallecimiento")),
						resultados.getString("DNI"),
						resultados.getString("apellido"),
						resultados.getString("nombre"),
						resultados.getString("cocheria"),
						resultados.getDate("fecha_fallecimiento"),
						resultados.getDate("fecha_ingreso")
						));
			}
			
			resultados.close();
			sentencia.close();
			conexion.close();
			
		}catch(Exception e) {
			System.out.println(comandoSQL);
			e.printStackTrace();
		}
			
		return ret;
	}

	@Override
	public Fallecido selectByDNI(String DNI) {
		String condicion = "DNI = '"+DNI+"'";
		List<Fallecido> lista = selectByCondicion(condicion);
		if (lista.isEmpty())
			return null;
		return lista.get(0);
	}

	@Override
	public List<Fallecido> selectByUbicacion(Ubicacion ubicacion) {
		String condicion = "ubicacion = "+ubicacion.getID();
		return selectByCondicion(condicion);
	}

	@Override
	public Fallecido ultimoInsertado() {
		Integer ID = selectLastID(tabla);
		if (ID == null)
			return null;
		else
			return selectByID(ID);
	}

	public Fallecido selectByID(Integer ID) {
		String condicion = "ID = "+ID;
		List<Fallecido> lista = selectByCondicion(condicion);
		if (lista.size() > 0)
			return lista.get(0);
		return null;
	}

}