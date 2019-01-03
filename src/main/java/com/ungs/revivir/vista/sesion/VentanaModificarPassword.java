package com.ungs.revivir.vista.sesion;

import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class VentanaModificarPassword extends JFrame {
	private JPanel contentPane;
	private JPasswordField inPassword;
	private JPasswordField inPasswordRep;
	private JButton btnCancelar;
	private JButton btnAceptar;
	private JButton btnReglaPassword;
	private JPasswordField inPasswordActual;

	public VentanaModificarPassword() {
		setBounds(100, 100, 458, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setTitle("Cambio de Password");
		setResizable(false);
		ImageIcon img = new ImageIcon("imagenes/icono.png");
		setIconImage(img.getImage());
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				btnCancelar.doClick();
			}
		});
		
		JLabel lblContrasea = new JLabel("CONTRASE\u00D1A NUEVA:");
		lblContrasea.setFont(new Font("Arial", Font.PLAIN, 12));
		lblContrasea.setBounds(10, 71, 146, 14);
		contentPane.add(lblContrasea);
		
		JLabel lblRepetirContrasea = new JLabel("REPETIR CONTRASE\u00D1A:");
		lblRepetirContrasea.setFont(new Font("Arial", Font.PLAIN, 12));
		lblRepetirContrasea.setBounds(10, 102, 146, 14);
		contentPane.add(lblRepetirContrasea);
		
		inPassword = new JPasswordField();
		inPassword.setFont(new Font("Arial", Font.PLAIN, 12));
		inPassword.setBounds(166, 68, 224, 20);
		contentPane.add(inPassword);
		
		inPasswordRep = new JPasswordField();
		inPasswordRep.setFont(new Font("Arial", Font.PLAIN, 12));
		inPasswordRep.setBounds(166, 99, 224, 20);
		contentPane.add(inPasswordRep);
		
		btnReglaPassword = new JButton("?");
		btnReglaPassword.setFont(new Font("Arial", Font.PLAIN, 12));
		btnReglaPassword.setBounds(403, 67, 39, 23);
		contentPane.add(btnReglaPassword);
		
		btnAceptar = new JButton("ACEPTAR");
		btnAceptar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnAceptar.setBounds(10, 141, 101, 23);
		contentPane.add(btnAceptar);
		
		btnCancelar = new JButton("CANCELAR");
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnCancelar.setBounds(329, 141, 101, 23);
		contentPane.add(btnCancelar);
		
		JLabel lblContraseaActual = new JLabel("CONTRASE\u00D1A ACTUAL:");
		lblContraseaActual.setFont(new Font("Arial", Font.PLAIN, 12));
		lblContraseaActual.setBounds(10, 36, 146, 14);
		contentPane.add(lblContraseaActual);
		
		inPasswordActual = new JPasswordField();
		inPasswordActual.setFont(new Font("Arial", Font.PLAIN, 12));
		inPasswordActual.setBounds(166, 34, 224, 20);
		contentPane.add(inPasswordActual);
	}

	public JPasswordField getPassword() {
		return inPassword;
	}

	public JPasswordField getPasswordRep() {
		return inPasswordRep;
	}
	
	public JPasswordField getPasswordActual(){
		return inPasswordActual;
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}

	public JButton getBtnAceptar() {
		return btnAceptar;
	}

	public JButton getBtnReglaPassword() {
		return btnReglaPassword;
	}
	
}