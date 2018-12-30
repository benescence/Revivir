package com.ungs.revivir.negocios.verificador;

import com.ungs.revivir.negocios.Validador;
import com.ungs.revivir.negocios.manager.ClienteManager;
import com.ungs.revivir.negocios.manager.FallecidoManager;
import com.ungs.revivir.negocios.manager.ResponsableManager;
import com.ungs.revivir.negocios.manager.ServicioManager;
import com.ungs.revivir.negocios.manager.UsuarioManager;
import com.ungs.revivir.persistencia.entidades.Cargo;
import com.ungs.revivir.persistencia.entidades.Cliente;
import com.ungs.revivir.persistencia.entidades.Fallecido;
import com.ungs.revivir.persistencia.entidades.Movimiento;
import com.ungs.revivir.persistencia.entidades.Pago;
import com.ungs.revivir.persistencia.entidades.Responsable;
import com.ungs.revivir.persistencia.entidades.Servicio;
import com.ungs.revivir.persistencia.entidades.Ubicacion;
import com.ungs.revivir.persistencia.entidades.Usuario;

public class Verificador {

	public static Responsable responsable(Responsable responsable) throws Exception {
		String observaciones = anular(responsable.getObservaciones());
		String mensaje = "";

		if (responsable.getCliente() == null)
			mensaje += "\n    -Debe seleccionar o cargar un cliente.";

		if (responsable.getFallecido() == null)
			mensaje += "\n    -Debe seleccionar o cargar un fallecido.";

		Cliente cliente = null;
		Fallecido fallecido = null;
		
		if (responsable.getCliente() != null)
			cliente = ClienteManager.traerPorID(responsable.getCliente());
		
		if (responsable.getFallecido() != null)
			fallecido = FallecidoManager.traerPorID(responsable.getFallecido());
		
		if (cliente != null && fallecido != null) {
			Responsable objetoBD =	ResponsableManager.traerPorClienteFallecido(cliente, fallecido);
			if (objetoBD != null)
				mensaje += "\n    -Cliente y fallecido ya estaban relacionados en el sistema.";
		}
		
		if (!mensaje.equals(""))
			throw new Exception("Se encontraron los siguientes errores en el formulario:"+mensaje);
		
		// Debo setearlos porque pudieron ser anulados
		responsable.setObservaciones(observaciones);
		return responsable;
	}
	
	public static Cliente cliente(Cliente nuevo, Cliente anterior) throws Exception {
		String nombre = anular(nuevo.getNombre());
		String apellido = anular(nuevo.getApellido());
		String DNI = anular(nuevo.getDNI());
		String telefono = anular(nuevo.getTelefono()); 
		String email = anular(nuevo.getEmail());
		String domicilio = anular(nuevo.getDomicilio());
		String mensaje = "";

		if (nombre == null)
			mensaje += "\n    -El NOMBRE no puede estar vac�o.";
		else if (!Validador.nombrePersona(nombre))
			mensaje += "\n    -El NOMBRE solo puede estar compuesto de letras y espacios.";

		if (apellido == null)
			mensaje += "\n    -El APELLIDO no puede estar vacio.";
		else if (!Validador.apellido(apellido))
			mensaje += "\n    -El APELLIDO solo puede estar compuesto de letras y espacios.";
		
		if (DNI == null)
			mensaje += "\n    -El DNI no puede estar vacio.";
		else if (!Validador.DNI(DNI))
			mensaje += "\n    -El DNI solo puede estar compuesto de n�meros.";
		else {
			Cliente clienteDNI = ClienteManager.traerPorDNI(DNI);
			if (clienteDNI != null && (anterior == null || anterior.getID() != clienteDNI.getID()))
				mensaje += "\n    -Ya se encuentra registrado un cliente con el DNI: "+DNI+".";
		}
		
		if (telefono != null && !Validador.telefono(telefono))
			mensaje += "\n    -El TELEFONO solo puede estar compuesto de numeros.";
		
		if (email != null && !Validador.email(email))
			mensaje += "\n    -El EMAIL debe tener un formato de E-Mail apropiado.";
		
		if (!mensaje.equals(""))
			throw new Exception("Se encontraron los siguientes errores en el formulario:"+mensaje);
		
		// Debo setearlos porque pudieron ser anulados
		if (anterior != null)
			nuevo.setID(anterior.getID());
		nuevo.setNombre(nombre);
		nuevo.setApellido(apellido);
		nuevo.setDNI(DNI);
		nuevo.setTelefono(telefono);
		nuevo.setEmail(email);
		nuevo.setDomicilio(domicilio);

		return nuevo;
	}
	
	public static Fallecido fallecido(Fallecido verificar) throws Exception {
		String nombre = anular(verificar.getNombre());
		String apellido = anular(verificar.getApellido());
		String DNI = anular(verificar.getDNI());
		String cocheria = anular(verificar.getCocheria());
		String mensaje = "";

		if (nombre == null)
			mensaje += "\n    -El NOMBRE no puede estar vacío.";
		else if (!Validador.nombrePersona(nombre))
			mensaje += "\n    -El NOMBRE solo puede estar compuesto de letras y espacios.";

		if (apellido == null)
			mensaje += "\n    -El APELLIDO no puede estar vacío.";
		else if (!Validador.apellido(apellido))
			mensaje += "\n    -El APELLIDO solo puede estar compuesto de letras y espacios.";
		
		if (DNI != null && !Validador.DNI(DNI))
			mensaje += "\n    -El DNI solo puede estar compuesto de números.";
		else {
			// Verifico que no exista ya un objeto con ese DNI, y si existe debe tener el mismo iD
			Fallecido objetoDNI = FallecidoManager.traerPorDNI(DNI);
			System.out.println(objetoDNI.getID());
			System.out.println(verificar.getID());
			
			if (objetoDNI != null && verificar.getID() != objetoDNI.getID())
				mensaje += "\n    -Ya se encuentra registrado un fallecido con el DNI: "+DNI+".";
		}
		
		if (!mensaje.equals(""))
			throw new Exception("Se encontraron los siguientes errores en el formulario:"+mensaje);
		
		// Seteo los que pudieron ser anulados
		verificar.setCocheria(cocheria);
		return verificar;
	}
	
	public static Movimiento movimiento(Movimiento movimiento) throws Exception {
		return movimiento;
	}
	
	public static Pago pago(Pago pago) throws Exception {
		return pago;
	}
	
	public static Ubicacion ubicacion(Ubicacion verificar) throws Exception {
		return VerificadorUbicacion.verificar(verificar);
	}	
	
	public static Cargo cargo(Cargo nuevo, Cargo anterior) throws Exception {
		String observaciones = anular(nuevo.getObservaciones());
		
		// Debo setearlos porque pudieron ser anulados
		if (anterior != null)
			nuevo.setID(anterior.getID());
		nuevo.setObservaciones(observaciones);
		return nuevo;
	}

	public static Servicio servicio(Servicio nuevo, Servicio anterior) throws Exception {
		String codigo = anular(nuevo.getCodigo());
		String nombre = anular(nuevo.getNombre());
		String descripcion = anular(nuevo.getDescripcion());
		String mensaje = "";

		if (codigo == null)
			mensaje += "\n    -El CODIGO no puede estar vacio.";
		else if (!Validador.codigo(codigo))
			mensaje += "\n    -El CODIGO solo puede estar compuesto de numeros.";
		else {
			Servicio objetoCodigo = ServicioManager.traerActivoPorCodigo(codigo);
			if (objetoCodigo != null && (anterior == null || anterior.getID() != objetoCodigo.getID()))
				mensaje += "\n    -Ya se encuentra registrado un servicio con el CODIGO: "+codigo+".";
		}
		
		if (nombre == null)
			mensaje += "\n    -El NOMBRE no puede estar vac�o.";
		else if (!Validador.nombreServicio(nombre))
			mensaje += "\n    -El NOMBRE solo puede estar compuesto de letras, numeros y espacios.";
		
		
		if (!mensaje.equals(""))
			throw new Exception("Se encontraron los siguientes errores en el formulario:"+mensaje);
		
		// Debo setearlos porque pudieron ser anulados
		if (anterior != null)
			nuevo.setID(anterior.getID());
		nuevo.setCodigo(codigo);
		nuevo.setNombre(nombre);
		nuevo.setDescripcion(descripcion);
		return nuevo;
	}
	
	public static Usuario usuario(Usuario nuevo, Usuario anterior) throws Exception {
		String nombre = anular(nuevo.getUsuario());
		String password = anular(nuevo.getPassword());
		String mensaje = "";
		
		if (nombre == null)
			mensaje += "\n    -El NOMBRE no puede estar vacio.";
		else if (!Validador.usuario(nombre))
			mensaje += "\n    -El NOMBRE solo puede estar compuesto de letras y numeros.";
		else {
			Usuario objetoNombre = UsuarioManager.traerPorUsuario(nombre);
			if (objetoNombre != null && (anterior == null || anterior.getID() != objetoNombre.getID()))
				mensaje += "\n    -Ya se encuentra registrado un USUARIO con el nombre: "+nombre+".";
		}
		
		if (password == null)
			mensaje += "\n    -El PASSWORD no puede estar vac�o.";
		else if (!Validador.password(password))
			mensaje += "\n    -El PASSWORD solo puede estar compuesto de letras, numeros y espacios.";
		
		
		if (!mensaje.equals(""))
			throw new Exception("Se encontraron los siguientes errores en el formulario:"+mensaje);
		
		// Debo setearlos porque pudieron ser anulados
		if (anterior != null)
			nuevo.setID(anterior.getID());
		nuevo.setUsuario(nombre);
		nuevo.setPassword(password);
		return nuevo;
	}

	public static String anular(String texto) {
		if (texto == null || texto.equals(""))
			return null;
		else
			return texto;
	}

}