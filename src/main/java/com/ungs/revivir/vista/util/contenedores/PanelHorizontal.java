package com.ungs.revivir.vista.util.contenedores;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class PanelHorizontal extends JPanel{
	private static final long serialVersionUID = 1L;
	
	public PanelHorizontal() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
	}
	
}