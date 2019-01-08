package com.ungs.revivir.persistencia.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ungs.revivir.persistencia.OBD;
import com.ungs.revivir.persistencia.entidades.Cliente;
import com.ungs.revivir.persistencia.interfaces.ClienteOBD;

public class ClienteOBDMySQL extends OBD implements ClienteOBD{
	private final String campos = "nombre, apellido, DNI, domicilio, telefono, email";
	private final String tabla = "rev_clientes";
	
	@Override
	public void insert(Cliente cliente) {
		String nombre = (cliente.getNombre() == null) ? null : "'"+cliente.getNombre()+"'"; 
		String apellido = (cliente.getApellido() == null) ? null : "'"+cliente.getApellido()+"'"; 
		String DNI = (cliente.getDNI() == null) ? null : "'"+cliente.getDNI()+"'"; 
		String domicilio = (cliente.getDomicilio() == null) ? null : "'"+cliente.getDomicilio()+"'"; 
		String telefono = (cliente.getTelefono() == null) ? null : "'"+cliente.getTelefono()+"'"; 
		String email = (cliente.getEmail() == null) ? null : "'"+cliente.getEmail()+"'"; 

		String valores = nombre 
				+", " + apellido
				+", " + DNI
				+", " + domicilio
				+", " + telefono
				+", " + email;
		String sql = "insert into "+tabla+"("+campos+") values("+valores+");";
		ejecutarSQL(sql);		
	}

	@Override
	public void update(Cliente cliente) {
		String nombre = (cliente.getNombre() == null) ? null : "'"+cliente.getNombre()+"'"; 
		String apellido = (cliente.getApellido() == null) ? null : "'"+cliente.getApellido()+"'"; 
		String DNI = (cliente.getDNI() == null) ? null : "'"+cliente.getDNI()+"'"; 
		String domicilio = (cliente.getDomicilio() == null) ? null : "'"+cliente.getDomicilio()+"'"; 
		String telefono = (cliente.getTelefono() == null) ? null : "'"+cliente.getTelefono()+"'"; 
		String email = (cliente.getEmail() == null) ? null : "'"+cliente.getEmail()+"'"; 

		String condicion = "ID = "+cliente.getID();
		String valores = "nombre = "+ nombre
				+", apellido = "+ apellido 
				+", DNI = "+DNI
				+", domicilio = "+domicilio
				+", telefono = "+telefono
				+", email = "+email;
		String consulta = "update "+tabla+" set "+valores+"  where ("+condicion+");";
		ejecutarSQL(consulta);
	}

	@Override
	public void delete(Cliente cliente) {
		String condicion = "ID = "+cliente.getID();
		String consulta = "delete from "+tabla+" where ("+condicion+");";
		ejecutarSQL(consulta);
	}

	@Override
	public Cliente selectByID(Integer ID) {
		String condicion = "ID = "+ID;
		return selectUnicoByCondicion(condicion);
	}

	@Override
	public Cliente ultimoInsertado() {
		Integer ID = selectLastID(tabla);
		if (ID == null)
			return null;
		else
			return selectByID(ID);
	}
	
	@Override
	public List<Cliente> select() {
		return selectByCondicion("true");
	}

	// ************************ METODOS ESPECIFICOS ********************************

	@Override
	public Cliente selectByDNI(String DNI) {
		String condicion = "DNI = '" + DNI +"'";
		return selectUnicoByCondicion(condicion);
	}

	@Override
	public List<Cliente> selectByNombreApellidoDNI(String nombre, String apellido, String DNI) {
		String condicion = "";
		if (nombre != null)
			condicion += "upper(nombre) like '"+nombre.toUpperCase()+"%'";
		
		if (apellido != null) {
			if (!condicion.equals(""))
				condicion += " and "; 
			condicion += "upper(apellido) like '"+apellido.toUpperCase()+"%'";
		}
		
		if (DNI != null) {
			if (!condicion.equals(""))
				condicion += " and "; 
			condicion += "upper(DNI) like '"+DNI.toUpperCase()+"%'";
		}
		
		return selectByCondicion(condicion);
	}

	// ************************ METODOS PRIVADOS  ********************************
	
	private Cliente selectUnicoByCondicion(String condicion) {
		List<Cliente> lista = selectByCondicion(condicion);
		if (lista.size() > 0)
			return lista.get(0);
		return null;
	}

	private List<Cliente> selectByCondicion(String condicion) {
		List<Cliente> ret = new ArrayList<Cliente>();
		String comandoSQL = "select ID, "+campos+" from "+tabla+" where ("+condicion+") limit "+limite+";";
		
		try { 
			Class.forName(driver); 
			Connection conexion = DriverManager.getConnection(cadenaConexion, usuarioBD, passwordBD); 
			Statement sentencia = conexion.createStatement ();
			ResultSet resultados = sentencia.executeQuery(comandoSQL);			

			while (resultados.next()) {
				ret.add(new Cliente(
						resultados.getInt("ID"),
						resultados.getString("nombre"),
						resultados.getString("apellido"),
						resultados.getString("DNI"),
						resultados.getString("domicilio"),
						resultados.getString("telefono"),
						resultados.getString("email")
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