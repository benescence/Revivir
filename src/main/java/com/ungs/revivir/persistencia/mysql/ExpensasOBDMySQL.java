package com.ungs.revivir.persistencia.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ungs.revivir.persistencia.OBD;
import com.ungs.revivir.persistencia.entidades.Expensas;
import com.ungs.revivir.persistencia.interfaces.ExpensasOBD;

public class ExpensasOBDMySQL extends OBD implements ExpensasOBD{
	private final String campos = "responsable, periodo, ubicacion, fecha_vencimiento, importe, observaciones";
	private final String tabla = "rev_expensas";
	
	@Override
	public void insert(Expensas expensas) {
		String responsable = (expensas.getResponsable() == null) ? null : "'"+expensas.getResponsable()+"'"; 
		String periodo = (expensas.getPeriodo() == null) ? null : "'"+expensas.getPeriodo()+"'"; 
		String ubicacion = (expensas.getUbicacion() == null) ? null : "'"+expensas.getUbicacion() +"'"; 
		String fecha = (expensas.getFechavencimiento()== null) ? null : "'"+expensas.getFechavencimiento()+"'"; 
		String importe = (expensas.getImporte()== null) ? null : "'"+expensas.getImporte()+"'"; 
		String observaciones = (expensas.getObservaciones() == null) ? null : "'"+expensas.getObservaciones()+"'"; 

		String valores =responsable 
				+", " + periodo
				+", " + ubicacion
				+", " + fecha
				+", " + importe
				+", " + observaciones;
		String sql = "insert into "+tabla+"("+campos+") values("+valores+");";
		ejecutarSQL(sql);		
	}

	@Override
	public void update(Expensas expensas) {
		String responsable = (expensas.getResponsable() == null) ? null : "'"+expensas.getResponsable()+"'"; 
		String periodo= (expensas.getPeriodo() == null) ? null : "'"+expensas.getPeriodo()+"'"; 
		String ubicaciones = (expensas.getUbicacion() == null) ? null : "'"+expensas.getUbicacion()+"'"; 
		String fecha = (expensas.getFechavencimiento() == null) ? null : "'"+expensas.getFechavencimiento()+"'"; 
		String importe = (expensas.getImporte() == null) ? null : "'"+expensas.getImporte()+"'"; 
		String observ = (expensas.getObservaciones() == null) ? null : "'"+expensas.getObservaciones() +"'"; 

		String condicion = "ID = "+expensas.getID();
		String valores = "responsable = "+ responsable
				+", periodo = "+ periodo
				+", ubicacion = "+ ubicaciones
				+", fecha_vencimiento = "+ fecha
				+", importe = "+ importe
				+", observaciones = "+observ;
		String consulta = "update "+tabla+" set "+valores+"  where ("+condicion+");";
		ejecutarSQL(consulta);
	}

	@Override
	public void delete(Expensas expensas) {
		String condicion = "ID = "+expensas.getID();
		String consulta = "delete from "+tabla+" where ("+condicion+");";
		ejecutarSQL(consulta);
	}

	@Override
	public List<Expensas> select() {
		return selectByCondicion("true");
	}

	@Override
	public Expensas selectByID(Integer ID) {
		String condicion = "ID = "+ID;
		List<Expensas> lista = selectByCondicion(condicion);
		if (lista.size() > 0)
			return lista.get(0);
		return null;
	}

	@Override
	public Expensas  ultimoInsertado() {
		Integer ID = selectLastID(tabla);
		if (ID == null)
			return null;
		else
			return selectByID(ID);
	}

	

	@Override
	public 	List<Expensas> selectByFechaVencimiento(Date fecha_vencimiento)
	{
		if (selectByFechaVencimiento(fecha_vencimiento) == null  )
			return selectByCondicion("true");
		
		String condicion = "";
		if (fecha_vencimiento != null)
			condicion += "fecha_vencimiento = '"+fecha_vencimiento+"%'";
	
		
		return selectByCondicion(condicion);
	}
	@Override
	public  List<Expensas>  selectByResponsable(Integer responsable){
	if (selectByResponsable(responsable) == null  )
		return selectByCondicion("true");
	
	String condicion = "";
	if (responsable!= null)
		condicion += "responsable = '"+responsable+"%'";

	
	return selectByCondicion(condicion);
	}
	@Override
	public  List<Expensas>  selectByUbicacion(Integer ubicacion){
		if (selectByUbicacion(ubicacion) == null  )
			return selectByCondicion("true");
		
		String condicion = "";
		if (ubicacion != null)
			condicion += "fecha_vencimiento = '"+ubicacion+"%'";
	
		
		return selectByCondicion(condicion);
	}

	

	private List<Expensas> selectByCondicion(String condicion) {
		List<Expensas> ret = new ArrayList<Expensas>();
		String comandoSQL = "select ID, "+campos+" from "+tabla+" where ("+condicion+");";  
		
		try { 
			Class.forName(driver); 
			Connection conexion = DriverManager.getConnection(cadenaConexion, usuarioBD, passwordBD); 
			Statement sentencia = conexion.createStatement ();
			ResultSet resultados = sentencia.executeQuery(comandoSQL);			

			while (resultados.next()) {
				ret.add(new Expensas(
						resultados.getInt("ID"),
						resultados.getInt("responsable"),
						resultados.getInt("periodo"),
						resultados.getInt("ubicacion"),					
						resultados.getDate("fecha_vencimiento"),
						resultados.getInt("importe"),
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
