package com.ungs.revivir.vista.menu.clientes.clienteAM;

import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.ungs.revivir.persistencia.entidades.Cliente;
import com.ungs.revivir.vista.util.Boton;
import com.ungs.revivir.vista.util.contenedores.PanelHorizontal;
import com.ungs.revivir.vista.util.contenedores.PanelVertical;
import com.ungs.revivir.vista.util.contenedores.Ventana;
import com.ungs.revivir.vista.util.entradas.EntradaTexto;

public class VentanaClientesAM extends Ventana {
	private static final long serialVersionUID = 1L;
	private EntradaTexto inNombre, inApellido, inDNI, inTelefono, inEmail, inDomicilio;
	private Boton btnAceptar, btnCancelar;
	
	public VentanaClientesAM() {
		super("Alta de cliente", 200, 200);
		inicializar();
	}
	
	public VentanaClientesAM(Cliente cliente) {
		super("Modificacion de cliente", 200, 200);
		inicializar();
		inNombre.getTextField().setText(cliente.getNombre());
		inApellido.getTextField().setText(cliente.getApellido());
		inDNI.getTextField().setText(cliente.getDNI());
		inTelefono.getTextField().setText(cliente.getTelefono());
		inEmail.getTextField().setText(cliente.getEmail());
		inDomicilio.getTextField().setText(cliente.getDomicilio());
	}

	public void inicializar() {
		Dimension dimTexto = new Dimension(100, 25);
		Dimension dimEntrada = new Dimension(300, 25);
		Dimension dimBoton = new Dimension(100, 25);

		inNombre = new EntradaTexto("Nombres", dimTexto, dimEntrada);
		inApellido = new EntradaTexto("Apellidos", dimTexto, dimEntrada);
		inDNI = new EntradaTexto("DNI", dimTexto, dimEntrada);
		inTelefono = new EntradaTexto("Telefono", dimTexto, dimEntrada);
		inEmail = new EntradaTexto("E-Mail", dimTexto, dimEntrada);
		inDomicilio = new EntradaTexto("Domicilio", dimTexto, dimEntrada);
		
		btnAceptar = new Boton("Aceptar", dimBoton);
		btnCancelar = new Boton("Cancelar", dimBoton);
		PanelHorizontal panelBotones = new PanelHorizontal();
		panelBotones.setBorder(new EmptyBorder(10, 0, 0, 0));
		panelBotones.add(btnAceptar);
		panelBotones.add(btnCancelar);

		PanelVertical panelPrincipal = new PanelVertical();
		panelPrincipal.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(panelPrincipal);

		panelPrincipal.add(inNombre);
		panelPrincipal.add(inApellido);
		panelPrincipal.add(inDNI);
		panelPrincipal.add(inTelefono);
		panelPrincipal.add(inEmail);
		panelPrincipal.add(inDomicilio);
		panelPrincipal.add(panelBotones);
		compactar();
	}

	public JTextField getInNombre() {
		return inNombre.getTextField();
	}	

	public JTextField getInApellido() {
		return inApellido.getTextField();
	}

	public JTextField getInDNI() {
		return inDNI.getTextField();
	}	

	public JTextField getInTelefono() {
		return inTelefono.getTextField();
	}

	public JTextField getInEmail() {
		return inEmail.getTextField();
	}	

	public JTextField getDomicilio() {
		return inDomicilio.getTextField();
	}	

	public Boton botonAceptar() {
		return btnAceptar;
	}

	public Boton botonCancelar() {
		return btnCancelar;
	}
		
}