package com.ungs.revivir.persistencia.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.ungs.revivir.persistencia.OBD;
import com.ungs.revivir.persistencia.interfaces.ConfiguracionOBD;

public class ConfiguracionOBDMySQL extends OBD implements ConfiguracionOBD {
	private final String tabla = "rev_configuracion";
	
	@Override
	public void insert(String clave, String valor) {
		String consulta = "insert into "+tabla+"(clave, valor) values('"+clave+"', '"+valor+"');";
		ejecutarSQL(consulta);
	}
	
	@Override
	public void update(String clave, String valor) {
		String consulta = "update rev_configuracion "
				+" set valor = "+valor
				+" where clave = '"+clave+"';";
		ejecutarSQL(consulta);
	}

	@Override
	public String selectByClave(String clave) {
		String ret = null;
		String comandoSQL = "select valor from "+tabla+" where clave = '"+clave+"';";
		
		try { 
			Class.forName(driver); 
			Connection conexion = DriverManager.getConnection(cadenaConexion, usuarioBD, passwordBD); 
			Statement sentencia = conexion.createStatement ();
			ResultSet resultados = sentencia.executeQuery(comandoSQL);			
	
			if (resultados.next())
				ret = resultados.getString("valor");
			
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