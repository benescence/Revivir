package com.ungs.revivir.persistencia.mysql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ungs.revivir.persistencia.OBD;
import com.ungs.revivir.persistencia.entidades.Cargo;
import com.ungs.revivir.persistencia.entidades.Pago;
import com.ungs.revivir.persistencia.interfaces.PagoOBD;

public class PagoOBDMYSQL extends OBD implements PagoOBD{
	private final String campos = "cargo, importe, observaciones, fecha";
	private final String tabla = "rev_pagos";
	
	@Override
	public void insert(Pago pago) {
		String observaciones = (pago.getObservaciones() != null) ? "'"+pago.getObservaciones()+"'" : null;
		String valores = pago.getCargo()
				+", "+pago.getImporte()
				+", "+observaciones
				+", '"+pago.getFecha()+"'";
		String sql = "insert into "+tabla+"("+campos+") values("+valores+");";
		ejecutarSQL(sql);		
	}

	@Override
	public void update(Pago pago) {
		String observaciones = (pago.getObservaciones() != null) ? "'"+pago.getObservaciones()+"'" : null;
		
		String condicion = "ID = "+pago.getID();
		String valores = " cargo = "+pago.getCargo()
				+", importe = "+pago.getImporte()
				+", observaciones = "+observaciones
				+", fecha= '"+pago.getFecha()+"'";
		String consulta = "update "+tabla+" set "+valores+"  where ("+condicion+");";
		ejecutarSQL(consulta);
	}

	@Override
	public void delete(Pago pago) {
		String condicion = "ID = "+pago.getID();
		String consulta = "delete from "+tabla+" where ("+condicion+");";
		ejecutarSQL(consulta);
	}

	@Override
	public Pago selectByID(Integer ID) {
		String condicion = "ID = "+ID;
		return selectUnicoByCondicion(condicion);
	}	
	
	@Override
	public Pago ultimoInsertado() {
		Integer ID = selectLastID(tabla);
		return (ID == null) ? null :selectByID(ID);
	}

	@Override
	public List<Pago> select() {
		return selectByCondicion("true");
	}

	//*********************** METODOS ESPECIFICOS ************************************
	
	/*@Override
	public List<Pago> selectByCliente(Cliente cliente) {
		String condicion = "cliente = "+cliente.getID();
		return selectByCondicion(condicion);
	}
	
	@Override
	public List<Pago> selectByClienteFecha(Cliente cliente, Date fecha) {
		String condicion = "cliente = "+cliente.getID()+" and fecha = '"+fecha+"'";
		return selectByCondicion(condicion);
	}*/
	
	@Override
	public List<Pago> selectByFecha(Date fecha) {
		String condicion = "fecha = '" +fecha+"'";
		return selectByCondicion(condicion);
	}

	@Override
	public List<Pago> selectByCargo(Cargo cargo) {
		String condicion = "cargo = "+cargo.getID();
		return selectByCondicion(condicion);
	}

	//*********************** METODOS PRIVADOS ************************************
	
	private List<Pago> selectByCondicion(String condicion) {
		List<Pago> ret = new ArrayList<Pago>();
		String comandoSQL = "select ID, "+campos+" from "+tabla+" where ("+condicion+") limit "+limite+";";
		try { 
			Class.forName(driver); 
			Connection conexion = DriverManager.getConnection(cadenaConexion, usuarioBD, passwordBD); 
			Statement sentencia = conexion.createStatement ();
			ResultSet resultados = sentencia.executeQuery(comandoSQL);			

			while (resultados.next()) {
				ret.add(new Pago(
					resultados.getInt("ID"),
					resultados.getInt("cargo"),
					//resultados.getInt("cliente"),
					resultados.getDouble("importe"),
					resultados.getString("observaciones"),
					resultados.getDate("fecha")
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

	private Pago selectUnicoByCondicion(String condicion) {
		List<Pago> lista = selectByCondicion(condicion);
		return (lista.size() > 0) ? lista.get(0) : null; 
	}

	@Override
	public List<Pago> selectByCargosDesdeHasta(List<Cargo> cargos, Date desde, Date hasta) {
		if (cargos.size() == 0)
			return new ArrayList<>();
		
		String condicionFecha = "fecha between '" +desde+"' and '"+hasta+"'";
		String condicionCargo = "cargo in (";
		
		for (int i = 0; i<cargos.size(); i++) {
			if (i>0)
				condicionCargo += ", ";
			condicionCargo += cargos.get(i).getID();
		}
		
		condicionCargo += ")";
		
		String condicion = "("+condicionFecha+") and ("+condicionCargo+")";
		return selectByCondicion(condicion);
	}

}