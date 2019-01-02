package com.ungs.revivir.persistencia.mysql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ungs.revivir.persistencia.Definido;
import com.ungs.revivir.persistencia.OBD;
import com.ungs.revivir.persistencia.definidos.SubSector;
import com.ungs.revivir.persistencia.entidades.Fallecido;
import com.ungs.revivir.persistencia.entidades.Ubicacion;
import com.ungs.revivir.persistencia.interfaces.UbicacionOBD;

public class UbicacionOBDMySQL extends OBD implements UbicacionOBD{
	private final String campos = "subsector, cementerio, nicho, fila,"
			+ "seccion, macizo, unidad, bis, bis_macizo, sepultura, parcela, mueble, inhumacion, circ, vencimiento";
	private final String tabla = "rev_ubicaciones";
	
	@Override
	public void insert(Ubicacion ubicacion) {
		String cementerio = (ubicacion.getCementerio() != null) ? "'"+ubicacion.getCementerio()+"'" : null;
		String seccion = (ubicacion.getSeccion() != null) ? "'"+ubicacion.getSeccion()+"'" : null;
		String vencimiento = (ubicacion.getVencimiento() != null) ? "'"+ubicacion.getVencimiento()+"'" : null;
		
		String valores = Definido.subsector(ubicacion.getSubsector())
				+", "+cementerio
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
				+", "+ubicacion.getCirc()
				+", "+vencimiento;
		String sql = "insert into "+tabla+"("+campos+") values("+valores+");";
		ejecutarSQL(sql);		
	}

	@Override
	public void update(Ubicacion ubicacion) {
		String cementerio = (ubicacion.getCementerio() != null) ? "'"+ubicacion.getCementerio()+"'" : null;
		String seccion = (ubicacion.getSeccion() != null) ? "'"+ubicacion.getSeccion()+"'" : null;
		String vencimiento = (ubicacion.getVencimiento() != null) ? "'"+ubicacion.getVencimiento()+"'" : null;
		
		String condicion = "ID = "+ubicacion.getID();
		String valores = " subsector = "+Definido.subsector(ubicacion.getSubsector())
				+", cementerio = "+cementerio
				+", nicho = "+ubicacion.getNicho()
				+", fila = "+ubicacion.getFila()
				+", seccion = "+seccion
				+", macizo = "+ubicacion.getMacizo()
				+", unidad = "+ubicacion.getUnidad()
				+", bis = "+ubicacion.getBis()
				+", bis_macizo = "+ubicacion.getBis_macizo()
				+", sepultura = "+ubicacion.getSepultura()
				+", parcela = "+ubicacion.getParcela()
				+", mueble = "+ubicacion.getMueble()
				+", inhumacion = "+ubicacion.getInhumacion()
				+", circ = "+ubicacion.getCirc()
				+", vencimiento = "+vencimiento;
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

				Integer macizo = resultados.getInt("macizo");
				macizo = (resultados.wasNull())? null: macizo;
				
				Integer unidad = resultados.getInt("unidad");
				unidad = (resultados.wasNull())? null: unidad;
				
				Integer sepultura = resultados.getInt("sepultura");
				sepultura = (resultados.wasNull())? null: sepultura;
				
				Integer parcela = resultados.getInt("parcela");
				parcela = (resultados.wasNull())? null: parcela;
				
				Integer mueble = resultados.getInt("mueble");
				mueble = (resultados.wasNull())? null: mueble;
				
				Integer inhumacion = resultados.getInt("inhumacion");
				inhumacion = (resultados.wasNull())? null: inhumacion;
				
				Integer circ = resultados.getInt("circ");
				circ = (resultados.wasNull())? null: circ;
				
				ret.add(new Ubicacion(
						resultados.getInt("ID"),
						Definido.subsector(resultados.getInt("subsector")),
						resultados.getString("cementerio"),
						nicho,
						fila,
						resultados.getString("seccion"),
						macizo,
						unidad,
						resultados.getBoolean("bis"),
						resultados.getBoolean("bis_macizo"),
						sepultura,
						parcela,
						mueble,
						inhumacion,
						circ,
						resultados.getDate("vencimiento")
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
	public List<Ubicacion> selectBySubsectorEntreFechas(SubSector subSector, Date desde, Date hasta) {
		String desdeStr = "'"+desde+"'";
		String hastaStr = "'"+hasta+"'";
		
		String condicion = "subsector = "+Definido.subsector(subSector)
			+ " and vencimiento between "+desdeStr+" and "+hastaStr;
		
		return selectByCondicion(condicion);
	}
	
}