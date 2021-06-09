package com.ungs.revivir;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import com.ungs.revivir.vista.sesion.iniciar.ControladorIniciarSesion;

public class Main {

	public static void configurarApariencia() {
		
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	
	}
	
	public static void main(String[] args) {
		configurarApariencia();
		new ControladorIniciarSesion();
	}
	
}