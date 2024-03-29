package com.ungs.revivir.vista.menu.vencimientos;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JInternalFrame;

import com.ungs.revivir.negocios.busqueda.Relacionador;
import com.ungs.revivir.negocios.manager.FallecidoUbicacionManager;
import com.ungs.revivir.negocios.manager.ClienteNotificacionManager;
import com.ungs.revivir.persistencia.definidos.SubSector;
import com.ungs.revivir.persistencia.entidades.Cliente;
import com.ungs.revivir.persistencia.entidades.vista.VFallecidoUbicacion;
import com.ungs.revivir.persistencia.entidades.vista.VClienteNotificacion;
import com.ungs.revivir.vista.menu.vencimientos.modificar.ControladorVencimientoAM;
import com.ungs.revivir.vista.menu.vencimientos.modificar.VencimientoInvocable;
import com.ungs.revivir.vista.principal.ControladorInterno;
import com.ungs.revivir.vista.principal.ControladorPrincipal;
import com.ungs.revivir.vista.reportes.ReporteNotificaciones;
import com.ungs.revivir.vista.reportes.ReporteVencimientos;
import com.ungs.revivir.vista.util.Popup;
import com.ungs.revivir.vista.visualizador.Visualizable;
import com.ungs.revivir.vista.visualizador.clientes.ControladorVerClientes;

public class ControladorVencimientos implements ControladorInterno, Visualizable, VencimientoInvocable {
	private ControladorPrincipal invocador;
	private VentanaVencimientos ventana;
	private List<VFallecidoUbicacion> listaLocal = new ArrayList<VFallecidoUbicacion>();
	
	public ControladorVencimientos(ControladorPrincipal invocador) {
		this.invocador = invocador;
		ventana = new VentanaVencimientos();
		ventana.botonBuscar().setAccion(e -> buscar());
		ventana.botonLimpiar().setAccion(e -> limpiar());
		ventana.botonVerClientes().setAccion(e -> verClientes());
		ventana.botonModificar().setAccion(e -> modificar());
		ventana.botonImpLista().setAccion(e -> imprimirLista());
		ventana.botonImpNotificaciones().setAccion(e -> imprimirNotificaciones());
	}

	private void modificar() {
		ventana.requestFocusInWindow();
		List<VFallecidoUbicacion> seleccion = ventana.getTabla().obtenerSeleccion();
		if (seleccion.size() != 1) {
			Popup.mostrar("Debe seleccionar exactamente 1 vencimiento para poder modificarlo.");
			return;
		}
		
		invocador.getVentana().deshabilitar();
		new ControladorVencimientoAM(this, seleccion.get(0));
	}
	
	private void verClientes() {
		List<VFallecidoUbicacion> seleccion = ventana.getTabla().obtenerSeleccion();
		if (seleccion.size() != 1) {
			Popup.mostrar("Debe seleccionar exactamente 1 ubicacion para ver sus clientes");
			return;
		}
		
		invocador.getVentana().deshabilitar();
		List<Cliente> clientes = Relacionador.traerClientes(FallecidoUbicacionManager.extraerFallecido(seleccion.get(0)));
		new ControladorVerClientes(this, clientes);
	}
	
	private void buscar() {
		SubSector subSector = (SubSector) ventana.getSubsector().getComboBox().getSelectedItem();
		Date desde = ventana.getDesde().getValor();
		Date hasta = ventana.getHasta().getValor();
		List<VFallecidoUbicacion> vencimientos = FallecidoUbicacionManager.buscarVencimientos(subSector, desde, hasta);
		listaLocal = vencimientos;
		ventana.getTabla().recargar(vencimientos);
	}	

	private void imprimirLista() {	
		SubSector subSector = (SubSector) ventana.getSubsector().getComboBox().getSelectedItem();
		Date desde = ventana.getDesde().getValor();
		Date hasta = ventana.getHasta().getValor();
		List<VFallecidoUbicacion> vencimientos = FallecidoUbicacionManager.buscarVencimientosSinLimite(subSector, desde, hasta);
		new ReporteVencimientos(vencimientos);
	}

	private void imprimirNotificaciones() {	
		SubSector subSector = (SubSector) ventana.getSubsector().getComboBox().getSelectedItem();
		Date desde = ventana.getDesde().getValor();
		Date hasta = ventana.getHasta().getValor();
		List<VClienteNotificacion> vencimientos = ClienteNotificacionManager.buscarVencimientosSinLimite(subSector, desde, hasta);
		new ReporteNotificaciones(vencimientos);
	}
	
	private void limpiar() {
		ventana.getTabla().recargar(new ArrayList<>());
	}
	
	@Override
	public boolean finalizar() {
		return true;
	}

	@Override
	public JInternalFrame getVentana() {
		return ventana;
	}

	@Override
	public void mostrar() {
		invocador.getVentana().mostrar();
	}

	@Override
	public void actualizarVencimientos() {
		ventana.getTabla().recargar(listaLocal);
	}

	@Override
	public void actualizarVencimientos(VFallecidoUbicacion nuevo) {
		listaLocal.add(nuevo);
		ventana.getTabla().recargar(listaLocal);
	}

}