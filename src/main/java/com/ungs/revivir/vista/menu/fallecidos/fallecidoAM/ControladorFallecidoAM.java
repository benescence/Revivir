package com.ungs.revivir.vista.menu.fallecidos.fallecidoAM;

import java.sql.Date;

import com.ungs.revivir.negocios.manager.FallecidoManager;
import com.ungs.revivir.negocios.manager.UbicacionManager;
import com.ungs.revivir.negocios.verificador.Verificador;
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
	private Fallecido modificar;
	
	public ControladorFallecidoAM(FallecidoInvocable invocador, Fallecido fallecido) {
		this.invocador = invocador;
		this.modificar = fallecido;
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
			Fallecido fallecido = traerFallecidoVerificado();
			
			// Es un alta
			if (modificar == null) {
				Ubicacion ubicacion = traerUbicacionVerificada();
				UbicacionManager.guardar(ubicacion);
				ubicacion = UbicacionManager.traerMasReciente();
				fallecido.setUbicacion(ubicacion.getID());
				FallecidoManager.guardar(fallecido);			
			}
			
			// Es una modificacion
			else
				FallecidoManager.modificar(fallecido);
			
			ventana.dispose();
			invocador.actualizarFallecidos();
			invocador.mostrar();
		
		} catch (Exception e) {
			e.printStackTrace();
			Popup.mostrar(e.getMessage());
		}
		
	}
	
	private Ubicacion traerUbicacionVerificada() throws Exception {
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
		Date vencimiento = ventana.getVencimiento().getValor();

		Ubicacion ubicacion = new Ubicacion(-1, subsector, otroCementerio, nicho, fila, seccion,
				macizo, unidad, bis, bis_macizo, sepultura, parcela, mueble, inhumacion, circ, vencimiento);
		
		return Verificador.ubicacion(ubicacion);		
	}	
	
	private Fallecido traerFallecidoVerificado() throws Exception {
		String nombre = ventana.getNombreFallecido().getText();;
		String apellido = ventana.getApellidoFallecido().getText();
		String DNI = ventana.getDNIFallecido().getText();
		String cocheria = ventana.getCocheria().getText();
		TipoFallecimiento tipo = (TipoFallecimiento) ventana.getInTipoFallecimiento().getSelectedItem();
		Date fFallecimiento = new Date(ventana.getInFechaFallecimiento().getDate().getTime());
		Date fIngreso = new Date(ventana.getInFechaIngreso().getDate().getTime());
		
		Fallecido fallecido = new Fallecido(-1, -1, tipo, DNI, apellido, nombre, cocheria, fFallecimiento, fIngreso);
		if (modificar != null)
			fallecido.setID(modificar.getID());
		
		return Verificador.fallecido(fallecido);
	}
	
	private void cancelar() {
		if (Popup.confirmar("Se perderan los datos ingresados.\n�Esta seguro de que desea cancelar la operacion?")) {
			ventana.dispose();
			invocador.mostrar();
		}
	}
	
}