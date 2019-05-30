package com.ungs.revivir.vista.paneles;

import java.awt.Component;
import java.awt.Dimension;
import java.util.List;

import com.ungs.revivir.negocios.manager.ServicioManager;
import com.ungs.revivir.persistencia.entidades.Servicio;
import com.ungs.revivir.vista.seleccion.servicio.ControladorSeleccionarServicio;
import com.ungs.revivir.vista.seleccion.servicio.ServicioSeleccionable;
import com.ungs.revivir.vista.util.Boton;
import com.ungs.revivir.vista.util.Popup;
import com.ungs.revivir.vista.util.TextoCentrado;
import com.ungs.revivir.vista.util.contenedores.PanelVertical;
import com.ungs.revivir.vista.util.contenedores.Ventana;
import com.ungs.revivir.vista.util.entradas.EntradaTexto;

public class PanelServicios extends PanelVertical implements ServicioSeleccionable {
	private static final long serialVersionUID = 1L;
	private EntradaTexto inNombre, inCodigo;
	private Ventana ventana;
	private Component siguienteComponente;
	private ServicioAutocompletable observador;
	
	public PanelServicios(Ventana ventana, Dimension dimTexto, Dimension dimEntrada, Dimension dimBoton, Component siguienteComponente, ServicioAutocompletable observador) {
		this.ventana = ventana;
		this.siguienteComponente = siguienteComponente;
		this.observador = observador;
		inNombre = new EntradaTexto("Nombre servicio", dimTexto, dimEntrada);
		inCodigo = new EntradaTexto("Codigo servicio", dimTexto, dimEntrada);
		Boton btnCargar = new Boton("Cargar", dimBoton);
		btnCargar.setAccion(e-> {
			this.requestFocus();
			autocompletar();
		});
		
		add(new TextoCentrado("Datos del servicio"));
		add(inNombre);
		add(inCodigo);
		add(btnCargar);
	}

	private void autocompletar() {
		// - Busca servicios con los datos ingresados
		// - Si encuentra un unico servicio lo coloca en el formulario
		// - Si no encuentra ninguno lanza mensaje pidiendo llenar los campos correspondientes
		// - Si encuentra demasiados abre ventana para seleccionar de una lista
		
		String nombre = inNombre.getValor();
		String codigo = inCodigo.getValor();
		List<Servicio> servicios = null;
		
		try {
			servicios = ServicioManager.traer(nombre, codigo);
			
			if (servicios.size() == 0) {
				Popup.mostrar("No existe ningun servicio con los parametros ingresados.");
				
			} else if (servicios.size() > 1) {
				ventana.deshabilitar();
				ControladorSeleccionarServicio controlador = new ControladorSeleccionarServicio(this);				
				controlador.setParametros(nombre, codigo);
				if (siguienteComponente != null)
					siguienteComponente.requestFocusInWindow();
				
			} else {
				Servicio unico = servicios.get(0);
				inNombre.setValor(unico.getNombre());
				inCodigo.setValor(unico.getCodigo());
				if (siguienteComponente != null)
					siguienteComponente.requestFocusInWindow();
				if (observador != null)
					observador.servicioAutocompletado(unico);
			}
		
		} catch (Exception e) {
			Popup.mostrar(e.getMessage());
		}
		
	}

	public EntradaTexto getNombre() {
		return inNombre;
	}	

	public EntradaTexto getCodigo() {
		return inCodigo;
	}

	@Override
	public void seleccionarServicio(Servicio servicio) {
		inNombre.setValor(servicio.getNombre());
		inCodigo.setValor(servicio.getCodigo());
	}

	@Override
	public void mostrar() {
		ventana.mostrar();
	}
	
}