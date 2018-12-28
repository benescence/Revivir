package com.ungs.revivir.vista.menu.fallecidos.fallecidoAM;

import java.sql.Date;

import com.ungs.revivir.negocios.Verificador;
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
			String nombre= ventana.getNombreFallecido().getText();;
			String apellido= ventana.getApellidoFallecido().getText();
			String DNI = ventana.getDNIFallecido().getText();
			String cocheria = ventana.getCocheria().getText();
			TipoFallecimiento tipo = (TipoFallecimiento) ventana.getInTipoFallecimiento().getSelectedItem();
			Date fechaFallecimiento = new Date(ventana.getInFechaFallecimiento().getDate().getTime());
			Date fechaIngreso = new Date(ventana.getInFechaIngreso().getDate().getTime());
			
			Fallecido nuevo = new Fallecido(-1, -1, tipo, DNI, apellido, nombre,
					cocheria, fechaFallecimiento, fechaIngreso);
			
			// Saltan los errores en un Popup
			nuevo = Verificador.fallecido(nuevo);
			
			// Es un alta
			if (fallecido == null) {
				guardarUbicacion();
				Ubicacion ubicacion = UbicacionManager.traerMasReciente();
				nuevo.setUbicacion(ubicacion.getID());
				FallecidoManager.guardar(nuevo);			
			}
			
			// Es una modificacion
			else {
				nuevo.setID(fallecido.getID());
				FallecidoManager.modificar(nuevo);
			}
			
			ventana.dispose();
			invocador.actualizarFallecidos();
			invocador.mostrar();
		
		} catch (Exception e) {
			Popup.mostrar(e.getMessage());
		}
		
	}
	
	private void guardarUbicacion() throws Exception {
		SubSector subsector = (SubSector) ventana.getSubSector().getSelectedItem();
		String otroCementerio = null;
		Integer nicho = (ventana.getNicho().isEnabled() ? ventana.getNicho().getValor() : null);
		Integer fila = (ventana.getFila().isEnabled() ? ventana.getFila().getValor() : null);
		String seccion = (ventana.getSeccion().isEnabled() ? ventana.getSeccion().getTextField().getText() : null);
		Integer macizo = (ventana.getMacizo().isEnabled() ? ventana.getMacizo().getValor():null);
		Integer unidad = (ventana.getUnidad().isEnabled() ? ventana.getUnidad().getValor() : null);
		
		Boolean bis = null;
		if (ventana.getInCheckBis().isEnabled()) 
			bis = ventana.getInCheckBis().isSelected();
		
		Boolean bis_macizo = null;
		if (ventana.getInCheckMacizo().isEnabled())
			bis_macizo = ventana.getInCheckMacizo().isSelected();

		Integer sepultura = (ventana.getSepultura().isEnabled() ? ventana.getSepultura().getValor() : null);
		Integer parcela = (ventana.getParcela().isEnabled() ? ventana.getParcela().getValor() : null);
		Integer mueble = (ventana.getMueble().isEnabled() ? ventana.getMueble().getValor() : null);
		Integer inhumacion = (ventana.getInhumacion().isEnabled() ? ventana.getInhumacion().getValor() : null);
		Integer circ = (ventana.getCirc().isEnabled() ? ventana.getCirc().getValor(): null);

		Ubicacion ubicacion = new Ubicacion(-1, subsector, otroCementerio, nicho, fila, seccion,
				macizo, unidad, bis, bis_macizo, sepultura, parcela, mueble, inhumacion, circ);
		
		UbicacionManager.guardar(ubicacion);		
	}	
	
	private void cancelar() {
		if (Popup.confirmar("Se perderan los datos ingresados.\n¿Está seguro de que desea cancelar la operación?")) {
			ventana.dispose();
			invocador.mostrar();
		}
	}
	
}