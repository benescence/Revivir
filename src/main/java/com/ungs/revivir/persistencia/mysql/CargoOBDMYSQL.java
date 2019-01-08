package com.ungs.revivir.persistencia.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ungs.revivir.persistencia.OBD;
import com.ungs.revivir.persistencia.entidades.Cargo;
import com.ungs.revivir.persistencia.entidades.Fallecido;
import com.ungs.revivir.persistencia.entidades.Servicio;
import com.ungs.revivir.persistencia.interfaces.CargoOBD;

public class CargoOBDMYSQL extends OBD implements CargoOBD{
	private final String campos = "fallecido, servicio, observaciones, pagado";
	private final String tabla = "rev_cargos";
	
	@Override
	public void insert(Cargo cargo) {
		String observaciones = (cargo.getObservaciones() != null) ? ("'"+cargo.getObservaciones()+"'"):null;
		
		String valores = cargo.getFallecido()
				+", "+cargo.getServicio()
				+", "+observaciones
				+", "+cargo.getPagado();
		String sql = "insert into "+tabla+"("+campos+") values("+valores+");";
		ejecutarSQL(sql);		
	}

	@Override
	public void update(Cargo cargo) {
		String observaciones = (cargo.getObservaciones() != null) ? ("'"+cargo.getObservaciones()+"'"):null;
		
		String condicion = "ID = "+cargo.getID();
		String valores = " fallecido = "+cargo.getFallecido()
				+", servicio = "+cargo.getServicio()
				+", observaciones = "+observaciones
				+", pagado = "+cargo.getPagado();
		
		String consulta = "update "+tabla+" set "+valores+"  where ("+condicion+");";
		ejecutarSQL(consulta);
	}

	@Override
	public void delete(Cargo cargo) {
		String condicion = "ID = "+cargo.getID();
		String consulta = "delete from "+tabla+" where ("+condicion+");";
		ejecutarSQL(consulta);
	}

	public Cargo selectByID(Integer ID) {
		String condicion = "ID = "+ID;
		return selectUnicoByCondicio(condicion);
	}
	
	@Override
	public Cargo ultimoInsertado() {
		Integer ID = selectLastID(tabla);
		if (ID == null)
			return null;
		else
			return selectByID(ID);
	}
	
	@Override
	public List<Cargo> select() {
		return selectByCondicion("true");
	}
	
	//*********************** METODOS ESPECIFICOS ************************************************

	@Override
	public List<Cargo> selectByFallecido(Fallecido fallecido) {
		String condicion = "fallecido = "+fallecido.getID();
		return  selectByCondicion(condicion);
	}

	@Override
	public List<Cargo> selectByFallecidoServicio(Fallecido fallecido, Servicio servicio) {
		String condicion = "fallecido = "+fallecido.getID()+" and servicio = "+servicio.getID();
		return  selectByCondicion(condicion);
	}

	@Override
	public List<Cargo> selectByServicio(Servicio servicio) {
		String condicion = "servicio = "+servicio.getID();
		return  selectByCondicion(condicion);
	}

	// ************************ METODOS PRIVADOS ************************************************** 
	
	private Cargo selectUnicoByCondicio(String condicion) {
		List<Cargo> lista = selectByCondicion(condicion);
		if (lista.size() > 0)
			return lista.get(0);
		return null;
	}

	private List<Cargo> selectByCondicion(String condicion) {
		List<Cargo> ret = new ArrayList<Cargo>();
		String comandoSQL = "select ID, "+campos+" from "+tabla+" where ("+condicion+") limit "+limite+";";  
		
		try {
			Class.forName(driver); 
			Connection conexion = DriverManager.getConnection(cadenaConexion, usuarioBD, passwordBD); 
			Statement sentencia = conexion.createStatement ();
			ResultSet resultados = sentencia.executeQuery(comandoSQL);			

			while (resultados.next()) {
				ret.add(new Cargo(
						resultados.getInt("ID"),
						resultados.getInt("fallecido"),
						resultados.getInt("servicio"),
						resultados.getString("observaciones"),
						resultados.getBoolean("pagado")
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
	
}