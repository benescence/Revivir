package com.ungs.revivir.persistencia.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ungs.revivir.persistencia.OBD;
import com.ungs.revivir.persistencia.entidades.Servicio;
import com.ungs.revivir.persistencia.interfaces.ServicioOBD;

public class ServicioOBDMySQL extends OBD implements ServicioOBD{
	private final String campos = "codigo, nombre, importe, descripcion, historico ";
	private final String tabla = "rev_servicios";
	
	@Override
	public void insert(Servicio servicio) {
		String valores = "'"+servicio.getCodigo()+"'"
				+", '"+servicio.getNombre()+"'"
				+", "+servicio.getImporte()
				+", '"+servicio.getDescripcion()+"'"
				+", "+servicio.getHistorico();
		String sql = "insert into "+tabla+"("+campos+") values("+valores+");";
		ejecutarSQL(sql);		
	}

	@Override
	public void update(Servicio precio) {
		String condicion = "ID = "+precio.getID();
		String valores = "codigo = '"+precio.getCodigo()+"'"
				+", descripcion = '"+precio.getDescripcion()+"'"
				+", importe = "+precio.getImporte()
				+", nombre = '"+precio.getNombre()+"'"
				+", historico = "+precio.getHistorico();
		String consulta = "update "+tabla+" set "+valores+"  where ("+condicion+");";
		ejecutarSQL(consulta);
	}

	@Override
	public void delete(Servicio precio) {
		String condicion = "ID = "+precio.getID();
		String consulta = "delete from "+tabla+" where ("+condicion+");";
		ejecutarSQL(consulta);
	}

	@Override
	public List<Servicio> select() {
		return selectByCondicion("true");
	}
	
	@Override
	public List<Servicio> selectActivos() {
		return selectByCondicion("historico = false");
	}
	
	@Override
	public Servicio selectByID(Integer ID) {
		String condicion = "ID = "+ID;
		List<Servicio> lista = selectByCondicion(condicion);
		if (lista.size() > 0)
			return lista.get(0);
		return null;
	}

	@Override
	public List<Servicio> selectByCodigoNombre(String codigo, String nombre) {
		String condicion = "";
		if (nombre != null)
			condicion += "upper(nombre) like '"+nombre.toUpperCase()+"%'";
		
		if (codigo != null) {
			if (!condicion.equals(""))
				condicion += " and "; 
			condicion += "upper(codigo) like '"+codigo.toUpperCase()+"%'";
		}
				
		return selectByCondicion(condicion);
	}
	
	@Override
	public Servicio selectActivoBycodigo(String codigo) {
		String condicion = "codigo = " +(codigo != null ? "'"+codigo+"'" : "codigo")+" and historico = false";
		List<Servicio> lista = selectByCondicion(condicion);
		if (lista.size() > 0)
			return lista.get(0);
		return null;
	}
	
	private List<Servicio> selectByCondicion(String condicion) {
		List<Servicio> ret = new ArrayList<Servicio>();
		String comandoSQL = "select ID, "+campos+" from "+tabla+" where ("+condicion+");";  
		
		try { 
			Class.forName(driver); 
			Connection conexion = DriverManager.getConnection(cadenaConexion, usuarioBD, passwordBD); 
			Statement sentencia = conexion.createStatement ();
			ResultSet resultados = sentencia.executeQuery(comandoSQL);			

			while (resultados.next()) {
				ret.add(new Servicio(
						resultados.getInt("ID"),
						resultados.getString("codigo"),
						resultados.getString("nombre"),
						resultados.getString("descripcion"),
						resultados.getDouble("importe"),
						resultados.getBoolean("historico")
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
	public Servicio ultimoInsertado() {
		Integer ID = selectLastID(tabla);
		if (ID == null)
			return null;
		else
			return selectByID(ID);
	}

}