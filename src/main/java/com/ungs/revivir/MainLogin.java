package com.ungs.revivir;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.util.Properties;
import java.awt.TrayIcon.MessageType;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import com.jtattoo.plaf.acryl.AcrylLookAndFeel;
import com.ungs.revivir.vista.sesion.ControladorIniciarSesion;
import com.ungs.revivir.vista.util.Popup;

public class MainLogin {
	static TrayIcon trayIcon;
	public static void configurarApariencia() {
		try {
			Properties props = new Properties();
			props.put("logoString", "Revivir");
			
			AcrylLookAndFeel.setCurrentTheme(props);
			UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(SystemTray.isSupported()){
			SystemTray tray = SystemTray.getSystemTray();
			ImageIcon image2 = new ImageIcon("imagenes/logocsm.png");
			Image image = image2.getImage();
			trayIcon = new TrayIcon(image);
			trayIcon.setImageAutoSize(true);
			try {
				tray.add(trayIcon);
			} catch (AWTException e) {
				e.printStackTrace();
			}
			trayIcon.displayMessage("Hola", "Bienvenido al sistema Revivir CSM.", MessageType.NONE);
		} else
			Popup.mostrar("Su sistema no soporta notificaciones.");
		
	}
	
	public static void main(String[] args) {
		configurarApariencia();
		new ControladorIniciarSesion();
	}

}