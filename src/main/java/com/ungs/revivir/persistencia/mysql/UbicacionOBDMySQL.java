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
import com.ungs.revivir.persistencia.interfaces.UbicacionOBD;

public class UbicacionOBDMySQL extends OBD implements UbicacionOBD{
	private final String campos = "subsector, otro_cementerio, nicho, fila,"
			+ "seccion, macizo, unidad, bis, bis_macizo, sepultura, parcela, mueble, inhumacion, circ";
	private final String tabla = "rev_ubicaciones";
	
	@Override
	public void insert(Ubicacion ubicacion) {
		String otroCementerio = (ubicacion.getOtroCementerio() != null) ? "'"+ubicacion.getOtroCementerio()+"'" : null;
		String seccion = (ubicacion.getSeccion() != null) ? "'"+ubicacion.getSeccion()+"'" : null;
		
		String valores = Definido.subsector(ubicacion.getSubsector())
				+", "+otroCementerio
				+", "+ubicacion.getNicho()
				+", "+ubicacion.getFila()
				+", "+seccion
				+", "+ubicacion.getMacizo()
				+", "+ubicacion.getUnidad()
				+", "+ubicacion.getBis()
				+", "+ubicacion.getBis_macizo()
				+", "+ubicacion.getSepultura()
				+", "+ubicacion.getParcela()
				+", "+ubicacion.getMueble()
				+", "+ubicacion.getInhumacion()
				+", "+ubicacion.getCirc();
		String sql = "insert into "+tabla+"("+campos+") values("+valores+");";
		ejecutarSQL(sql);		
	}

	@Override
	public void update(Ubicacion ubicacion) {
		String otroCementerio = (ubicacion.getOtroCementerio() != null) ? "'"+ubicacion.getOtroCementerio()+"'" : null;
		String seccion = (ubicacion.getSeccion() != null) ? "'"+ubicacion.getSeccion()+"'" : null;
		
		String condicion = "ID = "+ubicacion.getID();
		String valores = " subsector = "+Definido.subsector(ubicacion.getSubsector())
				+", otro_cementerio = "+otroCementerio
				+", Nicho = "+ubicacion.getNicho()
				+", Fila = "+ubicacion.getFila()
				+", Seccion = "+seccion
				+", Macizo = "+ubicacion.getMacizo()
				+", Unidad = "+ubicacion.getUnidad()
				+", bis = "+ubicacion.getBis()
				+", bis_macizo = "+ubicacion.getBis_macizo()
				+", Sepultura = "+ubicacion.getSepultura()
				+", Parcela = "+ubicacion.getParcela()
				+", Mueble = "+ubicacion.getMueble()
				+", Inhumacion = "+ubicacion.getInhumacion()
				+", Circ = "+ubicacion.getCirc();
		String consulta = "update "+tabla+" set "+valores+"  where ("+condicion+");";
		ejecutarSQL(consulta);
	}

	@Override
	public void delete(Ubicacion ubicacion) {
		String condicion = "ID = "+ubicacion.getID();
		String consulta = "delete from "+tabla+" where ("+condicion+");";
		ejecutarSQL(consulta);	
	}

	@Override
	public Ubicacion selectByID(Integer ID) {
		String condicion = "ID = "+ID;
		List<Ubicacion> lista = selectByCondicion(condicion);
		if (lista.size() > 0)
			return lista.get(0);
		return null;
	}

	@Override
	public Ubicacion ultimoInsertado() {
		Integer ID = selectLastID(tabla);
		if (ID == null)
			return null;
		else
			return selectByID(ID);
	}

	@Override
	public Ubicacion selectByFallecido(Fallecido fallecido) {
		String condicion = "ID = "+fallecido.getUbicacion();
		List<Ubicacion> lista = selectByCondicion(condicion);
		if (lista.size() > 0)
			return lista.get(0);
		return null;
	}
	
	@Override
	public List<Ubicacion> select() {
		return selectByCondicion("true");
	}
	
	private List<Ubicacion> selectByCondicion(String condicion) {
		List<Ubicacion> ret = new ArrayList<>();
		String comandoSQL = "select ID, "+campos+" from "+tabla+" where ("+condicion+");";  
		
		try { 
			Class.forName(driver); 
			Connection conexion = DriverManager.getConnection(cadenaConexion, usuarioBD, passwordBD); 
			Statement sentencia = conexion.createStatement ();
			ResultSet resultados = sentencia.executeQuery(comandoSQL);			

			while (resultados.next()) {
				
				Integer fila = resultados.getInt("fila");
				fila = (resultados.wasNull())? null: fila;
				
				Integer nicho = resultados.getInt("nicho");
				nicho = (resultados.wasNull())? null: nicho;
				
				
				
				
				ret.add(new Ubicacion(
						resultados.getInt("ID"),
						Definido.subsector(resultados.getInt("subsector")),
						resultados.getString("otro_cementerio"),
						nicho,
						fila,
						resultados.getString("seccion"),
						resultados.getInt("macizo"),
						resultados.getInt("unidad"),
						resultados.getBoolean("bis"),
						resultados.getBoolean("bis_macizo"),
						resultados.getInt("sepultura"),
						resultados.getInt("parcela"),
						resultados.getInt("mueble"),
						resultados.getInt("inhumacion"),
						resultados.getInt("circ")
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