package com.ungs.revivir.vista.menu.fallecidos.fallecidoAM;

import java.sql.Date;

import com.ungs.revivir.negocios.Validador;
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
		ventana.getCod_Fallecido().setEnabled(false);
		ventana.botonAceptar().setAccion(e -> aceptar());
		ventana.botonCancelar().setAccion(e -> cancelar());
		ventana.addWindowListener(new AccionCerrarVentana(e -> cancelar()));
		Fallecido fallecido = FallecidoManager.traerMasReciente();
		ventana.getCod_Fallecido().setText(Integer.toString(fallecido.getCod_fallecido()+1));
	} 
	
	private void aceptar() {
		ventana.requestFocusInWindow();
		
		try {
			Fallecido fallecido = traerFallecidoVerificado();
			if (fallecido.getCod_fallecido() == 0) {
				Popup.mostrar("El codigo solo puede consistir de numeros");
				return;
			}
			
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
			Popup.mostrar(e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	private Ubicacion traerUbicacionVerificada() throws Exception {
		SubSector subsector = (SubSector) ventana.getSubSector().getSelectedItem();
		String otroCementerio = ventana.getCementerio().getValor();
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
		//String DNI = ventana.getDNIFallecido().getText();
		String DNI = "";
		String cocheria = ventana.getCocheria().getText();
		TipoFallecimiento tipo = (TipoFallecimiento) ventana.getInTipoFallecimiento().getSelectedItem();
		Integer cod_fallecido = Validador.cod_fallecido(ventana.getCod_Fallecido().getText()) ? Integer.parseInt(ventana.getCod_Fallecido().getText()) : 0;
		Date fFallecimiento = ventana.getInFechaFallecimiento().getValor();
		Date fIngreso = ventana.getInFechaIngreso().getValor();
		
		Fallecido fallecido = new Fallecido(-1, -1, tipo,cod_fallecido, DNI, apellido, nombre, cocheria, fFallecimiento, fIngreso);
		if (modificar != null)
			fallecido.setID(modificar.getID());
		
		return Verificador.fallecido(fallecido);
	}
	
	private void cancelar() {
		if (Popup.confirmar("¿Seguro de que desea cancelar la operación?")) {
			ventana.dispose();
			invocador.mostrar();
		}
	}
	
}