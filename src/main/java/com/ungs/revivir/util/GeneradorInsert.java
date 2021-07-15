package com.ungs.revivir.util;

import java.util.Arrays;
import java.util.List;

public class GeneradorInsert {
	
	
	public static void main(String[] args) {
		//System.out.println("INSERT INTO rev_ubicaciones_totales (subsector, seccion, macizo, unidad, sepultura, bis_macizo, bis)");
		System.out.println("INSERT INTO rev_ubicaciones_totales (subsector, seccion, macizo, unidad,parcela,circ,fila)");
		System.out.println("VALUES");
		imprimirValores();
		System.out.println(";");
	}
	
	
	public static void imprimirValores() {
		
		// Valores generalmente fijos
		String subsector    = "9";
		String seccion      = "A";
		String unidad       = "3";
		//int sepulturaInicio =  1;
		int parcelaInicio =  1;
		boolean tieneBis    = false;
		

		// Valores que cambian seguido
		//int sepulturaFin =  80;
		int parcelaFin =  105;
		String macizo    = "1";
		String bisMacizo = "0";
		String circ = "6";
		int fila = 1;
		// Descomentar la siguiente linea si tiene BIS
		tieneBis = true;
		List<Integer> sepulturasConBIS = Arrays.asList( 80, 1, 40, 43, 42);
		
		//recorrerSepulturas(subsector, seccion, macizo, unidad, sepulturaInicio, sepulturaFin, bisMacizo, tieneBis, sepulturasConBIS);
		recorrerParcelas(subsector, seccion, unidad, parcelaInicio, parcelaFin, macizo, circ,fila);
	}
	
	/*
		MAC32-SEPULTURAS 79+6 BIS (SEP1BIS-SEP80BIS-SEP41BIS-SEP42BIS-SEP43BIS-SEP40BIS)
		// revisar la 33 
		
		MAC34-SEPULTURAS 80+2BIS (SEP80BIS-SEP1BIS)
		
		MAC35-SEPULTURAS 80+3BIS (SEP80BIS-SEP1BIS-SEP43BIS)
		
		MAC35BIS-SEPULTURAS 43
		
		MAC36-SEPULTURAS 122+2BIS (SEP 1BIS-SEP 80BIS)
		
		MAC37-SEPULTURAS 48+ 7 BIS (SEP47BIS-48BIS-49BIS-50BIS-51BIS-52BIS-53BIS).
		
		MAC38-SEPULTURAS 60 +4 BIS (42BIS-43BIS-30BIS-31BIS)
		
		MAC39-SEPULTURAS 285
		
		MAC40-SEPULTURAS 270
		
		MAC41-SEPULTURAS 360-
		
		MAC41BIS-SEPULTURAS 64-
		
		MAC42-SEPULTURAS 242-
		
		MAC43-SEPULTURAS 72-
	 */
	
	
	
	public static void recorrerSepulturas(String subsector, String seccion, String macizo, String unidad,
			int sepulturaInicio, int sepulturaFin, String bisMacizo, boolean tieneBis, List<Integer> sepulturasConBIS) {
		
		for (int i = sepulturaInicio; i < sepulturaFin + 1; i++) {
			
			// Si es la ultima linea va espacio vacio, sino una coma
			String fin = (sepulturaFin == i) ? "" : ",";
			
			if (tieneBis && sepulturasConBIS.contains(i))
				imprimirLinea(subsector, seccion, macizo, unidad, new Integer(i).toString(), bisMacizo, "1", ",");	

			imprimirLinea(subsector, seccion, macizo, unidad, new Integer(i).toString(), bisMacizo, "0", fin);
		}
		
	}	

	public static void recorrerParcelas(String subsector, String seccion, String unidad,
			int parcelaInicio, int parcelaFin, String macizo,String circ, int fila) {
		
		for (int i = parcelaInicio; i < parcelaFin + 1; i++) {
			
			// Si es la ultima linea va espacio vacio, sino una coma
			String fin = (parcelaFin == i) ? "" : ",";

			imprimirLineaNichos(subsector, seccion, macizo, unidad, new Integer(i).toString(), circ,fila, fin);
		}
		
	}	

	public static void imprimirLinea(String subsector, String seccion, String macizo, String unidad, String sepultura,
			String bis_macizo, String bis, String fin) {
		
		String linea = String.format( "(%s, '%s', %s, %s, %s, %s, %s)%s",
				subsector, seccion, macizo, unidad, sepultura, bis_macizo, bis,fin);
		System.out.println(linea);
	}	
	public static void imprimirLineaNichos(String subsector, String seccion, String macizo, String unidad,	String parcela	,	
			String circ, int fila, String fin) {
		
		String linea = String.format( "(%s, '%s', %s, %s, %s, %s, %s)%s",
				subsector, seccion, macizo, unidad,parcela, circ,fila,fin);
		System.out.println(linea);
	}	

//seccion a,macizo1,unidad3,parcela1, circ 6,fila 1 // falta insertar

}