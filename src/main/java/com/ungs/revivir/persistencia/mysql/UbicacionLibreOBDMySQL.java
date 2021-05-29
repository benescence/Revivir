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
import com.ungs.revivir.persistencia.entidades.Ubicacion;
import com.ungs.revivir.persistencia.interfaces.UbicacionLibreOBD;

public class UbicacionLibreOBDMySQL extends OBD implements UbicacionLibreOBD {
	private final String campos = "subsector, nicho, fila,"
			+ "seccion, macizo, unidad, bis, bis_macizo, sepultura, parcela, mueble, inhumacion, circ";
	private final String tabla = "rev_v_ubicaciones_libres";
	
	@Override
	public List<Ubicacion> select() {
		return selectByCondicion("true",limite);
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
				
				ret.add(new Ubicacion(
						resultados.getInt("ID"),
						Definido.subsector(resultados.getInt("subsector")),
						"",
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
						null
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
