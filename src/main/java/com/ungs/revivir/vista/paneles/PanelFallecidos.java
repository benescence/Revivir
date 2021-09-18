package com.ungs.revivir.vista.paneles;

import java.awt.Component;
import java.awt.Dimension;
import java.util.List;

import com.ungs.revivir.negocios.manager.FallecidoManager;
import com.ungs.revivir.persistencia.entidades.Fallecido;
import com.ungs.revivir.vista.seleccion.fallecidos.ControladorSeleccionarFallecido;
import com.ungs.revivir.vista.seleccion.fallecidos.FallecidoSeleccionable;
import com.ungs.revivir.vista.util.Boton;
import com.ungs.revivir.vista.util.Popup;
import com.ungs.revivir.vista.util.TextoCentrado;
import com.ungs.revivir.vista.util.contenedores.PanelVertical;
import com.ungs.revivir.vista.util.contenedores.Ventana;
import com.ungs.revivir.vista.util.entradas.EntradaNumero;
import com.ungs.revivir.vista.util.entradas.EntradaTexto;

public class PanelFallecidos extends PanelVertical implements FallecidoSeleccionable {
	private static final long serialVersionUID = 1L;
	private EntradaTexto inNombre, inApellido;
	private EntradaNumero inCodigo;
	private Ventana ventana;
	private Component siguienteComponente;
	
	public PanelFallecidos(Ventana ventana, Dimension dimTexto, Dimension dimEntrada,
			Dimension dimBoton, Component siguienteComponente) {
		this.ventana = ventana;
		System.out.println(ventana);
		this.siguienteComponente = siguienteComponente;
		
		inNombre = new EntradaTexto("Nombres fallecido", dimTexto, dimEntrada);
		inApellido = new EntradaTexto("Apellidos fallecido", dimTexto, dimEntrada);
		inCodigo = new EntradaNumero("Cod. Fallecido", dimTexto, dimEntrada);
		Boton btnCargar = new Boton("Cargar", dimBoton);
		btnCargar.setAccion(e-> {
			this.requestFocus();
			autocompletar();
		});
		
		add(new TextoCentrado("Datos del fallecido"));
		add(inNombre);
		add(inApellido);
		add(inCodigo);
		add(btnCargar);
	}

	private void autocompletar() {
		// - Busca fallecidos con los datos ingresados
		// - Si encuentra un unico fallecido lo coloca en el formulario
		// - Si no encuentra ninguno lanza mensaje pidiendo llenar los campos correspondientes
		// - Si encuentra demasiados abre ventana para seleccionar de una lista
		
		String nombre = inNombre.getValor();
		String apellido = inApellido.getValor();
		Integer codFallecido = inCodigo.getValor();
		List<Fallecido> fallecidos = null;
		
		try {
			fallecidos = FallecidoManager.traer(nombre, apellido, codFallecido);
			
			if (fallecidos.size() == 0) {
				Popup.mostrar("No existe ningun fallecido con los parametros ingresados.");
				
			} else if (fallecidos.size() > 1) {
				ventana.deshabilitar();
				ControladorSeleccionarFallecido controlador = new ControladorSeleccionarFallecido(this);
				controlador.setParametros(nombre, apellido, codFallecido);
				if (siguienteComponente != null)
					siguienteComponente.requestFocus();
				
			} else {
				Fallecido unico = fallecidos.get(0);
				inNombre.setValor(unico.getNombre());
				inApellido.setValor(unico.getApellido());
				inCodigo.setValor(unico.getCod_fallecido().toString());
				if (siguienteComponente != null)
					siguienteComponente.requestFocus();
			}
		
		} catch (Exception e) {
			Popup.mostrar(e.getMessage());
			e.printStackTrace();
		}
		
	}

	public EntradaTexto getNombre() {
		return inNombre;
	}	

	public EntradaTexto getApellido() {
		return inApellido;
	}

	public EntradaNumero getCodigo() {
		return inCodigo;
	}

	@Override
	public void seleccionarFallecido(Fallecido fallecido) {
		inNombre.setValor(fallecido.getNombre());
		inApellido.setValor(fallecido.getApellido());
		inCodigo.setValor(fallecido.getCod_fallecido().toString());
	}

	@Override
	public void mostrar() {
		ventana.mostrar();
	}
	
}