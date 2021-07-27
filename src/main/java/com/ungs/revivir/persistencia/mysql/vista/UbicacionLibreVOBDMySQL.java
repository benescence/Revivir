package com.ungs.revivir.persistencia.mysql.vista;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.ungs.revivir.persistencia.Definido;
import com.ungs.revivir.persistencia.OBD;
import com.ungs.revivir.persistencia.definidos.SubSector;
import com.ungs.revivir.persistencia.entidades.Ubicacion;
import com.ungs.revivir.persistencia.interfaces.vista.UbicacionLibreVOBD;

public class UbicacionLibreVOBDMySQL extends OBD implements UbicacionLibreVOBD {
	private final String campos = "subsector, nicho, fila,"
			+ "seccion, macizo, unidad, bis, bis_macizo, sepultura, parcela, mueble, inhumacion, circ";
	private final String tabla = "rev_v_ubicaciones_libres";
	
	@Override
	public List<Ubicacion> select() {
		return selectByCondicion("true", limite);
	}
	
	private List<Ubicacion> selectByCondicion(String condicion, int limite) {
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
				
				ret.add(
					new Ubicacion(
						resultados.getInt("ID"),
						Definido.subsector(resultados.getInt("subsector")),
						"",
						nicho,
						fila,
						resultados.getString("seccion"),
						macizo,
						unidad,
						(bis==null) ? false : bis,
						(bisMacizo==null) ? false : bisMacizo,
						sepultura,
						parcela,
						mueble,
						inhumacion,
						circ,
						null
					)
				);
				
			}

			resultados.close();
			sentencia.close();
			conexion.close();
			
		} catch(Exception e) {
			System.out.println(comandoSQL);
			e.printStackTrace();
		}
			
		return ret;
	}
	
	@Override
	public List<Ubicacion> selectByrangos(
									Integer nichoMax, Integer nichoMin,
									Integer circMax, Integer circMin,
									Integer filaMax, Integer filaMin,
									Integer parcelaMax, Integer parcelaMin,
									Integer unidadMax, Integer unidadMin,
									Integer muebleMax, Integer muebleMin,
									Integer sepulturaMax, Integer sepulturaMin,
									Integer inhumacionMax, Integer inhumacionMin,
									Integer macizoMax, Integer macizoMin,
									String seccion,
									SubSector subsector, Boolean macizo_BIS, Boolean bis) {
			
		String condicionSubsector =" subsector = " + Definido.subsector(subsector);
		String condicionSeccion = (seccion != null) ? (" and "+  "seccion = '" + seccion + "'") : "";
		String condicionNichoMin = (nichoMin!= null ) ? (" and nicho >= "+nichoMin ): "";
		String condicionNichoMax = ( nichoMax != null) ? (" and nicho <= " + nichoMax) : "";
		String condicionFilaMax = (filaMax != null) ? ("  and fila <= " + filaMax) : "";
		String condicionFilaMin = (filaMin!= null ) ? (" and fila >= "+filaMin) : "";
		String condicionCircMax = (circMax != null) ? (" and circ <= " + circMax) : "";
		String condicionCircMin = (circMin!= null) ? (" and circ >= "+circMin ) : "";
		String condicionUnidadMin = (unidadMin!= null ) ? (" and unidad >= "+unidadMin ) : "";
		String condicionUnidadMax= ( unidadMax != null) ? ("  and unidad <= " + unidadMax) : "";		
		String condicionParcelaMax = ( parcelaMax != null) ? ("  and  parcela <= " + parcelaMax) : "";
		String condicionParcelaMin = (parcelaMin!= null ) ? (" and parcela >= "+parcelaMin ) : "";
		String condicionMuebleMax = (  muebleMax != null) ? ("  and mueble <= " + muebleMax) : "";
		String condicionMuebleMin = (muebleMin!= null ) ? (" and mueble >= "+muebleMin) : "";
		String condicionMacizoMax = ( macizoMax != null) ? ("  and macizo <= " + macizoMax) : "";
		String condicionMacizoMin = (macizoMin!= null ) ? (" and macizo >= "+macizoMin ) : "";	
		String condicioninhumacionMax = (inhumacionMax != null) ? ("  and inhumacion <= " + inhumacionMax) : "";
		String condicioninhumacionMin = (inhumacionMin!= null) ? (" and inhumacion >= "+inhumacionMin) : "";
		String condicionSepulturaMax = (sepulturaMax != null) ? ("  and sepultura <= " + sepulturaMax) : "";
		String condicionSepulturaMin = (sepulturaMin!= null) ? (" and sepultura >= "+sepulturaMin ) : "";
		String condicionCheckMacizobis = (macizo_BIS!= null && macizo_BIS) ? (" and bis_macizo = "+macizo_BIS ) : "";
		String condicionCheckbis = (bis!= null && bis) ? (" and bis = "+bis ) : "";
		String condicion =  condicionSubsector 
							+ condicionSeccion
							+ condicionNichoMax
							+ condicionNichoMin
							+ condicionFilaMin
							+ condicionFilaMax
							+ condicionCircMax
							+ condicionCircMin
							+ condicionUnidadMax
							+ condicionUnidadMin
							+ condicionParcelaMin
							+ condicionParcelaMax
							+ condicionMuebleMax
							+ condicionMuebleMin
							+ condicionMacizoMax
							+ condicionMacizoMin
							+ condicioninhumacionMax
							+ condicioninhumacionMin
							+ condicionSepulturaMax 
							+ condicionSepulturaMin
							+ condicionCheckMacizobis
							+ condicionCheckbis;
		return selectByCondicion(condicion, limite);		
	}

}