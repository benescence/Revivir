package com.ungs.revivir.persistencia.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ungs.revivir.persistencia.OBD;
import com.ungs.revivir.persistencia.entidades.Cliente;
import com.ungs.revivir.persistencia.entidades.Fallecido;
import com.ungs.revivir.persistencia.entidades.Responsable;
import com.ungs.revivir.persistencia.interfaces.ResponsableOBD;

public class ResponsableOBDMySQL extends OBD implements ResponsableOBD{
	private final String campos = "cliente, fallecido, observaciones";
	private final String tabla = "rev_responsables";
	
	@Override
	public void insert(Responsable responsable) {
		String observaciones =  (responsable.getObservaciones() != null) ? "'"+responsable.getObservaciones()+"'" :null;
		
		String valores = responsable.getCliente()
				+", "+responsable.getFallecido()
				+", "+observaciones;
		String sql = "insert into "+tabla+"("+campos+") values("+valores+");";
		ejecutarSQL(sql);		
	}

	@Override
	public void update(Responsable responsable) {
		String observaciones =  (responsable.getObservaciones() != null) ? "'"+responsable.getObservaciones()+"'" :null;
		
		String condicion = "ID = "+responsable.getID();
		String valores = " cliente = '"+responsable.getCliente()+"'"
				+", fallecido= '"+responsable.getFallecido()+"'"
				+", observaciones = "+observaciones;
		String consulta = "update "+tabla+" set "+valores+"  where ("+condicion+");";
		ejecutarSQL(consulta);
	}

	@Override
	public void delete(Responsable responsable) {
		String condicion = "ID = "+responsable.getID();
		String consulta = "delete from "+tabla+" where ("+condicion+");";
		ejecutarSQL(consulta);
	}

	@Override
	public Responsable selectByID(Integer ID) {
		String condicion = "ID = "+ID;
		return selectUnicoByCondicion(condicion);
	}
	
	@Override
	public Responsable ultimoInsertado() {
		Integer ID = selectLastID(tabla);
		if (ID == null)
			return null;
		else
			return selectByID(ID);
	}
	
	@Override
	public List<Responsable> select() {
		return selectByCondicion("true");
	}

	
	//************************** METODOS ESPECIFICOS ************************************
	
	
	@Override
	public List<Responsable> selectByCliente(Cliente cliente) {
		String condicion = "cliente = "+cliente.getID();
		return selectByCondicion(condicion);
	}

	@Override
	public List<Responsable> selectByFallecido(Fallecido fallecido) {
		String condicion = "fallecido = "+fallecido.getID();
		return selectByCondicion(condicion);
	}

	@Override
	public Responsable selectByClienteFallecido(Cliente cliente, Fallecido fallecido) {
		String condicion = "cliente = "+cliente.getID() +" and fallecido = " +fallecido.getID();
		return selectUnicoByCondicion(condicion);
	}
	
	
	//**************************** METODOS PRIVADOS *************************************

	
	private Responsable selectUnicoByCondicion(String condicion) {
		List<Responsable> lista = selectByCondicion(condicion);
		if (lista.size() > 0)
			return lista.get(0);
		return null;
	}

	private List<Responsable> selectByCondicion(String condicion) {
		List<Responsable> ret = new ArrayList<Responsable>();
		String comandoSQL = "select ID, "+campos+" from "+tabla+" where ("+condicion+") limit "+limite+";";
		
		try { 
			Class.forName(driver); 
			Connection conexion = DriverManager.getConnection(cadenaConexion, usuarioBD, passwordBD); 
			Statement sentencia = conexion.createStatement ();
			ResultSet resultados = sentencia.executeQuery(comandoSQL);			

			while (resultados.next()) {
				ret.add(new Responsable(
					resultados.getInt("ID"),
					resultados.getInt("cliente"),
					resultados.getInt("fallecido"),
					resultados.getString("observaciones")
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