package com.ungs.revivir.util;

import java.util.Arrays;
import java.util.List;

public class GeneradorInsert {
	public static int contadorFinal=0; 
	
	public static void main(String[] args) {
		contadorFinal=0;
		//System.out.println("INSERT INTO rev_ubicaciones_totales (subsector, seccion, macizo, unidad, sepultura, bis_macizo, bis)");
		//System.out.println("INSERT INTO rev_ubicaciones_totales (subsector, seccion, macizo, unidad,parcela,circ,fila)");
		//System.out.println("INSERT INTO rev_ubicaciones_totales (subsector, seccion, macizo, unidad, sepultura, bis_macizo, bis, inhumacion)");
		System.out.println("INSERT INTO rev_ubicaciones_totales (subsector, mueble, nicho)");
		System.out.println("VALUES");
		imprimirValores();
		System.out.println(";");
		System.out.println("## Cantidad de lineas: "+ contadorFinal);
	}
	
	
	public static void imprimirValores() {
		
		// Valores generalmente fijos
		
		String subsector    = "10";
		String seccion      = "4ABIS";
		String unidad       = "5";
		int sepulturaInicio =  1;
		int nichoInicio =  1;
		
		//boolean tieneBis    = false;
		// Descomentar la siguiente linea si tiene BIS
		boolean tieneBis = false;
		List<Integer> sepulturasConBIS = Arrays.asList( 1,2);
		String bisMacizo = "0";
		int sepulturaFin = 11;
		int nichoFin = 30;
		int inhumacion= 4;
		int fila = 1;

		// Valores que cambian seguido //MAC43-SEPULTURAS 72-

		int parcelaInicio =  1;
		int parcelaFin =  22;
		String macizo    = "1";
		String macizoFin = "1";
		String circ = "3";
		//int fila = 1;
		List<Integer> macizoIntercalado = Arrays.asList();
		//while (Integer.parseInt(macizo) <= Integer.parseInt(macizoFin)  ) {     // esto para sepulturas adultos
		   while (fila >0  ) {	   // esto para indigentes 
			if ( !macizoIntercalado.contains(Integer.parseInt(macizo)) ) {
		recorrerPalmeras(subsector, fila, nichoInicio,nichoFin);
		//recorrerParcelas(subsector, seccion, unidad, parcelaInicio, parcelaFin, macizo, circ, 6, macizoFin);
			}
		macizo = Integer.toString(Integer.parseInt(macizo) +1);	// esto para sepulturas adultos
		  fila = fila -1;                               // esto es para indigenets
		}
	}
	
	
	public static void recorrerSepulturas(String subsector, String seccion, String macizo, String unidad,
			int sepulturaInicio, int sepulturaFin, String bisMacizo, boolean tieneBis, List<Integer> sepulturasConBIS) {
		
		for (int i = sepulturaInicio; i < sepulturaFin + 1; i++) {
			
			// Si es la ultima linea va espacio vacio, sino una coma
			String fin = (sepulturaFin == i) ? "" : ",";
			
			if (tieneBis && sepulturasConBIS.contains(i)) {
				imprimirSepultura(subsector, seccion, macizo, unidad, new Integer(i).toString(), bisMacizo, "1", ",");	
				contadorFinal++;}
			imprimirSepultura(subsector, seccion, macizo, unidad, new Integer(i).toString(), bisMacizo, "0", fin);
			contadorFinal++;
		}
		
	}	

	public static void recorrerSepulturasIndigentes(String subsector, String seccion, String macizo, String unidad,
			int sepulturaInicio, int sepulturaFin, String bisMacizo, boolean tieneBis, List<Integer> sepulturasConBIS, int inhumacion) {
		
		for (int i = sepulturaInicio; i < sepulturaFin + 1; i++) {
			
			// Si es la ultima linea va espacio vacio, sino una coma
			String fin = (sepulturaFin == i ) ? "" : ",";
			
			if (tieneBis && sepulturasConBIS.contains(i)) {
				imprimirSepulturaIndigente(subsector, seccion, macizo, unidad, new Integer(i).toString(), bisMacizo, "1", inhumacion,",");	
				contadorFinal++;}
			imprimirSepulturaIndigente(subsector, seccion, macizo, unidad, new Integer(i).toString(), bisMacizo, "0", inhumacion, fin);
			contadorFinal++;
		}
		
	}

	public static void recorrerPalmeras(String subsector, int fila,int nichoInicio, int nichoFin) {
		
		for (int i = nichoInicio; i < nichoFin + 1; i++) {
			
			// Si es la ultima linea va espacio vacio, sino una coma
			String fin = (nichoFin == i ) ? "" : ",";
			
			imprimirPalmeras(subsector, fila,i, fin);
			contadorFinal++;
		
		}
	}
	
	public static void recorrerParcelas(String subsector, String seccion, String unidad,
			int parcelaInicio, int parcelaFin, String macizo,String circ, int filaFin,String macizoFin) {
		for (int fila = 1 ; fila <= filaFin ; fila ++) {
			for (int i = parcelaInicio; i < parcelaFin + 1; i++) {
				
				// Si es la ultima linea va espacio vacio, sino una coma
				
				String fin = (parcelaFin == i && fila == filaFin && Integer.parseInt(macizo) == Integer.parseInt(macizoFin)) ? "" : ",";
	
				imprimirNicho(subsector, seccion, macizo, unidad, new Integer(i).toString(), circ,fila, fin);
				contadorFinal++;
				
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
	public static void imprimirSepulturaIndigente(String subsector, String seccion, String macizo, String unidad, String sepultura,
			String bisMacizo, String bis,int inhumacion, String fin) {
		
		String linea = String.format(
				"(%s, '%s', %s, %s, %s, %s, %s, %s)%s",
				subsector, seccion, macizo, unidad, sepultura, bisMacizo, bis,inhumacion,fin
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

	public static void imprimirPalmeras(String subsector, int fila, int nicho, String fin) {
		
		String linea = String.format(
				"(%s, 16, %s)%s",
				subsector, nicho,fin
			);
		System.out.println(linea);
	}	
}