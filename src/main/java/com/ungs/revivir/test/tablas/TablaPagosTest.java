package com.ungs.revivir.test.tablas;

import java.sql.Date;
import java.time.LocalDate;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import com.ungs.revivir.negocios.manager.ReportePagoManager;
import com.ungs.revivir.vista.tablas.TablaPagos;
import com.ungs.revivir.vista.util.contenedores.PanelVertical;
import com.ungs.revivir.vista.util.contenedores.Ventana;

public class TablaPagosTest extends Ventana{
	private static final long serialVersionUID = 1L;

	public TablaPagosTest() {
		super("Prueba tabla de pagos", 500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Date fechaDesde = Date.valueOf(LocalDate.of(2019, 8, 10));

		TablaPagos tabla = new TablaPagos(ReportePagoManager.traerPorFecha(fechaDesde));
		JScrollPane panelTabla = new JScrollPane(tabla);
		
		PanelVertical panel = new PanelVertical();
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(panel);
		
		panel.add(panelTabla);
		compactar();
	}
	
	public static void main(String[] args) {
		new TablaPagosTest();
	}

}