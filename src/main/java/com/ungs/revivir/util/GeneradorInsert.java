package com.ungs.revivir.util;

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
		String seccion      = "B";
		String unidad       = "3";
		//int sepulturaInicio =  1;
		//boolean tieneBis    = false;
		// Descomentar la siguiente linea si tiene BIS
		//tieneBis = true;
		//List<Integer> sepulturasConBIS = Arrays.asList( 80, 1, 40, 43, 42);
		//String bisMacizo = "0";
		//int sepulturaFin =  80;
		

		// Valores que cambian seguido
		int parcelaInicio =  1;
		int parcelaFin =  9;
		String macizo    = "17";
		String circ = "3";
		//int fila = 1;
		
		while (Integer.parseInt(macizo) < 33 && Integer.parseInt(macizo) > 16) {
		//recorrerSepulturas(subsector, seccion, macizo, unidad, sepulturaInicio, sepulturaFin, bisMacizo, tieneBis, sepulturasConBIS);
		recorrerParcelas(subsector, seccion, unidad, parcelaInicio, parcelaFin, macizo, circ, 4);
							macizo = Integer.toString(Integer.parseInt(macizo) +1);		}
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
				imprimirSepultura(subsector, seccion, macizo, unidad, new Integer(i).toString(), bisMacizo, "1", ",");	

			imprimirSepultura(subsector, seccion, macizo, unidad, new Integer(i).toString(), bisMacizo, "0", fin);
		}
		
	}	

	public static void recorrerParcelas(String subsector, String seccion, String unidad,
			int parcelaInicio, int parcelaFin, String macizo,String circ, int filaFin) {
		for (int fila = 1 ; fila <= filaFin ; fila ++) {
			for (int i = parcelaInicio; i < parcelaFin + 1; i++) {
				
				// Si es la ultima linea va espacio vacio, sino una coma
				
				String fin = (parcelaFin == i && fila == filaFin && Integer.parseInt(macizo) == 32) ? "" : ",";
	
				imprimirNicho(subsector, seccion, macizo, unidad, new Integer(i).toString(), circ,fila, fin);
			}
		}
	}	

	public static void imprimirSepultura(String subsector, String seccion, String macizo, String unidad, String sepultura,
			String bisMacizo, String bis, String fin) {
		
		String linea = String.format(
				"(%s, '%s', %s, %s, %s, %s, %s)%s",
				subsector, seccion, macizo, unidad, sepultura, bisMacizo, bis,fin
			);
		System.out.println(linea);
	}
	
	public static void imprimirNicho(String subsector, String seccion, String macizo, String unidad,	String parcela	,	
			String circ, int fila, String fin) {
		
		String linea = String.format(
				"(%s, '%s', %s, %s, %s, %s, %s)%s",
				subsector, seccion, macizo, unidad,parcela, circ,fila,fin
			);
		System.out.println(linea);
	}	

}