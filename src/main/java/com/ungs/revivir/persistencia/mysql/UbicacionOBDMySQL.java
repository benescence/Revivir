package com.ungs.revivir.persistencia.mysql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
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
		String consulta1 = "SET FOREIGN_KEY_CHECKS=0;";
		String consulta = " delete from "+tabla+" where ("+condicion+");";
		String consulta3 = "SET FOREIGN_KEY_CHECKS=1;";

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
	public Ubicacion  selectByUbicacion(Ubicacion ubicacion) {
			
		String condicion =" subsector = "+Definido.subsector(ubicacion.getSubsector())
				+" and nicho "+((ubicacion.getNicho() != null) ? (" = " +ubicacion.getNicho()) : " is null")
				+" and fila "+((ubicacion.getFila() != null) ? (" = " +ubicacion.getFila()) : " is null")
				+" and seccion" + ((ubicacion.getSeccion() != null) ? (" = "+ubicacion.getSeccion()) :  " is null")
				+" and macizo" + ((ubicacion.getMacizo() != null) ? (" = "+ubicacion.getMacizo()) :  " is null")
				+" and unidad" + ((ubicacion.getUnidad() != null) ? (" = "+ubicacion.getUnidad()) :  " is null")
				+" and bis" + ((ubicacion.getBis() != null) ? (" = "+ubicacion.getBis()) :  " is null")
				+" and bis_macizo" + ((ubicacion.getBis_macizo() != null) ? (" = "+ubicacion.getBis_macizo()) :  " is null")
				+" and sepultura" + ((ubicacion.getSepultura() != null) ? (" = "+ubicacion.getSepultura()) :  " is null")
				+" and parcela" + ((ubicacion.getParcela() != null) ? (" = "+ubicacion.getParcela()) :  " is null")
				+" and inhumacion" + ((ubicacion.getInhumacion() != null) ? (" = "+ubicacion.getInhumacion()) :  " is null")
				+" and circ" + ((ubicacion.getCirc() != null) ? (" = "+ubicacion.getCirc()) :  " is null");
				System.out.println(condicion);
		List<Ubicacion> lista = selectByCondicion(condicion);
		if (lista.size() > 0)
			return lista.get(0);
		return null;
	}
	
	@Override
	public List<Ubicacion> selectByrangos(Integer nichoMax,
									Integer nichoMin,
									Integer circMax,
									Integer circMin,
									Integer filaMax,
									Integer filaMin,
									Integer parcelaMax,
									Integer parcelaMin,
									Integer unidadMax,
									Integer unidadMin,
									Integer muebleMax,
									Integer muebleMin,
									Integer sepulturaMax,
									Integer sepulturaMin,
									Integer inhumacionMax,
									Integer inhumacionMin,
									Integer macizoMax,
									Integer macizoMin,
									String seccion,
									SubSector subsector) {
			
		String condicionSubsector =" subsector = "+Definido.subsector(subsector);
		String condicionSeccion = (seccion != null) ? (" and "+  "seccion = '" + seccion+"'") : "";

		String condicionNicho = (nichoMin!= null && nichoMax != null) ? (" and nicho > "+nichoMin +" and nicho < " + nichoMax) : "";
		String condicionFila = (filaMin!= null && filaMax != null) ? (" and fila > "+filaMin +" and fila < " + filaMax) : "";
		String condicionCirc = (circMin!= null && circMax != null) ? (" and circ > "+circMin +" and circ < " + circMax) : "";
		String condicionUnidad = (unidadMin!= null && unidadMax != null) ? (" and unidad > "+unidadMin +" and unidad < " + unidadMax) : "";
		String condicionParcela = (parcelaMin!= null && parcelaMax != null) ? (" and parcela > "+parcelaMin +" and  parcela < " + parcelaMax) : "";
		String condicionMueble = (muebleMin!= null && muebleMax != null) ? (" and mueble > "+muebleMin +" and mueble < " + muebleMax) : "";
		String condicionMacizo = (macizoMin!= null && macizoMax != null) ? (" and macizo > "+macizoMin +" and macizo < " + macizoMax) : "";
		String condicioninhumacion = (inhumacionMin!= null && inhumacionMax != null) ? (" and inhumacion > "+inhumacionMin +" and inhumacion < " + inhumacionMax) : "";
		String condicionSepultura = (sepulturaMin!= null && sepulturaMax != null) ? (" and sepultura > "+sepulturaMin +" and sepultura < " + sepulturaMax) : "";
		
		String condicion =  condicionSubsector 
							+ condicionSeccion
							+ condicionNicho
							+ condicionFila
							+ condicionCirc
							+ condicionUnidad
							+ condicionParcela
							+ condicionMueble
							+ condicionMacizo
							+ condicioninhumacion
							+ condicionSepultura ;
		
	
			System.out.println(condicion);
			return selectByCondicion(condicion);
		
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
		String comandoSQL = "select ID, "+campos+" from "+tabla+" where ("+condicion+") limit "+limite+";";
		
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

				Boolean bis = resultados.getBoolean("bis");
				bis = (resultados.wasNull())? null: bis;

				Boolean bisMacizo = resultados.getBoolean("bis_macizo");
				bisMacizo = (resultados.wasNull())? null: bisMacizo;
				
				ret.add(new Ubicacion(
						resultados.getInt("ID"),
						Definido.subsector(resultados.getInt("subsector")),
						resultados.getString("cementerio"),
						nicho,
						fila,
						resultados.getString("seccion"),
						macizo,
						unidad,
						bis,
						bisMacizo,
						sepultura,
						parcela,
						mueble,
						inhumacion,
						circ,
						Date.valueOf(resultados.getObject("vencimiento", LocalDate.class))
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
		String condicion = "subsector = "+Definido.subsector(subSector)
			+ " and vencimiento between '"+desde+"' and '"+hasta+"'";
		return selectByCondicion(condicion);
	}
	
}