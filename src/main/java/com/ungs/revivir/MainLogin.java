package com.ungs.revivir;

import java.util.Properties;
import javax.swing.UIManager;
import com.jtattoo.plaf.acryl.AcrylLookAndFeel;
import com.ungs.revivir.vista.sesion.ControladorIniciarSesion;

public class MainLogin {

	public static void configurarApariencia() {
		try {
			Properties props = new Properties();
			props.put("logoString", "Revivir");
			
			AcrylLookAndFeel.setCurrentTheme(props);
			UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		configurarApariencia();
		new ControladorIniciarSesion();
	}

}