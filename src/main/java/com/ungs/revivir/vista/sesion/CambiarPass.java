package com.ungs.revivir.vista.sesion;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JPasswordField;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class CambiarPass extends JFrame {

	private JPanel contentPane;
	private JPasswordField txtContraseña;
	private JPasswordField txtRepetirContraseña;
	private JButton btnCancelar;
	private JButton btnAceptar;
	private JButton btnReglaContraseña;
	private JPasswordField txtContraseñaActual;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CambiarPass frame = new CambiarPass();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CambiarPass() {
		setBounds(100, 100, 458, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setTitle("Cambio de contraseña");
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
		
		txtContraseña = new JPasswordField();
		txtContraseña.setFont(new Font("Arial", Font.PLAIN, 12));
		txtContraseña.setBounds(166, 68, 224, 20);
		contentPane.add(txtContraseña);
		
		txtRepetirContraseña = new JPasswordField();
		txtRepetirContraseña.setFont(new Font("Arial", Font.PLAIN, 12));
		txtRepetirContraseña.setBounds(166, 99, 224, 20);
		contentPane.add(txtRepetirContraseña);
		
		btnReglaContraseña = new JButton("?");
		btnReglaContraseña.setFont(new Font("Arial", Font.PLAIN, 12));
		btnReglaContraseña.setBounds(403, 67, 39, 23);
		contentPane.add(btnReglaContraseña);
		
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
		
		txtContraseñaActual = new JPasswordField();
		txtContraseñaActual.setFont(new Font("Arial", Font.PLAIN, 12));
		txtContraseñaActual.setBounds(166, 34, 224, 20);
		contentPane.add(txtContraseñaActual);
	}

	public JPasswordField getTxtContraseña() {
		return txtContraseña;
	}

	public JPasswordField getTxtRepetirContraseña() {
		return txtRepetirContraseña;
	}
	
	public JPasswordField getPassword(){
		return txtContraseñaActual;
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}

	public JButton getBtnAceptar() {
		return btnAceptar;
	}

	public JButton getBtnReglaContraseña() {
		return btnReglaContraseña;
	}
}
