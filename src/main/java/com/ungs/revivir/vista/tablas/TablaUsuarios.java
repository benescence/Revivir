package com.ungs.revivir.vista.tablas;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.ungs.revivir.persistencia.entidades.Usuario;

public class TablaUsuarios extends JTable {
	private static final long serialVersionUID = 1L;
	private String[] columnas = { "Usuario", "Password", "Permisos"};
	private DefaultTableModel modelo;
	private List<Usuario> usuarios;

	public TablaUsuarios(List<Usuario> usuarios) {
		modelo = new DefaultTableModel(null, columnas);
		setModel(modelo);
		recargar(usuarios);
	}	
	
	public void recargar(List<Usuario> usuarios) {
		this.usuarios = usuarios;
		modelo.setRowCount(0);
		modelo.setColumnCount(0);
		modelo.setColumnIdentifiers(columnas);

		for (Usuario usuario : usuarios) {
			Object[] fila = {
					usuario.getUsuario(),
					usuario.getPassword(),
					usuario.getRol()
			};
			modelo.addRow(fila);
		}
	}
	
	public List<Usuario> obtenerSeleccion() {
		List<Usuario> registros = new ArrayList<>();
		int[] indices = getSelectedRows();

		for (int indice : indices) {
			int registro = convertRowIndexToModel(indice);
			registros.add(usuarios.get(registro));
		}

		return registros;		
	}

}