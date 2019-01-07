package com.ungs.revivir.negocios.verificador;

import com.ungs.revivir.persistencia.definidos.SubSector;
import com.ungs.revivir.persistencia.entidades.Ubicacion;

public class VerificadorUbicacion {
	
	public static Ubicacion verificar(Ubicacion verificar) throws Exception {

		// Obtengo todos sus valores
		SubSector subSector = verificar.getSubsector();
		Integer nicho = verificar.getNicho();		
		Integer fila = verificar.getFila();
		String seccion = Verificador.anular(verificar.getSeccion());
		String cementerio = Verificador.anular(verificar.getCementerio());
		Integer macizo = verificar.getMacizo();		
		Integer unidad = verificar.getUnidad();		
		Boolean bis = verificar.getBis();
		Boolean bisMacizo = verificar.getBis_macizo();
		Integer sepultura = verificar.getSepultura();		
		Integer parcela = verificar.getParcela();
		Integer mueble = verificar.getMueble();	
		Integer inhumacion= verificar.getInhumacion();		
		Integer circ = verificar.getCirc();		
		
		// Anulo sus valores
		verificar.setCementerio(null);
		verificar.setNicho(null);
		verificar.setFila(null);
		verificar.setSeccion(null);
		verificar.setMacizo(null);
		verificar.setUnidad(null);
		verificar.setBis(null);
		verificar.setBis_macizo(null);
		verificar.setParcela(null);
		verificar.setMueble(null);
		verificar.setInhumacion(null);
		verificar.setCirc(null);
		
		// *****************  VERIFICO CASO A CASO ***********************************************************************
		
		if (subSector.equals(SubSector.PALMERAS_SEPULTURAS)) {
			String mensaje = "";
				
			if (sepultura == null)
				mensaje += "\n    -La SEPULTURA no puede estar vacio.";
			
			if (!mensaje.equals(""))
				throw new Exception("Se encontraron los siguientes errores en el formulario: "+mensaje);
			
			// Solo van los campos que corresponden, el resto va en NULL
			verificar.setSepultura(sepultura);
			return verificar;
		}

		if (subSector.equals(SubSector.CENIZARIO)) {
			String mensaje = "";
			
			if (mueble == null)
				mensaje += "\n    -El MUEBLE no puede estar vacio.";
			
			if (nicho == null)
				mensaje += "\n    -El NICHO no puede estar vacio.";
			
			if (!mensaje.equals(""))
				throw new Exception("Se encontraron los siguientes errores en el formulario: "+mensaje);
			
			// Solo van los campos que corresponden, el resto va en NULL
			verificar.setMueble(mueble);
			verificar.setNicho(nicho);
			return verificar;
		}

		if (subSector == SubSector.PALMERAS_ATAUD
			|| subSector == SubSector.PALMERAS_CENIZAS
			|| subSector == SubSector.PALMERAS_RESTOS) {
		
			String mensaje = "";
			
			if (fila == null)
				mensaje += "\n    -La FILA no puede estar vacio.";
			
			if (nicho == null)
				mensaje += "\n    -El NICHO no puede estar vacio.";
			
			if (!mensaje.equals(""))
				throw new Exception("Se encontraron los siguientes errores en el formulario: "+mensaje);
			
			// Solo van los campos que corresponden, el resto va en NULL
			verificar.setFila(fila);
			verificar.setNicho(nicho);
			return verificar;
		}
		
		if (subSector.equals(SubSector.COMPRADA)) {
			String mensaje = "";
				
			if (seccion == null)
				mensaje += "\n    -La SECCION no puede estar vacia.";
			
			if (macizo == null)
				mensaje += "\n    -El MACIZO no puede estar vacio.";
			
			if (unidad == null)
				mensaje += "\n    -La UNIDAD no puede estar vacio.";
			
			if (sepultura == null)
				mensaje += "\n    -La SEPULTURA no puede estar vacio.";
			
			if (parcela == null)
				mensaje += "\n    -La PARCELA no puede estar vacio.";

			if (!mensaje.equals(""))
				throw new Exception("Se encontraron los siguientes errores en el formulario: "+mensaje);
			
			verificar.setSeccion(seccion);
			verificar.setMacizo(macizo);
			verificar.setUnidad(unidad);
			verificar.setSepultura(sepultura);
			verificar.setParcela(parcela);
			verificar.setBis(bis);
			verificar.setBis_macizo(bisMacizo);
			return verificar;
		}
		
		if (subSector == SubSector.ADULTOS) {
			
			String mensaje = "";
				
			if (seccion == null)
				mensaje += "\n    -La SECCION no puede estar vacia.";
			
			if (macizo == null)
				mensaje += "\n    -El MACIZO no puede estar vacio.";
			
			// si es adultos la unidad puede ser null aunque este inhabilitada
			
			if (sepultura == null)
				mensaje += "\n    -La SEPULTURA no puede estar vacio.";

			if (!mensaje.equals(""))
				throw new Exception("Se encontraron los siguientes errores en el formulario: "+mensaje);
			
			verificar.setSeccion(seccion);
			verificar.setMacizo(macizo);
			verificar.setUnidad(unidad);
			verificar.setSepultura(sepultura);
			verificar.setParcela(parcela);
			verificar.setBis(bis);
			verificar.setBis_macizo(bisMacizo);
			return verificar;
		}
		
		if (subSector == SubSector.ANGELITOS) {
			
			String mensaje = "";
				
			if (seccion == null)
				mensaje += "\n    -La SECCION no puede estar vacia.";
			
			if (macizo == null)
				mensaje += "\n    -El MACIZO no puede estar vacio.";
			
			if (unidad == null)
				mensaje += "\n    -La UNIDAD no puede estar vacio.";
			
			if (parcela == null)
				mensaje += "\n    -La PARCELA no puede estar vacio.";

			if (!mensaje.equals(""))
				throw new Exception("Se encontraron los siguientes errores en el formulario: "+mensaje);
			
			verificar.setSeccion(seccion);
			verificar.setMacizo(macizo);
			verificar.setUnidad(unidad);
			verificar.setSepultura(sepultura);
			verificar.setParcela(parcela);
			verificar.setBis(bis);
			verificar.setBis_macizo(bisMacizo);
			return verificar;
		}
		
		if (subSector == SubSector.BOVEDA) {
			
			String mensaje = "";
				
			if (seccion == null)
				mensaje += "\n    -La SECCION no puede estar vacia.";
			
			if (circ == null)
				mensaje += "\n    -el CIRC no puede estar vacia.";
			
			if (macizo == null)
				mensaje += "\n    -El MACIZO no puede estar vacio.";
			
			if (unidad == null)
				mensaje += "\n    -La UNIDAD no puede estar vacio.";
			
			if (parcela == null)
				mensaje += "\n    -La PARCELA no puede estar vacio.";
		
			if (!mensaje.equals(""))
				throw new Exception("Se encontraron los siguientes errores en el formulario: "+mensaje);
			
			verificar.setCirc(circ);
			verificar.setSeccion(seccion);
			verificar.setMacizo(macizo);
			verificar.setUnidad(unidad);
			verificar.setParcela(parcela);
			verificar.setBis(bis);
			return verificar;
		}
		
		if (subSector == SubSector.NICHERA) {
			
			String mensaje = "";
				
			if (seccion == null)
				mensaje += "\n    -La SECCION no puede estar vacia.";
			
			if (fila == null)
				mensaje += "\n    -La FILA no puede estar vacia.";
			
			if (circ == null)
				mensaje += "\n    -el CIRC no puede estar vacia.";
			
			if (macizo == null)
				mensaje += "\n    -El MACIZO no puede estar vacio.";
			
			if (unidad == null)
				mensaje += "\n    -La UNIDAD no puede estar vacio.";
			
			if (parcela == null)
				mensaje += "\n    -La PARCELA no puede estar vacio.";
		
			if (!mensaje.equals(""))
				throw new Exception("Se encontraron los siguientes errores en el formulario: "+mensaje);
			
			verificar.setCirc(circ);
			verificar.setFila(fila);
			verificar.setSeccion(seccion);
			verificar.setMacizo(macizo);
			verificar.setUnidad(unidad);
			verificar.setParcela(parcela);
			return verificar;
		}
		
		if (subSector == SubSector.INDIGENTES) {
			
			String mensaje = "";
				
			if (seccion == null)
				mensaje += "\n    -La SECCION no puede estar vacia.";
			
			if (macizo == null)
				mensaje += "\n    -El MACIZO no puede estar vacio.";
			
			if (sepultura == null)
				mensaje += "\n    -La SEPULTURA no puede estar vacio.";
			
			if (inhumacion == null)
				mensaje += "\n    -La INHUMACION no puede estar vacio.";
		
			if (!mensaje.equals(""))
				throw new Exception("Se encontraron los siguientes errores en el formulario: "+mensaje);
			
			verificar.setSeccion(seccion);
			verificar.setMacizo(macizo);
			verificar.setSepultura(sepultura);
			verificar.setInhumacion(inhumacion);
			return verificar;
		}

		if (subSector == SubSector.OTRO_CEMENTERIO) {
			
			String mensaje = "";
				
			if (cementerio == null)
				mensaje += "\n    -El nombre del OTRO CEMENTERIO no puede estar vacio.";
		
			if (!mensaje.equals(""))
				throw new Exception("Se encontraron los siguientes errores en el formulario: "+mensaje);
			
			verificar.setCementerio(cementerio);
			return verificar;
		}
		
		return null;
	}	
	
}