package com.ungs.revivir.persistencia.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ungs.revivir.persistencia.Definido;
import com.ungs.revivir.persistencia.ExcepcionConexion;
import com.ungs.revivir.persistencia.OBD;
import com.ungs.revivir.persistencia.definidos.Rol;
import com.ungs.revivir.persistencia.entidades.Usuario;
import com.ungs.revivir.persistencia.interfaces.UsuarioOBD;

public class UsuarioOBDMySQL extends OBD implements UsuarioOBD{
	private final String campos = "usuario, password, rol"; 
	private final String tabla = "rev_usuarios";
	
	@Override
	public void insert(Usuario usuario) {
		String valores = "'"+usuario.getUsuario()+"'"
				+", '"+usuario.getPassword()+"'"
				+", "+Definido.rol(usuario.getRol());
		String sql = "insert into "+tabla+"("+campos+") values("+valores+");";
		ejecutarSQL(sql);
	}
	
	@Override
	public void update(Usuario usuario) {
		String condicion = "ID = "+usuario.getID();
		String valores = " usuario = '"+usuario.getUsuario()+"'"
				+", password = '"+usuario.getPassword()+"'"
				+", rol =  "+ Definido.rol(usuario.getRol());
		String consulta = "update "+tabla+" set "+valores+"  where ("+condicion+");";
		ejecutarSQL(consulta);
	}

	@Override
	public void delete(Usuario usuario) {
		String condicion = "ID = "+usuario.getID();
		String consulta = "delete from "+tabla+" where ("+condicion+");";
		ejecutarSQL(consulta);
	}

	@Override
	public List<Usuario> select() {
		return selectByCondicion("true");
	}

	@Override
	public Usuario selectByID(Integer ID) {
		String condicion = "ID = "+ID;
		List<Usuario> lista = selectByCondicion(condicion);
		if (lista.size() > 0)
			return lista.get(0);
		return null;
	}

	@Override
	public Usuario ultimoInsertado() {
		Integer ID = selectLastID(tabla);
		if (ID == null)
			return null;
		else
			return selectByID(ID);
	}
	
	@Override
	public List<Usuario> selectByRol(Rol rol) {
		String condicion = "rol = "+Definido.rol(rol);
		return selectByCondicion(condicion);
	}
	
	@Override
	public Usuario selectByUsuario(String usuario) {
		String condicion = "usuario = '"+usuario+"'";
		List<Usuario> ret = selectByCondicion(condicion);
		if (ret.isEmpty())
			return null;
		return ret.get(0);
	}
	
	private List<Usuario> selectByCondicion(String condicion) throws ExcepcionConexion 
	
	{
		
		List<Usuario> ret = new ArrayList<Usuario>();
		String comandoSQL = "select ID, "+campos+" from "+tabla+" where ("+condicion+") limit "+limite+";";
		
		try { 
			Class.forName(driver); 
			Connection conexion = DriverManager.getConnection(cadenaConexion, usuarioBD, passwordBD); 
			Statement sentencia = conexion.createStatement ();
			ResultSet resultados = sentencia.executeQuery(comandoSQL);			

			while (resultados.next()) {
				ret.add(new Usuario(
						resultados.getInt("ID"),
						resultados.getString("usuario"),
						resultados.getString("password"),
						Definido.rol(resultados.getInt("rol"))
						));
			}
			
			resultados.close();
			sentencia.close();
			conexion.close();
			
		}catch(Exception e) {
			System.out.println(comandoSQL);
			e.printStackTrace();
			throw new ExcepcionConexion("No se a podido conectar al servidor.");
		}
			
		return ret;
	}
	
}