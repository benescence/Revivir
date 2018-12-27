package com.ungs.revivir.vista.menu.fallecidos.fallecidoAM;

import java.sql.Date;

import com.ungs.revivir.negocios.manager.FallecidoManager;
import com.ungs.revivir.negocios.manager.UbicacionManager;
import com.ungs.revivir.persistencia.definidos.SubSector;
import com.ungs.revivir.persistencia.definidos.TipoFallecimiento;
import com.ungs.revivir.persistencia.entidades.Fallecido;
import com.ungs.revivir.persistencia.entidades.Ubicacion;
import com.ungs.revivir.vista.principal.ControladorExterno;
import com.ungs.revivir.vista.util.AccionCerrarVentana;
import com.ungs.revivir.vista.util.Popup;

public class ControladorFallecidoAM implements ControladorExterno{
	private VentanaFallecidoAM ventana;
	private FallecidoInvocable invocador;
	private Fallecido fallecido;
	
	public ControladorFallecidoAM(FallecidoInvocable invocador, Fallecido fallecido) {
		this.invocador = invocador;
		this.fallecido = fallecido;
		ventana = new VentanaFallecidoAM(fallecido);
		inicializar();
	}
	
	public ControladorFallecidoAM(FallecidoInvocable invocador) {
		this.invocador = invocador;
		ventana = new VentanaFallecidoAM();
		inicializar();
	}
	
	private void inicializar() {
		ventana.botonAceptar().setAccion(e -> aceptar());
		ventana.botonCancelar().setAccion(e -> cancelar());
		ventana.addWindowListener(new AccionCerrarVentana(e -> cancelar()));
	} 
	
	private void aceptar() {
		ventana.requestFocusInWindow();
		
		try {
			String nombre= ventana.getInNombreFallecido().getText();;
			String apellido= ventana.getInApellidoFallecido().getText();;
			String DNI = (!ventana.getInDNIFallecido().getText().equals("") ? ventana.getInDNIFallecido().getText() : null);
			String cocheria= (!ventana.getInCocheria().getText().equals("") ? ventana.getInCocheria().getText() : null);
			TipoFallecimiento tipo = (TipoFallecimiento) ventana.getInTipoFallecimiento().getSelectedItem();
			Date fechaFallecimiento = new Date(ventana.getInFechaFallecimiento().getDate().getTime());
			Date fechaIngreso = new Date(ventana.getInFechaIngreso().getDate().getTime());
			

			// Es un alta
			if (fallecido == null) {
				guardarUbicacion();
				Ubicacion ubicacion = UbicacionManager.traerMasReciente();
				Fallecido nuevo = new Fallecido(-1, ubicacion.getID(), tipo, DNI, apellido, nombre, cocheria, fechaFallecimiento, fechaIngreso);
				FallecidoManager.guardar(nuevo);			
			}
			
			// Es una modificacion
			else {
				Fallecido nuevo = new Fallecido(fallecido.getID(), fallecido.getUbicacion(), tipo, DNI, apellido, nombre, cocheria, fechaFallecimiento, fechaIngreso);
				FallecidoManager.modificar(nuevo);
			}
			
			ventana.dispose();
			invocador.actualizarFallecidos();
			invocador.mostrar();
		
		} catch (Exception e) {
			e.printStackTrace();
			Popup.mostrar(e.getMessage());
		}
		
	}
	
	private void guardarUbicacion() {
		SubSector subsector = (SubSector) ventana.getInSubSector().getSelectedItem();
		String otroCementerio = null;
		Integer nicho = new Integer((ventana.getInNicho().isEnabled() ? ventana.getInNicho().getText() : null));
		Integer fila = new Integer((ventana.getInFila().isEnabled() ? ventana.getInFila().getText() : null));
		String seccion = (ventana.getInSeccion().isEnabled() ? ventana.getInFila().getText() : null);
		Integer macizo = new Integer((ventana.getInMacizo().isEnabled() ? ventana.getInMacizo().getText() : null));
		Integer unidad = new Integer((ventana.getInUnidad().isEnabled() ? ventana.getInUnidad().getText() : null));
		
		Boolean bis = null;
		if (ventana.getInCheckBis().isEnabled()) 
			bis = ventana.getInCheckBis().isSelected();
		
		Boolean bis_macizo = null;
		if (ventana.getInCheckMacizo().isEnabled())
			bis_macizo = ventana.getInCheckMacizo().isSelected();

		Integer sepultura = new Integer((ventana.getInSepultura().isEnabled() ? ventana.getInSepultura().getText() : null));
		Integer parcela = new Integer((ventana.getInParcela().isEnabled() ? ventana.getInParcela().getText() : null));
		Integer mueble = new Integer((ventana.getInMueble().isEnabled() ? ventana.getInMueble().getText() : null));
		Integer inhumacion = new Integer((ventana.getInInhumacion().isEnabled() ? ventana.getInInhumacion().getText() : null));
		Integer circ = new Integer((ventana.getInCirc().isEnabled() ? ventana.getInCirc().getText() : null));


		Ubicacion ubicacion = new Ubicacion(-1, subsector, otroCementerio, nicho, fila, seccion,
				macizo, unidad, bis, bis_macizo, sepultura, parcela, mueble, inhumacion, circ);
		
		UbicacionManager.guardar(ubicacion);
	}	
	
	private void cancelar() {
		if (Popup.confirmar("Se perderan los datos ingresados.\n�Esta seguro de que desea cancelar la operacion?")) {
			ventana.dispose();
			invocador.mostrar();
		}
	}
	
}