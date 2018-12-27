package com.ungs.revivir.test.negocios;

import java.util.List;

import com.ungs.revivir.negocios.manager.ClienteManager;
import com.ungs.revivir.persistencia.entidades.Cliente;

public class ClienteManagerTest {

	public static void guardarTest(String nombre, String apellido, String DNI, String telefono, String email) {
		System.out.println("___ Guardar test");
		//ClienteManager.guardar(nombre, apellido, DNI, telefono, email);
	}
	
	public static void modificarTest(Cliente cliente) {
		System.out.println("___ Modificar test");
		//ClienteManager.modificar(cliente);
	}
	
	public static void eliminarTest(Cliente cliente) {
		System.out.println("___ Eliminar test");
		ClienteManager.eliminar(cliente);
	}
	
	public static void traerTodoTest() {
		System.out.println("___ Traer todo test");
		List<Cliente> lista = ClienteManager.traerTodo();
		System.out.println("Cantidad: "+lista.size());
		for (Cliente elemento : lista)
			System.out.println(elemento.getApellido()+", "+elemento.getNombre());
	}
	
	public static void main(String[] args) {
		guardarTest("Carlos", "Apellido", "12356", "5466", "sxs");
		
		
		
	}
}
