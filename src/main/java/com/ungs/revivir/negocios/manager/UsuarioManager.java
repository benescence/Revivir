package com.ungs.revivir.negocios.manager;

import java.util.List;

import com.ungs.revivir.negocios.verificador.Verificador;
import com.ungs.revivir.persistencia.FactoryOBD;
import com.ungs.revivir.persistencia.entidades.Usuario;
import com.ungs.revivir.persistencia.interfaces.UsuarioOBD;

public class UsuarioManager {
	
	public static void guardar(Usuario usuario) throws Exception {
		usuario = Verificador.usuario(usuario, null);
		UsuarioOBD obd = FactoryOBD.crearUsuarioOBD();
		obd.insert(usuario);
	}
	
	public static void modificar(Usuario nuevo, Usuario anterior) throws Exception {
		nuevo = Verificador.usuario(nuevo, anterior);
		UsuarioOBD obd = FactoryOBD.crearUsuarioOBD();
		obd.update(nuevo);
	}

	
	
	
	public static List<Usuario> traerTodo() {
		UsuarioOBD obd = FactoryOBD.crearUsuarioOBD();
		return obd.select();
	}

	public static void eliminar(Usuario usuario) {
		UsuarioOBD obd = FactoryOBD.crearUsuarioOBD();
		obd.delete(usuario);
	}

	public static Usuario traerPorUsuario(String usuario) {
		UsuarioOBD obd = FactoryOBD.crearUsuarioOBD();
		return obd.selectByUsuario(usuario);
	}

	
}