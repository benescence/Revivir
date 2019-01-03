package com.ungs.revivir.persistencia.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ungs.revivir.persistencia.OBD;
import com.ungs.revivir.persistencia.entidades.Fallecido;
import com.ungs.revivir.persistencia.entidades.Movimiento;
import com.ungs.revivir.persistencia.interfaces.MovimientoOBD;

public class MovimientoOBDMySQL extends OBD implements MovimientoOBD{
	private final String campos = "fallecido, antigua_ubicacion, causa_traslado, observaciones, fecha_movimiento";
	private final String tabla = "rev_movimientos";
	private final String Inner = "rev_movimientos inner join rev_fallecidos";
	
	@Override
	public void insert(Movimiento movimiento) {
		String fallecido = (movimiento.getFallecido() == null) ? null : ""+movimiento.getFallecido()+""; 
		String antiguaUbicacion = (movimiento.getAntiguaUbicacion() == null) ? null : "'"+movimiento.getAntiguaUbicacion()+"'"; 
		String causaTranslado = (movimiento.getCausaTraslado() == null) ? null : "'"+movimiento.getCausaTraslado()+"'"; 
		String observciones = (movimiento.getObservaciones() == null) ? null : "'"+movimiento.getObservaciones()+"'"; 
		String fecha = (movimiento.getFecha() == null) ? null : "'"+movimiento.getFecha()+"'"; 

		String valores = fallecido 
				+", " + antiguaUbicacion
				+", " + causaTranslado
				+", " + observciones
				+", " +fecha;
		String sql = "insert into "+tabla+"("+campos+") values("+valores+");";
		ejecutarSQL(sql);		
	}

	@Override
	public void update(Movimiento movimiento) {
		String antiguaUbicacion = (movimiento.getAntiguaUbicacion() == null) ? null : "'"+movimiento.getAntiguaUbicacion()+"'"; 
		String causaTranslado = (movimiento.getCausaTraslado() == null) ? null : "'"+movimiento.getCausaTraslado()+"'"; 
		String observciones = (movimiento.getObservaciones() == null) ? null : "'"+movimiento.getObservaciones()+"'"; 
		String fecha = (movimiento.getFecha() == null) ? null : "'"+movimiento.getFecha()+"'"; 

		String condicion = "ID = "+movimiento.getID();
		String valores = "fallecido = "+ movimiento.getFallecido()
				+", antigua_ubicacion = "+ antiguaUbicacion
				+", causa_traslado = "+causaTranslado
				+", observaciones = "+observciones
				+", fecha_movimiento = "+fecha;
		String consulta = "update "+tabla+" set "+valores+"  where ("+condicion+");";
		ejecutarSQL(consulta);
	}

	@Override
	public void delete(Movimiento movimiento) {
		String condicion = "ID = "+movimiento.getID();
		String consulta = "delete from "+tabla+" where ("+condicion+");";
		ejecutarSQL(consulta);
	}

	@Override
	public List<Movimiento> select() {
		return selectByCondicion("true");
	}

	@Override
	public Movimiento selectByID(Integer ID) {
		String condicion = "ID = "+ID;
		List<Movimiento> lista = selectByCondicion(condicion);
		if (lista.size() > 0)
			return lista.get(0);
		return null;
	}

	@Override
	public Movimiento ultimoInsertado() {
		Integer ID = selectLastID(tabla);
		if (ID == null)
			return null;
		else
			return selectByID(ID);
	}

	
	// CONSULTAS ESPECIFICAS

	

	private List<Movimiento> selectByCondicion(String condicion) {
		List<Movimiento> ret = new ArrayList<Movimiento>();
		String comandoSQL = "select ID, "+campos+" from "+tabla+" where ("+condicion+");";  
		
		try { 
			Class.forName(driver); 
			Connection conexion = DriverManager.getConnection(cadenaConexion, usuarioBD, passwordBD); 
			Statement sentencia = conexion.createStatement ();
			ResultSet resultados = sentencia.executeQuery(comandoSQL);			

			while (resultados.next()) {
				ret.add(new Movimiento (
						resultados.getInt("ID"),
						resultados.getInt("fallecido"),
						resultados.getString("antigua_ubicacion"),
						resultados.getString("causa_traslado"),
						resultados.getString("observaciones"),
						resultados.getDate("fecha_movimiento")
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
	
	private List<Movimiento> selectByCondicionInner(String condicion) {
		List<Movimiento> ret = new ArrayList<Movimiento>();
		String comandoSQL = "select rev_movimientos.ID, "+campos+" from "+Inner+" where ("+condicion+");";  
		
		try { 
			Class.forName(driver); 
			Connection conexion = DriverManager.getConnection(cadenaConexion, usuarioBD, passwordBD); 
			Statement sentencia = conexion.createStatement ();
			ResultSet resultados = sentencia.executeQuery(comandoSQL);			

			while (resultados.next()) {
				ret.add(new Movimiento (
						resultados.getInt("ID"),
						resultados.getInt("fallecido"),
						resultados.getString("antigua_ubicacion"),
						resultados.getString("causa_traslado"),
						resultados.getString("observaciones"),
						resultados.getDate("fecha_movimiento")
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
/*
	@Override
	public Movimiento selectByDNI(String DNI) {
		// TODO Auto-generated method stub
		return null;
	}
*/
	@Override
	public List<Movimiento> selectByFallecido(Fallecido fallecido) {
		String condicion = "fallecido = "+fallecido.getID();
		return selectByCondicion(condicion);
	}

	
	public List<Movimiento> selectByFallecidoNombre(String nombre, String apellido) {
		String condicion = "";
		if (nombre != null)
			condicion += "upper(nombre) like '"+nombre.toUpperCase()+"%'";
		
		if (apellido != null) {
			if (!condicion.equals(""))
				condicion += " and "; 
			condicion += "upper(apellido) like '"+apellido.toUpperCase()+"%'";
		}
		
		
		return selectByCondicionInner(condicion);
	}
	
}
